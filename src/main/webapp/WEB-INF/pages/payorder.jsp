<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="pagecontent" prefix ="addTrack.">

<html>
<head>
    <title>Pay an order</title>
    <style><jsp:include page = "/css/form.css"/></style>
    <jsp:include page = "../fragments/header.jsp"/>
</head>
<body>
<div class="content">
    <h2>Pay an order</h2>
        <c:if test="${orderedTracks eq null}">
            <h3>Nothing to pay for</h3>
        </c:if>
        <c:if test="${orderedTracks ne null}">
            <div class="form-style">
                <h2><fmt:message key="details"/></h2>
                <form action = "${pageContext.servletContext.contextPath}/controller?command=submitPayment" method ="post">
                    <label for="frmCCNum">
                        <input type="text" name="cardNumber" id="frmCCNum" required autocomplete="cc-number">
                    </label>
                    <label for="frmCCCVC">
                        <input type="text" name="cvc" id="frmCCCVC" required autocomplete="cc-csc">

                    </label>
                    <label for="frmCCExp">
                        <input type="text" name="ccExp" id="frmCCExp" required placeholder="MM-YYYY" autocomplete="cc-exp">

                    </label>
                    <label title="<fmt:message key="pay"/>">
                        <input type="submit" value="<fmt:message key="Pay"/>"/>
                    </label>
                    <a class="button" href ="${pageContext.servletContext.contextPath}/controller?command=cancelOrder" >Cancel order</a>
                </form>
            </div>
            <table>
                <th>Ordered tracks in order № ${orderedId}</th>
                <c:forEach items="${orderedTracks}" var="track"  varStatus="status">
                    <tr>
                        <td>${track.artist.name}</td>
                        <td>${track.title}</td>
                        <td>${track.releaseYear}</td>
                        <td>${track.genre.value}</td>
                        <td>${track.price}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>Total price</td>
                    <td>${orderTotalPrice}</td>

                </tr>
            </table>

        </c:if>
</div>
    </body>
    </html>
    </fmt:bundle>