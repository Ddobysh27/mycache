<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 08.04.2020
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CARS</title>
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
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new car</a>
</body>
</html>
