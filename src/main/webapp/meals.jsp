<%--
  Created by IntelliJ IDEA.
  User: Hergo
  Date: 06.10.2023
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
        <tr>
            <c:if test="${!meal.isExcess()}">
                <td style="color:green">
                    <c:out value="${meal.getFormattedDateTime()}"></c:out>
                </td>
                <td style="color:green">
                    <c:out value="${meal.getDescription()}"></c:out>
                </td>
                <td style="color:green">
                    <c:out value="${meal.getCalories()}"></c:out>
                </td>
                <td><a href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${meal.getId()}"/>">Update</a></td>
                <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </c:if>
            <c:if test="${meal.isExcess()}">
                <td style="color:red">
                    <c:out value="${meal.getFormattedDateTime()}"></c:out>
                </td>
                <td style="color:red">
                    <c:out value="${meal.getDescription()}"></c:out>
                </td>
                <td style="color:red">
                    <c:out value="${meal.getCalories()}"></c:out>
                </td>
                <td><a href="${pageContext.request.contextPath}/meals?action=update&id=<c:out value="${meal.getId()}"/>">Update</a></td>
                <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/meals?action=add">Add meal</a>
    </tbody>

</table>
</body>
</html>
