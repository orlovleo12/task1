<%--
  Created by IntelliJ IDEA.
  User: Lev
  Date: 11/13/2018
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h4>Приветствую тебя подаван.</h4>
<form items="${user}" var="user">
    Name : <input
        type="text" name="name"
        value=${user.getName()} readonly="readonly"> <br />
    Login : <input
        type="text" name="login"
        value=${user.getLogin()} readonly="readonly"> <br />
</form>
</body>
</html>
