<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<h1 class="news">News List</h1>
				<c:forEach var="gnewsInfo" items="${guestnews}">
				<c:url var="detailLink" value="News">
						<c:param name="action" value="loadDetail" />
						<c:param name="gnewsID" value="${gnewsInfo.newsId}" />
				</c:url>
				<div class="news-box">	
					<c:set var="string1" value="${gnewsInfo.newsContent}"></c:set>
					<c:set var="string2" value="${fn:substring(string1, 0, 150)}"></c:set>
					<h1>${gnewsInfo.newsTitle}</h1>
					<p>	
						${string2}...
						<a href="${detailLink}">Details</a>
					</p>
				</div>	
				</c:forEach>
		</main>
<jsp:include page="include/footer.jsp" />	
