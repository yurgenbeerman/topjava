<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <a href="users">Users list</a>
    <h2>Meal</h2>
    <form action="mealController?action=listMeals" method="post">
        <input type="hidden" name="action" value="${action}">
        <input type="hidden" name="id" value="${id}">
        <table>
            <tr>
                <td>${meal == null ?  '' : meal.id}</td>
                <td><input name="dateTime" style="color: inherit" type="datetime-local" value="${meal == null ?  '' : meal.dateTime}"></td>
                <td>
                    <input name="description" style="color: inherit" type="text" value="${meal == null ?  '' : meal.description}">
                    <!--<select name="description" style="color: inherit" value="${meal.description}">
                        <option value="" selected="selected"></option>
                    </select>-->
                </td>
                <td><input name="calories" style="color: inherit" type="number" value="${meal == null ?  '' : meal.calories}"></td>
            </tr>
        </table>
        <input type="submit" value="Send" >
    </form>
</body>
</html>
