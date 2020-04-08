<%-- 
    Document   : admin.jsp
    Created on : Jan 9, 2020, 1:12:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1 style="margin-left: 45%">Article Blog</h1>

        <c:set var="account_dto" value="${sessionScope.ACCOUNT_DTO}"/>
        <c:set var="msgDeleteUser" value="${requestScope.MSG}"/>

        <c:if test="${not empty account_dto}">
            <h3>
                <font color="red">Welcome, ${account_dto.name}</font>
            </h3>
        </c:if>

        <c:set var="message" value="${requestScope.MESSAGE}"/> 
        <c:set var="searchStatus" value="${requestScope.STATUS_SEARCH}"/>
        <form action="DispatchController" method="POST">
            &emsp; Search By Content: <input type="text" name="txtSearchContent" value="${param.txtSearchContent}" style="margin-left: 5%; width: 35%"/>
            &emsp; <input type="submit" value="Search Article" name="btAction" /><br/>

            <c:if test="${not empty message}">
                &emsp;<font color="red">${message}</font><br/>
            </c:if>

            &emsp; Search By Title: <input type="text" name="txtSearchByTitle" value="${param.txtSearchByTitle}" style="margin-left: 7%; width: 35%"/><br/>
            &emsp;Status:  &emsp; &emsp; All <input type="radio" name="txtSearchBy" value="All" checked="checked"/>
            &emsp; &emsp; New <input type="radio" name="txtSearchBy" value="New" 
                                     <c:if test="${searchStatus eq 'New'}">checked="checked"</c:if>/>
                                     &emsp; &emsp; Active <input type="radio" name="txtSearchBy" value="Active"
                                     <c:if test="${searchStatus eq 'Active'}">checked="checked"</c:if>/>
                                     &emsp; &emsp; Deleted <input type="radio" name="txtSearchBy" value="Deleted"
                                     <c:if test="${searchStatus eq 'Deleted'}">checked="checked"</c:if>/><br/>
            Email: <input type="text" name="txtEmailDelete" value="" /> <input type="submit" value="DeleteEmail" name="btAction" />
            
                22${msgDeleteUser}
            
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
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="numPage" value="${param.txtPage}"/>
                    <c:set var="sizeOfPage" value="${requestScope.SIZE_OF_PAGE}"/>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                    <form action="DispatchController" method="POST">
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
                                <input type="hidden" name="txtTitle" value="${dto.title}" />
                            </td>
                            <td>${dto.shortDescription}</td>
                            <td>${dto.author}</td>
                            <td>${dto.postingDate}</td>
                            <td>${dto.status}</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

        <c:set var="numberOfPages" value="${sessionScope.NUMBER_OF_PAGE}"/>
        <form action="DispatchController" method="POST">
            <c:if test="${not empty param.txtSearchContent}">
                <c:forEach var="numPage" begin="1" end="${numberOfPages}">
                    <c:url var="pagingLink" value="DispatchController">
                        <c:param name="txtPage" value="${numPage}"/>
                        <c:param name="btAction" value="Search Article"/>
                        <c:param name="txtSearchContent" value="${param.txtSearchContent}"/>
                        <c:param name="txtSearchByTitle" value="${param.txtSearchByTitle}"/>
                        <c:param name="txtSearchBy" value="${param.txtSearchBy}"/>
                    </c:url>
                    <a href="${pagingLink}">
                        <font color="blue">${numPage}</font>
                    </a>
                </c:forEach>
            </c:if>
            
            <c:if test="${empty param.txtSearchContent}">
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
            <br/><br/>
            <input type="hidden" name="txtSearchContent" value="${param.txtSearchContent}" />
            <input type="hidden" name="txtSearchByTitle" value="${param.txtSearchByTitle}" />
            <input type="hidden" name="txtSearchBy" value="${param.txtSearchBy}" />            
                <input type="submit" value="Delete All" name="btAction" 
                    <c:if test="${param.txtSearchBy eq 'Deleted'}">disabled="disabled"</c:if>
                    />
            
        </form>
    </c:if>

    <c:if test="${empty list}">
        <c:if test="${empty message}">
            <h3>No record Article is match!!!</h3>
        </c:if>
    </c:if>

    <br/>
    <c:url var="logoutLink" value="DispatchController">
        <c:param name="btAction" value="Logout"/>
    </c:url>
    <a href="${logoutLink}">
        <font color="blue">Logout</font>
    </a>
</body>
</html>
