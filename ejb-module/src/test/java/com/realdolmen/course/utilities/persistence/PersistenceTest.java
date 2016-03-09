package com.realdolmen.course.utilities.persistence;

import com.realdolmen.course.domain.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Prepares a persistence context for testing with JPA.
 * Use {@link #properties()} to configure database properties
 */
public abstract class PersistenceTest extends Assert {
    public static final String DRIVER = "javax.persistence.jdbc.driver";
    public static final String URL = "javax.persistence.jdbc.url";
    public static final String USER = "javax.persistence.jdbc.user";
    public static final String PASSWORD = "javax.persistence.jdbc.password";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PERSISTENCE_UNIT_NAME = "MyTestPersistenceUnit";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    protected DatabaseEngine databaseEngine;

    @Before
    public final void initialize() throws SQLException {
        databaseEngine = selectDatabaseEngine();
        initializeEntityManagerFactory();
        logger.info("Creating transacted EntityManager");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    /**
     * Chooses the database engine to run this unit test.
     * Default lets {@link DatabaseEngine#current()} choose.
     * Subclasses may override this, if they know what they're doing :)
     * @return The selected database engine.
     */
    protected DatabaseEngine selectDatabaseEngine() {
        return DatabaseEngine.current();
    }

    @After
    public final void destroy() {
        completeTransaction();
        destroyEntityManager();
        destroyEntityManagerFactory();
    }

    private void initializeEntityManagerFactory() throws SQLException {
        recreateSchema();
        loadPersistenceUnit();
    }

    private void loadPersistenceUnit() {
        logger.info("Creating EntityManagerFactory from persistence unit " + PERSISTENCE_UNIT_NAME);
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties());
    }

    private void recreateSchema() throws SQLException {
        DatabaseEngine databaseEngine = selectDatabaseEngine();
        if(!databaseEngine.isInMemory) {
            logger.info("Recreating database schema '" + databaseEngine.schema + "'");
            try (Connection connection = newConnection()) {
                connection.createStatement().execute("drop schema " + databaseEngine.schema);
                connection.createStatement().execute("create schema " + databaseEngine.schema);
            }
        }
    }

    /**
     * Provides connection settings for the database. These settings will merge with the ones already in the test persistence.xml.
     * Subclasses can override this to customize.
     * @return Map of JPA properties.
     */
    protected Map<String, String> properties() {
        DatabaseEngine databaseEngine = selectDatabaseEngine();
        HashMap<String, String> properties = new HashMap<>();
        properties.put(DRIVER, databaseEngine.driverClass);
        properties.put(URL, databaseEngine.url);
        properties.put(USER, databaseEngine.username);
        properties.put(PASSWORD, databaseEngine.password);
        return Collections.unmodifiableMap(properties);
    }

    private void destroyEntityManager() {
        if(entityManager != null) {
            entityManager.close();
        }
    }

    private void completeTransaction() {
        logger.info("Committing and closing transacted EntityManager");
        if(transaction != null) {
            if(transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        }
    }

    private void destroyEntityManagerFactory() {
        logger.info("Closing EntityManagerFactory");
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    /**
     * Obtains the current EntityManager. Use this to write tests against.
     */
    protected EntityManager entityManager() {
        return this.entityManager;
    }

    /**
     * Obtains a <strong>new</strong> JDBC connection using connection settings defined in {@link #properties()}.
     * Note this connection does not participate in the same transaction as the {@link #entityManager()}, so be careful
     * when asserting against both.
     * @return A new JDBC connection. Callsite is responsible for closing.
     * @throws SQLException When the shit hits the fan.
     */
    protected Connection newConnection() throws SQLException {
        Map<String, String> properties = properties();
        return DriverManager.getConnection(properties.get(URL), properties.get(USER), properties.get(PASSWORD));
    }

    /**
     * Convenience for unit tests that assert entity counts.
     * @param entityClass The entity class for which to count records.
     * @param <T> Type of the entity.
     * @return The number of said entities found in the database.
     */
    protected <T> long count(Class<T> entityClass) {
        CriteriaBuilder builder = entityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(entityClass)));
        return entityManager().createQuery(query).getSingleResult();
    }

    /**
     * Represents all available database engines that can be used for running unit tests.
     * Switching implementations can be done using a {@link #DATABASE_ENGINE_SYSTEM_PARAMETER system parameter}.
     */
    public static enum DatabaseEngine {
        /**
         * MySQL based database engine for running against a production-mirror.
         */
        mysql("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/", "root", "", false),

        /**
         * H2 based database enigne for running (fast) in-memory.
         */
        h2("org.h2.Driver", "jdbc:h2:mem:", "sa", "", true);

        private static final DatabaseEngine DEFAULT_DATABASE_ENGINE = DatabaseEngine.mysql;
        private static final String DATABASE_ENGINE_SYSTEM_PARAMETER = "databaseEngine";
        private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseEngine.class);
        private static final String DATABASE_SCHEMA_NAME = "realdolmen";

        public final String url;
        public final String username;
        public final String driverClass;
        public final String password;
        public final String schema = DATABASE_SCHEMA_NAME;
        public final boolean isInMemory;

        DatabaseEngine(String driverClass, String urlPrefix, String username, String password, boolean isInMemory) {
            this.password = password;
            this.driverClass = driverClass;
            this.username = username;
            this.isInMemory = isInMemory;
            this.url = urlPrefix + schema;
        }

        private static DatabaseEngine current() {
            String databaseEngineProperty = System.getProperty(DATABASE_ENGINE_SYSTEM_PARAMETER);
            DatabaseEngine databaseEngine;
            if(databaseEngineProperty == null) {
                databaseEngine = DEFAULT_DATABASE_ENGINE;
                LOGGER.warn("Missing system property -DdatabaseEngine. Using default (" + databaseEngine + ").");
            } else {
                databaseEngine = DatabaseEngine.valueOf(databaseEngineProperty);
            }
            LOGGER.info("Using database engine: " + databaseEngine);
            return databaseEngine;
        }

        /**
         * Fails when the expected database engine is not the currently selected one.
         * Allows clients to mandate a specific environment, without them needing to violate their cohesion.
         * @param expected The expected database engine.
         * @param why Please add a reason why you would have this almost unreasonable request.
         */
        public void assertEquals(DatabaseEngine expected, String why) {
            Assert.assertEquals(why, expected, current());
        }
    }
}
