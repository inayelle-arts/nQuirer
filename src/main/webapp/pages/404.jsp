<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<sys:set var="page" value="${requestScope.page}"/>

<!DOCTYPE HTML>
<html>
<head>
    <title>Page not found :c</title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="${IMG}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${CSS}/styles.css">
    <script type="text/javascript" src="${JS}/js.js"></script>
</head>

<body>

<div class="wrapper">

    <h1>404 Page not found :c</h1>
    ${page} does not exist

</div>

</body>

</html>
