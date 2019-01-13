<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="addTrack.">
<html>
<head>
    <title><fmt:message key="title"/></title>
    <style><jsp:include page = "/css/form.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <div class ="form-style">
        <div class="fhead">
            <h2><fmt:message key="title"/></h2>
        </div>
    <form action = "${pageContext.servletContext.contextPath}/controller?command=submitTrack" method ="post">

        <label title="artist">
            <c:if test="${param.artistName ne null}">
                <input type="text" name="artist" value="${param.artistName}" readonly>
            </c:if>
            <c:if test="${param.artistName eq null}">
                <input type="text" name="artist" placeholder="<fmt:message key="artist"/>"/>
            </c:if>
        </label>
        <label title="title">
            <input type="text" name="title" placeholder="<fmt:message key="audioTitle"/>"/>
        </label>
        <input type="hidden" name = "albumId" value="${param.albumId}">
        <label title="genre">
            <select name = "genre">
                <option value = "rock"> <fmt:message key="rock"/></option>
                <option value = "classic"> <fmt:message key="classic"/></option>
                <option value = "pop"> <fmt:message key="pop"/></option>
                <option value = "rap"> <fmt:message key="rap"/></option>
            </select>
        </label>
        <%--TODO Add some validation--%>
        <label title="<fmt:message key="price"/>">
            <input type="text" name="price" placeholder="<fmt:message key="price"/>"/>
        </label>
        <div class="buttons">
            <label title="<fmt:message key="add"/>">
                <input type="submit" value="<fmt:message key="add"/>"/>
            </label>
            <label title="<fmt:message key="cancel"/>">
                <c:if test="${param.albumId eq null}">
                    <a class="button" href="${pageContext.servletContext.contextPath}/controller?command=showTracks" >
                        <fmt:message key="cancel"/>
                    </a>
                </c:if>
                <c:if test="${param.albumId ne null}">
                    <a class="button" href="${pageContext.servletContext.contextPath}/controller?command=viewAlbum&albumId=${param.albumId}" >
                        <fmt:message key="cancel"/>
                    </a>
                </c:if>
            </label>
        </div>
    </form>
    </div>
</div>
</body>
</html>
</fmt:bundle>