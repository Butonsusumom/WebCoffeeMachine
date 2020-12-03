<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="menu.title"/></title>
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
        .content {
            /*height: 50%;*/
            padding: 40px 50px 10px 50px;
            background-color: #c19c82;
        }
        .c{
            background-color: #ffffcc;
        }

    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->


<form class="content" action="coffee_machine" method="post">

    <c:if test="${empty drinkList}">
        <h1><fmt:message key="menu.empty_menu"/></h1>
    </c:if>
    <%--    <div class="card-deck">--%>
    <div class="row row-cols-1 row-cols-md-4">
        <c:forEach var="drink" items="${drinkList}">

            <div class="col-md-4 mb-4">
                <div class="card h-100">
                        <%--                    <img class="card-img-top">--%>
                    <div class="card-body c">
                        <h5 class="card-title">${drink.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="menu.${drink.drinkSize}"/></h6>
                        <h6 class="card-title">${drink.price}</h6>
                        <form action="coffee_machine" method="post">
                            <input type="hidden" name="drinkId" value="${drink.id}"/>
                            <button type="submit" class="btn btn-sm" name="command" value="ADD_DRINK_TO_CART">
                                <fmt:message key="menu.add"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--    </div>--%>


</form>

</body>

</html>

