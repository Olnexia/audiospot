<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "../fragments/header.jsp"/>

<html>
<head>
    <title>Pay an order</title>
</head>
<body>
<div class="content">
    <h2>Pay an order</h2>
        <c:if test="${orderedTracks eq null}">
            <h3>Nothing to pay for</h3>
        </c:if>
        <c:if test="${orderedTracks ne null}">
            <table>
                <th>Ordered tracks in order № ${orderedId}</th>
                <c:forEach items="${orderedTracks}" var="track"  varStatus="status">
                    <tr>
                        <td>${track.artist.name}</td>
                        <td>${track.title}</td>
                        <td>${track.releaseYear}</td>
                        <td>${track.genre.value}</td>
                        <td>${track.price}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/controller?command=orderTrack&trackId=${track.id}">Remove from an order</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>Total price</td>
                    <td>${orderTotalPrice}</td>

                </tr>
            </table>
            <form action = "${pageContext.servletContext.contextPath}/controller?command=submitPayment" method ="post">
                <label for="frmCCNum">Card Number</label>
                <input name="cardNumber" id="frmCCNum" required autocomplete="cc-number">

                <label for="frmCCCVC">CVC</label>
                <input name="cvc" id="frmCCCVC" required autocomplete="cc-csc">

                <label for="frmCCExp">Expiry</label>
                <input name="ccExp" id="frmCCExp" required placeholder="MM-YYYY" autocomplete="cc-exp">
                <label title="Pay">
                    <button type="submit">Pay</button>
                </label>
                <label title="Cancel order">
                    <button type="button" onclick="document.history.back();">Cancel order</button>
                </label>
            </form>
        </c:if>
</div>