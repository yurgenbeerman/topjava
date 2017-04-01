<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <a href="users">Users list</a>
    <h2>Meals list</h2>
    <table>
        <c:forEach items="${mealsWithExceeded}" var="meal">
            <tr style="color: ${meal.exceed ?  'red' : 'green'}">
                <td>${meal.id}</td>
                <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd hh:mm" /></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="mealController?action=edit&id=${meal.id}">Edit</a></td>
                <td><a href="mealController?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="mealController?action=add">Add meal</a>
</body>
</html>
