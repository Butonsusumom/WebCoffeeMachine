<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="stylesheets/footer.css" %>
    </style>
    <title>Title</title>
</head>
<body>
<footer>
    <form action="" id="local">
        <button type="submit" name="locale" value="ua"><img class="loc" src="<c:url value="/img/ua.png"/>" alt="ua">
        </button>
        <button type="submit" name="locale" value="en"><img class="loc" src="<c:url value="/img/en.png"/>" alt="en">
        </button>
        <button type="submit" name="locale" value="ru"><img class="loc" src="<c:url value="/img/ru.png"/>" alt="ru">
        </button>
    </form>
</footer>
</body>
</html>