<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>The Blog | Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<c:import url="header.jsp"></c:import>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username" placeholder="email">
    <input type="password" name="password" placeholder="password">
    <button type="submit">Login</button>
</form>
<c:if test="${isError == true}">
    <p>Bad credentials.</p>
</c:if>

</body>
</html>
