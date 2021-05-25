<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<!-- Content Here -->
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	

<!-- 
TODO: Check no data case <c:if test="${fn:length(LIST) > 0}"></c:if>
-->