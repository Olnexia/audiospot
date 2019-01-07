<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="albums.">
    <html>
    <head>
        <title><fmt:message key="pageTitle"/></title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/album.css"/></style>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <c:if test="${sessionScope.user.role.value eq 'admin'}">
            <div class = "album" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addAlbum'">
                <div class="poster add-new">
                </div>
                <div>
                    <fmt:message key="addNew"/>
                </div>
            </div>
        </c:if>
        <c:forEach items="${albums}" var="album" varStatus="status">
            <div class = "album" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=viewAlbum&albumId=${album.id}'">
                <div class="poster">
                </div>
                <div>
                    <a>${album.title}</a>
                    <span>${album.artist.name}</span>
                    <span>${album.releaseYear}</span>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
    </html>
</fmt:bundle>
