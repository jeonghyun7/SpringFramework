<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"	
    isELIgnored="false"
%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%-- <c:set var="articlesList" value="${articlesMap.articlesList}"/>
<c:set var="totArticles" value="${articlesMap.totArticles}"/>
<c:set var="section" value="${articlesMap.section}"/>
<c:set var="pageNum" value="${articlesMap.pageNum}"/> --%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.no-uline {text-decoration:none;}
	.sel-page{text-decoration:none;color:red;}
	.cls1 {text-decoration:none;}
	.cls2 {text-align:center;font-size:30px;}
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script>
	function fn_articleForm(isLogOn,articleForm,loginForm){
		if(isLogOn != '' && isLogOn != 'false'){
			location.href=articleForm;	//로그인 상태이면 글쓰기창으로 이동합니다.
		} else{
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href=loginForm+'?action=/board/articleForm.do';
			//로그아웃 상태이면 action값으로 다음에 수행할 URL인 /board/articleForm.do를 전달하면서 로그인창으로 이동합니다.
		}
	}
</script>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>
	<c:choose>
		<c:when test="${empty articlesList }">
			<tr height="10">
				<td colspan="4">
					<p align="center">
						<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
					</p>
				</td>
			</tr>
		</c:when>
		<c:when test="${!empty articlesList }">
			<c:forEach var="article" items="${articlesList }" varStatus="articleNum">
				<tr align="center">
					<td width="5%"> ${articleNum.count } </td>
					<td width="10%">${article.id } </td>
					<td align='left' width="35%">
						<span style="padding-right:30px"></span>
						<c:choose>
							<c:when test="${article.level > 1 }">
								<c:forEach begin="1" end="${article.level }" step="1">
									<span style="padding-left:10px"></span>
								</c:forEach>
								<span style="font-size:12px;">[답변]</span>
								<a class='cls1' href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
							</c:when>
							<c:otherwise>
								<a class='cls1' href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td width="10%"><fmt:formatDate value="${article.writeDate }"/></td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
	
	<%-- <div class="cls2">
		<c:if test="${totArticles!= null }" >
			<c:choose>
				<c:when test="${totArticles >100 }"> <!-- 글 개수가 100 초과인경우 -->
					<c:forEach var="page" begin="1" end="10" step="1">
						<c:if test="${section >1 && page==1}">
							<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; pre </a>
						</c:if>
							<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
						<c:if test="${page ==10 }">
							<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nasp; next</a>
						</c:if>	
					</c:forEach>
				</c:when>
				
				<c:when test="${toArticles ==100 }"> <!-- 등록된 글 개수가 100개인 경우 -->
					<c:forEach var="page" begin="1" end="10" step="1">
						<a class="no-uline" href="#">${page }</a>
					</c:forEach>
				</c:when>
				
				<c:when test="${totArticles< 100 }"> <!-- 등록된 글 개수가 100개 미만인 경우 -->
					<c:forEach var="page" begin="1" end="${totArticles/10 +1}" step="1">
						<c:choose>
							<c:when test="${page==pageNum }">
								<a class="sel-page" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:when>
							<c:otherwise>
								<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
	</div> --%>
<!-- <a class="cls1" href="${contextPath }/board/articleForm.do"><p class="cls2">글쓰기</p></a>	-->
	<!-- 현재 로그인 상태를 함수인자로 미리 전달합니다. 로그인 상태일경우 이동할 글쓰기창 요청URL을 인자로 전달합니다. 로그인 상태가 아닐 경우 로그인창 요청 URL을 전달합니다. -->
<a class="cls1" href="javascript:fn_articleForm('${isLogOn}','${contextPath }/board/articleForm.do',
												'${contextPath }/member/loginForm.do')"><p class="cls2">글쓰기</p></a>
</body>
</html>