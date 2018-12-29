<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<html>
<head>
    <title></title>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
    <body>
    <div class="content">
        <c:if test="${tracks eq null}">
            <h2>There are not available tracks now</h2>
        </c:if>
        <c:if test="${tracks ne null}">
            <h2>Available tracks</h2>
        </c:if>
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