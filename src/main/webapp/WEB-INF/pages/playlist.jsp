<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="playlist.">
<html>
<head>
    <title><fmt:message key="pageTitle"/></title>
    <jsp:include page = "../fragments/header.jsp"/>
    <style><jsp:include page = "/css/table.css"/></style>
    <style><jsp:include page="/css/modal.css"/></style>
    <style><jsp:include page="/css/form.css"/></style>
    <style><jsp:include page="/css/icon-button.css"/></style>
    <script type="text/javascript"><jsp:include page="/js/modal/comment.js"/></script>
</head>
    <body>
        <div class="content">
            <c:if test="${fn:length(tracks)ne 0}">
                <table class="table">
                    <caption><fmt:message key="pageTitle"/></caption>
                    <thead>
                    <tr>
                        <th><fmt:message key="artist"/></th>
                        <th><fmt:message key="title"/></th>
                        <th><fmt:message key="genre"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tracks}" var="track"  varStatus="status">
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
                            <td class="manage-buttons">
                                <div class="icon-button " onclick="showCommentModal();
                                        document.getElementById('audiotrack_id').value = '${track.id}';">
                                    <div class="small add-comment-icon"></div>
                                    <p class="b-text"><fmt:message key="comment"/></p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div id="comment" class="modal">
                    <div class="form-style">
                        <div class="fhead">
                            <h2><fmt:message key="addComment"/></h2>
                            <div class="close" onclick="closeCommentModal()">&times;</div>
                        </div>
                        <form name="comment" action = "${pageContext.servletContext.contextPath}/controller?command=submitComment" method ="post">
                            <label title="comment">
                                <textarea name="text" cols="30" rows="5" placeholder="<fmt:message key="commentPlaceholder"/>"></textarea>
                            </label>
                            <input type="hidden" id="audiotrack_id" name ="audiotrack_id">
                            <div class="buttons">
                                <label title="<fmt:message key="send"/>">
                                    <input type="submit" value="<fmt:message key="send"/>"/>
                                </label>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
</fmt:bundle>
