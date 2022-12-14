<%@ page import="java.util.List" %>
<%@ page import="com.KoreaIT.Java.AM.dto.Article" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<%@ include file="/jsp/part/topBar.jspf" %>
	
	<div>
		<a href="write">게시글 작성</a>
	</div>
	<table border="2" bordercolor="green">
		<colgroup>
			<col width="50" />
			<col width="200" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
			<th>작성자</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>

		<%
		for (Article article : articles) {
		%>
		<tr>
			<td><%=article.id%></td>
			<td><%=article.regDate%></td>
			<td><a href="detail?id=<%=article.id%>"><%=article.title%></a></td>
			<td><%=article.writer__name%></td>
			<td><a href="modify?id=<%=article.id%>">수정</a></td>
			<td><a href="doDelete?id=<%=article.id%>">삭제</a></td>
		</tr>
		<%
		}
		%>
	</table>

	<style type="text/css">
	.page>a.red {
	color: red;
	}
	.page>a {
	text-decoration: none;
	}
	</style>
		
	<div class="page">
		<%
		int pageSize = 5;
		int from = cPage-pageSize;
		if(from < 1){
			from = 1;
		}
		int end = cPage+pageSize;
		if(end>totalPage){
			end = totalPage;
		}
		%>	
		
		<%if(cPage > 1){ %>
		<a href="list?page=1">◀◀</a>
		<%}%>
		<% for (int i = from; i <= end; i++) {%>		
		<a class="<%=cPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>		
		<%}%>
		<%if(cPage < totalPage) { %>
		<a href="list?page=<%=totalPage%>">▶▶</a>
		<% } %>		
	</div>

</body>
</html>