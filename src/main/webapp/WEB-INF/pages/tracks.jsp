<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="ordering.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <style><jsp:include page = "/css/table.css"/></style>
    <style><jsp:include page="/css/icon-button.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
    <body>
    <div class="content">
        <c:if test="${fn:length(tracks)eq 0}">
            <h2>There are not available tracks now</h2>
        </c:if>
        <c:if test="${fn:length(tracks)ne 0}">
            <table class="table">
                <caption><fmt:message key="pageTitle"/></caption>
                <thead>
                <tr>
                    <th><fmt:message key="artist"/></th>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="genre"/></th>
                    <th><fmt:message key="price"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tracks}" var="track"  varStatus="status">
                    <tr>
                        <td>${track.artist.name}</td>
                        <td>${track.title}</td>

                        <c:choose>
                            <c:when test = "${track.genre.value eq 'rock'}">
                                <td><fmt:message key="rock"/></td>
                            </c:when>
                            <c:when test = "${track.genre.value eq 'rap'}">
                                <td><fmt:message key="rap"/></td>
                            </c:when>
                            <c:when test = "${track.genre.value eq 'classic'}">
                                <td><fmt:message key="classic"/></td>
                            </c:when>
                            <c:when test = "${track.genre.value eq 'pop'}">
                                <td><fmt:message key="pop"/></td>
                            </c:when>
                        </c:choose>
                        <td>${track.price}</td>
                        <td class="manage-buttons">
                            <c:if test="${sessionScope.user.role.value eq 'client'}">
                            <div class="icon-button " onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=orderTrack&trackId=${track.id}'">
                                <div class="small order-icon"></div>
                                <p class="b-text"><fmt:message key="order"/></p>
                            </div>
                            </c:if>
                            <div class="icon-button " onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=showComments&trackId=${track.id}'">
                                <div class="small comments-icon"></div>
                                <p class="b-text"><fmt:message key="comments"/></p>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    </body>
</html>
</fmt:bundle>