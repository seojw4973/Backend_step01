<!-- 페이지 자체의 텍스트를 UTF-8이 포함된것이고, 응답을 UTF-8처리 해주지는 않음. 별도로 처리해야함 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	
	<jsp:include page="/Header.jsp"/>


    <h1>회원 목록</h1>
    <p><a href="add">신규 회원</a></p>
    
    
    <c:forEach var='member' items="${members }">
    	${member.no },
    	<a href='update?no=${member.no }'>${member.name }</a>,
    	${member.email },
    	${member.createdDate }
    	<a href='delete?no=${member.no }'>[삭제]</a><br>
	</c:forEach>
    
    <jsp:include page="/Tail.jsp"/>
</body>
</html>










