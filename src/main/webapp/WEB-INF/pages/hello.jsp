<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Test React</title>
<!-- Load React API -->
<script src= "https://unpkg.com/react@16/umd/react.production.min.js"></script>
<!-- Load React DOM-->
<script src= "https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<!-- Load Babel Compiler -->
<script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
<body>
	<script type="text/babel">
    //  JSX Babel code goes here
	</script>
	
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
</body>
</html>