<%-- 
    Document   : detailArticle
    Created on : Jan 11, 2020, 2:28:33 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article</title>
    </head>
    <body>
        <h1>Article Detail</h1>

        <c:set var="dto" value="${requestScope.DTO_ARTICLE}"/>

        <c:set var="cmtList" value="${requestScope.LIST_CMT}"/>

        <c:set var="account_dto" value="${sessionScope.ACCOUNT_DTO}"/>

        <c:set var="role" value="${account_dto.role}"/>

        <c:if test="${not empty account_dto}">
            <h4>
                <font color="red">Welcome, ${account_dto.name}</font>
            </h4>
        </c:if>

        Title:  <i>${dto.title}</i><br/>
        Short Description:  <i>${dto.shortDescription}</i><br/>
        Content:    <i>${dto.content}</i><br/>
        Author: <i>${dto.author}</i><br/>
        Posting Date:   <i>${dto.postingDate}</i><br/>
        <c:if test="${role eq 'admin'}">
            Status: <i>${dto.status}</i><br/>
        </c:if>
        <br/>
        Comment: <br/>
        <c:if test="${not empty cmtList}">
            <c:forEach var="dtoCmt" items="${cmtList}">
                <font style="margin-left: 5%">
                <i>${dtoCmt.name}: ${dtoCmt.comment}</i>
                </font><br/>
            </c:forEach>
        </c:if>

        <c:if test="${empty cmtList}">
            <font style="margin-left: 5%">
            <b>No comment for this Article</b>
            </font><br/>
        </c:if>

        <c:if test="${role eq 'member'}">
            <form action="DispatchController" method="POST">
                <input type="text" name="txtCmt" value="" />
                <input type="hidden" name="txtTitle" value="${dto.title}" />
                <input type="submit" value="Comment" name="btAction" />
                <input type="hidden" name="flag" value="" />
            </form>
        </c:if>

        <c:if test="${role eq 'admin'}">
            <form action="DispatchController" method="POST">
                <input type="submit" value="Accept" name="btAction"
                    <c:if test="${dto.status ne 'New'}">disabled="disabled"</c:if>
                        />
                <input type="submit" value="Delete" name="btAction" 
                    <c:if test="${dto.status eq 'Deleted'}">disabled="disabled"</c:if>
                        />  
                <input type="submit" value="Undo" name="btAction" 
                    <c:if test="${dto.status ne 'Deleted'}">disabled="disabled"</c:if>
                        />                        
                <input type="hidden" name="txtTitle" value="${dto.title}" />
                <input type="hidden" name="txtEmail" value="${dto.author}" />
                <input type="submit" value="DeleteCmt" name="btAction" />
                </form>
        </c:if>

        <c:set var="message" value="${requestScope.MESSAGE}"/>
        <c:if test="${not empty message}">
            <font color="red">
            ${message}
            </font>
        </c:if><br/>

        <c:url var="homePageLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${homePageLink}">
            <font color="blue">Back to home page</font>
        </a>
    </body>
</html>
