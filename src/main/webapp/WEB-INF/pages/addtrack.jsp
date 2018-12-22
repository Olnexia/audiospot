<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "../fragments/header.jsp"/>

<html>
<body>
<div class="content">
    <h2>Add new track</h2>
    <form action = "${pageContext.servletContext.contextPath}/controller?command=submitTrack" method ="post">
        <label title="artist">
            <input type="text" name="artist" placeholder="artist"/>
        </label>
        <label title="title">
            <input type="text" name="title" placeholder="title"/>
        </label>
        <label title="release year">
            <input type="text" name="release_year" placeholder="release year"/>
        </label>
        <label title="genre">
            <select name = "genre">
                <option value = "rock"> Rock</option>
                <option value = "class"> classic</option>
                <option value = "pop"> pop</option>
                <option value = "rap"> rap</option>
            </select>
        </label>
        <label title="price">
            <input type="text" name="price" placeholder="price"/>
        </label>
        <label title="Add">
            <button type="submit">Add</button>
        </label>
        <label title="Cancel">
            <button type="button" onclick="document.history.back();">Cancel</button>
        </label>
    </form>
</div>
