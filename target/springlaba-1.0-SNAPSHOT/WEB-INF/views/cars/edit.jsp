<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 10.05.2021
  Time: 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit car</title>
</head>
<body>
<form method="post">
    <input type="text" name="id" placeholder="${car.id}">
    <input type="text" name="name" placeholder="${car.name}"/><br/>
    <input type="text" name="description" placeholder="${car.description}"><br/>
    <input type="text"  name="date" placeholder="${car.date}"><br/>
    <input type="submit" value="send">
    <input type="reset" value="reset">
</form>
<a href="/cars">back to list</a>
</body>
</html>
