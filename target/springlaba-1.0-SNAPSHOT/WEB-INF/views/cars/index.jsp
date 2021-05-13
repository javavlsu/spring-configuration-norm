<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 10.05.2021
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list of cars</title>
</head>
<body>
  <table>
      <thead>
          <th>#</th>
          <th>name</th>
          <th>description</th>
          <th>date</th>
          <th>details</th>
          <th>edit</th>
          <th>delete</th>
      </thead>
      <c:choose>
          <c:when test="${cars != null} ">
              <c:forEach var="car" items="cars">
                  <tr>
                      <td>${car.id}</td>
                      <td>${car.name}</td>
                      <td>${car.descriptiob}</td>
                      <td><a href="/cars/details/${car.id}">Details</a> </td>
                      <td><a href="/cars/edit/${car.id}">Edit</a> </td>
                      <td><a href="/cars/delete/${car.id}">Removing</a> </td>
                  </tr>
              </c:forEach>
          </c:when>
          <c:otherwise>
              <tr>
                  <td colspan="4">no data</td>
              </tr>
          </c:otherwise>
      </c:choose>
  </table>
</body>
</html>
