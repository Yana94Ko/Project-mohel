<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/everyFoodWrite.css" type="text/css"/>
<script src="/js/food/everyFoodWrite.js"></script>

<div id="everyFoodWrite-container">
	<form method="post" action="/everyFoodWriteOk" id="everyFoodWriteFrm" enctype="multipart/form-data">
		<ul id="everyFoodWrite-contents">
			<li>제목 : </li>
			<li>
				<input type="text" name="title" id="everyFoodWrite-input-title" placeholder="내용을 입력해 주세요"/>
			</li>
			<li>일자 : </li>
			<li id="everyFoodWrite-date"></li>
			<li>내용 : </li>
			<li>
				<textarea name="contents" id="everyFoodWrite-input-content" rows="8" placeholder="내용을 입력해 주세요"></textarea>
			</li>
			<li>
				<input type="hidden" class="everyFoodWrite-data" name="meals" value="">
				<input type="hidden" class="everyFoodWrite-data" name="foodlist" value="">
				<input type="hidden" class="everyFoodWrite-data" name="foodcodes" value="">
				<input type="hidden" class="everyFoodWrite-data" name="foodcalories" value="">
			</li>
			<li>
				<img src="/" class="everyFoodWrite-img" id="image1" style="display:none"/>
				<img src="/" class="everyFoodWrite-img" id="image2" style="display:none"/>
				<img src="/" class="everyFoodWrite-img" id="image3" style="display:none"/>
				<input type="file" accept="image/*" id="select" name="images" style="display:none" multiple/>
				<div class="everyFoodWrite-btn" id="everyFoodWrite-selectPicture">사진 첨부</div>
				<div class="everyFoodWrite-btn" id="everyFoodWrite-shareEveryOne">모두의 식단에 공유하기</div>
			</li>
		</ul>
	</form>
</div>