<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ph" uri="/WEB-INF/customTags/pageHelper.tld" %>

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
        <c:if test="${fn:length(requestScope.tracks)eq 0}">
            <h2>There are not available tracks now</h2>
        </c:if>
        <c:if test="${sessionScope.user.role.value eq 'admin'}">
            <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addTrack'">
                <div class="small add-icon"></div>
                <p class="b-text"><fmt:message key="newTrack"/></p>
            </div>
        </c:if>
        <c:if test="${fn:length(requestScope.tracks)ne 0}">
            <table class="table">
                <c:if test="${sessionScope.user.role.value eq 'client'}">
                    <caption><fmt:message key="pageTitle"/></caption>
                </c:if>
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

                <ph:pagination items="${requestScope.tracks}" var="track" perPage="15" >
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
                            <td><fmt:formatNumber value="${track.price}" type="currency" currencySymbol="$"/></td>
                            <td class="manage-buttons">
                                <c:if test="${sessionScope.user.role.value eq 'client'}">
                                    <div class="icon-button " onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=orderTrack&trackId=${track.id}'">
                                        <div class="small order-icon"></div>
                                        <p class="b-text"><fmt:message key="order"/></p>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.user.role.value eq 'admin' and not empty param.audioSetId}">
                                    <div class="icon-button " onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addToSet&trackId=${track.id}&audioSetId=${param.audioSetId}'">
                                        <div class="small add-icon"></div>
                                        <p class="b-text"><fmt:message key="addToSet"/></p>
                                    </div>
                                </c:if>
                                <div class="icon-button " onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=showComments&trackId=${track.id}'">
                                    <div class="small comments-icon"></div>
                                    <p class="b-text"><fmt:message key="comments"/></p>
                                </div>
                            </td>
                        </tr>
                </ph:pagination>
                </tbody>
            </table>
            <c:if test="${not empty pageScope.prevHref}">
                <a class="prev-next" href="${pageScope.prevHref}"><fmt:message key="prevPage"/></a>
            </c:if>
            <c:if test="${not empty pageScope.nextHref}">
                <a class="prev-next" href="${pageScope.nextHref}"><fmt:message key="nextPage"/></a>
            </c:if>
        </c:if>
    </div>
    </body>
</html>
</fmt:bundle>