<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="clients.">
<html>
<head>
    <title><fmt:message key="title"/></title>
    <style><jsp:include page = "/css/table.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <table class="table">
        <tr class="tr">
            <th class="th">ID</th>
            <th class="th"><fmt:message key="name"/></th>
            <th class="th"><fmt:message key="surname"/></th>
            <th class="th"><fmt:message key="login"/></th>
            <th class="th"><fmt:message key="discount"/></th>
            <th class="th"><fmt:message key="status"/></th>
            <th></th>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr class="tr">
                <td class="td">${client.id}</td>
                <td class="td">${client.name}</td>
                <td class="td">${client.surname}</td>
                <td class="td">${client.login}</td>
                <td class="td">${client.discount}</td>
                <td class="td">
                    <c:if test="${client.active eq true}"><fmt:message key="active"/></c:if>
                    <c:if test="${client.active eq false}"><fmt:message key="blocked"/></c:if>
                </td>
                <td class="manage-buttons">
                    <a class="change-bonus" href ="#"><fmt:message key="changeBonus"/></a>
                    <c:if test="${client.active eq true}">
                        <a class="block" href="${pageContext.servletContext.contextPath}/controller?command=changeClientStatus&userId=${client.id}"><fmt:message key="block"/></a>
                    </c:if>
                    <c:if test="${client.active eq false}">
                        <a class="block" href="${pageContext.servletContext.contextPath}/controller?command=changeClientStatus&userId=${client.id}"><fmt:message key="unblock"/></a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
</fmt:bundle>