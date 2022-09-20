<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
boolean isLogined = (boolean)request.getAttribute("isLogined");
int loginedMemberId = (int)request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h1>메인 페이지</h1>
	
		<%if(isLogined == false) {%>
		<div>
			<a href="../member/login">로그인</a>
		</div>
		<% }%>
		<%if(isLogined == true) {%>
		<div>
		<%=loginedMemberId %>번 회원
		<a href="../member/logout">로그아웃</a>
		</div>
		<% }%>
		<div>
			<a href="../article/list">게시물 리스트</a>
			<a href="../member/join">회원가입</a>				
		</div>
</body>
</html>
