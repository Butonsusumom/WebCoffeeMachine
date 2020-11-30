<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>

<html>
<head>
    <style>
        <%@include file="stylesheets/index.css" %>
    </style>
    <title><fmt:message key="coffee_machine" bundle="${loc}"/></title>
</head>
<body>
<form method="POST">
    <label for="login"><fmt:message key="login" bundle="${loc}"/></label>
    <input type="text" class="txt" id="login" maxlength="16" pattern="^[_a-zA-Z0-9]{1,16}" name="login"
           placeholder="<fmt:message key="login" bundle="${loc}"/>" required autofocus/><br>
    <label for="password"><fmt:message key="password" bundle="${loc}"/></label>
    <input type="password" class="txt" id="password" maxlength="16" pattern="^[a-zA-Z0-9]{1,16}" name="password"
           placeholder="<fmt:message key="password" bundle="${loc}"/>" required/><br>
    <input type="submit" name="loginButton" formaction="/machine/login" class="buttons"
           value="<fmt:message key="login" bundle="${loc}"/>">
    <input type="submit" class="buttons" name="registrationButton"
           value="<fmt:message key="registration" bundle="${loc}"/>" formaction="/machine/registration"
           formnovalidate="formnovalidate"/><br>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
