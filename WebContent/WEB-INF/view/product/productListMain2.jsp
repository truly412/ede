<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="../css/header.css" rel="stylesheet">
<link href="../css/productListMain.css" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		var type = [];
		var category;
		
		$("#dry").click(function(){
			type = [];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		$("#neutral").click(function(){
			type = [];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		$("#oily").click(function(){
			type = [];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		$("#complex").click(function(){
			type = [];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		$("#sensitive").click(function(){
			type =[];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		
		$(".category").click(function(){
			type =[];
			var category = $("input[type=radio][name=category]:checked").val()
			$(".type").each(function(){
				if($(this).prop("checked")==true) {
					type.push($(this).val());
				};
			});
			$.get("./productFilter.product?del=${del}&brand=${brand}&type="+type+"&category="+category, function(data){
				$("#result").html(data);
			});
		});
		
		<c:forEach items="${type}" var="i">
		$("#${i}").prop("checked",true);
		</c:forEach>
	});
</script>
</head>
<body>
<%@ include file="../temp/header.jsp"%>

<div id="wrap">
	<input type="hidden" name="del" value="${del}">
	<input type="hidden" name="brand" value="${brand}">
	<div id="filter">
		<label class="checkbox-inline">
		<input type="checkbox" name="type" value="dry" id="dry" class="type" checked="checked">건성
		</label>
	    <label class="checkbox-inline">
		<input type="checkbox" name="type" value="neutral" id="neutral" class="type" checked="checked">중성
		</label>
		<label class="checkbox-inline">
		<input type="checkbox" name="type" value="oily" id="oily" class="type" checked="checked">지성
		</label>
		<label class="checkbox-inline">
		<input type="checkbox" name="type" value="complex" id="complex" class="type" checked="checked">복합성
		</label>
		<label class="checkbox-inline">
		<input type="checkbox" name="type" value="sensitive" id="sensitive" class="type" checked="checked">민감성
		</label>
	</div>
	<div id="filter2">
		<label class="radio-inline">
		<input type="radio" name="category" value="reviewCount" checked="checked" class="category">댓글순
		</label>
		<label class="radio-inline">
		<input type="radio" name="category" value="avg" class="category">평점순
		</label>
	</div>
	
	<div id="result">
		<table class="table">
			<tr>
				<th>brand</th>
				<th>pro_Name</th>
				<th>pro_Price</th>
				<th>capacity</th>
				<th>evt</th>
				<th>category</th>
				<th>type</th>
				<th>avg</th>
				<th>reply</th>
			</tr>
			<c:forEach items="${list}" var="i">
			<tr class="productList">
				<td class="brand">${i.brand}</td>
				<td class="pro_name"><a href="./productView.product?pro_num=${i.pro_num}">${i.pro_name}</a></td>
				<td class="pro_price">${i.pro_price}</td>
				<td class="capacity">${i.capacity}</td>
				<td class="evt">${i.evt}</td>
				<td class="category">${i.category }
				<td class="type">${i.type }</td>
				<td class="avg">${i.avg }</td>
				<td class="reply">${i.reply }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
	<%@ include file="../temp/footer.jsp"%>
</body>
</html>