<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="payOrder.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <style><jsp:include page = "/css/form.css"/></style>
    <style><jsp:include page = "/css/table.css"/></style>
    <style><jsp:include page = "/css/payOrder.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
    <body>
        <div class="content">
                <c:if test="${requestScope.orderedTracks eq null}">
                    <div class = "empty-order">
                        <h2><fmt:message key="emptyOrder"/></h2>
                        <div class ="empty-order-img"></div>
                        <h2><fmt:message key="buyNow"/></h2>
                        <div>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=showTracks"><fmt:message key="tracks"/></a>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=showAlbums"><fmt:message key="albums"/></a>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=showAudioSets"><fmt:message key="audioSets"/></a>
                        </div>
                    </div>
                </c:if>

                <c:if test="${requestScope.orderedTracks ne null}">
                    <div class="form-style">
                        <div class="fhead">
                            <h2><fmt:message key="details"/></h2>
                        </div>
                        <form action = "${pageContext.servletContext.contextPath}/controller?command=submitPayment" method ="post">
                            <label>
                                <input type="text" name="cardNumber" pattern="[0-9]{13,16}" title="<fmt:message key="CcnDesc"/>" required placeholder="<fmt:message key="cardNumber"/>">
                            </label>
                            <label>
                                <input type="text" name="cvc" pattern="[0-9]{3}" title="<fmt:message key="CvcDesc"/>" required placeholder="<fmt:message key="cvc"/>" autocomplete="cc-csc">
                            </label>
                            <label>
                                <input type="text" name="ccExp" pattern="(0[1-9]|1[012])/[0-9]{2}" title="<fmt:message key="ccExpDesc"/>" required placeholder="<fmt:message key="expiry"/>" autocomplete="cc-exp">
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

                        <table class="table">
                            <caption><fmt:message key="tableCaption"/> ${requestScope.orderId}</caption>
                            <thead>
                            <tr>
                                <th><fmt:message key="artist"/></th>
                                <th><fmt:message key="title"/></th>
                                <th><fmt:message key="genre"/></th>
                                <th><fmt:message key="price"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.orderedTracks}" var="track"  varStatus="status">
                                <tr>
                                    <td>${track.artist.name}</td>
                                    <td>${track.title}</td>
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
                                    <td>
                                        <fmt:formatNumber value="${track.price}" type="currency" currencySymbol="$"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td><fmt:message key="discount"/> ${sessionScope.user.discount} %</td>
                                <td><fmt:message key="totalPrice"/>
                                    <fmt:formatNumber value="${requestScope.orderTotalPrice}" type="currency" currencySymbol="$"/>
                                </td>
                                <td><fmt:message key="finalPrice"/></td>
                                <td>
                                    <strong>
                                        <fmt:formatNumber value="${requestScope.orderFinalPrice}" type="currency" minFractionDigits="2" currencySymbol="$"/>
                                    </strong>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                </c:if>
        </div>
    </body>
</html>
</fmt:bundle>