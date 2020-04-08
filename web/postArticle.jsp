<%-- 
    Document   : postArticle
    Created on : Jan 9, 2020, 1:12:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posting Page</title>
    </head>
    <body>
        <h1>Posting Page</h1>

        <c:set var="account_dto" value="${sessionScope.ACCOUNT_DTO}"/>
        <c:if test="${not empty account_dto}">
            <h3>
                <font color="red">Welcome, ${account_dto.name}</font>
            </h3>
        </c:if>

        <c:set var="errors" value="${requestScope.ERROR}"/>
        Information of the Article:
        <form action="DispatchController" method="POST">
            <br/>Title: <input type="text" name="txtTitle" value="${param.txtTitle}" /><br/>
            <c:if test="${not empty errors.titleLengthErr}">
                <font color="red">${errors.titleLengthErr}</font>
            </c:if><br/>
                
            Short Description: <input type="text" name="txtShortDescription" value="${param.txtShortDescription}" /><br/>
            <c:if test="${not empty errors.shortDescriptionLengthErr}">
                <font color="red">${errors.shortDescriptionLengthErr}</font>
            </c:if><br/>
            
            Content: <input type="text" name="txtContent" value="${param.txtContent}" /><br/>
            <c:if test="${not empty errors.contentFullFieldErr}">
                <font color="red">${errors.contentFullFieldErr}</font>
            </c:if><br/>
            
            <input type="submit" value="Post" name="btAction" />
        </form>        
        <c:if test="${not empty errors.titleIsExisted}">
            <font color="red">${errors.titleIsExisted}</font>
        </c:if><br/>

        <c:set var="postSuccessful" value="${requestScope.MESSAGE}"/>
        <c:if test="${not empty postSuccessful}">
            <font color="green">${postSuccessful}</font>
        </c:if><br/>
        
        <c:url var="homaPageLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${homaPageLink}">
            <font color="blue">Back to home page</font>
        </a><br/>
    </body>
</html>
