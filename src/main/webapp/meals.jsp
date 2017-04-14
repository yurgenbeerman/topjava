<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <form method="post" action="meals?action=filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal1">
            <jsp:useBean id="meal1" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal1.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal1.dateTime.toLocalDate()} ${meal1.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal1.getDateTime())%>--%>
                        ${fn:formatDateTime(meal1.dateTime)}
                </td>
                <td>${meal1.description}</td>
                <td>${meal1.calories}</td>
                <td><a href="meals?action=update&id=${meal1.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal1.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>