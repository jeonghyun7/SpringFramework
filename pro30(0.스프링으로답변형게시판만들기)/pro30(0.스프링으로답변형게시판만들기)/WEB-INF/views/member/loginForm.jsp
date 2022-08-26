<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<c:choose>
	<c:when test="${result=='loginFailed'}">
		<script>
			window.onload=function(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인하세요!");
			}
		</script>
	</c:when>
</c:choose>
</head>
<body>
	<form method="post" action="${contextPath}/member/login.do">
		<table border="1" align="center" width="80%">
			<tr align="center" bgcolor="lightblue">
				<td><b>아이디</b></td>
				<td><b>비밀번호</b></td>
			</tr>
			<tr align="center">
				<td> <input type="text" name="id" size="10"></td>
				<td> <input type="password" name="pwd" size="10"></td>
			</tr>
			<tr align="center">
				<td>
					<input type="submit" value="로그인">
				</td>
				<td>	
					<input type="reset" value="다시입력">
				</td>
		</table>
	</form>

</body>
</html>