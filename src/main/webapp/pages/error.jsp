<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>
<html>
<head>
    <style>
        <%@include file="stylesheets/error.css"%>
    </style>
    <title><fmt:message key="error_page" bundle="${loc}"/></title>
</head>
<body>
<fieldset>
    <h1><fmt:message key="error_message" bundle="${loc}"/></h1>
    <form>
        <input type="submit" formaction="/machine/index" id="backToHomePage"
               value="<fmt:message bundle="${loc}" key="back_to_home_page"/>"/>
    </form>
</fieldset>
</body>
</html>
