<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>
<html>
<head>
    <style>
        <%@include file="stylesheets/not_enough_money.css"%>
    </style>
    <title><fmt:message key="not_enough_money" bundle="${loc}"/></title>
</head>
<body>
<fieldset>
    <h1><fmt:message key="not_enough_money" bundle="${loc}"/></h1>
    <form>
        <input type="submit" formaction="/machine/login" id="backToMenu"
               value="<fmt:message bundle="${loc}" key="back_to_menu"/>"/>
    </form>
</fieldset>
</body>
</html>