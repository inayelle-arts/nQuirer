<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>
<sys:set var="tests" value="${requestScope.tests}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="shortcut icon" href="${IMG}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${CSS}/styles.css" type="text/css"/>
    <script type="text/javascript" src="${JS}/handlers.js"></script>
</head>
<body>

<div class="container">
    <div class="row align-items-center top-margin-5">
        <div class="col-6">
            <h1>
                <a class="a-no-decoration" href="index.jsp">nQuirer</a>
            </h1>
        </div>
        <div class="col-6">
            <div class="logins">
                <a class="btn btn-primary" href="signin.html">Sign in</a>
                <a class="btn btn-secondary" href="signup.html">Sign up</a>
            </div>
        </div>
    </div>

    <div class="row align-items-center top-margin-5">
        <div class="col">
            <header class="text-align-center">
                <h2>Select test... or create your own!</h2>
            </header>
        </div>
    </div>

    <div class="row top-margin-5">
        <div class="col-4">
            <a class="btn btn-light w-100" href="create.html">
                Create new
            </a>
            <ul class="list-group" id="test-list">

                <sys:forEach var="item" items="${tests}">
                    <li class="list-group-item">
                        <h5><sys:out value="${item.name}"/></h5>
                        <sys:out value="${item.description}"/>
                    </li>
                </sys:forEach>

            </ul>
        </div>

        <div class="col-8">
            <div>
                <h5 id="test-title"></h5>
                <h6 id="test-descr"></h6>
                <a class="btn btn-primary" href="#">Test your might!</a>
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = function () {
        test();
    };
</script>
</body>
</html>