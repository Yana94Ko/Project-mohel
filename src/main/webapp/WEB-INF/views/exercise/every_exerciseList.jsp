<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap CSS by bootswatch -->
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.0.1/dist/minty/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<style>

/*메인*/
#main_text {
	margin: 30px;
	text-align: center;
}

#recruitment {
	width: 400px;
	left: 450px;
	right: 0;
	margin: auto;
	position: relative;
}
/*모두의 운동리스트*/
#every_exercise_img {
	width: 300px;
	height: 320px;
}

#thumbnail-list {
	margin: 30px;
	width:1300px;
	height:100%;
}

.service_item {
	position: relative;
	left: 200px;
	float:left;
	margin:30px;
}
#thumbnail-text{
	width:300px;
	height:200px;
}
/*검색바*/
.search-bar {
	width: 500px;
	height: 50px;
	position: relative;
	left: 15px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h1 id='main_text'>모두의 운동</h1>

		<form id="search-box">
			<div class="mx-auto mt-5 search-bar input-group mb-3">
				<input name="q" type="text" class="form-control rounded-pill"
					placeholder="함께하고 싶은 운동을 검색해보세요">
			</div>
		</form>
		<div id="recruitment_box">
			<button type="submit" id="recruitment" class="btn btn-primary"
				onclick="location.href='/exercise/every_exerciseWrite'">운동원 모집하기</button>
		</div>
	</div>

	<div  id="thumbnail-list">
		<c:forEach var="vo" items="${lst }">
			<div id="wrap">
				<div class="service_item"
					onclick="location.href='/exercise/every_exerciseView?no=${vo.no}'">
					<h6 class="m-top-30">
						<img src="../img/exercise/22.png"
							onmouseover="this.style.transform='scale(1.2)'; this.style.transition='all 0.5s'"
							onmouseleave="this.style.transform='scale(1)'"
							id="every_exercise_img">
					</h6>
					<div class="separator_small"></div>
					<p id="thumbnail-text">
						<strong>${vo.title }</strong><br />${vo.contents}<br />${vo.startdate } - ${vo.enddate }
						<br/>${vo.placeinfo}<br/>참가자 수 ${vo.applicantCnt} / 최대 인원 ${vo.applicantMax }<br/>조회수 ${vo.hit}
					</p>
				</div>
			</div>
		</c:forEach>

	</div>

</body>
</html>