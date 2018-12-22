<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language = "java" contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<html>
<head>
   <style><jsp:include page = "/css/header-fixed.css"/></style>
   <script><jsp:include page = "../js/modal.js"/></script>
   <jsp:include page = "../fragments/header.jsp"/>
</head>
   <body>
   <div class = "content">
      <h2>hello to AudioSpot</h2>
      <%--<!-- The Modal -->--%>
         <%--<c:if test="${sessionScope.user.role.value eq 'admin'}">--%>
            <%--<div id="addAudio" class="modal">--%>

            <%--<!-- Modal content -->--%>
            <%--<div class="modal-content">--%>
               <%--<span class="close">&times;</span>--%>

            <%--</div>--%>
         <%--</c:if>--%>

      </div>
   </body>
</html>
