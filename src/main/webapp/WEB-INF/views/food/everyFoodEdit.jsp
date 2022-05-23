<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/everyFoodEdit.css" type="text/css"/>
<script src="/js/food/everyFoodEdit.js"></script>

<div id="everyFoodEdit-container">
	<form method="post" action="/everyFoodEditOk" id="everyFoodEditFrm" enctype="multipart/form-data">
		<ul id="everyFoodEdit-contents">
			<li>제목 : </li>
			<li>
				<input type="text" name="title" id="everyFoodEdit-input-title" placeholder="내용을 입력해 주세요" value="${bvo.title}"/>
			</li>
			<li>일자 : </li>
			<li id="everyFoodEdit-date">${bvo.writedate}</li>
			<li>내용 : </li>
			<li>
				<textarea name="contents" id="everyFoodEdit-input-content" rows="8" placeholder="내용을 입력해 주세요">${bvo.contents}</textarea>
			</li>
			<li>
				<input type="hidden" name="no" value="${bvo.no}">
			</li>
			<li>
				<img src="/img/food/${bvo.img1}" class="everyFoodEdit-img" id="image1" style="display:block"/>
				<c:choose>
					<c:when test="${bvo.img2==null}">
						<img src="/" class="everyFoodEdit-img" id="image2" style="display:none"/>
					</c:when>
					<c:otherwise>
						<img src="img/food/${bvo.img2}" class="everyFoodEdit-img" id="image2" style="display:block"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${bvo.img3==null}">
						<img src="/" class="everyFoodEdit-img" id="image3" style="display:none"/>
					</c:when>
					<c:otherwise>
						<img src="img/food/${bvo.img3}" class="everyFoodEdit-img" id="image3" style="display:block"/>
					</c:otherwise>
				</c:choose>
				<input type="file" accept="image/*" id="select" name="images" style="display:none" multiple/>
				<div class="everyFoodEdit-btn" id="everyFoodEdit-selectPicture">사진 첨부</div>
				<div class="everyFoodEdit-btn" id="everyFoodEdit-shareEveryOne">수정 완료</div>
			</li>
		</ul>
	</form>
</div>