<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %><html>
<head>
    <title>Meals</title>
    <meta charset="UTF-8">
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var = "meal" items="${meals}">
        <tr style="color: ${!meal.isExcess() ? 'green' : 'red'}">
            <td> ${meal.getDateTime().format(DateTimeFormatter
                   .ofPattern('yyyy-MM-dd HH:mm'))}
            </td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td><a href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${meal.getId()}"/>">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/meals?action=add">Add meal</a>
    </tbody>

</table>
</body>
</html>
