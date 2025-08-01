<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/22/2025
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Thong tin san pham
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Ten San Pham</th>
    <th>Nha San Xuat</th>
    <th>Gia</th>
    <th>So Luong</th>
    <th>Con hang</th>
  </tr>
  </thead>
  <c:forEach items="${danhSach}" var="sp">
    <tr>
      <td>${sp.id}</td>
      <td>${sp.tenSanPham}</td>
      <td>${sp.nhaSanXuat}</td>
      <td>${sp.gia}</td>
      <td>${sp.soLuong}</td>
      <td>${sp.conHang}</td>
    </tr>
  </c:forEach>

</table>
</body>
</html>
