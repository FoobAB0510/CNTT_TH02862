<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Student</title>
</head>
<body>
<h3>Add Student</h3>
<form action="/students/insert" method="post">
    <table>
        <tr>
            <td><label>Id</label></td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td><label>Name</label></td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td><label>Phone</label></td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>

</body>
</html>