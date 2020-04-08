<%-- 
    Document   : registration
    Created on : Jan 8, 2020, 9:09:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Registration a new account</h1>
        <c:set var="errors" value="${requestScope.ERROR}"/>
        <form action="DispatchController" method="POST">
            Email* <input type="text" name="txtEmail" value="${param.txtEmail}" /> (email@address.com) <br/>
            <c:if test="${not empty errors.emailLengthError}">
                <font color="red">${errors.emailLengthError}</font>
            </c:if>
            <c:if test="${not empty errors.emailSyntaxError}">
                <font color="red">${errors.emailSyntaxError}</font>
            </c:if><br/>

            Name* <input type="text" name="txtName" value="${param.txtName}" /> (2-50 chars) <br/>
            <c:if test="${not empty errors.nameLengthError}">
                <font color="red">${errors.nameLengthError}</font>
            </c:if><br/>

            Password* <input type="password" name="txtPassword" value="${param.txtPassword}" /> (6-30 chars) <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">${errors.passwordLengthError}</font>
            </c:if><br/>

            Confirm* <input type="password" name="txtConfirm" value="${param.txtConfirm}" /><br/>
            <c:if test="${not empty errors.confirmNotMatchError}">
                <font color="red">${errors.confirmNotMatchError}</font>
            </c:if><br/>

            Role <select name="txtRole">
                <c:set var="role" value="${param.txtRole}"/>
                <option 
                    <c:if test="${role eq 'member'}">selected</c:if>                       
                        > member </option>                   
                </select><br/>

            <c:set var="status" value="${param.txtStatus}"/>
            Status <select name="txtStatus">
                <option 
                    <c:if test="${status eq 'New'}">selected</c:if>
                        > New </option>
                </select><br/>

                <input type="submit" value="Create Account" name="btAction" />
            </form>
        <c:if test="${not empty errors.emailExistedError}">
            <font color="red">${errors.emailExistedError}</font>
        </c:if><br/>

        <c:if test="${not empty requestScope.MESSAGE}">
            <font color="green">${requestScope.MESSAGE}</font>
        </c:if> <br/>
        
        <c:url var="homePageLink" value="DispatchController">
            <c:param name="btAction" value="ShowAll"/>
        </c:url>
        <a href="${homePageLink}">
            <font color="blue">Back to home page</font>
        </a>
    </body>
</html>
