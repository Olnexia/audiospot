<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="comments.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <style><jsp:include page="/css/comments.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <ul class="comments">
        <c:forEach items="${comments}" var="comment" varStatus="status">
            <li class="comment">
                <div class="author-info">
                    <h3>${comment.user.login}</h3>
                    <p class="date">
                        <javatime:format value="${comment.dateTime}" style="MS" />
                    </p>

                </div>
                <div class="text">
                        ${comment.text}
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
</fmt:bundle>