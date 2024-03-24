<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar","\n"); %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
 
 
 <title>Blueberry</title>
<meta content="" name="description">
<meta content="" name="keywords">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->

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
  
  <!-- Reply Js -->
  <script defer src="assets/js/lsl/boardFreeASkReply.js"></script>
  
  <!-- Template Main CSS File -->
   <link href="assets/css/lsl/lslboardFreeContents.css" rel="stylesheet"> 
   <link href="assets/css/style.css" rel="stylesheet"> <!-- 헤더, 푸터, 사이드바 css -->
  
 <script>
window.onload = function() {
	 getUserNo();
     getUserNic();
	replyBoardFreeAskList(${boardAskContents.cboard_no});
    
}

function goBack() {
    window.history.back();
}
</script>

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
                top: $(this).offset().top + $(this).outerHeight() + -15, // 요소 바로 아래에 팝업 표시
                left: $(this).offset().left,
                background: 'white',
                border: '1px solid #ddd',
                padding: '5px',
                'z-index': 1000  // z-index를 지정하여 다른 요소 위에 표시
            })
            .append($('<a>').attr({
                href: '/addressaddForm?userId=' + encodeURIComponent(userId),
            }).text('주소록 추가').on('click', function(e) {
                // 기본 링크 이동 이벤트를 방지하고, 대신 수동으로 페이지 이동
                e.preventDefault();
                window.location.href = this.href;
            }));

        // 기존 팝업이 있으면 제거
        $('.custom-popup').remove();
        
        // 팝업을 body에 추가
        $('body').append(popupMenu);
        
        // 다른 부분 클릭 시 팝업 메뉴 제거
        $(document).on('click', function(e) {
            if (!$(e.target).is('.custom-popup') && !$(e.target).is('.authorName')) {
                $('.custom-popup').remove();
            }
        });

        // 작성자 이름 클릭 시 다른 클릭 이벤트를 방지
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
    <%@ include file="../header.jsp" %>
    
    
    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>


    <!-- ======= Main ======= -->
	<main id="main" class="main">
	    <div class="pagetitle">
	        <h1>게시판</h1> 
	        <nav style="--bs-breadcrumb-divider: '/">
	            <ol class="breadcrumb">
	         <li class="breadcrumb-item"><a href="boardAsk">정보 게시판</a></li>
	                <li class="breadcrumb-item">질문 게시판</li>
	            </ol>
	        </nav>
	    </div>
	    
    <section class="community-post-detail">
        <div class="row card main-card card-body">
            <div class="card-header community-post-header">
                <h3 class="card-title post-header-title">${boardAskContents.cboard_title}</h3>
                
                <div class="card-subtitle post-user-container">
	             <c:choose>
	                        <c:when test="${boardAskContents.user_profile !=null}">
	                    <img class="user-profile" src="${pageContext.request.contextPath}/upload/userImg/${boardAskContents.user_profile}" alt="User Profile" ></img>
	                </c:when>
	                <c:otherwise>
	                    <img class="user-profile" src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="">                   
	                 </c:otherwise>
                </c:choose>
                
                      <div class="card-title-header">
                        <h5 class="card-title post-user-name">
                            <label>${boardAskContents.user_nic}</label>
                        </h5>
                        <div class="card-subtitle post-subtitle"><p class="post-updated-at">작성일
                        <fmt:formatDate value="${boardAskContents.cboard_date}" pattern="yyyy.MM.dd a hh:mm" /></p>
                            <p class="post-veiw-count">조회수 ${boardAskContents.cboard_viewcnt}</p>
                        </div>
                    </div>
                </div>
            </div>
            
           <div class="community-post-header-body">
                  <span class="post-content">${fn:replace(boardAskContents.cboard_content, replaceChar, "<br/>")}</span>
                 <div class="boardFileBox">
                    <div id="files">
			          <c:forEach items="${boardAskFile}" var="boardAskFile">
			    		<div class="file-container">
			    		<i class="bi bi-file-earmark-arrow-down"></i>
			        	<a href="boardAskFileDownload?cboard_file_name=${boardAskFile.cboard_file_name }&cboard_file_user_name=${boardAskFile.cboard_file_user_name}" target="_blank" class="file-link">${boardAskFile.cboard_file_user_name}</a>
			   		 </div>
				</c:forEach>
                    </div>
                </div> 
           </div>	
           
          <section class="community-post-answer">
            <div class="answer-info-header">
                <div class="answer-info-title">댓글<span class="answer-info-title-count"  id="replyCnt">${boardReplyCnt}</span></div>
               
                <div class="boardPostComment">
							<input type="hidden" name="cboard_no" value="${boardAskContents.cboard_no}" /> 
							<input type="hidden" name="user_no" value="${boardAskContents.user_no}"> 
							<input type="hidden" name="creply_no" value="${boardAskContents.creply_no}" />
                            <input type="hidden" name="boardType" value="${boardAskContents.boardType}" />
                <div class="comment-editor">
                    <c:choose>
                        <c:when test="${user_profile !=null&& sessionScope.user_no == user_no}">
                            <img class="rounded-circle" src="${pageContext.request.contextPath}/upload/userImg/${sessionScope.user_profile}" alt="Profile" style="height: 36px; width: 36px"></img>
                        </c:when>
                    	<c:otherwise>
                            <img class="rounded-circle" src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle" style="height: 36px; width: 36px"></img>
                        </c:otherwise>
                    </c:choose>
                    <input type="text" name="comment" id="creply_content" placeholder="${sessionScope.user_nic}님, 댓글을 작성해보세요." class="form-control" required="required">
                </div>
                
                <div class="btn-container is-editor-open">
                        <button type="button" id="submitBtn" class="btn submitBtn">등록</button>
                </div>
                </div>
               </div>
                
                 <!-- 댓글 리스트 -->
				<div id="boardReplyForm">
					<!-- ajax 비동기 갱신 -->
           		 </div>
           		 
        </section>
        
        
          <div class="btnBox">
			  <c:if test="${sessionScope.user_no eq boardAskContents.user_no}">             
                        <a href="boardFreeModify?boardType=ask&cboard_no=${boardAskContents.cboard_no}" class="badge bg-light text-dark">
						    <i class="bi bi-highlighter"></i>수정</a>
               <form id="deleteForm" action="/deleteAskBoard" method="post">
		 	 	  	 <input type="hidden" name="cboard_no" value="${boardAskContents.cboard_no}">
		   			 <input type="hidden" name="user_no" value="${boardAskContents.user_no}">
		    		 <button type="submit" class="badge bg-light text-dark"><i class="bi bi-trash"></i> 삭제</button>
			   </form>
                </c:if>
		  </div>
                        <button type="button" class="btn bacList" onclick="goBack()">목록</button>                   
          </div>
            </section>
</main>


    
    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>


    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
   
   
    <!-- Vendor JS Files -->
    <script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script defer src="assets/vendor/chart.js/chart.umd.js"></script>
    <script defer src="assets/vendor/echarts/echarts.min.js"></script>
    <script defer src="assets/vendor/quill/quill.min.js"></script>
    <script defer src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script defer src="assets/vendor/php-email-form/validate.js"></script>
    
    
    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
    
</body>
</html>
