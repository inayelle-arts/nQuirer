<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<sys:set var="tests" value="${requestScope.tests}"/>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${CSS}/core.css" type="text/css">

    <title>nQuirer</title>
</head>
<body>

<header style="margin-top: 1%">
    <div class="container">
        <div class="row align-items-center justify-content-between">
            <div class="col">
                <a href="/"><h1 class="text-dark">nQuirer</h1></a>
            </div>
            <div class="col" id="sign-in-up">
                <div class="btn-group">
                    <a class="btn btn-primary" href="#">
                        Sign in
                    </a>
                    <a class="btn btn-secondary" href="#">
                        Sign up
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>

<div id="content" style="margin-top: 3%">

    <div class="container">
        <div class="row">

            <div class="col-4">
                <h2 class="text-center" style="margin-bottom: 5%; ">Available tests</h2>
                <ul class="list-group">
                    <li class="create-button list-group-item" style="padding: 0;">
                        <a href="/create" class="d-block w-100" style="padding: 0.75rem 1.25rem;">Create test</a>
                    </li>
                    <sys:forEach items="${tests}" var="test">
                        <li class="list-group-item" id="test-item-${test.id}" data-description="${test.description}"
                            data-id="${test.id}">${test.title}</li>
                    </sys:forEach>
                </ul>
            </div>
            <div class="col-8">
                <div id="invite" class="w-100 text-center">
                    <h4>Choose one of existing tests or create your own!</h4>
                </div>
                <div class="row">
                    <div class="col-7">
                        <div id="test-info">
                            <div id="test-descriptor">
                                <h4 id="test-descriptor-title" class="text-left"></h4>
                                <h5 id="test-descriptor-description" class="text-left text-muted"></h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-5">
                        <a href="#" class="btn btn-primary w-50" style="float: right; display: none;" id="go-test">Test
                            your might!</a>
                    </div>
                </div>

            </div>

        </div>
    </div>


</div>


<script type="text/javascript" src="${JS}/jquery-3.3.1.js" defer></script>
<script type="text/javascript" src="${JS}/common.js" defer></script>
<script type="text/javascript" src="${JS}/index_bundle.js" defer></script>
</body>
</html>