<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/everyFoodWrite.css" type="text/css"/>
<script src="/js/food/everyFoodWrite.js"></script>

<div id="everyFoodWrite-container">
	<ul>
		<li>제목 : </li>
		<li>
			<input type="text" id="everyFoodWrite-input-title" placeholder="내용을 입력해 주세요"/>
		</li>
		<li>일자 : </li>
		<li>2022-05-04</li>
		<li>내용 : </li>
		<li>
			<textarea id="everyFoodWrite-input-content" rows="8" placeholder="내용을 입력해 주세요"></textarea>
		</li>
		<li></li>
		<li>
			<img src="/" class="everyFoodWrite-img" id="image1" style="display:none"/>
			<img src="/" class="everyFoodWrite-img" id="image2" style="display:none"/>
			<img src="/" class="everyFoodWrite-img" id="image3" style="display:none"/>
			<input type="file" accept="image/*" id="select" style="display:none" multiple/>
			<div class="everyFoodWrite-btn" id="everyFoodWrite-selectPicture">사진 첨부</div>
			<div class="everyFoodWrite-btn" id="everyFoodWrite-shareEveryOne">모두의 식단에 공유하기</div>
		</li>
	</ul>
</div>