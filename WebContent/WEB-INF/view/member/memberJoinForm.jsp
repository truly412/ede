<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#nn").click(function(){
			 window.open("../MemberAddrsearch.jsp", "", "top=300, left=750, width=400, height=400");
		});
		
	});
</script>
</head>
<body>

	<h1>Member Join</h1>
	<form action="./memberJoin.member" method="post">
	<p>아이디: <input type="text" name="id"></p>
	<p>비밀번호: <input type="password" name="pw"></p>
	<p>비밀번호 확인 : <input type="password"></p>
	<p>이름: <input type="text" name="name"></p>
	<p>닉네임:<input type="text" name="nickname"></p>
	<p>이메일: <input type="email" name="email"></p>
	<p>핸 드 폰번호  <input type="text" name="phone"></p>
	<p>성별: F <input type="radio" checked="checked" name="gender" value="F">
	   M <input type="radio" name="gender" value="M"></p>
	<p>생년월일:<input type="date" name="birth"></p>
	<p>주소:<input type="text" name="addr"><input type="button" value="addr" id="nn"></p>
	<input type="submit" value="Join" id="btn">
	</form>	
</body>
</html>