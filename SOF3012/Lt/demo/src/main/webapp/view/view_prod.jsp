<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h2>Products</h2>
<a href="/products/new">Add new Product</a>

<table border="1">
  <tr>
    <th>#</th>
    <th>Id</th>
    <th>Name</th>
    <th>Category</th>


    <th colspan="3">Actions</th>
  </tr>
  <c:forEach var="tempProduct" items="${prod}" varStatus="i">
    <tr>
      <td>${i.index + 1}</td>
      <td>${tempProduct.id}</td>
      <td>${tempProduct.name}</td>
      <td>${tempProduct.category.name}</td>

      <td>
        <a href="/products/edit?id=${tempProduct.id}">Edit</a>
        <a href="/products/delete?id=${tempProduct.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>