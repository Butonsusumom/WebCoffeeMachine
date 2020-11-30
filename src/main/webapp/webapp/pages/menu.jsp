<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/sugarTag" prefix="st" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n" var="loc"/>

<html>
<head>
    <style>
        <%@include file="stylesheets/menu.css" %>
    </style>
    <title><fmt:message key="menu" bundle="${loc}"/></title>
</head>
<body>
<form method="GET" action="/machine/personalAccount" id="userLine">
    <input type="submit" name="gotoPA" id="gotoPA" class="userLineAttr"
           value="<fmt:message key="personal_cabinet" bundle="${loc}"/> ">
    <span class="userLineAttr"><fmt:message key="balance" bundle="${loc}"/> ${currentUser.getBalance()} UAH</span>
    <span class="userLineAttr">${currentUser.getLogin()}</span>
    <span><a href="/machine/index" id="exitButton" class="userLineAttr"><img src="<c:url value="/img/exit_button.png"/>"
                                                                             align="right"
                                                                             alt="Exit"></a></span>
</form>
<aside>
    <br/>
    <form action="/machine/login">
        <br/>
        <p align="center"><fmt:message key="type_of_drink" bundle="${loc}"/></p>
        <label for="typeOfDrink"><input type="radio" name="drinkType" id="typeOfDrink" value="allDrinks"
                <c:if test="${drinkType eq 'allDrinks'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="all_drinks" bundle="${loc}"/></label><br/>
        <label for="coffeeCheck"><input type="radio" name="drinkType" id="coffeeCheck" value="coffee"
                <c:if test="${drinkType eq 'coffee'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="coffee" bundle="${loc}"/></label><br/>
        <label for="teaCheck"><input type="radio" name="drinkType" id="teaCheck" value="tea"
                <c:if test="${drinkType eq 'tea'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="tea" bundle="${loc}"/></label><br/><br/>

        <p align="center"><fmt:message key="milk" bundle="${loc}"/></p>
        <label for="milkAll"><input type="radio" name="milkAvailability" id="milkAll" value="allDrinks"
                <c:if test="${milkAvailability eq 'allDrinks'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="all_drinks" bundle="${loc}"/></label><br/>
        <label for="withMilk"><input type="radio" name="milkAvailability" id="withMilk" value="withMilk"
                <c:if test="${milkAvailability eq 'withMilk'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="with_milk" bundle="${loc}"/></label><br/>
        <label for="withoutMilk"><input type="radio" name="milkAvailability" id="withoutMilk" value="withoutMilk"
                <c:if test="${milkAvailability eq 'withoutMilk'}">
                    checked="checked"
                </c:if>
        /><fmt:message key="without_milk" bundle="${loc}"/></label><br/><br/><br/>
        <input type="submit" class="buttons" id="submitFilter" value="<fmt:message key="show" bundle="${loc}"/>">
    </form>
</aside>

<main>
    <form action="/machine/makeDrink" id="coffeeForm" method="GET"><br/>
        <span id="sugarSpan">
            <c:if test="${sugarAvailable}">
                <fmt:message key="count_of_sugar" bundle="${loc}"/>
            </c:if>
            <st:sugarTag/></span><br/><br/>
        <c:forEach var="drink" items="${drinks}">
            <input type="submit" class="buttons" name="selectedDrink" value="${drink.getName()} ${drink.getPrice()} UAH"
                    <c:if test="${0 > currentUser.getBalance().compareTo(drink.getPrice())}">
                        disabled="disabled"
                    </c:if>/>
        </c:forEach>
    </form>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
