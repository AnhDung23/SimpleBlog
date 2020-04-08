<%-- 
    Document   : verify
    Created on : Mar 7, 2020, 9:40:53 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Check mail to verification account</h3>
        <form action="DispatchController" method="POST">
            <input type="text" name="code" value="${param.code}"/>
            <input type="hidden" name="email" value="${requestScope.EMAIL}"/>
            <input type="submit" name="btAction" value="Sent"/>
        </form>
    </body>
</html>
