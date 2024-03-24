<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Blueberry</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet" type="text/css">

<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->


<!-- ******************************************************** -->

<link href="assets/css/ykm/boardContest.css" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function() {
    // 작성자 이름 클릭 이벤트 처리
    $('.authorName').on('click', function() {
        // 현재 클릭된 요소의 사용자 ID를 데이터 속성에서 가져옴
        var userId = $(this).data('userId');
        
        // 팝업 메뉴 생성
        var popupMenu = $('<div>')
            .addClass('custom-popup')
            .css({ 
                position: 'absolute', 
                top: $(this).offset().top + $(this).outerHeight() + -15,
                left: $(this).offset().left,
                background: 'white',
                border: '1px solid #ddd',
                padding: '5px',
                'z-index': 1000
            })
            .append($('<a>').attr({
                href: '/addressaddForm?userId=' + encodeURIComponent(userId),
            }).text('주소록 추가').on('click', function(e) {
                e.preventDefault();
                window.location.href = this.href;
            }));
        $('.custom-popup').remove();
        $('body').append(popupMenu);
        $(document).on('click', function(e) {
            if (!$(e.target).is('.custom-popup') && !$(e.target).is('.authorName')) {
                $('.custom-popup').remove();
            }
        });
        return false;
    });
    
    // 주소록 추가 페이지에서 input 필드에 사용자 아이디 채우기
    if(window.location.pathname === '/addressaddForm') {
        var params = new URLSearchParams(window.location.search);
        var userId = params.get('userId');
        
        if(userId) {
            $('#inputid').val(decodeURIComponent(userId));
        }
    }
});
</script>
<style type="text/css">
.authorName:hover {
	cursor: pointer;
	text-decoration: underline;
}
</style>
</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- ======= 게시판 페이지 헤더 ======= -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>정보게시판</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">정보게시판</li>
					<li class="breadcrumb-item">공모전</li>
				</ol>
			</nav>
		</div>

		<!-- 게시판 페이지 본문 -->
		<section class="section">
			<div class="row card card-body">
				<div class="community-header">
					<span>공모전 게시판</span>
				</div>
				<div class="community-body">
					<div class="contest-title">
						<span>함께 성장할 공모전 팀원을 모집해보세요</span>
					</div>
					<!-- 검색 탭  -->
					<div class="search-bar justify-content-end">
						<form action="boardContest" method="GET" class="search-form d-flex align-items-center">
							<input type="hidden" name="comm_mid" value="${comm_mid}">
							<select class="form-select" name="type" aria-label="Default select example" required="required">
								<option value="A">전체</option>
								<option value="TC">제목+내용</option>
								<option value="W">작성자</option>
							</select> 
							<input type="text" name="keyword" placeholder="관심 스터디를 검색해보세요" title="Enter search keyword" class="keyword-bar">
							<button type="submit">
								<i class="bi bi-search"></i>
							</button>
						</form>
					</div>
				</div>

				<!-- 게시판 글 리스트 -->
				<div class="table-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col" style="width: 5%">#</th>
								<th scope="col" style="width: 45%">제목</th>
								<th scope="col" style="width: 10%">작성자</th>
								<th scope="col" style="width: 10%">날짜</th>
								<th scope="col" style="width: 10%">조회수</th>
								<th scope="col" style="width: 10%">댓글</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${getCntPostList}" var="cntPostList" varStatus="loop">
								<tr>
									<th scope="row">${stuPage.start + loop.index}</th>
									<td><a href="/post?cboard_no=${cntPostList.cboard_no}">${cntPostList.cboard_title}</a></td>
									<td class="authorName" data-user-id="${cntPostList.user_id}">${cntPostList.user_nic}</td>
									<td><fmt:formatDate value="${cntPostList.cboard_date}" pattern="yyyy-MM-dd" /></td>
									<td>${cntPostList.cboard_viewcnt}</td>
									<td>${cntPostList.reply_count}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="community-bottom">
					<!-- 글쓰기 -->
					<div class="btn-write">
						<button class="btn btn-primary custom-btn wriBtn" onclick="location.href='/writeForm?comm_mid=10&comm_big=200'">글쓰기</button>
					</div>
					
					<!-- ======= 페이지 번호 ======= -->
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:if test="${stuPage.startPage > stuPage.pageBlock}">
								<li class="page-item"><a class="page-link"
									href="/boardContest?currentPage=${stuPage.startPage - stuPage.pageBlock}&type=${type}&keyword=${keyword}">
									<span aria-hidden="true">«</span></a></li>
							</c:if>
							<c:forEach var="i" begin="${stuPage.startPage}"
								end="${stuPage.endPage}">
								<li class="page-item"><a class="page-link"
									href="/boardContest?currentPage=${i}&comm_mid=${comm_mid}&type=${type}&keyword=${keyword}">${i}</a></li>
							</c:forEach>
							<c:if test="${stuPage.endPage < stuPage.totalPages}">
								<li class="page-item"><a class="page-link"
									href="/boardContest?currentPage=${stuPage.startPage + stuPage.pageBlock}&type=${type}&keyword=${keyword}"><span
										aria-hidden="true">»</span></a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>