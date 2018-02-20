<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/25/2017
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>productAll</title>
</head>
<body>
<b>ALL PRODUCTS PAGE</b>
<ul>
    <c:forEach var="product" items="${productList}">
        <li>
            <a href="product.do?id=${product.id}">${product.name}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
