<html>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "../fragments/header.jsp"/>

<body>
<div class="content">
    <h2>Playlists</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>name</th>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td
                <td><a href="#">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<html>
