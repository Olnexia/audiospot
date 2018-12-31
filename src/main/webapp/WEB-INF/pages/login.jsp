<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="pagecontent" prefix ="login.">

<html>
<head>
    <title><fmt:message key="title"/></title>
    <style><jsp:include page = "/css/login.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
   <body class="login">
       <div class ="content">
           <div class="login-page">
               <div class="form">
                       <%--<form class="register-form">--%>
                       <%----%>
                       <%--<input type="text" placeholder="name"/>--%>
                       <%--<input type="password" placeholder="password"/>--%>
                       <%--<input type="text" placeholder="email address"/>--%>
                       <%--<button>create</button>--%>
                       <%--<p class="message">Already registered? <a href="#">Sign In</a></p>--%>
                       <%--</form>--%>
                   <form class="login-form" action = "${pageContext.servletContext.contextPath}/controller?command=login" method ="post">
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
                           <p class=""><fmt:message key="registeredQuestion"/> <a href="#"><fmt:message key="createAnAccount"/></a></p>
                           <c:if test="${wrongInput eq true}">
                               <p class ="error"><fmt:message key="wrongInput"/></p>
                           </c:if>
                           <c:if test="${blocked eq true}">
                               <p class ="error"><fmt:message key="blocked"/></p>
                           </c:if>
                       </div>

                   </form>
               </div>
           </div>
       </div>
   </body>
</html>
</fmt:bundle>
