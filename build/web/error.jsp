<%-- 
    Document   : error
    Created on : Jan 8, 2020, 10:20:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>ERROR PAGE!</h1>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <h3>
            <font color="red">${error}</font>
        </h3>
        
        <c:url var="homePageLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${homePageLink}">
            <font color="blue">Back to home page</font>
        </a>
    </body>
</html>
