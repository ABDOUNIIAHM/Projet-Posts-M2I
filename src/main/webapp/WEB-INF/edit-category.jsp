<%--
  Created by IntelliJ IDEA.
  User: Abdallah
  Date: 25/04/2023
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit category</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp"></c:import>
<div class="container">
<form method="post" action="edit-category">
  <div class="input-group mb-3">
    <input type="text" class="form-control" name="name" placeholder="enter new name for your category...">
  </div>
  <div class="input-group">
    <button name="id" type="submit" class="btn btn-outline-dark" value="${cat.id}">Submit</button>
  </div>
</form>
</div>
</body>
</html>
