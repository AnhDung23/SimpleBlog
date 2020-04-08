<%-- 
    Document   : login
    Created on : Jan 8, 2020, 10:07:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:set var="flag" value="${requestScope.FLAG}"/>
        <form action="DispatchController" method="POST">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            Password: <input type="password" name="txtPassword" value="${param.txtPassword}" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="hidden" name="txtFlag" value="${flag}" />
        </form>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${not empty error}">
            <font color="red">${error}</font><br/>           
        </c:if>
        
        <a href="registration.jsp">
            <font color="blue">Create new Account</font>
        </a><br/>
        
        <c:url var="homePageLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${homePageLink}">
            <font color="blue">Back to home page</font>
        </a>
    </body>
</html>
