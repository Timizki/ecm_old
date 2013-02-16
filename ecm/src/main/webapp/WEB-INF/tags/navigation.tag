<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="navigationsId" required="true" %>
<ul id="${navigationsId}">
	<sec:authorize var="authorized" ifAllGranted="ROLE_ADMIN"/>
	<c:set var="items" value="${sitemapItem.sitemap.sitemapItems}" />
	<c:forEach var="item" items="${items}" varStatus="status">
		<c:if test="${item.id eq sitemapItem.id}">
			<c:set var="cssClass" value="active" />
		</c:if>
		<li class="${cssClass} ${status.first ? 'first' : ''} ${status.last ? 'last' : ''}">
			<c:choose>
				<c:when test="${(item.id eq sitemapItem.id) and (param['edit'] and authorized)}">
					<form:input path="properties['title']"/>
				</c:when>
				<c:otherwise>
					<a href="${item.pathAsString }" class="${cssClass}"><span class="${cssClass}"><c:out value="${item.properties['title']}" /></span></a>
				</c:otherwise>
			</c:choose>							
		</li>
	</c:forEach>
</ul>