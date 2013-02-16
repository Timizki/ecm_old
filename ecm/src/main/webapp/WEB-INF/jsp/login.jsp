<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title>Kirjautuminen</title>
<link rel="stylesheet" type="text/css" href="/resources/errorScreen.css" />
</head>
<body>
	<div id="page">
		<c:if test="">
			<div id="loginError">
				<span>${errorMessage}</span>
			</div>
		</c:if>
		<form action="<c:url value='j_spring_security_check' />" method="post">
			<input type='text' name='j_username'>
			<input type='password' name='j_password' />
			<input name="submit" type="submit"	value="Kirjaudu" />
			</form>
		</div>
	</body>
</html>