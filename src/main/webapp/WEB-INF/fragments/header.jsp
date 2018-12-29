<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="pagecontent" prefix ="header.">

<html>
<head>
    <style><jsp:include page = "/css/header-fixed.css"/></style>
</head>

<body>
    <header class="header-fixed">
        <div class="header-limiter">
            <h1><a href="${pageContext.servletContext.contextPath}/controller?command=home">Audio<span>Spot</span></a></h1>
            <nav>
                    <c:if test="${sessionScope.user.role ne null}">
                        <a href="#" class = "selected">${sessionScope.user.login}</a>
                    </c:if>

                    <%--??????????--%>


                    <a href="${pageContext.servletContext.contextPath}/controller?command=changeLang&lang=en"><fmt:message key="en"/></a>
                    <a class = left-lined href="${pageContext.servletContext.contextPath}/controller?command=changeLang&lang=ru"><fmt:message key="ru"/></a>

                    <c:if test="${sessionScope.user.role ne null}">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=logout"><fmt:message key="logOut"/></a>
                    </c:if>
            </nav>
        </div>
    </header>

    <!-- Main container -->
    <c:if test="${sessionScope.user.role.value ne null}">
    <div class="container">
        <!-- Menu -->
        <nav id="ml-menu" class="menu">
            <div class="menu_wrap">
                <c:if test="${sessionScope.user.role.value eq 'admin'}">
                    <ul data-menu="main" class="menu_level">
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=addTrack"><fmt:message key="addTrack"/></a></li>
                        <%--<li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=addAlbum"><fmt:message key="addAlbum"/></a></li>--%>
                        <%--<li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylists"><fmt:message key="audioSets"/></a></li>--%>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showClients"><fmt:message key="clients"/></a></li>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.user.role.value eq 'client'}">
                    <ul data-menu="main" class="menu_level">
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylist"><fmt:message key="myPlaylist"/></a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=buyTracks"><fmt:message key="buyNewTrack"/></a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=payOrder"><fmt:message key="PayOrder"/></a></li>
                    </ul>
                </c:if>
            </div>
        </nav>
    </div>
    </c:if>
</body>
</html>
</fmt:bundle>