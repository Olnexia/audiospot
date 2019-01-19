<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ph" uri="/WEB-INF/customTags/pageHelper.tld" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="albums.">
    <html>
    <head>
        <title><fmt:message key="pageTitle"/></title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/block.css"/></style>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <c:if test="${sessionScope.user.role.value eq 'admin'}">
            <div class = "block" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addAlbum'">
                <div class="poster add-new">
                </div>
                <div>
                    <fmt:message key="addNew"/>
                </div>
            </div>
        </c:if>

        <ph:pagination items="${albums}" var="album" perPage="20">
            <div class = "block" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=viewAlbum&albumId=${album.id}'">
                <div class="poster album">
                </div>
                <div>
                    <a>${album.title}</a>
                    <span>${album.artist.name}</span>
                    <span>${album.releaseYear}</span>
                </div>
            </div>
        </ph:pagination>

        <c:if test="${not empty pageScope.prevHref}">
            <a class="prev-next" href="${pageScope.prevHref}"><fmt:message key="prevPage"/></a>
        </c:if>
        <c:if test="${not empty pageScope.nextHref}">
            <a class="prev-next" href="${pageScope.nextHref}"><fmt:message key="nextPage"/></a>
        </c:if>
    </div>
    </body>
    </html>
</fmt:bundle>
