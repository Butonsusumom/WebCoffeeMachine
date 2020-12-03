<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<style type="text/css">
    .dropdown-menu{
        width: 50px;
        background-color: #464343;
    }
    .dropdown-item{
        width: 50px;
        background-color: #464343;
        color: white;
    }
</style>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar">
    <div class="container">

        <!-- Brand -->
        <a class="navbar-brand text-white" href="coffee_machine?command=INDEX_PAGE">
            <strong><fmt:message key="nav_bar.brand"/> </strong>
        </a>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="coffee_machine?command=INDEX_PAGE">
                        <fmt:message key="nav_bar.home"/>
                    </a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="coffee_machine?command=DRINK_LIST">
                        <fmt:message key="nav_bar.drinks"/>
                    </a>
                </li>
                <c:if test="${user != null}">
                    <li class="nav-item ">
                        <a class="nav-link" href="coffee_machine?command=CART_PAGE">
                            <fmt:message key="nav_bar.cart"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <li class="nav-item ">
                        <a class="nav-link" href="coffee_machine?command=USER_LIST">
                            <fmt:message key="nav_bar.user_list"/>
                        </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="coffee_machine?command=DRINK_MANAGEMENT">
                            <fmt:message key="nav_bar.drink_management"/>
                        </a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ml-md-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="nav_bar.language"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <form action="coffee_machine" method="get">
                            <button type="submit" class="dropdown-item" name="language"
                                    value="ru">
                                Ру
                            </button>
                        </form>
                        <form action="coffee_machine" method="get">
                            <button type="submit" class="dropdown-item" name="language"
                                    value="en">
                                En
                            </button>
                        </form>
                    </div>
                </li>
                <%--                <li class="nav-item">--%>
                <%--                    <form action="coffee_machine" method="get">--%>
                <%--                        <button type="submit" class="nav-link btn-outline-white mr-1 ml-1" name="language"--%>
                <%--                                value="ru">--%>
                <%--                            Ru--%>
                <%--                        </button>--%>
                <%--                    </form>--%>
                <%--                </li>--%>
                <%--                <li class="nav-item">--%>
                <%--                    <form action="coffee_machine" method="get">--%>
                <%--                        <button type="submit" class="nav-link btn-outline-white mr-1 ml-1" name="language"--%>
                <%--                                value="en">--%>
                <%--                            En--%>
                <%--                        </button>--%>
                <%--                    </form>--%>
                <%--                </li>--%>
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="coffee_machine?command=USER_PROFILE">
                                ${user.name}
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1" href="coffee_machine?command=SIGN_OUT">
                            <fmt:message key="nav_bar.sign_out"/>
                        </a>
                    </li>
                </c:if>


                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1" href="coffee_machine?command=SIGN_IN_PAGE">
                            <fmt:message key="nav_bar.sign_in"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1"
                           href="coffee_machine?command=REGISTRATION_PAGE">
                            <fmt:message key="nav_bar.sign_up"/>
                        </a>
                    </li>
                </c:if>
            </ul>

            <!-- Collapse -->
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>

</nav>

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();
</script>
