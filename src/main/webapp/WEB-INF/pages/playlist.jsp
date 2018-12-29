<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<html>
<head>
    <title>My playlist</title>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <h2>Playlist</h2>
    <table>
        <c:forEach items="${tracks}" var="track"  varStatus="status">
            <tr>
                <td>${track.artist.name}</td>
                <td>${track.title}</td>
                <td>${track.releaseYear}</td>
                <td>${track.genre.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<html>