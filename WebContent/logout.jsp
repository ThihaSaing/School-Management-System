<%
	session.setAttribute("account", null);
	session.setAttribute("email", null);
	session.setAttribute("role", null);
	session.setAttribute("id", null);
	session.setAttribute("accountid", null);
	response.sendRedirect("Index");
%>