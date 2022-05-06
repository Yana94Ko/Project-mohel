<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap CSS by bootswatch -->
 <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.0.1/dist/minty/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous"/>

<meta charset="UTF-8">
<style>
/*메인폼*/
#exercise-mainFrm{
	width:1000px;
	height:800px;
	border:solid gray #ddd;
	
}
	#main-title{
		text-align:center;
		margin:30px;
		position:relative;
		font-size:30px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="container" id="exercise-mainFrm">
<form method="post" action="/exercise/exerciseWriteOk" id="exerciseFrm">
  <fieldset>
    <legend id="main-title">나만의 운동 기록</legend>
    <div class="form-group row">
      <label for="title" class="col-sm-2 col-form-label">제목</label>
      <div class="col-sm-10">
        <input type="text"  class="form-control" name="title" placeholder="글의 제목을 입력하세요">
      </div>
    </div>
    <div class="form-group">
      <label for="keyword" class="form-label mt-4">오늘의 키워드</label>
      <input type="text" class="form-control" id="keyword" placeholder="#하체 #스쿼트">
      <small id="keywordhelp" class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
    </div>

    <div class="form-group">
      <label for="contents" class="form-label mt-4">내용</label>
      <textarea class="form-control" id="contents" rows="3" name="contents"></textarea>
    </div>
    <div class="form-group">
      <label for="formFile" class="form-label mt-4">사진 업로드</label>
      <input class="form-control" type="file" id="formFile" name="img1">
      <input type='hidden' name='category' id='category' value='${category}'/>
      <input type='hidden' name='nickname' id='nickname' value='${nickname}'/>
   
    </div>

    <button type="submit" class="btn btn-primary">등록하기</button>
  </fieldset>
</form>
</div>
</body>
</html>