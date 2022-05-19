<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/js/board/boardList.js"></script> 
<link href="/css/board/boardList.css" rel="stylesheet" type="text/css">
<div class="notice">
    <h3>${title}</h3>
    <table>
      <tr>
       	<th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
      <c:forEach var="vo" items="${list}">
      	<tr class="post">
        	<td><a href="/board/boardView?no=${vo.no}&category=${category}">${vo.title}</a></td>  
        	<td>${vo.nickname}</td>
        	<td>${vo.writedate}</td>
        	<td>${vo.hit}</td> 
     	 </tr>
      </c:forEach>
    </table>
    <!--  검색  -->
    <div class="btn-Box">
      <form method= "get" action="/board/boardList" id="searchFrm" >
      <input type= "hidden"  name="category" value = "${category}">
        <select name="searchKey">
          <option value="title">제목</option>
          <option value="nickname">작성자</option>  
          <option value="contents">내용</option>
        </select>
        <div class="searchWord">
          <div><label for="searchWord">🔎</label></div> 
          <input type="text" name="searchWord" id="searchWord">
        </div>
      </form>
  	<div class="paging">
  	<ul>
  	<!-- 이전 페이지 --> 
		<c:if test="${pVO.pageNum==1}">
			<li><span>&#60;</span></li>
		</c:if>
		<c:if test="${pVO.pageNum>1}">
			<li>
				<a href="/board/boardList?pageNum=${pVO.pageNum-1}&category=${category}
				<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">&#60;
				</a>
			</li>
		</c:if>
		
		<c:forEach var="p" begin="${pVO.startPage}" end="${pVO.startPage+pVO.onePageCount-1}">
			<!-- 총 페이지 수 보다 출력할 페이지 번호가 작을 때 -->
			<c:if test="${p<=pVO.totalPage}">
				<c:if test="${p==pVO.pageNum}">
					<li>
					<span class="selected">${p}</span>
					</li> 
				</c:if>
				<c:if test="${p!=pVO.pageNum}">
					<li>
					<a class= "numbering" href="/board/boardList?pageNum=${p}&category=${category}
					<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>
					">${p}</a>
					</li>
				</c:if>
			</c:if>
		</c:forEach>
		
		<!-- 다음 페이지 -->
		<c:if test="${pVO.pageNum==pVO.totalPage}">
			<li><span>&#62;</span></li> 
		</c:if>
		<c:if test="${pVO.pageNum<pVO.totalPage}">
			<li><a href="/board/boardList?pageNum=${pVO.pageNum+1}&category=${category}
			<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>
			">&#62;</a></li>
		</c:if>
		</ul>
  	</div>
      <a class="boardwrite" href="${url}/board/boardWrite?category=${category}">글쓰기</a>
    </div>
  </div>