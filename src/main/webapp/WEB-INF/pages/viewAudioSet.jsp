<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType = "text/html;charset=utf-8"
         isELIgnored ="false"
         pageEncoding ="utf-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>

<fmt:bundle basename="pagecontent" prefix ="audioSetView.">
    <html>
    <head>
        <title>${requestScope.audioSet.title}</title>
        <style><jsp:include page = "/css/table.css"/></style>
        <style><jsp:include page = "/css/block.css"/></style>
        <style><jsp:include page = "/css/view.css"/></style>
        <style><jsp:include page = "/css/icon-button.css"/></style>
        <style><jsp:include page = "/css/modal.css"/></style>
        <style><jsp:include page = "/css/form.css"/></style>
        <script type="text/javascript"><jsp:include page="/js/modal/audioSet.js"/></script>
        <jsp:include page = "../fragments/header.jsp"/>
    </head>
    <body>
    <div class="content">
        <div class="view-header">
            <div class="poster set">
            </div>
            <div class="view-info">
                <h1>${requestScope.audioSet.title}</h1>
                <span>${requestScope.audioSet.description}</span>
            </div >

            <div class="buttons">
                <c:if test="${sessionScope.user.role.value eq 'admin'}">
                    <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=addTracks&audioSetId=${requestScope.audioSet.id}'">
                        <div class="medium add-icon"></div>
                        <p class="b-text"><fmt:message key="addTracks"/></p>
                    </div>
                    <div class="icon-button" onclick="showAudioSetModal();
                            document.getElementById('audioSetTitle').value = '${requestScope.audioSet.title}';
                            document.getElementById('audioSetDesc').value = '${requestScope.audioSet.description}';
                            document.getElementById('audioSetId').value = '${requestScope.audioSet.id}';">
                        <div class="medium edit-icon"></div>
                        <p class="b-text"><fmt:message key="editAudioSet"/></p>
                    </div>
                </c:if>
            </div>
            <c:if test="${sessionScope.user.role.value eq 'client'}">
                <div class="icon-button" onclick="window.location='${pageContext.servletContext.contextPath}/controller?command=orderAudioSet&audioSetId=${requestScope.audioSet.id}'">
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
                    <th><fmt:message key="artist"/></th>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="price"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.tracks}" var="track"  varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${track.artist.name}</td>
                        <td>${track.title}</td>
                        <td><fmt:formatNumber value="${track.price}" type="currency" currencySymbol="$"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div id="editAudioSet" class="modal">
            <div class="form-style">
                <div class="fhead">
                    <h2><fmt:message key="editAudioSet"/></h2>
                    <div class="close" onclick="closeAudioSetModal()">&times;</div>
                </div>
                <form name="editAudioSet" action = "${pageContext.servletContext.contextPath}/controller?command=submitAudioSet" method ="post">
                    <label title="title">
                        <input id="audioSetTitle" type="text" name="title" >
                    </label>
                    <label title="description">
                        <textarea id="audioSetDesc" name="description" cols="30" rows="5"></textarea>
                    </label>
                    <input type="hidden" id="audioSetId" name ="audioset_id">
                    <div class="buttons">
                        <label title="<fmt:message key="save"/>">
                            <input type="submit" value="<fmt:message key="save"/>"/>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
