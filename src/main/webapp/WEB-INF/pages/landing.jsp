<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=utf-8"
         isELIgnored="false"
         pageEncoding="utf-8" %>
<c:if test="${not empty sessionScope.user}">
    <jsp:forward page="main.jsp"/>
</c:if>

<fmt:setLocale
        value="${not empty sessionScope.lang?sessionScope.lang:not empty param.lang?param.lang:not empty requestScope.lang?requestScope.lang:'eng'}"/>
<fmt:bundle basename="pagecontent" prefix="landing.">
    <html>
    <head>
        <title>AudioSpot</title>
        <style>
            <jsp:include page="/css/login.css"/>
        </style>
        <script>
            <jsp:include page="/js/loginRegisterSwitch.js"/>
        </script>
        <jsp:include page="../fragments/header.jsp"/>
    </head>
    <body class="login">
    <div class="content">
        <div class="login-page">
            <div id="login-register" class="form"
                 <c:if test="${requestScope.registrationMessage ne null}">style="height: 320px;"</c:if>>

                <form id="register" class="register-form"
                      <c:if test="${requestScope.registrationMessage ne null}">style="display: block;"</c:if>
                      <c:if test="${requestScope.registrationMessage eq null}">style="display: none;"</c:if>
                      action="${pageContext.servletContext.contextPath}/controller?command=register${not empty param.lang?"&lang=":""}${not empty param.lang?param.lang:""}"
                      method="post">
                    <input type="text" name="login" pattern="[A-Za-z0-9_]{1,15}" title="<fmt:message key="loginDesc"/>"
                           placeholder="<fmt:message key="login"/>" required/>
                    <input type="text" name="name" pattern="[A-Za-z]{1,15}" title="<fmt:message key="nameDesc"/>"
                           placeholder="<fmt:message key="name"/>" required/>
                    <input type="text" name="surname" pattern="[A-Za-z]{1,32}" title="<fmt:message key="surnameDesc"/>"
                           placeholder="<fmt:message key="surname"/>" required/>
                    <input type="password" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
                           title="<fmt:message key="passwordDesc"/>" placeholder="<fmt:message key="password"/>"
                           required/>
                    <label title="<fmt:message key="registrationButtonMessage"/>">
                        <button type="submit"><fmt:message key="registrationButtonMessage"/></button>
                    </label>
                    <p class="message"><fmt:message key="registeredQuestion"/>
                        <a href="#" onclick="switchLoginRegister()"><fmt:message key="signIn"/></a>
                    </p>
                    <c:if test="${requestScope.registrationMessage ne null}">
                        <c:forEach items="${requestScope.registrationMessage}" var="registrationMessage">
                            <p class="error"><fmt:message key="${registrationMessage}"/></p>
                        </c:forEach>
                    </c:if>
                </form>

                <form id="login" class="login-form"
                      <c:if test="${requestScope.registrationMessage ne null}">style="display: none;"</c:if>
                      action="${pageContext.servletContext.contextPath}/controller?command=login${not empty param.lang?"&lang=":""}${not empty param.lang?param.lang:""}"
                      method="post">
                    <label>
                        <input type="text" name="login" pattern="[A-Za-z0-9_]{1,15}"
                               placeholder="<fmt:message key="login"/>" required/>
                    </label>
                    <label>
                        <input type="password" name="password" placeholder="<fmt:message key="password"/>" required/>
                    </label>
                    <label>
                        <button type="submit"><fmt:message key="loginButtonMessage"/></button>
                    </label>
                    <div class="message">
                        <p class=""><fmt:message key="notRegisteredQuestion"/>
                            <a href="#" onclick="switchLoginRegister()"><fmt:message key="createAnAccount"/></a>
                        </p>
                        <c:if test="${requestScope.loginMessage ne null}">
                            <p class="error"><fmt:message key="${requestScope.loginMessage}"/></p>
                        </c:if>
                    </div>
                </form>

            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
