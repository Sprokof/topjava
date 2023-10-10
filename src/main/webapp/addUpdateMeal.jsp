<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Update meal</title>
    <meta charset="UTF-8">
    <style>
        form {
            display: block;
            width: 25%;
        }
        label {
            display: inline-block;
            margin-top: 5px;
        }
        div {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<h1>Add Or Update Meal</h1>
<form method="POST" action='${pageContext.request.contextPath}/meals' name="updateMeal">
    <input type="hidden" name="id" value="${meal.getId()}">
    <label>DateTime: <input name="dateTime" type="datetime-local" value="${meal.getDateTime().format(DateTimeFormatter
        .ofPattern('yyyy-MM-dd HH:mm'))}"></label>
    <label>Description: <input name="desc" type="text" value="${meal.getDescription()}"></label>
    <label>Calories: <input name="calories" type="number" value="${meal.getCalories()}"></label>
    <div>
        <button type="submit">Submit</button>
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/meals'">Cancel</button>
    </div>

</form>
</body>
</html>
