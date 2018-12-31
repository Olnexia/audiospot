<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="playlist.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <jsp:include page = "../fragments/header.jsp"/>
    <style><jsp:include page = "/css/table.css"/></style>
</head>
    <body>
        <div class="content">
            <c:if test="${fn:length(tracks)ne 0}">
                <table class="table">
                    <caption><fmt:message key="pageTitle"/></caption>
                    <thead>
                    <tr>
                        <th><fmt:message key="artist"/></th>
                        <th><fmt:message key="title"/></th>
                        <th><fmt:message key="year"/></th>
                        <th><fmt:message key="genre"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tracks}" var="track"  varStatus="status">
                        <tr>
                            <td>${track.artist.name}</td>
                            <td>${track.title}</td>
                            <td>${track.releaseYear}</td>

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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
    </body>
</html>
</fmt:bundle>
