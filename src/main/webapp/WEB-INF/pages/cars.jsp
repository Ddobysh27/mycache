<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>--%>

<html>
<head>
    <title>CARS IN DATABASE</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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

<c:url value="/add" var="add"/>
<a href="${add}">Add new car</a>

<form:form method="post" action="/search" modelAttribute="car">
    <table>
        <td>Поиск по ID:</td>
        <td><form:input path="id" placeholder="ID" required="true"/></td>
        <td colspan="3"><input type="submit" value="Search"/></td>
    </table>
</form:form>


<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    function doAjaxPost() {

        $.ajax({
            type: "GET",
            url: "subView",
            success: function (response) {
                $("#subViewDiv").html(response);
            }
        });
    }
</script>

<script type="text/javascript">
    var intervalId = setInterval(doAjaxPost, 100);
</script>


<h3>Список cars в кэше</h3>


<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>year</th>
    </tr>

</table>
<div id="subViewDiv"></div>
</body>
</html>
