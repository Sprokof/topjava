<%--
  Created by IntelliJ IDEA.
  User: Hergo
  Date: 07.10.2023
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1>Update meal</h1>
<form method="POST" action='${pageContext.request.contextPath}/meal' name="updateMeal">
    <label>DateTime: <input name="dateTime" type="datetime-local" value="${meal.getFormattedDateTime()}"></label>
    <label>Description: <input name="desc" type="text" value="${meal.getDescription()}"></label>
    <label>Calories: <input name="calories" type="number" value="${meal.getCalories()}"></label>
    <input type="hidden" name="name" value="update">
    <input type="hidden" name="id" value="${meal.getId()}">
    <div>
        <button type="submit">Submit</button>
        <button type="button" onclick="window.location.href='/topjava/meals'">Cancel</button>
    </div>

</form>
</body>
</html>
