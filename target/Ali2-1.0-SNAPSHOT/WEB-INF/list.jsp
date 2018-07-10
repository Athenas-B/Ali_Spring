

<%@page import="java.util.List"%>
<%@page import="data.Item"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" uri="/WEB-INF/mt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="userDao" type="data.UserDao" scope="request" />
<jsp:useBean id="itemDao" type="data.ItemDao" scope="request" />
<!DOCTYPE html>
<%

    long lo = (Long) request.getSession().getAttribute("login");
    int id = (int) lo;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <mt:css/>
    </head>
    <body>
        <div id="demo">
            <h1>Aliexpress List</h1>
            <c:set var="user">${sessionScope.login}</c:set>

                <h2>Welcome <%= userDao.getUser(id).getLogin()%>  </h2>
            <% if (userDao.getUser(id).isAdmin()) {
                        out.print("<a href='manage.html'>User management</a>");
                    } %> <a href="user.html">Logout</a>
        </div>
        <hr/>
        <h3>${message.message}</h3>
        <table class="paleBlueRows">
            <thead><tr><td>id</td><td>Title</td><td>Count</td><td>Author</td><td>Note</td> <td>Url</td><td>Checked</td><td>Action</td></tr></thead>  
            <tbody>
                <%
                    itemDao.setCurrUser(id);
                    if (userDao.getUser(id).isAdmin()) {
                        itemDao.setCurrUser(-1);
                    }
                %>

                <c:forEach var="item" items="${itemDao.allItemsOfCurrUser}">


                    <tr>

                        <td><c:out value="${item.id}"/> </td>
                        <td><c:out value="${item.title}"/> </td>
                        <td><c:out value="${item.count}"/> </td>
                        <td><c:out value="${item.author.login}"/> </td>
                        <td><c:out value="${item.note}"/> </td>
                        <td><c:out value="${fn:substring(item.url, 0, 20)}"/> </td>
                        <td><c:out value="${item.checked}"/> </td>


                        <td>

                            <form method="POST" action="list.html"><input type="number" name="id" value="${item.id}" hidden="true"/><input type="submit" name="submit" value="Delete" />
                                <input type="submit" name="submit" value="Check"/>
                            </form> </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <br/>
        <form method="POST" action="list.html">
            URL: <input type="text" name="url" />
            Title: <input type="text" name="title" />
            Count: <input type="number" name="count" min="1" value="1" />
            Note: <input type="text" name="note" />
            <input type="number" name="author" value="${user}" hidden="true" />
            <br/>
            <input type="submit" name="submit" value="Insert" />
            <input type="reset"  value="clear" />
        </form>

        <hr/>
        Page hit counter: <b><mt:number number="${cookie.hitCounter.value}" format="#,###"/> </b>
    </body>
</html>
