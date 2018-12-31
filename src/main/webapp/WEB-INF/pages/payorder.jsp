<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="payorder.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <style><jsp:include page = "/css/form.css"/></style>
    <style><jsp:include page = "/css/table.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
        <c:if test="${orderedTracks eq null}">
            <h3><fmt:message key="emptyOrder"/></h3>
        </c:if>

        <c:if test="${orderedTracks ne null}">
            <div class="form-style">
                <h2><fmt:message key="details"/></h2>
                <form action = "${pageContext.servletContext.contextPath}/controller?command=submitPayment" method ="post">
                    <label>
                        <input type="text" name="cardNumber" required placeholder="<fmt:message key="cardNumber"/>" autocomplete="cc-number">
                    </label>
                    <label>
                        <input type="text" name="cvc" required placeholder="<fmt:message key="cvc"/>" autocomplete="cc-csc">
                    </label>
                    <label>
                        <input type="text" name="ccExp" required placeholder="<fmt:message key="expiry"/>" autocomplete="cc-exp">
                    </label>
                    <div class="buttons">
                        <label>
                            <input type="submit" value="<fmt:message key="pay"/>"/>
                        </label>
                        <div>
                            <a class="button" href ="${pageContext.servletContext.contextPath}/controller?command=cancelOrder" >
                                <fmt:message key="cancel"/>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div>
                <table class="table">
                    <caption><fmt:message key="tableCaption"/> ${orderId}</caption>
                    <thead>
                    <tr>
                        <th><fmt:message key="artist"/></th>
                        <th><fmt:message key="title"/></th>
                        <th><fmt:message key="year"/></th>
                        <th><fmt:message key="genre"/></th>
                        <th><fmt:message key="price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderedTracks}" var="track"  varStatus="status">
                        <tr>
                            <td>${track.artist.name}</td>
                            <td>${track.title}</td>
                            <td>${track.releaseYear}</td>
                            <c:choose>
                                <c:when test = "${track.genre.value eq 'rock'}">
                                    <td><fmt:message key="rock"/></td>
                                </c:when>
                                <c:when test = "${track.genre.value eq 'rap'}">
                                    <td><fmt:message key="rap"/></td>
                                </c:when>
                                <c:when test = "${track.genre.value eq 'classic'}">
                                    <td><fmt:message key="classic"/></td>
                                </c:when>
                                <c:when test = "${track.genre.value eq 'pop'}">
                                    <td><fmt:message key="pop"/></td>
                                </c:when>
                            </c:choose>
                            <td>${track.price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><fmt:message key="totalPrice"/></td>
                        <td>${orderTotalPrice}</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </c:if>
</div>
</body>
</html>
</fmt:bundle>