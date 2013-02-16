<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ecm" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<div id="ecmManagerToolbar">
		<ul>
			<c:choose>
				<c:when test="${param['edit']}">
					<li class="first"><a onclick="document.forms['ecmForm'].submit();">Tallenna</a></li>				</c:when>
				<c:otherwise>
					<li class="first"><a href="?edit=true">Muokkaa</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="?createPage=true">Uusi sivu</a></li>
		</ul>
		<div class="clear"></div>
		<span><a href="/logout.do">Kirjaudu ulos</a></span>
		<span id="ecmLoginUser">${pageContext.request.remoteUser}</span>
	</div>
</sec:authorize>