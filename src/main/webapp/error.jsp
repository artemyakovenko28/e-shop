<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/24/2017
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<h1>ERROR PAGE</h1>
    <c:forEach var="stackTraceElement" items="${exception}">
        <p>${stackTraceElement}</p>
    </c:forEach>
<a href="productAll.jsp">All products page</a>
</body>
</html>
