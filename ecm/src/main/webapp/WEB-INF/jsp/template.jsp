<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>

<html>
	<head>	
		<title>
			<ecm:title />
		</title>
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<link rel="stylesheet" type="text/css" href="resources/css/ecm.css">
		</sec:authorize>
		<link rel="stylesheet" type="text/css" href="resources/css/screen.css">
	</head>
	<body>
		<ecm:ecm>
			<jsp:attribute name="body">
				<div id="page">		
					<div id="header">
						<tiles:insertAttribute name="header"/>
					</div>
					<div id="menu">
						<tiles:insertAttribute name="menu"/>
					</div>
					<div id="body">
						<tiles:insertAttribute name="body"/>
					</div>
					<div id="footer">
						<tiles:insertAttribute name="footer"/>
					</div>		
				</div>
			</jsp:attribute>
		</ecm:ecm>
	</body>
</html>