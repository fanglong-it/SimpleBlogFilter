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
        <form action="confirmCode">
            Email:
            <input type="text" name="email" value="${sessionScope.ACTIVE_ACCOUNT}" readonly=""/>
            <hr>
            Code:
            <input type="text" name="subCode" value="" />
            <a href="resendCode?email=${param.email}" type="submit">Resend Code</a>
            <input type="submit" value="submitCode" name="btnAction" />

            <c:if test="${not empty requestScope.CREATEACCOUNT_MSG}">
                ${requestScope.CREATEACCOUNT_MSG}
            </c:if>

            <c:if test="${not empty requestScope.CONFIRM_MSG}">
                ${requestScope.CONFIRM_MSG}
            </c:if>

        </form>


    </body>
</html>
