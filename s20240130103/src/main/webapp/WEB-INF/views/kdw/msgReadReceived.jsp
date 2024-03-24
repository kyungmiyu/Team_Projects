<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Blueberry</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    
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
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<!-- jQuery를 포함 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgReadReceived.css" rel="stylesheet">

<script type="text/javascript">
$(document).ready(function() {
    // 확장자에 따른 아이콘 이미지 매핑
    var fileExtensionImages = {
        'pdf': '/assets/img/kdw/pdf_icon.png',
        'docx': '/assets/img/kdw/docx_icon.png',
        'xlsx': '/assets/img/kdw/xlsx_icon.png',
        'jpg': '/assets/img/kdw/jpg_icon.png',
        'png': '/assets/img/kdw/png_icon.png',
        'txt': '/assets/img/kdw/txt_icon.png',
        'eps': '/assets/img/kdw/eps_icon.png',
        'exe': '/assets/img/kdw/exe_icon.png',
        'php': '/assets/img/kdw/php_icon.png',
        'dwg': '/assets/img/kdw/dwg_icon.png',
        'zip': '/assets/img/kdw/zip_icon.png',
        'psg': '/assets/img/kdw/psg_icon.png',
        // 추가 확장자 및 아이콘
    };

    // 파일 아이콘 업데이트 함수
    function updateFileIcons() {
        $('#files a').each(function() {
            var fileName = $(this).text(); // 파일 이름 추출
            var fileExtension = fileName.split('.').pop().toLowerCase(); // 확장자 추출
            var iconPath = fileExtensionImages[fileExtension] || '/assets/img/kdw/default_icon.png'; // 매핑된 아이콘 경로
            $(this).prepend('<img src="' + iconPath + '" class="file_icon" style="margin-right: 5px;">'); // 아이콘 삽입
        });
    }

    updateFileIcons();
});
</script>
</head>

<body>
	<!-- 여기에 헤더, 사이드바 등 공통 요소를 import-->
	<%@ include file="../header.jsp"%>
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>받은쪽지</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">쪽지</li>
					<li class="breadcrumb-item"><a href="/msgReceivebox">받은쪽지함</a></li>
					<li class="breadcrumb-item active">받은쪽지</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<!-- 쪽지쓰기 세션 부분 -->
		<div class="card">
			<section class="read-message-section">
				<div class="message-container">
					<form id="readMessage-form">
						<!-- Reply Button -->
						<div class="form-group">
							<!-- <a href="/msgReply" class="reply-btn">답장쓰기</a> -->
							<a href="/msgReply?sender=${receivedMessageInfo.msg_sender}&msgTitle=${receivedMessageInfo.msg_title}" class="reply-btn">답장쓰기</a>
						</div>
						<!-- 취소 Button : 이전 페이지로 돌아가기 -->
						<!-- Referer 헤더는 사용자가 현재 요청을 보내기 전에 어떤 페이지에서 왔는지를 식별 -->
					    <div class="form-group">
					        <a href="<%= request.getHeader("Referer") %>" class="cancel-btn">취소</a>
					    </div>
						<!-- Title -->
						<div class="form-group">
							<div class="message-title-group">
								<!-- Title TEXT -->
								<div class="message-info">${receivedMessageInfo.msg_title}</div>
							</div>
						</div>
						<!-- 보낸사람 -->
						<div class="form-group">
							<div class="message-info-group">
								<!-- Sender Text -->
								<div class="message-info-prepend">
									<span class="message-info-text">보낸사람</span>
								</div>
								<!-- Sender Display -->
								<div class="message-info" id="senderText">${senderUser.user_nic} (${senderUser.user_id})</div>
							</div>
						</div>
						<!-- 받는사람 -->
						<div class="form-group">
							<div class="message-info-group">
								<!-- Receiver Text -->
								<div class="message-info-prepend">
									<span class="message-info-text">받는사람</span>
								</div>
								<!-- Receiver Display -->
								<div class="message-info">${receiverUser.user_nic} (${receiverUser.user_id})</div>
							</div>
						</div>
						<!-- Sent Time -->
						<div class=time-group>
							<div class="time-form-group">
								<div class="message-info-group">
									<!-- Sent Time Text -->
									<div class="message-time-info-prepend">
										<span class="message-time-info-text">보낸 시간</span>
									</div>
									<!-- Sent Time Display -->
									<div class="message-time-info">${receivedMessageInfo.msg_createdate}</div>
								</div>
							</div>
							<!-- Read Time -->
							<div class="time-form-group">
								<div class="message-info-group">
									<!-- Read Time Text -->
									<div class="message-time-info-prepend">
										<span class="message-time-info-text">읽은 시간</span>
									</div>
									<!-- Read Time Display -->
									<div class="message-time-info">${receivedMessageInfo.msg_readdate}</div>
								</div>
							</div>
						</div>
						<!-- Attachments 파일첨부 -->
						<div class="form-group">
						    <div class="mb-3">
								<div class="file-attachment">
								    <div id="files">
								        <c:choose>
								            <c:when test="${not empty fileMsgs}">
								                <c:forEach items="${fileMsgs}" var="fileMsg">
								                    <a href="/downloadFile?msgNo=${fileMsg.msg_no}&fileCnt=${fileMsg.msg_file_cnt}" 
								                       target="_blank" class="file-link">${fileMsg.msg_file_user_name}</a><br/>
								                </c:forEach>
								            </c:when>
								            <c:otherwise>
								                <span style="color: #808080;">첨부된 파일이 없습니다.</span>
								            </c:otherwise>
								        </c:choose>
								    </div>
								</div>
							</div>
						</div>
						<!-- Content -->
						<div class="form-group">
							<div class="content-group">
								<%-- Message 내용부분 텍스트 or 이미지 읽기 --%>
								<c:choose>
									<c:when test="${fn:contains(receivedMessageInfo.msg_content, ',')}">
										<%-- 만약 msg_content가 콤마로 구분된 목록이라면, 이를 배열로 취급 --%>
										<c:forEach var="content"
											items="${fn:split(receivedMessageInfo.msg_content, ',')}">
											<c:choose>
												<c:when
													test="${fn:endsWith(content, '.jpg') or fn:endsWith(content, '.png')}">
													<img src="${content}" alt="Message Image">
												</c:when>
												<c:otherwise>
													<p>${content}</p>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when
												test="${fn:endsWith(receivedMessageInfo.msg_content, '.jpg') or fn:endsWith(receivedMessageInfo.msg_content, '.png')}">
												<img src="${receivedMessageInfo.msg_content}"
													alt="Message Image">
											</c:when>
											<c:otherwise>
												<p>${receivedMessageInfo.msg_content}</p>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form>
				</div>
			</section>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<!-- 스크롤하면 우측 아래 생기는 버튼 : 클릭하면 페이지의 맨 위로 이동 -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

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