<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	
	<h1>게시물 작성</h1>
	
	<form action="doWrite" method="post" >
	<div> 제목 : <input autocomplete="off" placeholder="제목을 입력해주세요" type="text" name="title"/></div>
	<div> 내용 : <textarea autocomplete="off" placeholder="내용을 입력해주세요" type="text" name="body"></textarea></div>
	<button type="submit">글쓰기</button>
	</form>
	
	<div>
		<a href="list">리스트로 돌아가기</a>
	</div>
	
</body>
</html>