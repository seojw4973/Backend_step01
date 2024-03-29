<!-- 페이지 자체의 텍스트를 UTF-8이 포함된것이고, 응답을 UTF-8처리 해주지는 않음. 별도로 처리해야함 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
    <h1>회원 목록</h1>
    <p><a href="add">신규 회원</a></p>
    <%
        List<Member> members = (List<Member>)request.getAttribute("members");
    	for(Member member : members){
    %>
    	<%=member.getNo() %>,
    	<a href='update?no=<%=member.getNo() %>'><%=member.getName() %></a>,
    	<%=member.getEmail() %>,
    	<%=member.getCreatedDate() %>
    	<a href='delete?no=<%=member.getNo() %>'>[삭제]</a><br>
    <%
    	}

    %>
</body>
</html>










