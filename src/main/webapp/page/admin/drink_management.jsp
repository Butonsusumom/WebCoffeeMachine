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
    <title><fmt:message key="drink_management.title"/></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/fullstyle.css" rel="stylesheet">
    <style type="text/css">
        /* Necessary for full page carousel*/
        html,
        body,
        header,
        .view {
            height: 50%;
            /*top: 150px;*/
            background-color: #c19c82;
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

        .form-table {
            width: 60%;
            margin: 10px auto;
            background-color: white;
        }

        .form-active {
            padding: 60px 50px 15px 50px;
            background-color: #c19c82;
        }
        .card-body {
            background-color: #c19c82;
        }

    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="../jspheader/nav.jsp" %>
<!-- Navbar -->


<%--<div class="view">--%>
<div class="card text-center">
    <div class="card-body">
        <form class="form-active" action="coffee_machine" method="get">
            <button type="submit" class="btn white" name="command" value="ADD_DRINK_PAGE">
                <fmt:message key="drink_management.add_drink"/>
            </button>
        </form>
    </div>
</div>
<div class="form-table">
    <div>
        <table class="table">
            <thead class="thead ">
            <tr>
                <th scope="col"><fmt:message key="drink_management.drink_title"/></th>
                <th scope="col"><fmt:message key="drink_management.volume"/></th>
                <th scope="col"><fmt:message key="drink_management.price"/></th>
                <th scope="col"><fmt:message key="drink_management.servings_number"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="drink" items="${drinkList}">
                <tr>
                    <td>${drink.title}</td>
                    <td><fmt:message key="drink_management.${drink.drinkSize}"/></td>
                    <td>${drink.price}</td>
                    <td>${drink.servingNumber}</td>
                    <td>
                        <form action="coffee_machine" method="get">
                            <input type="hidden" name="drinkId" value="${drink.id}"/>
                            <button type="submit" class="btn" name="command" value="ADD_SERVINGS_PAGE">
                                <fmt:message key="drink_management.add_servings"/>
                            </button>
                        </form>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>


<%--</div>--%>
<footer>
    <ctg:copyrightTag/>
</footer>
</body>

</html>

