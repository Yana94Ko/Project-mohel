<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<link href="${url}/css/exercise/exerciseEdit.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
		src="${url}/js/exercise/exerciseEdit.js"></script>
<meta charset="UTF-8">
<style>

</style>


<title>Insert title here</title>
</head>
<body>
	<div class="container" id="exercise-mainFrm">
		<form method="post" action="/exercise/exerciseEditOk"
			id="exerciseFrm" enctype="multipart/form-data">
			<fieldset>
				<legend id="main-title">나만의 운동 기록</legend>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="title" id="title"
							value="${vo.title }">
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label for="keyword" class="form-label mt-4">오늘의 키워드</label> <input
						type="text" class="form-control" id="keyword"
						placeholder="#하체 #스쿼트"> <small id="keywordhelp"
						class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
				</div>
				 -->

				<div class="form-group">
					<label for="contents" class="form-label mt-4">내용</label>
					<textarea class="form-control" id="contents" rows="3"
						name="contents" >${vo.contents}</textarea>
				</div>
				<div class="form-group">
					<label for="formFile" class="form-label mt-4">사진 업로드</label> <input
						class="form-control" type="file" id="formFile" name="filename">
					<input type='hidden' name='category' id='category'
						value='${category}' /> 

					<input type='hidden' name='nickname'
						id='nickname' value='${nickname}' />
						<input type='hidden' name='no' value='${vo.no }'/>
					<img src='/img/exercise/${vo.img1}' id="preview" name="img1" class="rounded"/>
				</div>


					<button class="btn btn-warning">수정완료</button>
					<input type="hidden" name="no" value="${vo.no }"/>
					<button type="button" class="btn btn-primary" onclick="location.href='/exercise/exerciseList' " id="exerciselist-btn">나만의 운동목록</button>
			</fieldset>
		</form>
	</div>
</body>
</html>