<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update Student</title>
</head>
<body>

<h3>Update Student</h3>
<form action="/students/update" method="post">
  <table>
    <tr>
      <td><label>Id</label></td>
      <td><input type="text" name="id" value="${student.id}" readonly></td>
    </tr>
    <tr>
      <td><label>Name</label></td>
      <td><input type="text" name="name" value="${student.name}"></td>
    </tr>
    <tr>
      <td><label>Email</label></td>
      <td><input type="text" name="email" value="${student.email}"></td>
    </tr>
    <tr>
      <td><label>Phone</label></td>
      <td><input type="text" name="phone" value="${student.phone}"></td>
    </tr>
    <tr>
      <td><label></label></td>
      <td><input type="submit" value="Update"></td>
    </tr>
  </table>
</form>
</body>
</html>