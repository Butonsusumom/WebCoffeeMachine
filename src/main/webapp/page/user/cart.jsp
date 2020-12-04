<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="cart.title"/></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/cart.css" rel="stylesheet" type="text/css">
    <link href="css/fullstyle.css" rel="stylesheet">
    <style type="text/css">
        /* Necessary for full page carousel*/
        html,
        body,
        header
        .view {
            height: 100%;
            /*top: 150px;*/
            background-color: black;
        }

        @media (min-width: 800px) and (max-width: 850px) {
            .navbar:not(.top-nav-collapse) {
                background: #1C2331 !important;
            }
        }

        .content {
            position: fixed;
            bottom: 0;
            background: rgba(0, 0, 0, 0.5);
            color: #f1f1f1;
            width: 100%;
            padding: 20px;
        }

        .form-active {
            height: auto;
            width: 60%;
            margin: 60px auto;
            /*background: ;*/
            padding: 60px 120px 80px 120px;
            background-color: white;
        }

    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="../jspheader/nav.jsp" %>
<!-- Navbar -->

<!--<h1><fmt:message key="cart.cart"/></h1>
<c:if test="${not empty errorMessage}">
    <div class="text-center text-warning">
        <h2 class="text">
            <fmt:message key="${errorMessage}"/>
        </h2>
    </div>
</c:if>-->

<section class="view card white wow fadeIn" id="intro">
    <table class="table">
        <thead class="thead ">
        <tr>
            <th/>
            <th><fmt:message key="cart.product"/></th>
            <th><fmt:message key="cart.volume"/></th>
            <th><fmt:message key="cart.price"/></th>
            <th><fmt:message key="cart.removing"/></th>

        </tr>
        </thead>

        <tbody>
        <c:if test="${empty orderList}">
            <h1><fmt:message key="cart.empty_cart"/></h1>
        </c:if>

        <c:if test="${not empty orderList}">
        <c:forEach var="order" items="${orderList}">
            <tr>
                    <%--            <div class="product">--%>
                    <%--                <div class="product-details">--%>
                        <td/>
                <td>${order.drink.title}</td>
                <td><fmt:message key="cart.${order.drink.drinkSize}"/></td>
                    <%--                </div>--%>
                <td>${order.drink.price}</td>
                <td>
                    <form action="coffee_machine" method="post">
                        <input type="hidden" name="drinkId" value="${order.id}">
                        <button type="submit" class="btn small btn-danger" name="command" value="DELETE_DRINK_FROM_CART">
                            <fmt:message key="cart.remove"/>
                        </button>
                    </form>
                </td>

                    <%--            </div>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>

</section>

<%--<div class="shopping-cart">--%>

<%--    <div class="column-labels">--%>
<%--        <label class="product-details"><fmt:message key="cart.product"/></label>--%>
<%--        <label class="product-price"><fmt:message key="cart.price"/></label>--%>
<%--&lt;%&ndash;                    <label class="product-quantity">Quantity</label>&ndash;%&gt;--%>
<%--        <label class="product-removal"><fmt:message key="cart.removing"/></label>--%>
<%--        <label class="product-line-price"><fmt:message key="cart.total"/></label>--%>
<%--    </div>--%>

<%--    <c:if test="${empty orderList}">--%>
<%--        <h1><fmt:message key="cart.empty_cart"/></h1>--%>
<%--    </c:if>--%>

<%--    <c:forEach var="order" items="${orderList}">--%>
<%--        <div class="product">--%>
<%--            <div class="product-details">--%>
<%--                <div class="product-title">${order.drink.title}</div>--%>
<%--                <p><fmt:message key="cart.${order.drink.drinkSize}"/></p>--%>
<%--            </div>--%>
<%--            <div class="product-price">${order.drink.price}</div>--%>
<%--&lt;%&ndash;                            <div class="product-quantity">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <input type="number" value="1" min="1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--            <div class="product-removal">--%>
<%--                <form action="coffee_machine" method="post">--%>
<%--                    <input type="hidden" name="drinkId" value="${order.id}">--%>
<%--                    <button type="submit" class="remove-product" name="command" value="DELETE_DRINK_FROM_CART">--%>
<%--                        <fmt:message key="cart.remove"/>--%>
<%--                    </button>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--            <div class="product-line-price">${order.drink.price}</div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>


<%--</div>--%>
<form action="coffee_machine" method="post">
    <button id="checkoutButton" type="submit" class="checkout" name="command" value="CHECKOUT_CART">
        <fmt:message key="cart.checkout"/>
    </button>
</form>

</body>
</html>

