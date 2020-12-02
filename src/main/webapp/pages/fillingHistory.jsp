<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/pages" prefix="pg" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>
<html>
<head>
    <style>
        <%@include file="stylesheets/fillingHistory.css" %>
    </style>
    <title><fmt:message key="fillings_history" bundle="${loc}"/></title>
</head>
<body>
<form method="GET" id="userLine">
    <span>${currentUser.getLogin()}</span>
    <span><a href="/machine/index" id="exitButton" class="userLineAttr"><img src="<c:url value="/img/exit_button.png"/>"
                                                                             align="right"
                                                                             alt="Exit"></a></span>
</form>
<main>
    <table id="fillingsHistory">
        <tr>
            <th><fmt:message key="name" bundle="${loc}"/></th>
            <th><fmt:message key="number" bundle="${loc}"/></th>
            <th><fmt:message key="date" bundle="${loc}"/></th>
            <th><fmt:message key="user" bundle="${loc}"/></th>
        </tr>
        <c:forEach var="fill" items="${fills}">
            <tr>
                <td>${fill.getName()}</td>
                <td>${fill.getQuantity()}</td>
                <td>${fill.getDate()}</td>
                <td>${fill.getUser()}</td>
            </tr>
        </c:forEach>
    </table>

    <pg:pages activated="${activated}" length="${fillsLength}"/>
    <form id="back">
        <input type="submit" id="backToSettings" value="<fmt:message key="back_to_settings" bundle="${loc}"/>"
               formaction="/machine/login">
    </form>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
