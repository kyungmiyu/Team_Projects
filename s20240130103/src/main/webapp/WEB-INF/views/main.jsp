<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <link href="assets/css/kph/mainSecssion.css"  rel="stylesheet"  type="text/css"> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
    
</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

        <div class="pagetitle">
            <h1>워크 스페이스</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section dashboard">
        
			<div class="card1 info-card sales-card project">
		        <div class="card-body my-project">
		        	<div class="my-project-title">
		            	<h5 class="card-title">나의 프로젝트</h5>
		            	<a href="projectAddForm"><button type="submit" class="plus-btn">+</button></a>
		          	</div>
		          	<c:forEach var="project" items="${projectList }">
		        		<div class="detail-card info-card sales-card project-detail">
		        			<div class="card-body my-project-detail">
		        				<div>
		            				<h5 class="my-project-detail-title">${project.project_title }</h5>
		            				<form action="evalForm" method="post">
		            					<c:if test="${project.project_comp_chk eq 1 && project.isEvalByUser == 0}">
		            						<input name="project_no" value="${project.project_no }" hidden="true"/>
		            						<input type="submit" class="evaluation-btn" value="팀원평가"/>	
		            					</c:if>
									</form>
		          				</div>
		          				<div>
		          					<div class="progress" style="width:70%; margin-top:20px; height: 20px; border-radius: 10px; background-color: rgba(246, 249, 255, 0.3);">
					                <c:choose>
					                	<c:when test="${project.comp_task_count == 0 && project.uncomp_task_count !=0 }">
					                		<div class="progress-bar" role="progressbar" style="color:#0d6efd; font-weight: bold; width: 7%; font-size: 0.6rem;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
					                	</c:when>
					                    <c:when test="${project.comp_task_count + project.uncomp_task_count != 0}">
					                        <div class="progress-bar" role="progressbar" style="color:#0d6efd; font-weight: bold; width: ${(project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100}%; font-size: 0.6rem;" aria-valuenow="${(project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100}" aria-valuemin="0" aria-valuemax="100">${Math.round((project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100)}%</div>
					                    </c:when>
					                    <c:otherwise>
					                        <div class="progress-bar" role="progressbar" style="color:#0d6efd; font-weight: bold; width: 7%; font-size: 0.6rem;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
					                    </c:otherwise>
					                </c:choose>
				            		</div>
				            		<form action="detailProject" method="get">
		            					<input name="project_no" value="${project.project_no }" hidden="true" />
		            					<input type="submit" class="enter-btn" value="입장하기" />
		            				</form>
		          				</div>
		       				</div>
	      				</div>
		        	</c:forEach>
		          	
		       </div>
	      	</div>
	
	      <div class="card2 info-card sales-card summary">
	        <div class="card-body">
	          <div class="all-project-report">
	            <h5 class="card-title">전체 프로젝트 리포트</h5>
	            	<div class="all-project-report-box">
	            		<div class="all-project-report-detail card-body">
			            	<div>
			            		<p>전체 과업</p>
			            		<p><a href="totalTaskList">${totalTaskCount }</a></p>
			            	</div>
			            	<div class="compPercent">${Math.round((totalCompTaskCount/totalTaskCount)*100) }%</div>
				        </div>
				        <div class="all-project-report-detail card-body">
			            	<div>
			            		<p>완료 과업</p>
			            		<p><a href="totalTaskList?clickedNav=comp">${totalCompTaskCount }</a></p>
			            	</div>
				        </div>
				        <div class="all-project-report-detail card-body">
			            	<div>
			            		<p>미완료 과업</p>
			            		<p><a href="totalTaskList?clickedNav=uncomp">${totalUnCompTaskCount }</a></p>
			            	</div>
				        </div>
	            	</div>
			        
			     </div>
					<div class="address">
						<div class="address-header">
							<h5 class="address-card-title">주소록</h5>
							<div class="search-bar">
								<div class="search-form" action="projectAddressSearch">
									<input id="search-text" type="text" placeholder="이름을 입력하세요">
									<button id="search-btn">
										<i class="bi bi-search"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="address-list">
							<c:forEach var="addressUser" items="${addressUserList }">
								<div class="address-list-card">
									<div class="address-list-card-detail">
										<div class="profile-img-user-name">
										<!-- 여기 수정함 회원 기본프로필 띄우는거 -->
											<c:if test="${empty addressUser.user_profile }">
												<img src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle address-list-profile-img">
											</c:if>
											<c:if test="${not empty addressUser.user_profile }">
												<img src="${pageContext.request.contextPath}/upload/userImg/${addressUser.user_profile}" alt="Profile" class="rounded-circle address-list-profile-img">
											</c:if>
										<!-- 여기 수정함 회원 기본프로필 띄우는거 -->
											<div>
												<p class="user-name">${addressUser.user_name }</p>
												<p class="user-id">#${addressUser.user_id }</p>
											</div>
										</div>
										<div class="score-message">
											<div class="user-score rounded-circle">${Math.round(addressUser.user_score*10) / 10 }</div>
											<form action="msgWrite" method="get"> <!-- action은 쪽지 기능 완료 후 로직 구현 -->
												<input type="hidden" name="user_no" value="${addressUser.user_no }">
												<button type="submit" class="rounded-circle message">
													<i class="bi bi-envelope-fill"></i>
												</button>
											</form>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
	      </div>
	      
        </section>
    </main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

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