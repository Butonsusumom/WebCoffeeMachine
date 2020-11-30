<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>

<html>
<head>
    <style>
        <%@include file="stylesheets/machine_settings.css" %>
    </style>
    <title><fmt:message key="machine_settings" bundle="${loc}"/></title>
</head>
<body>
<form method="GET" id="userLine">
    <span>
        ${currentUser.getLogin()}
    </span>
    <input type="submit" value="<fmt:message key="ingr_fills_history" bundle="${loc}"/>" name="ingrFillsType"
           class="fillingHistory" formaction="/machine/fillingHistory"/>
    <input type="submit" value="<fmt:message key="items_fills_history" bundle="${loc}"/>" name="itemfillsType"
           class="fillingHistory" formaction="/machine/fillingHistory"/>
    <span><a href="index" id="exitButton" class="userLineAttr"><img src="<c:url value="/img/exit_button.png"/>"
                                                                    align="right"
                                                                    alt="Exit"></a></span>
</form>
<form method="GET" id="ingredientsLine" action="/machine/fillContainer">
    <fieldset>
        <legend><fmt:message key="ingredients" bundle="${loc}"/></legend>
        <c:forEach var="ingredient" items="${ingredients}">
            <label>${ingredient.getCamelCaseName()} Quantity: ${ingredient.filledInPercentage()}%</label>
            <input type="submit" class="buttons" name="${ingredient.getName()}"
                   value="<fmt:message key="fill_container" bundle="${loc}"/>"
            <c:if test="${5 < ingredient.filledInPercentage()}">
                   disabled="disabled"</c:if>
            ><br>
        </c:forEach>
    </fieldset>
    <br/><br/><br/>
    <fieldset>
        <legend><fmt:message key="items" bundle="${loc}"/></legend>
        <c:forEach var="item" items="${items}">
            <label>${item.getName()} Count: ${item.filledInPercentage()}%</label>
            <input type="submit" class="buttons" name="${item.getCanonicalName()}"
                   value="<fmt:message key="fill_container" bundle="${loc}"/>"
            <c:if test="${5 < item.filledInPercentage()}">
                   disabled="disabled"
            </c:if>><br/>
        </c:forEach>
    </fieldset>
    <br/>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
