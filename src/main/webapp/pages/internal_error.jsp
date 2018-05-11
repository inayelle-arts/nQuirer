<%@ page contentType="text/html" isErrorPage="true" pageEncoding="utf-8" %>
<%@ taglib prefix="sys" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sys:set var="CSS" value="${pageContext.request.contextPath}/resources/css"/>
<sys:set var="IMG" value="${pageContext.request.contextPath}/resources/img"/>
<sys:set var="JS" value="${pageContext.request.contextPath}/resources/js"/>

<!DOCTYPE HTML>
<html>
<head>
    <title>Internal error</title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="shortcut icon" href="${IMG}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${CSS}/styles.css"/>
    <script type="text/javascript" src="${JS}/js.js"></script>
</head>
<body>

<div class="wrapper">
    <h1>
        We are sorry if you see this page.
        Unfortunatly, server processing was interrupted by unhandled exception.
    </h1>
    <h2>Info for administrators</h2>
    <div>
        Error message:
        <%
            exception.getMessage();
        %>
        Stacktrace:
        <%
            exception.printStackTrace();
        %>
    </div>
</div>

</body>
</html>
