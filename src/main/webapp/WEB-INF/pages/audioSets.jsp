<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="audioSets.">
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
            <div class = "block" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addAudioSet'">
                <div class="poster add-new">
                </div>
                <div>
                    <fmt:message key="addNew"/>
                </div>
            </div>
        </c:if>
        <c:forEach items="${audioSets}" var="audioSet" varStatus="status">
            <div class = "block" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=viewAudioSet&audioSetId=${audioSet.id}'">
                <div class="poster set">
                </div>
                <div>
                    <a>${audioSet.title}</a>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
    </html>
</fmt:bundle>
