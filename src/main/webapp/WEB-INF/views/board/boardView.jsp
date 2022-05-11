<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/css/boardView.css" rel="stylesheet" type="text/css"> 
<div class="board-view">
    <h3>${title}</h3>  
    <div class="title">
      <h3>${vo.title}</h3> 
      <div class="writer">
        <span>${vo.nickname}</span> 
        <p>${vo.writedate}</p> 
        <p><span>조회수</span> ${vo.hit}</p>
      </div>
    </div>
	
    <div class="contents">
      <p>${vo.contents}</p> 
    </div> 

    <div class="comment-box">
      <div class="write-area">
        <span>작성자</span> <!-- 로그인시 작성자 닉네임 -->
        <form>
          <textarea cols="30" rows="3" placeholder="내용을 입력해주세요."></textarea>
          <button>등록</button>
        </form>
      </div>

      <div class="comment">
        <div class="info">
          <span>헬스25년차</span> <!-- 댓글 닉네임 -->
          <p>22.05.10</p>
        </div>
        <div class="contents">
          <p>마이프로틴 추천</p>
          <div class="btn-box">
            <button class="modify">수정</button>
            <button class="delete">삭제</button>
          </div>
        </div>
      </div>
    </div>

  </div>
	<div>
		<c:if test="${logId==vo.nickname}"> 
			<a href="/board/boardEdit?no=${vo.no}">수정</a> 
			<a href="javascript:delCheck()">삭제</a>
		</c:if>
	</div>
	<hr/>
	
	<!-- 댓글쓰기(로그인) 
	<c:if test="${logStatus=='Y'}">
		<form method="post" id="replyFrm">
			<input type="hidden" name="no" value="${vo.no}"/>
			<textarea name="coment" id="coment" style="width:500px; height:100px;"></textarea>
			<input type="submit" value="댓글등록"/>
		</form>
	</c:if>

	<div id="replyList"> 
	
	</div> -->
<