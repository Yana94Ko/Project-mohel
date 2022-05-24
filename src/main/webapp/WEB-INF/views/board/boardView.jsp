<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/js/board/boardView.js"></script> 
<link href="/css/board/boardView.css" rel="stylesheet" type="text/css"> 
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
	<div>
		<c:if test="${userInfo.nickname==vo.nickname}"> 
			<a class="board-btn" id="edit-btn" href="/board/boardEdit?no=${vo.no}&category=${category}">글수정</a> 
			<a class="board-btn" id="del-btn"href="javascript:delCheck()">글삭제</a>
		</c:if>
	</div>
    <div class="comment-box">
      <div class="write-area">
        <span>${userInfo.nickname}</span> <!-- 로그인시 작성자 닉네임 -->
        <form id="replyFrm">
          <input type="hidden" name="boardNo" value="${vo.no}">
          <textarea name="contents" id="replyContents"cols="30" rows="3" placeholder="내용을 입력해주세요."></textarea>
          <button>등록</button>
        </form>
      </div>
      <div class="comment" id="replyList">
        	<div class="info">
      		</div>
   	  </div>
  	</div>
</div>	
<script>
	function delCheck(){
		//confirm()은 사용자가 y, n을 선택 가능한 대화 상자
		if(confirm("글을 삭제하시겠습니까?")){
			//확인버튼 선택시
			location.href = "/board/boardDel?no=${vo.no}&category=${vo.category}";
		}
	}
	//댓글-----------------------------------------
	$(function(){
		//댓글목록
		function replyListAll(){//현재 글의 댓글을 모두 가져온다
			var url = "/mohel/reply/list";
			var params = "boardNo=${vo.no}";
			$.ajax({
				url : url,
				data : params,
				success : function(result){
					//alert(JSON.stringify(result))
					var $result = $(result);
					
					var tag = "<ul>";
					
					$result.each(function(idx, vo){
						tag += "<li><div><span class='reply-name'>"+vo.nickname+"</span>";
						tag += "("+vo.writedate+") ";
						
						if(vo.nickname=='${userInfo.nickname}'){
							tag += "<input class='replyedit-btn' type='button' value='수정'/>";
							tag += "<input class='replydel-btn' type='button' value='삭제' title='"+vo.no+"'/>";
						}
						tag += "<br/><span class='reply-contents'>"+vo.contents+"</span></div>";
						
						//본인 글일때 댓글수정 폼이 있어야 한다
						if(vo.nickname=='${userInfo.nickname}'){
							tag += "<div class='edit-box'style='display:none'><form method='post'>";
							tag += "<input type='hidden' name='no' value='"+vo.no+"'/>";
							tag += "<textarea name='contents' rows='2'>"+vo.contents+"</textarea>";
							tag += "<input type='submit' value='수정'/>";
							tag += "</form></div>";
						}
						
						tag += "<hr/></li>";
					});
					
					tag += "</ul>";
					$("#replyList").html(tag);
				},
				error : function(e){
					console.log(e.responseText);
				}
			});
		}
		//댓글 등록
		$("#replyFrm").submit(function(){
			event.preventDefault();
			if($("#replyContents").val()==""){
				alert("댓글을 입력하세요");
				return;
			}else{
				var params = $("#replyFrm").serialize();
				
				$.ajax({
					url : "/mohel/reply/writeOk",
					data : params,
					type : "POST",
					success : function(r){
						//댓글 입력 성공 후 쓰기 창을 초기화
						//alert(JSON.stringify(r))
						$("#replyContents").val("");
						
						replyListAll();
					},
					error : function(e){
						console.log(e.responseText);
					}
				});
				const contents=document.querySelector("#replyContents");
				      contents.value="";
			}
		});
		document.getElementById('replyContents').addEventListener('keydown',function(event){
		    if(event.keyCode ==13){
		    event.preventDefault();
		        document.querySelector('#replyFrm button').click();
		    }
		});
		//댓글 수정버튼 선택시 해당 댓글의 수정 폼 보여주기
		$(document).on("click","#replyList input[value=수정]",function(){
			//this : 이벤트 발생 요소
			$(this).parent().css("display", "none");//숨기기
			//보여주기
			$(this).parent().next().css("display", "block");
		});
		//댓글 수정(DB)
		
		$(document).on("submit","#replyList form",function(){
			event.preventDefault();
			//데이터
			var params = $(this).serialize();
			var url = "/mohel/reply/editOk";
			console.log(params);
			$.ajax({
				url : url,
				data : params,
				type : "POST",
				success : function(result){
					console.log(result);
					replyListAll();
				},
				error : function(){
					console.log("수정 에러 발생");
				}
			});
		});
		//댓글 삭제
		$(document).on("click", "#replyList input[value=삭제]", function(){
			if(confirm("댓글을 삭제하시겠습니까?")){
				var params = "no=" + $(this).attr("title");
				$.ajax({
					url : "/mohel/reply/del",
					data : params,
					success : function(result){
						console.log(result);
						replyListAll();
					},
					error : function(){
						console.log("댓글 삭제 에러");
					}
				});
			}
		});
		//글 상세보기 진입시 댓글리스트 출력	
		replyListAll();
	});
</script>	