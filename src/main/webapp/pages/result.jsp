<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<sys:set var="user_points" value="${requestScope.user_points}"/>
<sys:set var="max_points" value="${requestScope.max_points}"/>
<sys:set var="test" value="${requestScope.test}"/>
<sys:set var="percentage"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                            value="${user_points/max_points*100.00}"/></sys:set>

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
                <h2 class="text-center" style="margin-bottom: 5%; ">Getting results...</h2>
                <h3 class="text-center" style="margin-bottom: 5%">${percentage} %</h3>
                <h5 class="w-100 text-center" style="margin-top: 30px">Questions</h5>
                <ul class="list-group" id="question-list">

                    <sys:forEach items="${test.questions}" var="question">

                        <li class="list-group-item" id="question-side-${question.id}" data-qid="${question.id}">
                            <h5 class="question-text">${question.content}</h5>
                            <h6 class="text-muted question-number">Q${question.id}</h6>
                        </li>

                    </sys:forEach>

                </ul>
            </div>

            <div class="col-8">
                <form action="#" data-tid="1" id="test-result-form" class="no-user-select non-clickable"
                      style="margin-left: 5%; margin-top: 2.5%;">

                    <sys:forEach var="question" items="${test.questions}">

                        <div id="question-${question.id}" data-qid="${question.id}" style="display: none">

                            <h4 id="question-${question.id}-text" class="w-100 text-center">${question.content}</h4>
                            <ul class="list-group">

                                <sys:forEach var="answer" items="${question.answers}">

                                    <li class="list-group-item form-group"
                                        id="question-${question.id}-answer-${answer.id}" data-aid="${answer.id}"
                                        data-correct="${answer.isCorrect}" data-user-correct="${answer.isUserCorrect}">
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


                    <%--<div id="question-1" data-qid="1" style="display: none">--%>

                    <%--<h4 id="question-1-text" class="w-100 text-center">Question 1 text</h4>--%>
                    <%--<ul class="list-group">--%>

                    <%--<li class="list-group-item form-group" id="question-1-answer-1" data-aid="1"--%>
                    <%--data-correct="true" data-user-correct="false">--%>
                    <%--<div class="answer-text">--%>
                    <%--Answer 1--%>
                    <%--</div>--%>
                    <%--<div class="answer-number">--%>
                    <%--A1--%>
                    <%--</div>--%>
                    <%--</li>--%>

                    <%--<li class="form-group list-group-item" id="question-1-answer-2" data-aid="2"--%>
                    <%--data-correct="false" data-user-correct="true">--%>
                    <%--<div class="answer-text">--%>
                    <%--Answer 2--%>
                    <%--</div>--%>
                    <%--<div class="answer-number">--%>
                    <%--A2--%>
                    <%--</div>--%>
                    <%--</li>--%>

                    <%--</ul>--%>
                    <%--</div>--%>

                    <%--<div id="question-2" data-qid="2" style="display: none">--%>

                    <%--<h4 id="question-2-text" class="w-100 text-center">Question 2 text</h4>--%>
                    <%--<ul class="list-group">--%>

                    <%--<li class="list-group-item form-group" id="question-2-answer-1" data-aid="1"--%>
                    <%--data-correct="true" data-user-correct="true">--%>
                    <%--<div class="answer-text">--%>
                    <%--Answer 1--%>
                    <%--</div>--%>
                    <%--<div class="answer-number">--%>
                    <%--A1--%>
                    <%--</div>--%>
                    <%--</li>--%>

                    <%--<li class="form-group list-group-item" id="question-2-answer-2" data-aid="2"--%>
                    <%--data-correct="false" data-user-correct="false">--%>
                    <%--<div class="answer-text">--%>
                    <%--Answer 2--%>
                    <%--</div>--%>
                    <%--<div class="answer-number">--%>
                    <%--A2--%>
                    <%--</div>--%>
                    <%--</li>--%>

                    <%--</ul>--%>
                    <%--</div>--%>


                </form>
                <button class="btn btn-primary w-100" id="add-answer-button" type="button" style="display: none;">Add
                    answer
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="${JS}/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${JS}/result_bundle.js"></script>
</body>
</html>