<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="addAlbum.">
    <html>
    <head>
        <title><fmt:message key="pageTitle"/></title>
        <style><jsp:include page = "/css/form.css"/></style>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <div class ="form-style">
            <div class="fhead">
                <h2><fmt:message key="pageTitle"/></h2>
            </div>
            <form action = "${pageContext.servletContext.contextPath}/controller?command=submitAlbum" method ="post">
                <label>
                    <input type="text" name="artist" title="<fmt:message key="artistTitleDesc"/>"  pattern="[A-Za-z0-9',. ]{1,32}"  placeholder="<fmt:message key="artist"/>" required/>
                </label>
                <label>
                    <input type="text" name="title" title="<fmt:message key="artistTitleDesc"/>" pattern="[A-Za-z0-9',. ]{1,32}" required placeholder="<fmt:message key="title"/>"/>
                </label title="<fmt:message key="artistTitleDesc"/>">
                <label>
                    <input type="text" name="release_year" title="<fmt:message key="yearDesc"/>" pattern="[0-9]{4}" placeholder="<fmt:message key="year"/>"/>
                </label>
                <c:if test="${requestScope.addAlbumMessage ne null}">
                    <c:forEach items="${requestScope.addAlbumMessage}" var="addAlbumMessage">
                        <p class ="error"><fmt:message key="${addAlbumMessage}"/></p>
                    </c:forEach>
                </c:if>
                <div class="buttons">
                    <label title="<fmt:message key="add"/>">
                        <input type="submit" value="<fmt:message key="add"/>"/>
                    </label>
                    <label title="<fmt:message key="cancel"/>">
                        <a class="button" href="${pageContext.servletContext.contextPath}/controller?command=showAlbums" ><fmt:message key="cancel"/></a>
                    </label>
                </div>
            </form>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
