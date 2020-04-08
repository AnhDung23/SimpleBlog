<%-- 
    Document   : homePage
    Created on : Jan 8, 2020, 9:35:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Blog</title>
    </head>
    <body>

        <h1 style="margin-left: 45%">Article Blog</h1>

        <c:set var="account_dto" value="${sessionScope.ACCOUNT_DTO}"/>

        <c:if test="${not empty account_dto}">
            <h4>
                <font color="red">Welcome, ${account_dto.name}</font>
            </h4>

                <a href="postArticle.jsp">
                    <font color="blue" style="margin-left: 80%"> Post </font>
                </a>

                <c:url var="logoutLink" value="DispatchController">
                    <c:param name="btAction" value="Logout"/>
                </c:url>
                <a href="${logoutLink}" style="margin-left: 5px">
                    <font color="blue"> Logout </font>
                </a>
        </c:if>

        <c:if test="${empty account_dto}">
            <c:url var="postLink" value="DispatchController">
                <c:param name="btAction" value="Post"/>
                <c:param name="flag" value="loginToPostArticle"/>
            </c:url>
            <a href="${postLink}">
                <font color="blue" style="margin-left: 80%"> Post </font>
            </a>
            <a href="registration.jsp">
                <font color="blue" style="margin-left: 5px"> Registration </font>
            </a>
            <a href="login.jsp" style="margin-left: 5px">
                <font color="blue"> Login </font>
            </a>
        </c:if>

        <c:set var="message" value="${requestScope.MESSAGE}"/> 

        <form action="DispatchController" method="POST">
            &emsp;Search By Content: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" style="margin-left: 5%; width: 35%"/>      
            &emsp;<input type="submit" value="Search" name="btAction" />
            <c:if test="${not empty message}">
                <br/>&emsp;<font color="red">${message}</font><br/>
            </c:if>
        </form><br/>

        <c:url var="showAllLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${showAllLink}">
            <font color="blue">Show All</font>
        </a>

        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Short Description</th>
                        <th>Author</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="numPage" value="${param.txtPage}"/>
                    <c:set var="sizeOfPage" value="${requestScope.SIZE_OF_PAGE}"/>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>
                                <c:if test="${empty numPage}">
                                    <c:set var="numPage" value="1"/>
                                </c:if>
                                ${counter.count + (numPage-1)*sizeOfPage}
                            </td>
                            <td>
                                <c:url var="showDetailLink" value="DispatchController">
                                    <c:param name="btAction" value="ShowDetail"/>
                                    <c:param name="txtTitle" value="${dto.title}"/>
                                </c:url>
                                <a href="${showDetailLink}" style="text-decoration: none">
                                    <font color="black">${dto.title}</font>
                                </a>
                            </td>
                            <td>${dto.shortDescription}</td>
                            <td>${dto.author}</td>
                            <td>${dto.postingDate}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

            <c:set var="numberOfPages" value="${sessionScope.NUMBER_OF_PAGE}"/>
            <form action="DispatchController" method="POST">     
                <c:if test="${not empty param.txtSearchValue}">
                    <c:forEach var="numPage" begin="1" end="${numberOfPages}">                        
                        <c:url var="pagingLink" value="DispatchController">
                            <c:param name="txtPage" value="${numPage}"/>
                            <c:param name="btAction" value="Search"/>
                            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                        </c:url>
                        <a href="${pagingLink}">
                            <font color="blue">${numPage}</font>
                        </a>
                    </c:forEach>
                </c:if>
                
                <c:if test="${empty param.txtSearchValue}">
                    <c:forEach var="numPage" begin="1" end="${numberOfPages}">                        
                        <c:url var="pagingLink" value="DispatchController">
                            <c:param name="txtPage" value="${numPage}"/>
                            <c:param name="btAction" value="ShowAll"/>
                        </c:url>
                        <a href="${pagingLink}">
                            <font color="blue">${numPage}</font>
                        </a>
                    </c:forEach>
                </c:if>
            </form>
        </c:if>

        <c:if test="${empty list}">
            <c:if test="${empty message}">
                <h3>No record Article is match!!!</h3>
            </c:if>
        </c:if>

        <br/>
    </body>
</html>
