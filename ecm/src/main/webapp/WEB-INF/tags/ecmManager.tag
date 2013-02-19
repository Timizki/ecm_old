<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ecm" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<c:set var="mode" value="${param['mode']}" />
	<div id="ecmManagerToolbar">
		<ul>
			<c:choose>
				<c:when test="${(mode eq 'edit' or mode eq 'addPage')}">
					<li class="first"><a onclick="document.forms['ecmForm'].submit();">Tallenna</a></li>				</c:when>
				<c:otherwise>
					<li class="first"><a href="?mode=edit">Muokkaa</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="?mode=addPage">Uusi sivu</a></li>
		</ul>
		<c:if test="${mode eq 'addPage'}">
			<span>Sivun nimi:</span><form:input path="name"/>
		</c:if>
		<div class="clear"></div>
		<span><a href="/logout.do">Kirjaudu ulos</a></span>
		<span id="ecmLoginUser">${pageContext.request.remoteUser}</span>
	</div>
</sec:authorize>