<html>
<%@ page contentType = "text/html;charset=utf-8"
    isELIgnored ="false"
    pageEncoding ="utf-8"%>

   <body>
   <jsp:include page = "../fragments/header.jsp"/>
   <form action = "${pageContext.servletContext.contextPath}/controller?command=login" method ="post">
      <label title = "Login">
         <input type = "text" name = "login"/>
      </label>
      <br/>
      <label title = "Password">
         <input type = "password" name = "password"/>
      </label>
      <br/>
      ${errorLoginPassMessage}
      <br/>
      <input type = "submit" value = "Log in">
   </form>
   </body>
</html>
