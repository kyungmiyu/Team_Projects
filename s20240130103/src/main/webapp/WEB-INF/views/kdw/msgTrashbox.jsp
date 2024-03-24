<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

 <!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgTrashbox.css" rel="stylesheet">

<!-- 검색바&드롭박스 JS -->
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {


	// 체크박스
	var selectAllCheckbox = document.getElementById("select-all-checkbox");

	// 체크박스 상태 변경 확인 로직 추가
	selectAllCheckbox.addEventListener("click", function() {
	    var messageCheckboxes = document.querySelectorAll(".message-checkbox");
	    for (var i = 0; i < messageCheckboxes.length; i++) {
	        messageCheckboxes[i].checked = selectAllCheckbox.checked;
	        // 체크박스 상태 로그 출력
	        console.log("Checkbox " + i + " checked: " + messageCheckboxes[i].checked);
	    }
	});

	// '영구 삭제' 버튼 클릭 시 선택된 쪽지들의 번호를 가져옴
	var btnMsgPermanentDelete = document.querySelector(".btn-msg-permanent-delete");
	// '영구 삭제' 버튼 클릭 이벤트 핸들러 수정
	btnMsgPermanentDelete.addEventListener("click", function() {
	    var selectedMessageNos = Array.from(document.querySelectorAll(".message-checkbox:checked")).map(function(checkbox) {
	        return checkbox.getAttribute("data-msg-no");
	    });


	    if (selectedMessageNos.length === 0) {
	        alert('선택된 쪽지가 없습니다.');
	        return;
	    }

	    // 커스텀 모달 표시
	    showModal(selectedMessageNos);
	});
	
	// 영구삭제 모달
	function showModal(selectedMessages) {
	    // 모달을 표시하는 로직
	    document.getElementById('customModal').style.display = 'block';

	    // '확인' 버튼에 대한 이벤트 핸들러 설정
	    var confirmDeleteButton = document.getElementById('confirmDelete');
	    // 이전에 추가된 이벤트 리스너가 있다면 제거
	    var newConfirmButton = confirmDeleteButton.cloneNode(true);
	    confirmDeleteButton.parentNode.replaceChild(newConfirmButton, confirmDeleteButton);
	    
	    // 새로운 '확인' 버튼에 이벤트 리스너 추가
	    newConfirmButton.addEventListener('click', function() {
	        permanentDeleteMessages(selectedMessages);
	    });

	    // '취소' 버튼에 대한 이벤트 핸들러 설정 (모달 숨기기)
	    document.getElementById('cancelModal').addEventListener('click', function() {
	        document.getElementById('customModal').style.display = 'none';
	    });
	}
	
	// 서버 요청 전 데이터 상태 확인
	function permanentDeleteMessages(selectedMessages) {
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/permanentDeleteMessages', true);
	    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4) {
	            console.log("Server Response:", xhr.status, xhr.responseText);
	            if (xhr.status === 200) {
	                alert('쪽지가 성공적으로 영구 삭제되었습니다.');
	                location.reload();
	            } else {
	                alert('쪽지 영구 삭제에 실패했습니다.');
	            }
	        }
	    };
	    
	    var data = { msgNos: selectedMessages.map(Number) };
	    console.log("Sending Data:", JSON.stringify(data));  // 전송 데이터 상태 확인 로그
	    xhr.send(JSON.stringify(data));
	    // 모달 숨기기
	    document.getElementById('customModal').style.display = 'none';
	}
});
// 페이징 << >> 버튼 더이상 쪽지 없을시 비활성화
document.addEventListener('DOMContentLoaded', function () {
    // 'disabled' 클래스를 가진 모든 페이지 링크에 대해 클릭 이벤트를 방지합니다.
    document.querySelectorAll('.pagination .disabled a').forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
        });
    });
});
</script>

</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>
	
	
	<!-- ======= 받은 쪽지함 Main ======= -->
	<main id="main" class="main">

		<!-- 받은 쪽지함 pageTitle -->
		<div class="pagetitle">
			<h1>휴지통</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">쪽지</li>
					<li class="breadcrumb-item active"><a href="msgTrashbox">휴지통</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<div class="card">
			<!-- 받은 쪽지함 세션 부분 -->
			<section class="receivebox-section">

				<!-- 쪽지 쓰기 -->
				<div class="msg-create">
					<a href="/msgWrite" class="msg-create-btn">쪽지 쓰기</a>
				</div>
				<!-- 읽은 쪽지 개수와 전체 받은 쪽지 개수를 표시하는 영역 -->
				<div id="noteCount" class="note-count">
				    전체쪽지&nbsp;&nbsp;<span id="readCount">${totUnreadTrashMsgCnt }</span>/<span id="totalCount">${totTrashMsgCnt }</span>
				</div>
				<!-- 검색바&드롭박스 -->
				<div class="search-container">
					<div class="search-bar">
				        <form class="search-form d-flex align-items-center" 
				        	method="get" action="/msgTrashbox">
				            <select id="dropdownSelect" name="type">
								<option value="all">전체</option>
								<option value="sender">보낸사람</option>
								<option value="receiver">받는사람</option>
								<option value="titleContent">제목+내용</option>
							</select>	
							<input type="text" name="keyword" placeholder="Search" title="Enter search keyword">
				            <button type="submit" title="Search">
				                <i class="bi bi-search"></i>
				            </button>
						</form>
					</div>
				</div>
				<!-- 검색바&드롭박스 END -->
				<!-- 툴바와 테이블 -->
				<div class="table-container">
					<table class="table">
						<thead>
							<tr>
		                       <th>
		                            <input type="checkbox" id="select-all-checkbox">
		                        </th>
								<th scope="col" class="readStatus">읽음</th>
								<th scope="col" class="attachment">첨부</th>
								<th scope="col" class="mailType">유형</th>
								<th scope="col" class="author">아이디</th>
								<th scope="col" class="subject">제목</th>
								<th scope="col" class="date">일시</th>
							</tr>
						</thead>
						<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
						<tbody id="mailList">
						    <c:forEach var="message" items="${trashMessages}">
						    	<!-- msg_readdate(읽음여부)가 'null'이라면 굵기 800 아니면 500 -->
						        <tr class="list-item" style="font-weight: ${empty message.msg_readdate ? '800' : '500'};">
						        	<!-- 체크박스 -->
						            <td><input type="checkbox" class="message-checkbox"
										data-msg-no="${message.msg_no}"></td>
								    <td class="readStatus">
								        <c:choose>
								            <c:when test="${message.msg_readdate ne null}">
								                <img src="assets/img/kdw/msg-read-icon.png" width="15" height="15">
								            </c:when>
								            <c:otherwise>
								                <img src="assets/img/kdw/msg-unread-icon.png" width="15" height="16">
								            </c:otherwise>
								        </c:choose>
								    </td>
									<!-- 파일 첨부 이미지 표시 -->
							        <td class="attachment" style="text-align: center; vertical-align: middle;">
							            <c:if test="${not empty message.first_file_name}">
							                <img src="assets/img/kdw/attachments.png" width="17" height="17">
							            </c:if>
							        </td>
									<td class="mailType">
										<!-- 로그인유저와 보낸사람이(user_no = msg_sender) 같다면 -->
									    <c:choose>
									        <c:when test="${message.msg_sender == trashboxUserNo}">
									            발신
									        </c:when>
									        <c:otherwise>
									            수신
									        </c:otherwise>
									    </c:choose>
									</td>
									<td class="author">
										<!-- 로그인유저와 보낸사람이(user_no = msg_sender) 같다면 -->
									    <c:choose>
									        <c:when test="${message.msg_sender == trashboxUserNo}">
									            ${message.trashedNic}
									        </c:when>
									        <c:otherwise>
									            ${message.trashedNic}
									        </c:otherwise>
									    </c:choose>
									</td>
								    <!-- 제목 -->
								    <!-- 보낸사람(로그인) == trashboxUserNo(로그인) 참일시 보낸쪽지 읽기페이지 -->
									<td class="subject" 
									    <c:choose>
									        <c:when test="${message.msg_sender == trashboxUserNo}">
									            onclick="location.href='/msgReadSent?msg_no=${message.msg_no}'"
									        </c:when>
									        <c:otherwise>
									            onclick="location.href='/msgReadReceived?msg_no=${message.msg_no}'"
									        </c:otherwise>
									    </c:choose>>
									    ${message.msg_title}
									</td>
						            <td class="date">${message.msg_createdate}</td>
						        </tr>
						    </c:forEach>
						</tbody>
						<tbody class="mailList-whiteSpace">
							<tr>
								<td colspan="7"></td>
							</tr>
						</tbody>
					</table>
					<!-- 영구삭제버튼 클릭시 커스텀 모달 생성 -->
					<div id="customModal" class="delete-modal">
					    <div class="modal-content">
					        <span class="modal-header">
					            <i class="bi bi-exclamation-lg"></i>
					            <i class="bi bi bi-circle-fill"></i>
					        </span>
					        <div class="modal-body">
					            <p>휴지통의 메일을 지우면 지워진 메일들은 복구할 수 없습니다.<br>메일을 삭제하시겠습니까?</p>
					        </div>
					        <div class="modal-footer">
					            <button id="cancelModal" class="btn-cancel">취소</button>
					            <button id="confirmDelete" class="btn-confirm">확인</button>
					        </div>
					    </div>
					</div>
				</div>
			</section>

			<!-- 받은 쪽지함 세션 END -->
			<!-- 리스트 하단 버튼 -->
			<div class="btn-container">
				<button type="button" id="showModal" class="btn-msg-permanent-delete">영구 삭제</button>
			</div>
			<!-- 리스트 번호 -->
			<nav aria-label="Page navigation"
				class="msgTrashbox-pagination-container">
			    <ul class="pagination">
			        <li class="page-item ${page.startPage <= 1 ? 'disabled' : ''}">
			            <a class="page-link prev-page" 
			            href="?currentPage=${page.startPage > 1 ? page.startPage - 1 : '#'}&keyword=${keyword}&type=${tpye}" aria-label="Previous">
			                <span aria-hidden="true">&laquo;</span>
			            </a>
			        </li>
			
			        <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
			            <li class="page-item ${i == page.currentPage ? 'active' : ''}">
			                <a class="page-link" href="?currentPage=${i}&keyword=${keyword}&type=${tpye}">${i}</a>
			            </li>
			        </c:forEach>
			
			        <li class="page-item ${page.endPage >= page.totalPage ? 'disabled' : ''}">
			            <a class="page-link next-page" 
			            href="?currentPage=${page.endPage < page.totalPage ? page.endPage + 1 : '#'}&keyword=${keyword}&type=${tpye}" aria-label="Next">
			                <span aria-hidden="true">&raquo;</span>
			            </a>
			        </li>
			    </ul>
			</nav>
		</div> <!-- card END -->
	</main>
	<!-- 받은 쪽지함 Main END-->


	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>




	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>


</body>
</html>
