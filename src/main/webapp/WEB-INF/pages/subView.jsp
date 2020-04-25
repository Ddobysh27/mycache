
<%--<h3>Список cars в кэше</h3>--%>

<%--<table>--%>
<%--    <tr>--%>
<%--        <th>id</th>--%>
<%--        <th>title</th>--%>
<%--        <th>year</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="car" items="${cacheCarsList}">--%>
<%--        <tr>--%>
<%--            <td>${car.id}</td>--%>
<%--            <td>${car.title}</td>--%>
<%--            <td>${car.year}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<table>
    <c:forEach var="car" items="${cacheCarsList}">
        <tr>
            <td>${car.id}</td>
            <td>${car.title}</td>
            <td>${car.year}</td>
        </tr>
    </c:forEach>
</table>




</body>
</html>
