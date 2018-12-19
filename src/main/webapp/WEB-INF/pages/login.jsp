<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<html>
<head>
    <title>Log in</title>
    <style><jsp:include page = "../css/login.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
   <body>
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
               <label title="login">
                   <input type="text" name="login" placeholder="login"/>
               </label>
               <label title="password">
                   <input type="password" name="password" placeholder="password"/>
               </label>
               <label title="log in">
                   <button type="submit">Log In</button>
               </label>
               <p class="message">Not registered? <a href="#">Create an account</a></p>
           </form>
       </div>
   </div>
   </body>
</html>
