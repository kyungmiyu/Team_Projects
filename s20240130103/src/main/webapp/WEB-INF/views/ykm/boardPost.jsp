<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar","\n"); %>
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

<!-- 댓글, 대댓글, 삭제 확인 팝업 JS File -->
<script defer src="assets/js/ykm/ykmBoard.js"></script>

<!-- 화면 css -->
<link href="assets/css/ykm/boardPost.css" rel="stylesheet">

<script>
	window.onload = function() {
		getCommentList(${getPost.cboard_no});
		getUserInfo();
	};
</script>

</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- 게시판 페이지 헤더 -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>정보게시판</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">정보게시판</li>
					<c:choose>
						<c:when test="${comm_mid eq 10}">
							<li class="breadcrumb-item"><a href="boardContest">공모전</a></li>
						</c:when>
						<c:when test="${comm_mid eq 20}">
							<li class="breadcrumb-item"><a href="boardStudy">스터디</a></li>
							<li class="breadcrumb-item">글수정</li>
						</c:when>
					</c:choose>
				</ol>
			</nav>
		</div>
		<!-- 게시판 페이지 본문 -->
		<section class="community-post-detail">
			<div class="row card card-body">
				<div class="card-header community-post-header">
					<div class="sider-header">
						<h3 class="card-title post-title">${getPost.cboard_title}</h3>
						<div class="sider_right">
							<input type="hidden" name="cboard_no" value="${getPost.cboard_no}" /> 
							<input type="hidden" name="comm_mid2" value="${getPost.comm_mid2}" />
							<c:if test="${sessionScope.user_no ne getPost.user_no}">
								<button class="recruitBtn" name="comm_mid2" value="${getPost.comm_mid2}" disabled>
									${getPost.comm_mid2 == 10 ? '모집 중' : '모집 완료'}</button>
							</c:if>
							<c:if test="${sessionScope.user_no eq getPost.user_no}">
								<button class="recruitBtn" name="comm_mid2" value="${getPost.comm_mid2}">
									${getPost.comm_mid2 == 10 ? '모집 중' : '모집 완료'}
								</button>
							</c:if>
							<c:if test="${sessionScope.user_no == getPost.user_no}">
								<div class="tooltip">모집완료 상태로 바꾸려면 클릭</div>
							</c:if>
						</div>
					</div>
					<div class="card-subtitle post-user-container">
						<c:choose>
							<c:when test="${getPost.user_profile ne null}">
								<img class="rounded-circle post-user-profile"
									src="${pageContext.request.contextPath}/upload/userImg/${getPost.user_profile}" alt="유저 프로필"></img>
							</c:when>
							<c:otherwise>
								<img class="rounded-circle post-user-profile"
									src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="유저 프로필" 
									></img>
							</c:otherwise>
						</c:choose>
						<div class="card-title-header">
							<h5 class="card-title post-user-info post-user-name"> ${getPost.user_nic}</h5>
							<div class="card-subtitle post-subtitle">
								<p class="post-updated-at"> 작성일
									<fmt:formatDate value="${getPost.cboard_date}" pattern="yyyy.MM.dd a hh:mm" />
								</p>
								<p class="post-veiw-count">조회수 ${getPost.cboard_viewcnt}</p>
							</div>
						</div>

						<c:if test="${sessionScope.user_no == getPost.user_no}">
							<div class="modify-delete-container">
								<a href="/updateForm?cboard_no=${getPost.cboard_no}" class="badge bg-light text-dark"> 
									<i class="bi bi-highlighter"></i> 수정
								</a>
								<button type="button" id="postDeleteBtn" class="badge bg-light text-dark">
									<i class="bi bi-trash"></i> 삭제
								</button>
							</div>
						</c:if>
					</div>
				</div>

				<div class="community-post-body">
					<table class="table-file list-group">
						<c:forEach items="${getFileList}" var="file">
							<tr>
								<td><a href="/fileDownload?cboard_file_name=${file.cboard_file_name}&cboard_file_user_name=${file.cboard_file_user_name}"
										class="list-group-item list-group-item-action">
									<i class="bi bi-file-earmark-arrow-down"></i>${file.cboard_file_user_name}</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<span class="post-content">${fn:replace(getPost.cboard_content, replaceChar, "<br/>")}</span>
				</div>
				<div class="community-post-answer">
					<div class="comment-form">
						댓글 <span id="answer-count" class="answer-count">${countComment}</span>
					</div>

					<!-- 댓글 등록 폼 -->
					<div class="boardPostComment">
						<input type="hidden" name="cboard_no" value="${getPost.cboard_no}" />
						<input type="hidden" name="user_no" value="${getPost.user_no}">
						<input type="hidden" name="creply_no" value="" />
						<!-- 유저 프로필 -->
						<div class="comment-editor">
							<c:choose>
								<c:when test="${user_profile !=null&& sessionScope.user_no == user_no}">
									<img class="rounded-circle" alt="유저 프로필" 
											src="${pageContext.request.contextPath}/upload/userImg/${user_profile}"></img>
								</c:when>
								<c:otherwise>
									<img class="rounded-circle"
										src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
										alt="유저 프로필"></img>
								</c:otherwise>
							</c:choose>
							<input type="text" name="comment" id="creply_content" class="form-control" required="required"
									placeholder="${sessionScope.user_nic}님, 댓글을 작성해보세요.">
						</div>
						<div class="btn-container">
							<button id="commentResetBtn" type="button" class="hidden btn btn-secondary">취소</button>
							<button id="commentSubmitBtn" type="submit" class="hidden btn btn-primary">등록</button>
						</div>
					</div>
				</div>
				<!-- 댓글 JavaScript -->
				<div id="commentContainer">

					<!-- AJAX 비동기 화면 갱신 -->

					<!-- 대댓글 -->
				</div>
				<!-- ======= 삭제 확인 pop up ======= -->
				<div class="card card-body confirmPopup">
					<div class="popup-container">
						<i class="bi bi-exclamation-triangle popup-icon"></i> 
						<span class="popup1">정말로 삭제하시겠습니까?</span> 
						<span class="popup2">확인을 누르면 글이 삭제됩니다.</span>
					</div>
					<div class="confirm-deletion">
						<button type="button" id="cancelButton" class="btn btn-outline-dark">취소</button>
						<a href="/deletePost?cboard_no=${getPost.cboard_no}&comm_mid=${getPost.comm_mid}">
							<button type="button" id="confirmButton" class="btn btn-primary">확인</button>
						</a>
					</div>
				</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>
