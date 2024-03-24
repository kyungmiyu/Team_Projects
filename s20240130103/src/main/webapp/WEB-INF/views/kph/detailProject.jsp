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
    <link href="assets/css/kph/detailProject.css"  rel="stylesheet"  type="text/css"> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
    <link href="assets/css/kph/detailProject_calender.css"  rel="stylesheet"  type="text/css">
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script type="module" defer src="/assets/js/kph/detailProject.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <script type="module" src="/assets/js/kph/detailProject_calender.js"></script>

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
    <%@ include file="../header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

		<div class="pagetitle">
			<h1>워크 스페이스</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
					<li class="breadcrumb-item active">프로젝트 홈</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<input class="projectLeader_no" type="hidden" name="projectLeader_no" value="${projectLeader_no }" />
			<div class="top">
				<div class="top-btn">
					<form action="detailProject" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="button" class="project-home-btn btn btn-primary">프로젝트
						홈</button>
					</form>
					<form action="boardProject" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="submit" class="project-board-btn btn btn-secondary">공유
						게시판</button>			
					</form>
					<form action="proejctSchedule" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="submit" class="project-schedule-btn btn btn-secondary">프로젝트 일정</button>			
					</form>
				</div>
				<div class="top-list">
					<div class="team-list">
						<div id="team-list-title" class="team-list-title dropdown-toggle">팀원
							목록</div>
						<ul id="team-list-content" class="team-list-content">
							<c:forEach var="projectMember" items="${projectMemberList }">
								<li>
									<div>
										<c:if test="${empty projectMember.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
												alt="Profile"
												class="rounded-circle">${projectMember.user_name }
										</c:if>
										<c:if test="${not empty projectMember.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/${projectMember.user_profile}"
												alt="Profile"
												class="rounded-circle">${projectMember.user_name }
										</c:if>
									</div>
									<c:if test="${session_user_no != projectMember.user_no }">
										<i id="${projectMember.user_no}" class="bi bi-x"><input type="hidden" name="user_no" value="${projectMember.user_no}" /></i>
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="setting">
						<i id="setting-btn" class="bi bi-gear-fill"></i>
						<ul id="setting-content" class="setting-content team-list-content">
							<li id="team-member-add-btn">팀원 추가</li>
							<li id="project-update-btn">프로젝트 수정</li>
                            <li id="project-delete-btn">프로젝트 삭제</li>
                            <c:if test="${isProjectCompleted == 0 }">
                            	<li id="project-end-btn">프로젝트 완료</li>
                            </c:if>
						</ul>
					</div>
				</div>
			</div>
			<div class="project-home">
				<div class="card task-card">
					<div class="task-card-head">
						<h5 class="card-title">과업 목록</h5>
						<form action="taskAddForm" method="post">
							<input type="hidden" name="project_no" value="${project_no }" />
							<input type="submit" class="plus-btn" value="+" />
						</form>
					</div>
					<div class="task-list">
						<c:forEach var="task" items="${taskList}">
							<div id="${task.task_no }" class="task-list-detail">
								<div class="task-head">
									<div class="task-title">${task.task_title}</div>
									<i class="bi bi-three-dots-vertical"></i>
									<ul class="task-setting">
										<li class="task-update-btn"><input type="hidden" name="task_no" value="${task.task_no }" />과업 수정</li>
                                    	<li class="task-delete-btn"><input type="hidden" name="task_no" value="${task.task_no }" />과업 삭제</li>
									</ul>
								</div>
								<div class="task-detail">
									<div class="task-comp-chk">
										<i class="bi bi-check-circle"></i>
										<c:choose>
											<c:when test="${task.task_comp_chk == 0 }">
												<button class="comp-chk-btn uncomp-btn"><input type="hidden" name="task_no" value="${task.task_no }" />미완료</button>	
											</c:when>
											<c:otherwise>
												<button class="comp-chk-btn comp-btn"><input type="hidden" name="task_no" value="${task.task_no }" />완료</button>	
											</c:otherwise>
										</c:choose>
									</div>
									<div class="member">
										<i class="bi bi-people"></i>
										<div class="task-member-toggle">
											<div class="task-member-title dropdown-toggle">참여자</div>
											<ul class="task-member-list">
												<c:forEach var="user" items="${task.users }">
													<li>
														<c:if test="${empty user.user_profile }">
															<img
																src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
																alt="Profile" class="rounded-circle">${user.user_name }
														</c:if> 
														<c:if test="${not empty user.user_profile }">
															<img
																src="${pageContext.request.contextPath}/upload/userImg/${user.user_profile}"
																alt="Profile" class="rounded-circle">${user.user_name }
														</c:if> 
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
									<div class="due-date">
										<i class="bi bi-calendar3"></i>
										<fmt:parseDate value = "${task.task_end }" pattern = "yyyy-MM-dd HH:mm" var = "date"/>
										<div><fmt:formatDate value="${date}" pattern="yyyy-MM-dd (E) HH:mm" />까지</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="card task-callender-card">
					<div class="project-report">
						<div class="project-report-head">
							<h5 class="card-title">프로젝트 리포트</h5>
						</div>
						<div class="project-report-content">
							<div class="project-report-card total-task">
								<div>
									<p>전체 과업</p>
									<p class="taskFilter">${unCompTaskListCount + compTaskListCount}</p>
								</div>
								<div class="compPercent">${Math.round((compTaskListCount/(unCompTaskListCount + compTaskListCount))*100) }%</div>
							</div>
							<div class="project-report-card comp-task">
								<div>
									<p>완료 과업</p>
									<p class="taskFilter">${compTaskListCount}</p>
								</div>
							</div>
							<div class="project-report-card uncomp-task">
								<div>
									<p>미완료 과업</p>
									<p class="taskFilter">${unCompTaskListCount }</p>
								</div>
							</div>
						</div>
					</div>
					<div class="callender-card">
						<div class="callender-head">
							<h5 class="card-title">프로젝트 일정</h5>
						</div>
						<div id="calender"></div>
					</div>
				</div>
			</div>
			<div class="team-member-add-box-no-authority">
				<div>
					<div class="team-member-add-box-no-authority-title">
						권한이 없습니다
					</div>
					<button type="button" class="btn btn-primary authority-cancle">확인</button>
				</div>
			</div>
			<div class="project-delete-box">
                <div>
                    <div class="project-delete-box-title">
                        삭제 시 복구가 불가능 합니다.<br /> 정말로 삭제하시겠습니까?
                    </div>
                    <div class="delete-btn-list">
                        <button type="button" class="btn btn-primary check-btn">확인</button>
                        <button type="button" class="btn btn-secondary cancle-btn">취소</button>
                    </div>
                </div>
            </div>
            <div class="project-member-delete-box">
                <div>
                    <div class="project-member-delete-box-title">
                        삭제 시 복구가 불가능 합니다.<br /> 정말로 삭제하시겠습니까?
                    </div>
                    <div class="member-delete-btn-list">
                        <button type="button" class="btn btn-primary member-check-btn">확인</button>
                        <button type="button" class="btn btn-secondary member-cancle-btn">취소</button>
                    </div>
                </div>
            </div>
            <div class="task-delete-box">
                <div>
                    <div class="task-delete-box-title">
                        삭제 시 복구가 불가능 합니다.<br /> 정말로 삭제하시겠습니까?
                    </div>
                    <div class="task-delete-btn-list">
                        <button type="button" class="btn btn-primary task-delete-check-btn">확인</button>
                        <button type="button" class="btn btn-secondary task-delete-cancle-btn">취소</button>
                    </div>
                </div>
            </div>
            <div class="project-end-box">
                <div>
                    <div class="project-end-box-title">
                        완료 시 복구가 불가능 합니다.<br /> 정말로 완료하시겠습니까?
                    </div>
                    <div class="end-btn-list">
                        <button type="button" class="btn btn-primary end-check-btn">확인</button>
                        <button type="button" class="btn btn-secondary end-cancle-btn">취소</button>
                    </div>
                </div>
            </div>
		</section>
	</main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
    <!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
    <!-- 
    <script src="assets/vendor/quill/quill.min.js"></script>
 	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script> -->

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>