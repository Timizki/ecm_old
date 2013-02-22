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
		<div id="ecmToolbarLeftPart">
			<div id="ecmPageActions">
				<ul>
					<c:choose>
						<c:when test="${(mode eq 'edit' or mode eq 'addPage')}">
							<li class="first"><a href="#" onclick="document.forms['ecmForm'].submit();">Tallenna</a></li>				</c:when>
						<c:otherwise>
							<li class="first"><a href="?mode=edit">Muokkaa sivua</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="?mode=addPage">Uusi sivu</a></li>
				</ul>
			</div>
			<div id="ecmPageAttributes">
				<c:if test="${mode eq 'edit' or mode eq 'addPage'}">
					<span>Sivun nimi:</span><form:input path="name"/>
					<span>Yläsivu:</span>
					<form:select path="parent">
						<form:option value="">Ei yläsivua</form:option>
						<form:option value="1">Home</form:option>
						<form:option value="50">Ajankohtaista</form:option>
					</form:select>
					<span>Sivupohja:</span><form:select path="decorationName" items="${templates}" itemLabel="name" itemValue="name"/>
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
		<div id="ecmToolbarRightPart">
			<div id="ecmLoginInfo">
				<span id="ecmLoginUser">${pageContext.request.remoteUser}</span>
				<span><a href="/logout.do">Kirjaudu ulos</a></span>				
			</div>
		</div>
	</div>
</sec:authorize>