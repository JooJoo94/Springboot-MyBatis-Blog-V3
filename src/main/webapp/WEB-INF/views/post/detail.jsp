<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<div class="card">
		<div class="card-header">
			<h4 class="card-title">${post.title}</h4>
		</div>
		<div class="card-body">
			<p class="card-text">${post.content}</p>
		</div>
		<div class="card-footer">

			<c:if test="${post.userId eq sessionScope.principal.id}">
				<a href="/post/update/${post.id}" class="btn btn-warning">수정</a>
				<button id="post--delete--submit" value="${post.id}" class="btn btn-danger">삭제</button>
			</c:if>

			<a href="/" class="btn btn-primary">목록</a>
		</div>
	</div>

	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-body">
				<input type="hidden" id="postId" value="${post.id}" /> <input type="hidden" id="userId" value="${sessionScope.principal.id}" />
				<textarea class="form-control" rows="2" id="content"></textarea>
			</div>
			<div class="card-footer">
				<button id="comment--save--submit" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>

	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글 리스트</h4>
			</div>
			
			<ul id="comment--items" class="list-group">
				<li id="comment--item--1" class="list-group-item d-flex justify-content-between align-items-center"> 
					<div class="font-italic">첫번째 댓글입니다.</div>
					<div class="badge badge-warning badge-pill ml-auto">작성자:COS</div>
					<button onclick="commentDelete(1)" class="badge badge-danger badge-pill">삭제</button>
				</li>
			</ul>

		</div>
	</div>

</div>

<script src="/js/detail.js"></script>

<%@include file="../include/footer.jsp"%>









