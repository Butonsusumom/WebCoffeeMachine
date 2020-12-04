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
    <title><fmt:message key="user_profile.title"/></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/profile_style.css" rel="stylesheet" type="text/css">
    <link href="css/fullstyle.css" rel="stylesheet">
</head>
<body style="background:linear-gradient(#000000,#434343)">
<!-- Navbar -->
<%@include file="../jspheader/nav.jsp" %>
<!-- Navbar -->

<div class="shift" >
    <div class="container emp-profile">
        <form action="coffee_machine" method="get">
            <div class="row">
                <div class="col-md-4">
                </div>

                <div class="col-md-6">
                    <div class="profile-head">
                        <h5>
                            ${user.name}
                        </h5>

                        <ul class="nav nav-tabs" id="myTab">
                            <li class="nav-item">
                                <a class="nav-link active" id="home-tab" data-toggle="tab" data-target="#about"
                                   role="tab"
                                   href=""><label><fmt:message key="user_profile.about"/></label></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="profile-tab" data-toggle="tab" data-target="#history" href=""
                                   role="tab">
                                    <label><fmt:message key="user_profile.order_history"/></label></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="submit" name="command" value="EDIT_PROFILE_PAGE" class="btn profile-edit-btn">
                        <fmt:message key="user_profile.edit_profile"/>
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="profile-work">
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">

                        <div class="tab-pane fade show active" id="about" role="tabpanel" aria-labelledby="home-tab">
                            <div class="row">
                                <div class="col-md-6">
                                    <label><fmt:message key="user_profile.name"/></label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.name}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label><fmt:message key="user_profile.email"/></label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.email}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label><fmt:message key="user_profile.card_number"/></label>
                                </div>
                                <div class="col-md-6">
                                    <c:if test="${user.cardAccount == null or user.cardAccount.cardNumber == null}">
                                        <p><fmt:message key="user_profile.empty_card"/></p>
                                        <button type="submit" name="command" value="ADD_CARD_PAGE"
                                                class="btn profile-edit-btn">
                                            <fmt:message key="user_profile.attach"/>
                                        </button>
                                    </c:if>
                                    <c:if test="${user.cardAccount != null or user.cardAccount.cardNumber != null}">
                                        <p>${user.cardAccount.cardNumber}</p>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${user.cardAccount != null and user.cardAccount.cardNumber != null}">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label><fmt:message key="user_profile.amount"/></label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${user.cardAccount.amount}</p>
                                        <button type="submit" name="command" value="EDIT_CARD_PAGE"
                                                class="btn profile-edit-btn">
                                            <fmt:message key="user_profile.edit_card"/>
                                        </button>
                                        <button type="submit" name="command" value="ADD_MONEY_PAGE"
                                                class="btn profile-edit-btn">
                                            <fmt:message key="user_profile.add_money"/>
                                        </button>
                                    </div>
                                </div>
                            </c:if>
                        </div>


                        <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="profile-tab">
                            <c:if test="${history == null or empty history}">
                                <p><fmt:message key="user_profile.empty_history"/></p>
                            </c:if>
                            <c:if test="${not empty history}">
                                <table class="table">
                                    <thead class="thead ">
                                    <tr>
                                        <th scope="col"><fmt:message key="user_profile.drink_title"/></th>
                                        <th scope="col"><fmt:message key="user_profile.volume"/></th>
                                        <th scope="col"><fmt:message key="user_profile.price"/></th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="drink" items="${history}">
                                        <tr>
                                            <td>${drink.title}</td>
                                            <td><fmt:message key="drink_management.${drink.drinkSize}"/></td>
                                            <td>${drink.price}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- SCRIPTS -->

</body>
</html>
