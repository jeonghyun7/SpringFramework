<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.spring.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
<style>
	.cls1 {
		font-size: 40px;
		text-align: center;
	}
	.cls2 {
		text-align: center;
	}
</style>
</head>
<body>
	<p class="cls1">회원정보</p>
	<table align="center" width="100%">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
			<td width="5%">가입일</td>
		</tr>
		<c:forEach var="member" items="${membersList}">
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.pwd}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>