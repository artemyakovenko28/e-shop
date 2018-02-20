<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.company.eshop.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>product</title>
</head>
<body>
<b>PRODUCT PAGE</b>
<br/>id: ${product.id} <%--EL - Expression Language--%>
<%--<br/>id: <%=((Product) request.getAttribute("product")).getId()%> &lt;%&ndash;Scriptlet-->--%>
<br/>name: ${product.name}
<br/><a href="productAll.do">All products page</a>
<br/><!--Add quiz to bucket-->
<a href="productAddToBucket.do?id=${product.id}">Add this product to bucket</a>

<hr/><!--Show products bucket-->
<h2>Products in bucket</h2>
<ul>
    <c:forEach var="productInBucket" items="${productsInBucket}">
        <li>
            <a href="product.do?id=${productInBucket.key.id}">${productInBucket.key.name} = ${productInBucket.value}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
