<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>JEE7 Starter</title>
        <c:url value="/resources/bootstrap-3.3.6/css/bootstrap-jsf.css" var="bootstrapStylesheet"/>
        <link rel="stylesheet" type="text/css" href="${bootstrapStylesheet}"/>

        <c:url value="/resources/bootstrap-3.3.6/js/bootstrap.js" var="bootstrapScript"/>
        <script src="${bootstrapScript}"></script>

        <c:url value="/resources/jquery-2.2.1/jquery.js" var="jQueryScript"/>
        <script src="${jQueryScript}"></script>
    </head>
    <body>
        <div class="container">
            <h1>People data</h1>
            <p>This page is rendered by a Servlet &amp; JSP.</p>
            <table id="peopleTable" class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                <c:forEach var="person" items="${people}">
                    <tr>
                        <td>${person.id}</td>
                        <td>${person.firstName}</td>
                        <td>${person.lastName}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script>
            var $pageContent = $('body > div.container');
            $pageContent.hide();
            $(function() {
                $pageContent.fadeIn();
            });
        </script>
    </body>
</html>
