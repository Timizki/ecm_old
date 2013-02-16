<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="name" required="true" %>
<sec:authorize var="authorized" ifAllGranted="ROLE_ADMIN"/>
<c:choose>
	<c:when test="${param['edit'] and authorized}">
		<form:textarea path="properties['${name}']"/>
	</c:when>
	<c:otherwise>
		<c:out value="${sitemapItem.properties[name]}" escapeXml="true"/>
	</c:otherwise>
</c:choose>