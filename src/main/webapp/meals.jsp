<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
