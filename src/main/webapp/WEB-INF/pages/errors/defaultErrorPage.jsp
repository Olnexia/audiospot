<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="errorPage.">
    <html>
    <head>
        <title><fmt:message key="pageTitle"/></title>
        <style><jsp:include page = "/css/error.css"/></style>
        <jsp:include page = "../../fragments/header.jsp"/>
    </head>
    <body>
    <div class = "content">
        <div class = "error">
            <h1><fmt:message key="errorMessage"/></h1>
            <h2>${requestScope['javax.servlet.error.message']}</h2>
        </div>
        <div class ="error-img"></div>
    </div>
    </body>
    </html>
</fmt:bundle>
