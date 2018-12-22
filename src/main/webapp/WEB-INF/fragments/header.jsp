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
            <h1><a href="#">Audio<span>Spot</span></a></h1>
            <nav>
                <%--<form>--%>
                    <%--<select id="language" name="language" onchange="submit()">--%>
                        <%--<option value="en_US" ${language == 'en_US' ? 'selected' : ''} selected>English</option>--%>
                        <%--<option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Russian</option>--%>
                    <%--</select>--%>
                <%--</form>--%>

                <c:if test="${sessionScope.user.role ne null}">
                    <a href="#">${sessionScope.user.login}</a>
                    <a href="#" class="selected">Balance: ${sessionScope.user.account}</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=logout">Log out</a>
                </c:if>
                    <li><a href="?language=en">en</a></li>
                    <li><a href="?language=ru">ru</a></li>
            </nav>
        </div>
    </header>

    <!-- Main container -->
    <div class="container">
        <!-- Menu -->
        <nav id="ml-menu" class="menu">
            <div class="menu__wrap">
                <c:if test="${sessionScope.user.role.value eq 'admin'}">
                    <ul data-menu="main" class="menu__level">
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=addTrack">Add new track</a></li>
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=addAlbum">Add new album</a></li>
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylists">Playlists</a></li>
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=showClients">Clients</a></li>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.user.role.value eq 'client'}">
                    <ul data-menu="main" class="menu__level">
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylist">My playlist</a></li>
                        <li class="menu__item"><a href="${pageContext.servletContext.contextPath}/controller?command=buyTracks">Buy new tracks</a></li>
                    </ul>
                </c:if>
            </div>
        </nav>
    </div>
</body>
</html>