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
                <label title="<fmt:message key="artist"/>">
                    <input type="text" name="artist" pattern="[A-Za-z0-9 ]{1,32}" title="<fmt:message key="artistTitleDesc"/>" placeholder="<fmt:message key="artist"/>"/>
                </label>
                <label title="<fmt:message key="title"/>">
                    <input type="text" name="title" pattern="[A-Za-z0-9 ]{1,32}" title="<fmt:message key="artistTitleDesc"/>" placeholder="<fmt:message key="title"/>"/>
                </label>
                <label title="<fmt:message key="year"/>">
                    <input type="text" name="release_year" pattern="[0-9]{4}" title="<fmt:message key="yearDesc"/>" placeholder="<fmt:message key="year"/>"/>
                </label>
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
