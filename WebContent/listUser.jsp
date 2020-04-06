<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>listUser</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}" /></td>
            <td><c:out value="${user.getName()}" /></td>
            <td><c:out value="${user.getLogin()}" /></td>
            <td><c:out value="${user.getPassword()}" /></td>
            <td><a href="edit?action=edit&userId=<c:out value="${user.getId()}"/>">Update</a></td>
            <td><a href="delete?action=delete&userId=<c:out value="${user.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<p><a href="add?action=add">Add User</a></p>
</body>
</html>