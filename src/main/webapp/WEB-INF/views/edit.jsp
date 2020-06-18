<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<div align="center">

    <h1>Users</h1>

    <form action="/admin/edit" method="POST">
        <table border="1" cellpadding="5">
            <caption><h2>Edit User</h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45" value="<c:out value='${user.name}' />"/>
                </td>
            </tr>
            <tr>
                <th>User Password:</th>
                <td>
                    <input type="text" name="password" size="45" value="<c:out value='${user.password}' />"/>
                </td>
            </tr>
            <tr>
                <th>User Role:</th>
                <td>
                    <input type="text" name="role" size="45" value="<c:out value='${user.role}' />"/>
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" size="45" value="<c:out value='${user.email}' />"/>
                </td>
            </tr>
            <tr>
                <th>Date Of Birth:</th>
                <td>
                    <input type="text" name="dateOfBirth" size="15" value="<c:out value='${user.dateOfBirth}' />"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
    </form>

    <h3>
        <a href="/admin">Back to User List</a>
    </h3>
</div>
</body>
</html>
