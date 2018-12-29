<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="login.">
<html>
<head>
    <title>

    </title>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <h2>Clients</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>surname</th>
            <th>login</th>
            <th>account</th>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.surname}</td>
                <td>${client.login}</td>
                <td>${client.account}</td>
                <td><a href="#">Add bonus</a></td>
                <%--<c:if test="${sessionScope.user.active eq true}">--%>
                    <%--<td><a href="#">Block</a></td>--%>
                <%--</c:if>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
</fmt:bundle>