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
<%--<head>--%>
<%--    <title>Edit</title>--%>
<%--</head>--%>
<head>
    <c:if test="${empty car.title}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty car.title}">
        <title>Edit</title>
    </c:if>
</head>
<body>

<c:if test="${empty car.title}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty car.title}">
    <c:url value="/edit" var="var"/>
</c:if>

<form action="${var}" method="POST">
    <c:if test="${!empty car.title}">
        <input type="hidden" name="id" value="${car.id}">
    </c:if>
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <label for="year">Year</label>
    <input type="text" name="year" id="year">
    <c:if test="${empty car.title}">
        <input type="submit" value="Add new car">
    </c:if>
    <c:if test="${!empty car.title}">
        <input type="submit" value="Edit car">
    </c:if>
</form>
</body>
</html>