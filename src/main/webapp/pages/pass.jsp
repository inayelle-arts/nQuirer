<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<sys:set var="test" value="${requestScope.test}"/>
<sys:set var="not_found" value="${requestScope.not_found}"/>

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
                <h2 class="text-center" style="margin-bottom: 5%; ">
                    <sys:if test="${not_found == false}">
                        ${test.title}
                    </sys:if>
                </h2>

                <button id="end-test-button" class="btn btn-info w-100" type="submit" form="test-pass-form">
                    End test
                </button>

                <h5 class="w-100 text-center" style="margin-top: 30px">Questions</h5>
                <ul class="list-group" id="question-list">


                    <sys:choose>
                        <sys:when test="${not_found == true}">

                            <li class="list-group-item">
                                <h5 class="question-text">Test not found</h5>
                            </li>

                        </sys:when>

                        <sys:otherwise>

                            <sys:forEach items="${test.questions}" var="question">

                                <li class="list-group-item" id="question-side-${question.id}" data-qid="${question.id}">
                                    <h5 class="question-text">${question.content}</h5>
                                    <h6 class="text-muted question-number">Q${question.id}</h6>
                                </li>

                            </sys:forEach>

                        </sys:otherwise>

                    </sys:choose>

                </ul>
            </div>

            <div class="col-8">
                <form action="/check" method="post" data-tid="1" id="test-pass-form" class="no-user-select"
                      style="margin-left: 5%; margin-top: 2.5%;">

                    <input type="text" name="json" hidden/>

                    <sys:if test="${not_found == false}">

                        <sys:forEach var="question" items="${test.questions}">


                            <div id="question-${question.id}" data-qid="${question.id}" style="display: none">

                                <h4 id="question-${question.id}-text" class="w-100 text-center">${question.content}</h4>
                                <ul class="list-group">

                                    <sys:forEach var="answer" items="${question.answers}">

                                        <li class="list-group-item form-group"
                                            id="question-${question.id}-answer-${answer.id}" data-aid="${answer.id}"
                                            data-correct="false">
                                            <div class="answer-text">
                                                    ${answer.content}
                                            </div>
                                            <div class="answer-number">
                                                A${answer.id}
                                            </div>
                                        </li>

                                    </sys:forEach>

                                </ul>
                            </div>

                        </sys:forEach>


                    </sys:if>

                </form>
                <button class="btn btn-primary w-100" id="add-answer-button" type="button" style="display: none;">Add
                    answer
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="${JS}/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${JS}/pass_bundle.js"></script>
</body>
</html>