<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="main.">
<html>
<head>
   <title>AudioSpot</title>
    <style><jsp:include page = "/css/main.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
   <body>
   <div class = "content">
       <div class = "welcome">
           <h1><fmt:message key="hello"/></h1>
           <h2><fmt:message key="slogan"/></h2>
       </div>
       <div class ="main-img"></div>
       </div>
   </body>
</html>
</fmt:bundle>