<%@page import="data.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userDao" type="data.UserDao" scope="request" />
<jsp:useBean id="message" type="data.Message" scope="request" />
<%@taglib prefix="mt" uri="/WEB-INF/mt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <mt:css/>
    </head>
    <body>
        <div id="demo">
            <h1>Aliexpress List</h1>

                <h2>Welcome </h2>
   
        </div>
        <hr/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Please login or register</h2>
        <body>
        <form method="POST" action="user.html">
            Login: <input type="text" name="login" />
            Password <input type="password" name="pass" />
            <input type="submit" name="submit" value="Login" />
            <input type="submit" name="submit" value="Register" />
        </form>
            <fmt:message key="${message.message}"/>
            <h3>${message.message}</h3>

        <hr><ol> 
            <% for (User user : userDao.getAllUsers()) { %>
            <li> <%= user %> </li>
        <% } %>
        </ol><hr>
        Page hit counter: <b> ${cookie.hitCounter.value} </b>
    </body>
</html>
