<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>
<html>
<head>
    <style>
        <%@include file="stylesheets/account_repl.css" %>
    </style>
    <title><fmt:message key="account_replenished" bundle="${loc}"/></title>
</head>
<body>
<fieldset>
    <p><fmt:message key="replenished_by" bundle="${loc}"/> ${value} UAH.</p>
    <form method="post" action="/machine/login">
        <input type="submit" value="<fmt:message key="back_to_menu" bundle="${loc}"/>">
        <input type="submit" value="<fmt:message key="back_to_home_page" bundle="${loc}"/>" formaction="/machine/index">
    </form>
</fieldset>
</body>
</html>