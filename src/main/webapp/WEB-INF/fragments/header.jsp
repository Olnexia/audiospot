<%@ page language = "java" contentType = "text/html;charset=utf-8"
    isELIgnored ="false"
    pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <style><jsp:include page = "/css/header-fixed.css"/></style>
    <script><jsp:include page = "../js/modal.js"/></script>
</head>

<body>
    <header class="header-fixed">
        <div class="header-limiter">
            <h1><a href="${pageContext.servletContext.contextPath}/controller?command=home">Audio<span>Spot</span></a></h1>
            <nav>
                <%--<form>--%>
                    <%--<select id="language" name="language" onchange="submit()">--%>
                        <%--<option value="en_US" ${language == 'en_US' ? 'selected' : ''} selected>English</option>--%>
                        <%--<option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Russian</option>--%>
                    <%--</select>--%>
                <%--</form>--%>
                    <c:if test="${sessionScope.user.role ne null}">
                        <a href="#" class = "selected">${sessionScope.user.login}</a>
                    </c:if>

                    <%--??????????--%>

                    <a href="${pageContext.request.requestURL}/?language=en">en</a>
                    <a class = left-lined href="${pageContext.servletContext.contextPath}/?language=ru">ru</a>

                    <c:if test="${sessionScope.user.role ne null}">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=logout">Log out</a>
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
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=addTrack">Add new track</a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=addAlbum">Add new album</a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylists">Playlists</a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showClients">Clients</a></li>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.user.role.value eq 'client'}">
                    <ul data-menu="main" class="menu_level">
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylist">My playlist</a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=buyTracks">Buy new tracks</a></li>
                        <li class="menu_item"><a href="${pageContext.servletContext.contextPath}/controller?command=payOrder">Pay an order</a></li>
                    </ul>
                </c:if>
            </div>
        </nav>
    </div>
    </c:if>
</body>
</html>