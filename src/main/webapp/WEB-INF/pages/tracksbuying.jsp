<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<jsp:include page = "../fragments/header.jsp"/>

<html>
<head>
    <title></title>
</head>
    <body>
    <div class="content">
        <h2>Available tracks</h2>
        <table>
            <c:forEach items="${tracks}" var="track"  varStatus="status">
                <tr>
                    <td>${track.artist.name}</td>
                    <td>${track.title}</td>
                    <td>${track.releaseYear}</td>
                    <td>${track.genre.value}</td>
                    <td>${track.price}</td>
                    <td><a href="${pageContext.servletContext.contextPath}/controller?command=orderTrack&trackId=${track.id}">Add to an order</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
<html>