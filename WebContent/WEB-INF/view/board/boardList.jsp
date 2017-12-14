<%@page import="com.ede.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<% MemberDTO memberDTO=(MemberDTO)request.getSession().getAttribute("member");
   String id = memberDTO.getId();
   
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		var kind='${make.kind}';
		$(".kind").each(function(){
			if($(this).val()==kind){
				$(this).attr("selected", true);
			}
		});
		
		
		
		$(".list").click(function(){
			var cur = $(this).attr("title");
			document.frm.curPage.value=cur;
			document.frm.submit();
		});
	});
</script>

</head>
<body>
	<h1>${board}</h1>
	<div>
		<form name="frm" action="./${board}List.${board}">
			<input type="hidden" name="curPage">
			<select name="kind">
				<option class="kind" value="title">TITLE</option>
				<option class="kind" value="writer">WRITER</option>
				<option class="kind" value="contents">CONTENTS</option>
			</select>
			<input type="text" name="search" value="${make.search}">
			<button>Search</button>
		</form>
	</div>
	
	<table>
		<tr>
			<td>NUM</td>
			<td>TITLE</td>
			<td>WRITER</td>
			<td>DATE</td>
			<td>HIT</td>
		</tr>
	
		<c:forEach items="${list}" var="i">
			<tr>
				<td>${i.num}</td>
				<td>
				<c:catch>
				<c:if test="${i.depth eq 1}">
					--[${i.ref}번의 답변]
				</c:if>
				<c:if test="${i.depth gt 1}">
					<c:forEach begin="1" end="${i.depth}">--</c:forEach>
					[${i.ref}번의 추가답변]
				</c:if>
				</c:catch>				
				<a href="./${board}View.${board}?num=${i.num}">${i.title}</a>
				</td>
				<td>${i.writer} </td>
				<td>${i.reg_date}</td>
				<td>${i.hit}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${page.curBlock gt 1}">
		 <input type="button" class="list" title="${page.startNum-1}" value="[이전]">
	</c:if>
	
	<c:forEach begin="${page.startNum}" end="${page.lastNum}" var="i">
		<input type="button" class="list" title="${i}" value="${i}"> 
	</c:forEach>

	<c:if test="${page.curBlock lt page.totalBlock}">
		<input type="button" class="list" title="${page.lastNum+1}" value="[다음]">  
	</c:if>
		<a href="./${board}Write.${board}">Write</a>
</body>
</html>






