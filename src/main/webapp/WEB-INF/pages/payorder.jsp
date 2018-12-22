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
    <form action = "${pageContext.servletContext.contextPath}/controller?command=submitPayment" method ="post">
        <label title="artist">
            <input type="" name="cardNumber" placeholder="card number"/>
        </label>
        <label title="title">
            <input type="text" name="owner" placeholder="name of the owner"/>
        </label>
        <label title="CVV">
            <input type="text" name="cvv" placeholder="CVV"/>
        </label>
        <label title="Pay">
            <button type="submit">Pay</button>
        </label>
        <label title="Cancel order">
            <button type="button" onclick="document.history.back();">Cancel order</button>
        </label>
    </form>
</div>