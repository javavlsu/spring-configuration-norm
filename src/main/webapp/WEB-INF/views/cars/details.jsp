<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 10.05.2021
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>details</title>
</head>
<body>
<h4>Car details</h4>
<p>id is ${car.id}</p><br/>
<p>name is ${car.name}</p><br/>
<p>description is ${car.description}</p><br/>
<p>date is ${car.date}</p><br/>
<a href="/cars">back to list</a>
</body>
</html>
