<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<div align="center">

    <h1>Users</h1>

    <form action="add" method="POST">
        <table border="1" cellpadding="5">
            <caption><h2>Add New User</h2></caption>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${user.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${user.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Date Of Birth:</th>
                <td>
                    <input type="text" name="dateOfBirth" size="15"
                           value="<c:out value='${user.dateOfBirth}' />"
                    />
                </td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
    </form>

    <h3>
        <a href="/">Back to User List</a>
    </h3>
</div>
</body>
</html>
