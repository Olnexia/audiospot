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
        <label>
            <input type="text" name="artist" title="<fmt:message key="artistTitleDesc"/>" pattern="[A-Za-z0-9,.' ]{1,32}" placeholder="<fmt:message key="artist"/>" required/>
        </label>
        <label>
            <input type="text" name="title" title="<fmt:message key="artistTitleDesc"/>" pattern="['A-Za-z0-9,.' ]{1,32}" placeholder="<fmt:message key="audioTitle"/>" required/>
        </label>
        <label title="<fmt:message key="genre"/>">
            <select name = "genre">
                <option value = "rock"> <fmt:message key="rock"/></option>
                <option value = "classic"> <fmt:message key="classic"/></option>
                <option value = "pop"> <fmt:message key="pop"/></option>
                <option value = "rap"> <fmt:message key="rap"/></option>
            </select>
        </label>
        <label>
            <input type="text" name="price" title="<fmt:message key="priceDesc"/>" pattern="^[0-9]+(\.[0-9]{2})?$" placeholder="<fmt:message key="price"/>" required/>
        </label>
        <c:if test="${requestScope.addTrackMessage ne null}">
            <c:forEach items="${requestScope.addTrackMessage}" var="addTrackMessage">
                <p class ="error"><fmt:message key="${addTrackMessage}"/></p>
            </c:forEach>
        </c:if>
        <div class="buttons">
            <label title="<fmt:message key="add"/>">
                <input type="submit" value="<fmt:message key="add"/>"/>
            </label>
            <label title="<fmt:message key="cancel"/>">
                <a class="button" href="${pageContext.servletContext.contextPath}/controller?command=showTracks" >
                    <fmt:message key="cancel"/>
                </a>
            </label>
        </div>
    </form>
    </div>
</div>
</body>
</html>
</fmt:bundle>