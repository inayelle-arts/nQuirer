<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${CSS}/core.css" type="text/css">

    <title>nQuirer</title>

    <style>
        label
        {
            font-weight : bold;
        }
    </style>
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
                    <a class="btn btn-primary" href="/signin.php">
                        Sign in
                    </a>
                    <a class="btn btn-secondary" href="/signup.php">
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
                <h2 class="text-center" style="margin-bottom: 5%; ">Creating test...</h2>

                <div class="btn-group" id="test-dos-group">
                    <button id="change-description-button">
                        Change description
                    </button>
                    <button id="add-question-button">
                        Add question
                    </button>
                </div>
                <button id="submit-test">Submit test</button>
                <h5 class="w-100 text-center" style="margin-top: 30px">Your questions</h5>
                <small class="text-muted w-100 text-center" id="zero-questions-helper" style="display: block"></small>
                <ul class="list-group" id="question-list">


                </ul>
            </div>
            <div class="col-8">
                <div id="invite" class="w-100 text-center">
                    <h4>Title your test and add your first question!</h4>
                </div>

                <form action="#" method="post" id="test-form">

                    <div id="test-info-block">

                        <div class="form-group">
                            <label for="test-title">Test title</label>
                            <input type="text" name="title" id="test-title" class="form-control" placeholder="Title..."
                                   aria-describedby="test-title-helper">
                            <small id="test-title-helper" class="form-text text-muted"></small>
                        </div>
                        <div class="form-group">
                            <label for="test-description">Test description</label>
                            <input type="text" name="description" id="test-description" class="form-control"
                                   placeholder="Description...">
                        </div>

                    </div>

                </form>
                <button class="btn btn-primary w-100" id="add-answer-button" type="button" style="display: none;">Add
                    answer
                </button>
            </div>

        </div>
    </div>


</div>


<script type="text/javascript" src="${JS}/jquery-3.3.1.js" defer></script>
<script type="text/javascript" src="${JS}/common.js" defer></script>
<script type="text/javascript" src="${JS}/create_bundle.js" defer></script>
</body>
</html>