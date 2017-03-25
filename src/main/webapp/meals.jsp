<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Month" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: yurii.pyvovarenko
  Date: 3/25/2017
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );
    List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
    int count = mealsWithExceeded.size();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <a href="users">Users list</a>
    <h2>Meals list</h2>
    <table>
        <% for (MealWithExceed mealWithExceed : mealsWithExceeded) {
            String color = mealWithExceed.isExceed() ?  "red" : "green";
        %>
        <tr style="color: <%=color%>">
            <td><%=mealWithExceed.getDateTime()%></td>
            <td><%=mealWithExceed.getDescription()%></td>
            <td><%=mealWithExceed.getCalories()%></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
