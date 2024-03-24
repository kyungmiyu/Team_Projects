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
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
<!-- jQuery를 포함 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgReceivebox.css" rel="stylesheet">

<!-- 검색바&드롭박스 JS -->
<script type="text/javascript">
	
	document.addEventListener('DOMContentLoaded', function() {
		
		// ================ 체크박스 ===================
		var selectAllCheckbox = document.getElementById("select-all-checkbox");

		selectAllCheckbox.addEventListener("click", function() {
			var messageCheckboxes = document
					.querySelectorAll(".message-checkbox");

			for (var i = 0; i < messageCheckboxes.length; i++) {
				messageCheckboxes[i].checked = selectAllCheckbox.checked;
			}
		});

		// 보관 버튼 클릭 시 선택된 쪽지들의 번호를 가져옴
		var btnMsgStorebox = document.querySelector(".btn-msg-storebox");
		btnMsgStorebox.addEventListener("click", function() {
		    // selectedMessageNos를 정의
		    var selectedMessageNos = Array.from(
		            document.querySelectorAll(".message-checkbox:checked"))
		            .map(function(checkbox) {
		                return checkbox.getAttribute("data-msg-no");
		            });
		
		    // 선택된 쪽지가 없을 경우 함수 실행 중단
		    if (selectedMessageNos.length === 0) {
		        alert('선택된 쪽지가 없습니다.');
		        return; // 여기서 함수 실행을 중단
		    }
		
		    console.log("Selected Message Nos:", selectedMessageNos);
		
		    // 선택된 메시지들의 번호를 서버로 보내어 업데이트하는 함수 호출
		    updateMsgStoreStatus(selectedMessageNos);
		});

		function updateMsgStoreStatus(selectedMessages) {
		    var xhr = new XMLHttpRequest();
		    xhr.open('POST', '/updateMsgStoreStatus', true);
		    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

		    xhr.onreadystatechange = function() {
		        if (xhr.readyState === 4) {
		            console.log("Server Response:", xhr.status, xhr.responseText); // 응답 상태 콘솔에 출력

		            if (xhr.status === 200) {
		                // 성공적으로 업데이트된 경우의 처리를 여기에 추가합니다.
		                alert('쪽지가 성공적으로 보관되었습니다.');
		                // 알림창 확인 시 화면을 새로고침
		                location.reload();
		            } else {
		                alert('쪽지 보관에 실패했습니다.');
		            }
		        }
		    };

		    var data = {
		        msgNos : selectedMessages.map(Number)
		    };

		    xhr.send(JSON.stringify(data));
		}
		
		
		//휴지통으로 보내기
		//삭제 버튼 클릭 시 선택된 쪽지들의 번호를 가져옴
		var btnMsgTrashbox = document.querySelector(".btn-msg-trashbox");
		btnMsgTrashbox.addEventListener("click", function() {
		    // selectedMessageNos를 정의
		    var selectedMessageNos = Array.from(document.querySelectorAll(".message-checkbox:checked")).map(function(checkbox) {
		        return checkbox.getAttribute("data-msg-no");
		    });

		    // 선택된 쪽지가 없을 경우 함수 실행 중단
		    if (selectedMessageNos.length === 0) {
		        alert('선택된 쪽지가 없습니다.');
		        return; // 여기서 함수 실행을 중단
		    }

		    console.log("Selected Message Nos to Delete:", selectedMessageNos);

		    // 선택된 메시지들의 번호를 서버로 보내어 삭제하는 함수 호출
		    deleteMessages(selectedMessageNos);
		});

		function deleteMessages(selectedMessages) {
		    // AJAX를 사용하여 서버에 삭제 요청을 보냅니다.
		    var xhr = new XMLHttpRequest();
		    xhr.open('POST', '/updateMsgDeleteStatus', true);
		    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

		    xhr.onreadystatechange = function() {
		        if (xhr.readyState === 4) {
		            console.log("Server Response:", xhr.status, xhr.responseText);  // 응답 상태 콘솔에 출력

		            if (xhr.status === 200) {
		                // 성공적으로 삭제된 경우의 처리를 여기에 추가합니다.
		                alert('쪽지가 성공적으로 삭제되었습니다.');
		                // 알림창 확인 시 화면을 새로고침
		                location.reload();
		            } else {
		                alert('쪽지 삭제에 실패했습니다.');
		            }
		        }
		    };
		    
		    var data = {
		        msgNos: selectedMessages.map(Number)
		    };

		    xhr.send(JSON.stringify(data));
		}
	}); // 건들지마
	
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
<!-- 검색바&드롭박스 JS END-->
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
			<h1>받은쪽지함</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">쪽지</li>
					<li class="breadcrumb-item active">받은쪽지함</li>
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
					전체쪽지&nbsp;&nbsp;<span id="readCount">${totUnreadReceiveMsgCnt }</span>/<span id="totalCount">${totReceiveMsgCnt }</span>
				</div>
				<!-- 검색바&드롭박스 -->
				<div class="search-container">
				    <div class="search-bar">
				        <form class="search-form d-flex align-items-center" method="get" action="/msgReceivebox">
				            <select id="dropdownSelect" name="type">
				                <option value="all">전체</option>
				                <option value="sender">보낸사람</option>
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
								<th><input type="checkbox" id="select-all-checkbox">
									<label for="select-all-checkbox"></label></th>
								<th scope="col" class="readStatus">읽음</th>
								<th scope="col" class="attachment">첨부</th>
								<th scope="col" class="subject">제목</th>
								<th scope="col" class="author">보낸사람</th>
								<th scope="col" class="date">일시</th>
							</tr>
						</thead>
						<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
						<!-- 테이블 내용 부분 -->
						<tbody id="mailList">
							<c:forEach var="message" items="${receivedMessages}">
								<!-- msg_readdate(읽음여부)가 'null'이라면 굵기 800 아니면 500 -->
								<tr class="list-item"
									style="font-weight: ${empty message.msg_readdate ? '800' : '500'};">
									<td><input type="checkbox" class="message-checkbox"
										data-msg-no="${message.msg_no}"></td>
									<!-- 읽음 여부 -->
									<td class="readStatus"><c:choose>
											<c:when test="${message.msg_readdate ne null}">
												<img src="assets/img/kdw/msg-read-icon.png" width="15"
													height="15">
											</c:when>
											<c:otherwise>
												<img src="assets/img/kdw/msg-unread-icon.png" width="15"
													height="16">
											</c:otherwise>
										</c:choose></td>
							        <!-- 파일 첨부 이미지 표시 -->
							        <td class="attachment" style="text-align: center; vertical-align: middle;">
							            <c:if test="${not empty message.first_file_name}">
							                <img src="assets/img/kdw/attachments.png" width="17" height="17">
							            </c:if>
							        </td>
									<!-- 제목 -->
									<td class="subject"
										onclick="location.href='/msgReadReceived?msg_no=${message.msg_no}'"
										style="cursor: pointer;">${message.msg_title}</td>
									<!-- 보낸사람 -->
									<td class="author">${message.senderNic}</td>
									<!-- 기간 -->
									<td class="date">${message.msg_createdate}</td>
								</tr>
							</c:forEach>
						</tbody>
						<tbody class="mailList-whiteSpace">
							<tr>
								<td colspan="6"></td>
							</tr>
						</tbody>
					</table>
				</div>

			</section>

			<!-- 받은 쪽지함 세션 END -->
			<!-- 리스트 하단 버튼 -->
			<div class="btn-container">
				<button type="button" class="btn-msg-storebox">보관</button>
			</div>
			<div class="btn-container">
				<button type="button" class="btn-msg-trashbox">삭제</button>
			</div>
			<!-- 받은쪽지 페이징 -->
			<nav aria-label="Page navigation" class="msgReceivebox-pagination-container">
			    <ul class="pagination">
			        <li class="page-item ${page.startPage <= 1 ? 'disabled' : ''}">
			            <a class="page-link prev-page" href="?currentPage=${page.startPage > 1 ? page.startPage - 1 : '#'}&keyword=${keyword}&type=${tpye}" aria-label="Previous">
			                <span aria-hidden="true">&laquo;</span>
			            </a>
			        </li>
			
			        <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
			            <li class="page-item ${i == page.currentPage ? 'active' : ''}">
			                <a class="page-link" href="?currentPage=${i}&keyword=${keyword}&type=${tpye}">${i}</a>
			            </li>
			        </c:forEach>
			
			        <li class="page-item ${page.endPage >= page.totalPage ? 'disabled' : ''}">
			            <a class="page-link next-page" href="?currentPage=${page.endPage < page.totalPage ? page.endPage + 1 : '#'}&keyword=${keyword}&type=${tpye}" aria-label="Next">
			                <span aria-hidden="true">&raquo;</span>
			            </a>
			        </li>
			    </ul>
			</nav>
		</div>
		<!-- card END -->
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
	<!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
	<!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
	<script src="assets/vendor/quill/quill.min.js"></script>
  	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>


</body>
</html>
