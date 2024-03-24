<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>글쓰기</title>
<!-- 페이지 제목은 변경하지 않았습니다. -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- jQuery CDN -->
<meta content="" name="description">
<meta content="" name="keywords">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
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
<link href="assets/css/lsl/lslboardFreeContents.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- 헤더, 푸터, 사이드바 css -->


<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<style>
@import url("https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css");
/*--------------------------------------------------------------
# community-post
--------------------------------------------------------------*/
.main-card {
  border: none;
  box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
  min-width: 660px;
  margin: 30px;
}

.card-body {
  padding-bottom: 3rem;
}

.community-post-detail * {
  font-family: "NanumSquare", sans-serif;
}

.post-header .btn-container {
  position: relative;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 0.3rem 0.5rem;
  width: 100%;
  max-width: 200px;
}

.post-header .btn-container:hover button,
.post-header .btn-container:hover i {
  color: #212121;
}

.community-post-header {
  align-items: center;
  position: relative;
  flex-grow: 1;
}

.card-title-header {
  display: flex;
  flex-direction: column;
}

.post-user-container {
  display: flex;
  align-items: center;
  padding: 0.3rem 0.5rem;
  margin-top: 0px;
}

.post-user-name {
  margin-bottom: 0px;
}

.post-user-profile {
  margin-right: 10px;
  font-size: 2em;
}

.post-updated-at {
  margin-bottom: 0px;
}

.post-subtitle {
  display: flex;
  position: relative;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
  color: #757575;
  font-size: 13px;
  margin-left: 10px;
  margin: 0;
}

.post-veiw-count {
  margin-left: 10px;
  margin-bottom: 0px;
}

/*--------------------------------------------------------------
# community-post-body
--------------------------------------------------------------*/
.community-post-header {
  background-color: #fff !important;
}

.community-post-answer {
  padding: 16px;
  border-top: 1px solid #f1f3f5;
  border-bottom: 1px solid #f1f3f5;
  background-color: #f8f9fa;
}

.community-post-header-body {
  font-size: 16px;
  padding: 16px;
  min-height: 200px;
}

.answer-info-title {
  height: 27px;
  letter-spacing: -0.3px;
  color: #495057;
  font-size: 18px;
  font-weight: 700;
}

.comment-editor {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
}

.comment-user-profile {
  margin-left: 10px;
  font-size: 2em;
}

.btn-container {
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: flex-end;
  height: 30px;
  margin-left: 16px;
  margin-right: 16px;
}

.form-control {
  margin-top: 8px;
  margin-bottom: 8px;
  margin-left: 16px;
  margin-right: 16px;
  min-height: calc(2em + 1rem + 2px);
}

.btn-container form {
  margin-left: 10px;
}

/*--------------------------------------------------------------
# comment
--------------------------------------------------------------*/
.re-comment-body {
  padding: 16px;
  border-top: 1px solid #f1f3f5;
  border-bottom: 1px solid #f1f3f5;
  background-color: #f8f9fa;
}

.markdown-body {
  margin-bottom: 0px;
}

.card-header {
  background-color: #f8f9fa;
}

.comment-header {
  display: flex;
  align-items: center;
  flex-direction: row;
}

.comment-user-profile {
  margin-left: 10px;
  font-size: 2em;
  margin-right: 16px;
}

.comment-user-name {
  margin-bottom: 5px;
}

.comment-header .re-btn-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 0.3rem 0.5rem;
  margin-left: auto;
}

.comment-updated-at {
  font-size: 13px;
}

/*--------------------------------------------------------------
# ref-comment
--------------------------------------------------------------*/
.comment-user-profile {
  margin-right: 10px;
  font-size: 2em;
}

/*--------------------------------------------------------------
# btn
--------------------------------------------------------------*/
.btn {
  position: relative;
  width: 100%;
  right: 100%;
  display: inline-block;
  border: none;
  background-color: #7e57c2;
  color: white;
}

.btn:hover {
  background-color: #9575cd;
  color: white;
}

.btn.bfcDelete {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 260px;
  bottom: 10px;
}

.btn.bfcModify {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 140px;
  bottom: 10px;
}

.btn.bfcList {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 20px;
  bottom: 10px;
}

.btn.bacReply {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  left: 20px;
  bottom: 10px;
}

.btn.bacDelete {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 260px;
  bottom: 10px;
}

.btn.bacModify {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 140px;
  bottom: 10px;
}

.btn.bacList {
  position: absolute;
  margin-top: 10px;
  width: 100px;
  right: 20px;
  bottom: 10px;
}

.submitBtn {
  position: absolute;
  width: 80px;
  right: 50px;
  margin-top: -10px;
}

.pagetitle {
  margin: 10px 0;
}

</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>


	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- ======= Main ======= -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>문의내역</h1>
		</div>
		<!-- End Page Title -->
		<section class="community-post-detail">
			<div class="row card main-card card-body">
				<div class="card-header community-post-header">
					<h3 class="card-title post-header-title">
						${askContent.admin_title}</h3>
					<div class="card-subtitle post-user-container">
						<i class="bi bi-person-circle post-user-profile"></i>
						<div class="card-title-header">
							<h5 class="card-title post-user-name">
								<a href="#">${askContent.user_nic}</a>
							</h5>
							<div class="card-subtitle post-subtitle">
								<p class="post-updated-at">
									작성일
									<fmt:formatDate value="${askContent.admin_date}"
										pattern="yyyy.MM.dd a hh:mm" />
								</p>
							</div>
							<c:if test="${askContent.admin_start ne null }">
								<div class="card-subtitle post-subtitle">
									<p class="post-updated-at">
										발생일
										<fmt:formatDate value="${askContent.admin_start}"
											pattern="yyyy.MM.dd a hh:mm" />
									</p>
								</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="community-post-header-body">
					<span class="post-content">${askContent.admin_content}</span>
				</div>

				<button type="button" class="btn bacList" onclick="goBack()">
					목록</button>

				<script>
					function goBack() {
						window.history.back();
					}
				</script>
			</div>
		</section>
		<hr />
		
		<c:if test="${askContent.admin_reply_chk eq 1 }">
			<section class="community-post-detail">
				<div class="row card main-card card-body">
					<div class="card-header community-post-header">
						<h3 class="card-title post-header-title">${askResponse.admin_title }</h3>
						<div class="card-subtitle post-user-container">
							<i class="bi bi-person-circle post-user-profile"></i>
							<div class="card-title-header">
								<h5 class="card-title post-user-name">관리자</h5>
								<div class="card-subtitle post-subtitle">
									<p class="post-updated-at">
										작성일
										 <fmt:formatDate value="${askResponse.admin_date}" pattern="yyyy.MM.dd a hh:mm"/> 
										
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="community-post-header-body">
						<span class="post-content">${askResponse.admin_content }</span>
					</div>
				</div>
			</section>
		</c:if>
		
	</main>


	<!-- ======= End Main ======= -->


	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->


	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>


	<!-- Vendor JS Files -->
	<script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script defer src="assets/vendor/chart.js/chart.umd.js"></script>
	<script defer src="assets/vendor/echarts/echarts.min.js"></script>
	<script defer src="assets/vendor/quill/quill.min.js"></script>
	<script defer
		src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script defer src="assets/vendor/php-email-form/validate.js"></script>


	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>