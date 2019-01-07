<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="albumView.">
    <html>
    <head>
        <title><fmt:message key="title"/></title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/album.css"/></style>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <div>
            <h1>${album.title}</h1>
        </div>
        <div>
            <span>${album.artist.name}</span>
            <span>${album.releaseYear}</span>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
