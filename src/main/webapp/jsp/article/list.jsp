<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>)request.getAttribute("articleRows");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	
	<h1>게시물 리스트</h1>
		
		<table border="2" bordercolor="green">
			<colgroup>
				<col width ="50px"/>
				<col width ="200px"/>
			</colgroup>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
			<th>삭제</th>
		</tr>
		
		<%for(Map<String, Object> articleRow : articleRows){ %>
		<tr>
			<td><%=(int)articleRow.get("id")%> 번</td>
			<td><%=(LocalDateTime)articleRow.get("regDate") %></td>
			<td><a href="detail?id=<%=(int)articleRow.get("id") %>"><%=(String)articleRow.get("title") %></a></td>
			<td><a href="doDelete?id=<%=(int)articleRow.get("id")%>">삭제</a></td>
		</tr>	
		<%} %>		
		</table>
			
</body>
</html>