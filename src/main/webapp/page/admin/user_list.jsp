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
    <title><fmt:message key="user_list.title"/></title>
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
            /*top: 150px;*/
            background: linear-gradient(#000000,#434343);
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


<%--<div class="view">--%>
<form class="form-active">
    <div>
        <table class="table">
            <thead class="thead ">
            <tr>
                <th scope="col"><fmt:message key="user_list.name"/></th>
                <th scope="col"><fmt:message key="user_list.role"/></th>
                <th scope="col"><fmt:message key="user_list.activity"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <form action="coffee_machine" method="get">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <td>${user.name}</td>
                        <td>${user.role}</td>
                        <td><fmt:message key="user_list.${user.activity}"/></td>
                        <td>
                            <button type="submit" class="btn" name="command" value="USER_EDIT">
                                <fmt:message key="user_list.edit_user"/>
                            </button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</form>



</body>

</html>

