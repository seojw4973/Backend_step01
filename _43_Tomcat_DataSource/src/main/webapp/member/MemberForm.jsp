<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
</head>
<body>
	<h1>회원 등록</h1>
	<form action='add' method='post'>
	<p>이름 : <input type='text' name='name'></p>
	<p>이메일 : <input type='text' name='email'></p>
	<p>암호 : <input type='text' name='password'></p>
	<input type='submit' value='추가'>
	<input type='reset' value='취소'>
	
	</form>
</body>
</html>