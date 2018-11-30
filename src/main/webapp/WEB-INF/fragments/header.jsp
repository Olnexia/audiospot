<%@ page language = "java" contentType = "text/html;charset=utf-8"
    isELIgnored ="false"
    pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>AudioSpot</h1>
<a href="${pageContext.servletContext.contextPath}/controller?command=showPlaylists">Playlists</a>
    <a href="${pageContext.servletContext.contextPath}/controller?command=buyTrack">Buy track</a>