<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="addAudioSet.">
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
            <form action = "${pageContext.servletContext.contextPath}/controller?command=submitAudioSet" method ="post">
                <label title="<fmt:message key="titleDesc"/>">
                    <input type="text" name="title" pattern="[A-Za-z0-9 ]{1,32}" required placeholder="<fmt:message key="title"/>"/>
                </label>
                <label title="<fmt:message key="descriptionDesc"/>">
                    <textarea name="description" cols="30" rows="5" placeholder="<fmt:message key="descriptionPlaceholder"/>"></textarea>
                </label>
                <c:if test="${requestScope.addAudioSetMessage ne null}">
                    <c:forEach items="${requestScope.addAudioSetMessage}" var="addAudioSetMessage">
                        <p class ="error"><fmt:message key="${addAudioSetMessage}"/></p>
                    </c:forEach>
                </c:if>
                <div class="buttons">
                    <label title="<fmt:message key="add"/>">
                        <input type="submit" value="<fmt:message key="add"/>"/>
                    </label>
                    <label title="<fmt:message key="cancel"/>">
                        <a class="button" href="${pageContext.servletContext.contextPath}/controller?command=showAudioSets" ><fmt:message key="cancel"/></a>
                    </label>
                </div>
            </form>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
