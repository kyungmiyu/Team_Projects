<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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
<link href="assets/css/lsl/lslboardAsk.css" rel="stylesheet">


<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

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


	<!-- ======= Header ======= -->

	<%@ include file="../header.jsp"%>

	<!-- ======= Side bar ======= -->
	<%@ include file="../asidebar.jsp"%>


	<!-- ======= Page Title ======= -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>정보 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">정보 게시판</li>
					<li class="breadcrumb-item">질문 게시판</li>
				</ol>
			</nav>
		</div>

		<div class="row justify-content-center">
			<div class="col-lg-13">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">질문 게시판</h5>


						<!-- Search Bar -->
						<form action="boardAskSearch"
							class="search-form d-flex align-items-center">
							<div class="search-bar">
								<select class="form-select" id="searchSelect" name="type"
									method="get">
									<option value="all">전체</option>
									<option value="writer">작성자</option>
									<option value="titleContent">제목+내용</option>
								</select> <input type="text" name="keyword" placeholder="키워드를 입력하세요"
									title="Enter search keyword">
								<button type="submit" title="Search">
									<i class="bi bi-search"></i>
								</button>
							</div>
						</form>


						<!-- 게시판 테이블 -->
						<table class="table table-hover">
							<thead>
								<tr class="list">
									<th scope="col" class="board-number">#</th>
									<th scope="col" class="title-column">제목</th>
									<th scope="col" class="short-column">작성자</th>
									<th scope="col" class="short-column">작성일</th>
									<th scope="col" class="short-column">조회수</th>
									<th scope="col" class="short-column">댓글</th>
								</tr>
							</thead>
							<tbody class="listTbody">
								<c:forEach items="${boardAskList}" var="boardAsk"
									varStatus="loop">
									<tr>
										<th scope="row">${bapage.start + loop.index}</th>
										<td><a
											href="boardAskContents?cboard_no=${boardAsk.cboard_no}">${boardAsk.cboard_title}</a></td>
										<td class="authorName" data-user-id="${boardAsk.user_id}">${boardAsk.user_nic}</td>
										<td><fmt:formatDate value="${boardAsk.cboard_date}"
												pattern="yyyy-MM-dd" /></td>
										<td>${boardAsk.cboard_viewcnt}</td>
										<td>${boardAsk.creply_cnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>



						<div class="text-end">
							<a href="boardAskWrite">
								<button type="button" class="btn baWrite">글쓰기</button>
							</a>
						</div>

						<!-- 페이지 표시 -->
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
								<c:if test="${bapage.startPage > bapage.pageBlock}">
									<li class="page-item"><a class="page-link"
										href="boardAsk?currentPage=${bapage.startPage - bapage.pageBlock}&keyword=${keyword}&type=${tpye}"
										aria-label="Previous"> <span aria-hidden="true">«</span></a></li>
								</c:if>

								<c:forEach var="i" begin="${bapage.startPage}"
									end="${bapage.endPage}">
									<li class="page-item"><a class="page-link"
										href="boardAsk?currentPage=${i}&keyword=${keyword}&type=${type}">${i}</a></li>
								</c:forEach>

								<c:if test="${bapage.endPage < bapage.totalPage}">
									<li class="page-item"><a class="page-link"
										href="boardAsk?currentPage=${bapage.startPage+bapage.pageBlock}&keyword=${keyword}&type=${type}"
										aria-label="Next"><span aria-hidden="true">»</span></a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</main>




	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>


	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>

</html>