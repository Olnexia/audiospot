<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="audioSetView.">
    <html>
    <head>
        <title>${audioSet.title}</title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/block.css"/></style>
        <style><jsp:include page = "/css/album.css"/></style>
        <style><jsp:include page = "/css/icon-button.css"/></style>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <div class="album-header">
            <div class="poster set">
            </div>
            <div class="album-info">
                <h1>${audioSet.title}</h1>
                <p>${audioSet.description}</p>
            </div >

            <c:if test="${sessionScope.user.role.value eq 'admin'}">
                <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addTracks&audioSetId=${audioSet.id}'">
                    <div class="medium add-icon"></div>
                    <p class="b-text"><fmt:message key="addTracks"/></p>
                </div>
            </c:if>
            <%--<c:if test="${sessionScope.user.role.value eq 'client'}">--%>
                <%--<div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=orderAudioSet&albumId=${audioSet.id}'">--%>
                    <%--<div class="medium order-icon"></div>--%>
                    <%--<p class="b-text"><fmt:message key="order"/></p>--%>
                <%--</div>--%>
            <%--</c:if>--%>
        </div>

        <c:if test="${fn:length(tracks)ne 0}">
            <table class="table">
                <caption><fmt:message key="tracks"/></caption>
                <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="price"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tracks}" var="track"  varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${track.title}</td>
                        <td><fmt:formatNumber value="${track.price}" type="currency" currencySymbol="$"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    </body>
    </html>
</fmt:bundle>
