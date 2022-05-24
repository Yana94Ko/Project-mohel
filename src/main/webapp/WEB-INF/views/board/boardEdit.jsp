<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/js/board/boardWrite.js"></script>
<script src="https://cdn.ckeditor.com/4.17.2/basic/ckeditor.js"></script>
<link href="/css/board/boardWrite.css" rel="stylesheet" type="text/css"> 
<div class="board-container"> 
	<h1>글쓰기</h1>
	<form method="post" action="/board/boardEditOk" id="boardFrm">
		<input type="hidden" name="no" value="${vo.no}"/>
		<input type="hidden" name="category" value="${category}"/>
		<ul>
			<li><input type="text" name="title" id="title" value="${vo.title}"/></li>
			<li><textarea name="contents" id="contents">${vo.contents}</textarea></li>
			<li><input type="submit" value="확인"/><input type="button" value="취소" onclick="history.back()"></li>
		</ul>
	</form>
</div>