<%@ page language = "java" contentType = "text/html;charset=utf-8"
    isELIgnored ="false"
    pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>AudioSpot</h1>

<c:if test="${sessionScope.user.role eq 'admin'}">
    <a href="${pageContext.servletContext.contextPath}/controller?command=addTrack">Add new track</a>
    <a href="${pageContext.servletContext.contextPath}/controller?command=addAlbum">Add new album</a>
    <a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylis ts">Playlists</a>
    <a href="${pageContext.servletContext.contextPath}/controller?command=showClients">Clients</a>

</c:if>

<c:if test="${sessionScope.user.role eq 'client'}">
    <a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylist">My playlist</a>
    <a href="${pageContext.servletContext.contextPath}/controller?command=buyTracks">Buy new tracks</a>
</c:if>