<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 08.04.2020
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>CARS IN DATABASE</title>
</head>
<body>

<h2>Cars</h2>
<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>year</th>
        <th>action</th>
    </tr>
    <c:forEach var="car" items="${carsList}">
        <tr>
            <td>${car.id}</td>
            <td>${car.title}</td>
            <td>${car.year}</td>
            <td>
                <a href="/edit/${car.id}">edit</a>
                <a href="/delete/${car.id}">delete</a>
                <a href="/addCache/${car.id}">inCache</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%--<h2>Add</h2>--%>
<c:url value="/add" var="add"/>
<a href="${add}">Add new car</a>

<form:form method="post" action="/search" modelAttribute="car">
    <table>

<%--        <td>Поиск по году:</td>--%>
<%--        <td><form:input path="year" placeholder="Год" required="true"/></td>--%>
<%--        <td colspan="3"><input type="submit" value="Search"/></td>--%>
    <td>Поиск по ID:</td>
    <td><form:input path="id" placeholder="ID" required="true"/></td>
    <td colspan="3"><input type="submit" value="Search"/></td>
    </table>
</form:form>


<h3>Список cars в кэше</h3>

<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>year</th>
    </tr>
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
