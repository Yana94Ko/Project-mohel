<link rel="stylesheet" href="/css/mypage/userDel.css" type="text/css">
<script src="/js/mypage/userDel.js"></script>
<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>
	
	<div class="del-info">
	<div class="del-head">
	<h3><img src="">회원탈퇴</h3>
	<p>계정을 삭제 하시겠습니까?<br>계정을 삭제하면 회원님의 모든 컨텐츠와 활동 기록이<a class="del-head-a"> 전부 삭제됩니다.</a><br>삭제된 정보는 복구할 수 없으니 신중하게 결정해 주세요.</p>
	</div>
	<div class="del-middle">
		<h3> 회원탈퇴 사유</h3>
		<p>회원 탈퇴 사유를 알려주세요. 서비스 개선에 중요한 자료로 활용하겠습니다.</p>
		<form id="delFrm" method="post" action="/mypage/userDelOk">
			<ul>
				<li>
					<input id="delReason1" name="reason" type="radio" value="1" checked>
					<label for="delReason1"> 컨텐츠 등 이용할만한 서비스 부족</label>
				</li>
				<li>
					<input id="delReason2" name="reason" type="radio" value="2">
					<label for="delReason2"> 사이트 속도 및 안정성 불만</label>
				</li>
				<li>
					<input id="delReason3" name="reason" type="radio" value="3">
					<label for="delReason3"> 사이트 이용 불편</label>
				</li>
				<li>
					<input id="delReason4" name="reason" type="radio" value="4">
					<label for="delReason4"> 다른 사이트가 더 좋아서</label>
				</li>
				<li>
					<input id="delReason5" name="reason" type="radio" value="5">
					<label for="delReason5"> 기타</label><br>
					<textarea name="reasonText" id="delReason5Text" class="del-reason-input" placeholder="기타 사유를 적어주세요." disabled></textarea>
				</li>
				
				<c:if test="${kakao!='true' }">
					<li><h3> 비밀번호 입력</h3></li>
					<li><input id="pwd" name="pwd" class="del-pwd-input" type="password" placeholder="사용중인 비밀번호를 입력해주세요. "><br></li>
				</c:if>
				
			</ul>
			<button class="del-button">탈퇴하기</button>
		</form>
	</div>
</div>
</div>