<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
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
			<th>삭제</th>
		</tr>

		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<tr>
			<td><%=articleRow.get("id")%></td>
			<td><%=articleRow.get("regDate")%></td>
			<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
			<td><a href="doDelete?id=<%=articleRow.get("id")%>">삭제하기</a></td>
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
		<a href="list?page=1">◀</a>		
		<% for (int i = 1; i <= totalPage; i++) {%>		
		<a class="<%=cPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>		
		<%}%>
		<a href="list?page=<%=totalPage %>">▶</a>	
	</div>
	
</body>
</html>