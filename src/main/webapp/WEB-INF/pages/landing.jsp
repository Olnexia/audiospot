<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<fmt:setLocale value="${not empty sessionScope.lang?sessionScope.lang:not empty param.lang?param.lang:not empty requestScope.lang?requestScope.lang:'eng'}"/>
<fmt:bundle basename="pagecontent" prefix ="landing.">
<html>
<head>
    <title><fmt:message key="title"/></title>
    <style><jsp:include page = "/css/login.css"/></style>
    <script><jsp:include page = "/js/loginRegisterSwitch.js"/></script>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
   <body class="login">
       <div class ="content">
           <div class="login-page">
               <div id="login-register" class="form"
                    <c:if test="${requestScope.registrationMessage ne null}">style="height: 320px;"</c:if>>
                   <form id ="register" class="register-form"
                         <c:if test="${requestScope.registrationMessage ne null}">style="display: block;"</c:if>
                         <c:if test="${requestScope.registrationMessage eq null}">style="display: none;"</c:if>"
                         action = "${pageContext.servletContext.contextPath}/controller?command=register${not empty param.lang?"&lang=":""}${not empty param.lang?param.lang:""}" method ="post">
                       <input type="text" name="login" placeholder="<fmt:message key="login"/>"/>
                       <input type="text" name="name" placeholder="<fmt:message key="name"/>"/>
                       <input type="text" name="surname" placeholder="<fmt:message key="surname"/>"/>
                       <input type="password" name="password" placeholder="<fmt:message key="password"/>"/>
                       <label>
                           <button type="submit"><fmt:message key="registrationButtonMessage"/></button>
                       </label>
                       <p class="message"><fmt:message key="registeredQuestion"/>
                           <a href="#" onclick="switchLoginRegister()"><fmt:message key="signIn"/></a>
                       </p>
                       <c:if test="${requestScope.registrationMessage ne null}">
                           <p class ="error"><fmt:message key="${requestScope.registrationMessage}"/></p>
                       </c:if>
                   </form>

                   <form id="login" class="login-form"
                         <c:if test="${requestScope.registrationMessage ne null}">style="display: none;"</c:if>
                         action = "${pageContext.servletContext.contextPath}/controller?command=login${not empty param.lang?"&lang=":""}${not empty param.lang?param.lang:""}" method ="post">
                       <label>
                           <input type="text" name="login" placeholder="<fmt:message key="login"/>"/>
                       </label>
                       <label>
                           <input type="password" name="password" placeholder="<fmt:message key="password"/>"/>
                       </label>
                       <label>
                           <button type="submit"><fmt:message key="loginButtonMessage"/></button>
                       </label>
                       <div class="message">
                           <p class=""><fmt:message key="notRegisteredQuestion"/>
                               <a href="#" onclick="switchLoginRegister()"><fmt:message key="createAnAccount"/></a>
                           </p>
                           <c:if test="${requestScope.loginMessage ne null}">
                               <p class ="error"><fmt:message key="${requestScope.loginMessage}"/></p>
                           </c:if>
                       </div>
                   </form>
               </div>
           </div>
       </div>
   </body>
</html>
</fmt:bundle>
