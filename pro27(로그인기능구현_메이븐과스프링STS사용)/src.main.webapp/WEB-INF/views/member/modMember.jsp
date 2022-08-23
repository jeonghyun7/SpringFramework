<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정창</title>
<style>
	.text_center{
		text-align:center;
	}
</style>
</head>
<body>
	<form method="post" action="${contextPath}/member/modMember.do">
		<h1 class="text_center">회원 정보 수정창</h1>
	<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</p></td>
				<td width="400"><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">비밀번호</p></td>
				<td width="400"><input type="text" name="pwd"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이름</p></td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이메일</p></td>
				<td width="400"><input type="text" name="email"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">&nbsp;</p></td>
				<td width="400">
				<input type="submit" value="수정하기">
				<input type="reset" value="다시입력">
			</tr>
		</table>
</body>
</html>