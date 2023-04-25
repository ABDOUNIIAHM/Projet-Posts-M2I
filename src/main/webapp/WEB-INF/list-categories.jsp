<%--
  Created by IntelliJ IDEA.
  User: Abdallah
  Date: 24/04/2023
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>categories</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  </head>
  <body>
  <c:import url="header.jsp"></c:import>
  <div class="container">
    <h1>Categories</h1>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-3 align-content-center">
      <c:forEach var="cat" items="${categories}">
        <div class="col">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">${cat.name}</h5>
            </div>
            <div class="card-text">
              <form action="categories" method="post">
                <div class="input-group">
                  <button name="delete" class="btn btn-outline-dark" value="${cat.id}">Delete</button>
                </div>
              </form>
              <form action="edit-category">
                <button name="edit" class="btn btn-outline-dark" value="${cat.id}">Edit</button>
              </form>

            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>

  <div class="container">
    <form action="add-category">
      <button class="btn btn-outline-dark">Add category</button>
    </form>
  </div>
  </body>
</html>