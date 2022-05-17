<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#subject{
		width:99%;
	}
	#boardFrm li{
		padding:10px 5px;
	}
</style>
<script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>
<script> 
	$(function(){
		CKEDITOR.replace("contents");
		
		$("#boardFrm").submit(function(){
			if($("#subject").val()==''){
				alert("글 제목을 입력하세요");
				return false;
			}
			if(CKEDITOR.instances.content.getData()==''){
				alert("글 내용을 입력하세요");
				return false;
			}
		});
	});
</script>
<div class="container"> 
	<h1>글 수정 폼</h1>
	<form method="post" action="/board/boardEditOk" id="boardFrm">
		<input type="hidden" name="no" value="${vo.no}"/>
		<input type="hidden" name="category" value="${category}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="title" id="title" value="${vo.title}"/></li>
			<li><textarea name="contents" id="contents">${vo.contents}</textarea></li>
			<li><input type="submit" value="글 수정"/></li>
		</ul>
	</form>
</div>