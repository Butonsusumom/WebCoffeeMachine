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
    <title><fmt:message key="add_drink.title"/></title>
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
            height: 100%;
            background-image: url(${pageContext.request.contextPath}/img/machine.jpg);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .form-active {
            width: 60%;
            margin: 60px auto;
            /*background: ;*/
            padding: 60px 120px 80px 120px;
            /*text-align: center;*/
            -webkit-box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
            box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
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


        .modal {
            /*padding: 50px;*/
            background: #ff3d00;
            position: fixed;
            top: 50%;
            left: 50%;
        }


    </style>
</head>
<body>
<%@include file="../jspheader/nav.jsp" %>

<section class="view card brown wow fadeIn" id="intro">

    <!-- Content -->
    <div class="card-body text-white  py-5 px-5 my-5">


        <form class="brown form-active" action="coffee_machine" method="post">
            <c:if test="${not empty errorMessage}">
                <div class="text-center text-warning">
                    <label class="text">
                        <fmt:message key="${errorMessage}"/>
                    </label>
                </div>
            </c:if>
            <div class="form-group">
                <label for="inputTitle"><fmt:message key="add_drink.drink_title"/></label>
                <input type="text" class="form-control" id="inputTitle" name="drinkTitle" required>
            </div>
            <div class="form-group">
                <select class="form-control" name="drinkVolume">
                    <option value="LARGE"><fmt:message key="add_drink.large"/></option>
                    <option value="MEDIUM"><fmt:message key="add_drink.medium"/></option>
                    <option value="SMALL"><fmt:message key="add_drink.small"/></option>
                </select>
            </div>
            <div class="form-group">
                <label for="price"><fmt:message key="add_drink.price"/></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend">$</span>
                    </div>
                    <input type="text" class="form-control" id="price" aria-describedby="inputGroupPrepend"
                           name="drinkPrice"
                           pattern="[0-9]+([\.,][0-9]{2})?"
                           title="<fmt:message key="add_drink.money_validation"/>" required>
                </div>
            </div>
            <div class="form-group">
                <label for="inputNumber"><fmt:message key="add_drink.servings_number"/></label>
                <input type="text" class="form-control" id="inputNumber" name="servingNumber"
                       pattern="[0-9]{1,}"
                       title="<fmt:message key="add_drink.servings_validation"/>" required>
            </div>
            <button type="submit" class="btn btn-outline-white btn-lg" name="command"
                    value="ADD_DRINK">
                <fmt:message key="add_drink.add"/>
            </button>
        </form>
    </div>
    <!-- Content -->
</section>
<footer font="Georgia" style="font-weight:bold;background-color:black;">
    <ctg:copyrightTag/>
</footer>
</body>
</html>
