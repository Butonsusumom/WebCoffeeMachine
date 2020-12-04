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
    <title><fmt:message key="user_edit.title"/></title>
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
        body,
        header
        .view {
            height: 50%;
            /*top: 150px;*/
            background: linear-gradient(#000000,#434343);
        }


        .form-act {
            width: 60%;
            margin: 60px auto;
            padding: 60px 120px 80px 120px;
            /*text-align: center;*/
            -webkit-box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
            box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
        }

    </style>

</head>
<body >
<!-- Navbar -->
<%@include file="../jspheader/nav.jsp" %>
<!-- Navbar -->
<div class =" form-act" style="background-color: white;align-items: center;">
    <h5><fmt:message key="user_edit.name"/>: ${userProfile.name}</h5>
    <h5><fmt:message key="user_edit.email"/>: ${userProfile.email}</h5>
    <c:if test="${userProfile.activity.equals(true)}">

        <form action="coffee_machine" method="post">
            <input type="hidden" id="BlockUserID" name="userId" value="${userProfile.id}">
            <button type="submit" name="command" value="BLOCK_USER"
                    class="btn btn-outline-danger">
                <fmt:message key="user_edit.block"/>
            </button>
        </form>

    </c:if>

    <c:if test="${!userProfile.activity.equals(true)}">

        <form action="coffee_machine" method="post">
            <input type="hidden" id="UnblockUserID" name="userId" value="${userProfile.id}">
            <button type="submit" name="command" value="UNBLOCK_USER"
                    class="btn btn-outline-success">
                <fmt:message key="user_edit.unblock"/>
            </button>
        </form>

    </c:if>
</div>
</body>
</html>
