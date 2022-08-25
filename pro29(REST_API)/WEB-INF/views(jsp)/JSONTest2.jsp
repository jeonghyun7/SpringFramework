<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<title>JSONTest2</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>  
<script>
  $(function() {
      $("#checkJson").click(function() {
      	var article = {articleNO:"114", 
	               writer:"박지성",
	               title:"안녕하세요", 
	               content:"상품 소개 글입니다."
	              };
  
  	$.ajax({
  	    //type:"POST",
        //url:"${contextPath}/boards", // 새 글을 등록하는 메소드를 호출.
        type:"PUT",
        url:"${contextPath}/boards/114", // 글번호 114번에 대해 수정을 요청.
        contentType: "application/json",
        data :JSON.stringify(article), // 글 정보를 JSON 형식으로 전송.
      success:function (data,textStatus){
          alert(data);
      },
      error:function(data,textStatus){
        alert("에러가 발생했습니다.");ㅣ
      },
      complete:function(data,textStatus){
      }
   });  //end ajax	
   });
});
</script>
</head>
<body>
  <input type="button" id="checkJson" value="새글 쓰기"/><br><br>
  <div id="output"></div>
</body>
</html>