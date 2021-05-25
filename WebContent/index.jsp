<%@page import="guest.GuestNews"%>
<%@page import="java.util.List"%>
<jsp:include page="include/header.jsp" />
<%
	@SuppressWarnings("unchecked")
	List<GuestNews> news = (List<GuestNews>) request.getAttribute("news");
%>
		<main>
			<figure class="school-photo">
				<img src="img/schoolPhoto.png" alt="school-photo" height="400px" width="100%">
			</figure>	
			<div class="latest-new">
				<h1>Latest News</h1>
				<% 
					if(news.size() > 0) { 
					GuestNews mainNews = news.get(0);
				%>	
				<h2><%= mainNews.getNewsTitle() %></h2>
				<p><%= mainNews.getNewsContent() %></p>
				<% } %>
			</div>
			<div class="mini-news">
				<%
					if(news.size() > 3) {
						for (int i = 1; i < 4; i ++) {
							GuestNews miniNews = news.get(i);
				%>
				<article>
					<h3><%= miniNews.getNewsTitle() %></h3>
					<p>
						<%= miniNews.getNewsContent().substring(0, 150) + "..." %> 
						<a href="#">Continue Reading</a>
					</p>
				</article>
				<% 
						}
					}
				%>
			</div>
		</main>
<jsp:include page="include/footer.jsp" />		