
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h2>Students</h2>
<a href="/students/new">Add</a>
<table border="1">
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th colspan="3">Actions</th>
    </tr>
    <c:forEach var="tempStudent" items="${student}" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${tempStudent.id}</td>
            <td>${tempStudent.name}</td>
            <td>${tempStudent.email}</td>
            <td>${tempStudent.phone}</td>
            <td>
                <a href="/students/edit?id=${tempStudent.id}">Edit</a>
                <a href="/students/delete?id=${tempStudent.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>