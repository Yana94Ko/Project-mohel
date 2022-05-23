<nav class="switch">
	<a href="/mypage/myWrite">게시글</a>
	<a href="/mypage/myComment">댓글</a>
	<select id="selectCategory" class="switch-select">
		<option value="" <c:if test="${category==null}">selected</c:if>>전체</option>
		<option value="everyMeal" <c:if test="${category=='everyMeal'}">selected</c:if>>모두의 식단</option>
		<option value="myExercise" <c:if test="${category=='myExercise'}">selected</c:if>>나만의 운동</option>
		<option value="free" <c:if test="${category=='free'}">selected</c:if>>자유 게시판</option>
		<option value="challenge" <c:if test="${category=='challenge'}">selected</c:if>>챌린지 게시판</option>
		<option value="ba" <c:if test="${category=='ba'}">selected</c:if>>Before&After</option>
	</select>
</nav>