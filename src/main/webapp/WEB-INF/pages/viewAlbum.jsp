<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="albumView.">
    <html>
    <head>
        <title>${requestScope.album.title}</title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/block.css"/></style>
        <style><jsp:include page = "/css/view.css"/></style>
        <style><jsp:include page = "/css/icon-button.css"/></style>
        <style><jsp:include page = "/css/modal.css"/></style>
        <style><jsp:include page = "/css/form.css"/></style>
        <script><jsp:include page="/js/modal/albumTrack.js"/></script>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <div class="view-header">
            <div class="poster">
            </div>
            <div class="view-info">
                <h1>${requestScope.album.title}</h1>
                <span>${requestScope.album.artist.name} ${requestScope.album.releaseYear}</span>
            </div >
                <c:if test="${sessionScope.user.role.value eq 'admin'}">
                    <div class="icon-button" onclick="showAlbumTrackModal();
                            document.getElementById('albumId').value = '${requestScope.album.id}';
                            document.getElementById('artistName').value = '${requestScope.album.artist.name}';">
                        <div class="medium add-icon"></div>
                        <p class="b-text"><fmt:message key="addTrack"/></p>
                    </div>
                    <div id="addTrackToAlbum" class="modal" <c:if test="${requestScope.addTrackMessage ne null}">style="display: block;"</c:if>>
                        <div class="form-style">
                            <div class="fhead">
                                <h2><fmt:message key="addTrack"/></h2>
                                <div class="close" onclick="closeAlbumTrackModal()">&times;</div>
                            </div>
                            <form name="addTrack" action = "${pageContext.servletContext.contextPath}/controller?command=submitTrack" method ="post">
                                <input type="hidden" id="albumId" name ="album_id">
                                <input type="hidden" id="artistName" name ="artist">
                                <label>
                                    <input type="text" name="title" pattern="['A-Za-z0-9,.' ]{1,32}" title="<fmt:message key="artistTitleDesc"/>" placeholder="<fmt:message key="title"/>" required/>
                                </label>
                                <label title="<fmt:message key="genre"/>">
                                    <select name = "genre">
                                        <option value = "rock"> <fmt:message key="rock"/></option>
                                        <option value = "classic"> <fmt:message key="classic"/></option>
                                        <option value = "pop"> <fmt:message key="pop"/></option>
                                        <option value = "rap"> <fmt:message key="rap"/></option>
                                    </select>
                                </label>
                                <label>
                                    <input type="text" name="price" pattern="^[0-9]+(\.[0-9]{2})?$" title="<fmt:message key="priceDesc"/>" placeholder="<fmt:message key="price"/>" required/>
                                </label>
                                <c:if test="${requestScope.addTrackMessage ne null}">
                                    <c:forEach items="${requestScope.addTrackMessage}" var="addTrackMessage">
                                        <p class ="error"><fmt:message key="${addTrackMessage}"/></p>
                                    </c:forEach>
                                </c:if>
                                <div class="buttons">
                                    <label title="<fmt:message key="add"/>">
                                        <input type="submit" value="<fmt:message key="add"/>"/>
                                    </label>
                                    <label title="<fmt:message key="cancel"/>">
                                        <a class="button" onclick="closeAlbumTrackModal();" >
                                            <fmt:message key="cancel"/>
                                        </a>
                                    </label>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user.role.value eq 'client'}">
                    <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=orderAlbum&albumId=${requestScope.album.id}'">
                        <div class="medium order-icon"></div>
                        <p class="b-text"><fmt:message key="order"/></p>
                    </div>
                </c:if>
        </div>

        <c:if test="${fn:length(requestScope.tracks)ne 0}">
                <table class="table">
                    <caption><fmt:message key="tracks"/></caption>
                    <thead>
                    <tr>
                        <th>№</th>
                        <th><fmt:message key="title"/></th>
                        <th><fmt:message key="price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.tracks}" var="track"  varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${track.title}</td>
                            <td><fmt:formatNumber value="${track.price}" type="currency" currencySymbol="$"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </c:if>
    </div>
    </body>
    </html>
</fmt:bundle>
