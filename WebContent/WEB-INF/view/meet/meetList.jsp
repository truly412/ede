<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<h1>meetList</h1>
		
	<div>
		<form name="frm" action="./meetList.meet">
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
				<td>${i.m_num}</td>
				<td><a href="./meetView.meet?num=${i.m_num}">${i.m_title}</a></td>
				<td>${i.id} </td>
				<td>${i.start_date}</td>
				<td>${i.m_hit}</td>
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
	
	<a href="./meetWrite.meet">Write</a>
</body>
</html>