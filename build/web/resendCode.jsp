<%-- 
    Document   : confirmCode
    Created on : Oct 2, 2021, 6:03:48 PM
    Author     : cunpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Active code</h1>
        <form action="resendCode">
            Email:
            <input type="text" name="email" value="${sessionScope.ACTIVE_ACCOUNT}" required=""/>
            <hr>
            <input type="submit" value="resend" name="btnAction" />
            <c:if test="${not empty requestScope.CONFIRM_MSG}">
                <p style="color: red">${requestScope.CONFIRM_MSG}</p>
            </c:if>
                
                <a href="loginPage">Login Page</a>
        </form>
            

    </body>
</html>
