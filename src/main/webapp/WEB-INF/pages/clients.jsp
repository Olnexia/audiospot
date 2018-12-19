<html>
<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "../fragments/header.jsp"/>

<body>
<div class="content">
    <h2>Clients</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>surname</th>
            <th>login</th>
            <th>account</th>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.surname}</td>
                <td>${client.login}</td>
                <td>${client.account}</td>
                <td><a href="#">Add bonus</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
