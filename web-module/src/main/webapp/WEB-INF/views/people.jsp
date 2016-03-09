<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>List of people</title>
        <c:url value="/resources/bootstrap-3.3.6/css/bootstrap.css" var="bootstrapStylesheet"/>
        <link rel="stylesheet" type="text/css" href="${bootstrapStylesheet}"/>

        <c:url value="/resources/bootstrap-3.3.6/js/bootstrap.js" var="bootstrapScript"/>
        <script src="${bootstrapScript}"></script>

        <c:url value="/resources/jquery-2.2.1/jquery.js" var="jQueryScript"/>
        <script src="${jQueryScript}"></script>
    </head>
    <body>
        <div class="container">
            <h1>Showing a list of people</h1>
            <p>This page is rendered by a Servlet &amp; JSP.</p>
            <table id="peopleTable" class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Last name</th>
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
            var $peopleTable = $('#peopleTable');
            $peopleTable.hide();
            $(function() {
                $peopleTable.fadeIn();
            })
        </script>
    </body>
</html>
