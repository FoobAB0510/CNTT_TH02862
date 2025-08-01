<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/14/2025
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Studient</h1>
<c:forEach var="Temp" items="${stud_list}">
    ${Temp}
</c:forEach>>
</body>
</html>
