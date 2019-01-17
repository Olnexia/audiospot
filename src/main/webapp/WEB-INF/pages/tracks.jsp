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
        <c:if test="${fn:length(tracks)eq 0}">
            <h2>There are not available tracks now</h2>
        </c:if>
        <c:if test="${sessionScope.user.role.value eq 'admin'}">
            <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addTrack'">
                <div class="small add-icon"></div>
                <p class="b-text"><fmt:message key="newTrack"/></p>
            </div>
        </c:if>
        <c:if test="${fn:length(tracks)ne 0}">
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
                <c:set var="tracksPerPage" value="10"/>
                <c:set var="page" value="${not empty param.page and param.page>0?param.page:1}"/>
                <c:forEach items="${tracks}" begin="${page*tracksPerPage-tracksPerPage}" end="${page*tracksPerPage}" var="track"  varStatus="status">
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
                    <%--</c:if>--%>
                </c:forEach>
                </tbody>
            </table>
            <fmt:message key="prevPage" var="prev" />
            <fmt:message key="nextPage" var="next" />
            <ph:pagination entryAmount="${fn:length(tracks)}" amountByPage="${tracksPerPage}"
            prevText= "${prev}" nextText="${next}" styleClass="prev-next"/>
        </c:if>
    </div>
    </body>
</html>
</fmt:bundle>