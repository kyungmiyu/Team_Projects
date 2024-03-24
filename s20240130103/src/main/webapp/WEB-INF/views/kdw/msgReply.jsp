<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgReply.css" rel="stylesheet">

<script type="text/javascript">
//================ 주소록 ================
	//================ 주소록 ================
	$(document).ready(function() {
	    // 토글 버튼과 사용자 리스트 드롭다운 요소를 찾아 변수에 저장
	    var toggleButton = $('#toggleButton');
	    var userListDropdown = $('#userListDropdown');
	    var isToggleButtonClicked = false; // 토글 버튼 클릭 상태를 추적하는 변수 초기화
	    var userMapping = {}; // 사용자 정보와 user_no를 매핑하는 객체
		// 토글 버튼 클릭 이벤트 핸들러 정의
		toggleButton.click(function() {
		    // 클릭 상태를 토글
		    isToggleButtonClicked = !isToggleButtonClicked;
		    // 클릭 상태에 따라 버튼의 클래스 토글
		    toggleButton.toggleClass('clicked', isToggleButtonClicked);
		    // 드롭다운의 표시 여부를 클릭 상태에 따라 조절
		    userListDropdown.css('display', isToggleButtonClicked ? 'block' : 'none');
		    // 화살표 클래스도 클릭 상태에 따라 토글하여 방향을 변경
		    toggleButton.find('.arrow').toggleClass('down', isToggleButtonClicked);
		});
		
		// "받는 사람" 인풋 필드 클릭 이벤트 핸들러 정의
		$('#receiverInput').on('click', function(event) {
			event.stopPropagation();
		    // 위와 동일한 로직을 사용하여 드롭다운 표시 여부 토글
		    isToggleButtonClicked = !isToggleButtonClicked;
		    toggleButton.toggleClass('clicked', isToggleButtonClicked);
		    userListDropdown.css('display', isToggleButtonClicked ? 'block' : 'none');
		    toggleButton.find('.arrow').toggleClass('down', isToggleButtonClicked);
		    event.stopPropagation(); // 이벤트 버블링 방지
		    loadAddressBookList(); // 주소록 데이터 로드 및 드롭다운 업데이트 함수 호출
		});
		
		
		// ========== 받은사람 인풋 필드 입력 키 제한 =============
		// 입력 필드의 이전 상태를 저장할 변수
		let previousValue = '';
		
		// 입력 필드에 포커스 될 때 이전 상태를 현재 값으로 업데이트
		$('#receiverInput').on('focus', function() {
		    previousValue = $(this).val();
		});

		// keydown 이벤트를 사용하여 키 입력 및 사용자 정보 삭제 처리
		$('#receiverInput').on('keydown', function(e) {
		    if (e.key === "Backspace") {
		        let inputValue = $(this).val();
		        let users = inputValue.split(', ').filter(Boolean); // 빈 값을 제외한 배열 생성
		        if (users.length > 0 && (inputValue.slice(-2) === ', ' || inputValue.slice(-1) !== ',')) {
		            let removedUserText = users.pop(); // 마지막 사용자 정보 제거
		            delete userMapping[removedUserText]; // 매핑에서 제거된 사용자 정보 삭제
		            // 변경된 사용자 정보를 반영하여 입력 필드 업데이트
		            let newValue = users.join(', ') + (users.length > 0 ? ', ' : '');
		            $(this).val(newValue);
		            updateInputFields(users.join(', '), false); // 숨겨진 필드 업데이트
		        }
		        e.preventDefault(); // 기본 동작 방지
		    } else {
		        // 백스페이스 이외의 키 입력 시, DOM 업데이트 최소화
		        setTimeout(() => {
		            let currentValue = $(this).val(); // 현재 값 다시 확인
		            $(this).val(previousValue); // 값을 이전 상태로 되돌림
		            showCustomAlert(); // 커스텀 알림 표시
		        }, 0);
		        e.preventDefault(); // 기본 동작 방지
		    }
		});
		
		// 커스텀 알림을 표시하는 함수
		function showCustomAlert() {
		    $('#customAlert').css('display', 'block').css('opacity', '1');
		    setTimeout(function() {
		        $('#customAlert').css('opacity', '0');
		        setTimeout(function() {
		            $('#customAlert').css('display', 'none');
		        }, 1000); // CSS의 transition 시간과 일치해야 함
		    }, 1700);
		}
		
		// 토글 버튼 내 화살표 클릭 시 "받는 사람" 인풋 필드 클릭 이벤트 트리거
		toggleButton.find('.arrow').on('click', function(event) {
		    $('#receiverInput').trigger('click');
		    event.stopPropagation(); // 이벤트 버블링 방지
		});
		
		// 주소록 버튼 클릭 이벤트 핸들러 정의
		$('.receiver-addressBtn button').on('click', function() {
		    // AJAX를 사용하여 서버에서 주소록 데이터 가져오기
		    $.ajax({
		        url: '/getAddressBookList', // 데이터를 가져올 URL
		        type: 'GET', // HTTP 메소드 지정
		        success: function(data) {
		            // 데이터 로딩 성공 시 UI 업데이트 함수 호출
		            updateAddressBookUI(data);
		        },
		        error: function(error) {
		            // 데이터 로딩 실패 시 오류 메시지 출력
		            console.log("Error loading address book: ", error);
		        }
		    });
		});
		
		// 주소록 UI 업데이트 함수 수정
		function updateAddressBookUI(addresses) {
		    var addressList = $('#addressList');
		    addressList.empty();
		    $.each(addresses, function(index, user) {
		        var userText = user.user_nic + " (" + user.user_id + ")"; // 사용자 정보 텍스트
		        var listItem = $('<li>').addClass('list-group-item');
		        var checkbox = $('<input>').attr('type', 'checkbox').addClass('address-checkbox').val(user.user_no);
		        var label = $('<label>').append(checkbox).append(document.createTextNode(" " + userText));
		
		        listItem.append(label);
		        addressList.append(listItem);
		    });
		}
		
		// 체크박스 상태 변경 시 이벤트 핸들러
		$('#addressList').on('change', '.address-checkbox', function() {
		    var checkbox = $(this);
		    var userNo = checkbox.val(); // 사용자 번호(user_no)를 가져옵니다.
		    var checked = checkbox.is(':checked');
		    var userText = checkbox.parent().text().trim(); // 체크박스와 함께 있는 label 내의 텍스트를 가져옵니다.
		
		    if (checked) {
		        // 체크된 경우, 해당 사용자 정보를 "받는사람" 목록에 추가합니다.
		        var newItem = $('<li>').addClass('list-group-item selected-address').text(userText).attr('data-user-no', userNo);
		        $('#selectedAddresses').append(newItem);
		    } else {
		        // 체크 해제된 경우, "받는사람" 목록에서 해당 user_no를 가진 항목을 제거합니다.
		        // userText를 사용하는 대신, data-user-no 속성을 활용해 정확한 요소를 식별하고 제거합니다.
		        $('#selectedAddresses li').filter(function() {
		            return $(this).attr('data-user-no') === userNo;
		        }).remove();
		    }
		});
		
		    // "모두 선택" 체크박스의 동작을 처리합니다. 체크시 리스트 전체 체크 해제시 전체 해제
		    $('#selectAllAddresses').change(function() {
		        var isChecked = $(this).is(':checked');
		        $('#addressList .address-checkbox').prop('checked', isChecked).trigger('change');
		    });
		 // "저장" 버튼 클릭 이벤트 핸들러
		    $('#saveSelectedAddresses').on('click', function() {
		        // 선택된 주소록의 체크박스에서 사용자 번호(user_no)를 배열로 수집
		        var selectedUserNos = $('.address-checkbox:checked').map(function() {
		            return $(this).val(); // 사용자 번호
		        }).get();
		
		        // 선택된 주소록에서 사용자의 닉네임과 이름을 텍스트로 수집
		        var selectedUserTexts = $('.address-checkbox:checked').map(function() {
		            // 체크박스와 연결된 label의 텍스트를 가져옴
		            return $(this).closest('label').text().trim();
		        }).get();
		
		        // 숨겨진 입력 필드에 사용자 번호(user_no) 설정
		        $('#receiverUserNos').val(selectedUserNos.join(', '));
		
		        // "받는 사람" 입력 필드에 사용자 닉네임(user_nic)과 이름(user_id) 표시
		        $('#receiverInput').val(selectedUserTexts.join(', '));
		
		        // 모달 창 숨김
		        $('#addressBookModal').modal('hide');
		    });
		    $('#addressBookModal').on('hidden.bs.modal', function () {
		        // "전체 선택" 체크박스 초기화
		        $('#selectAllAddresses').prop('checked', false);
		
		        // "받는 사람" 목록에서 모든 체크박스의 선택 상태 해제
		        $('#addressList .address-checkbox').prop('checked', false);
		
		        // 추가적으로 "받는사람" 목록도 비우고 싶다면
		        $('#selectedAddresses').empty();
		    });
		
		 // 주소록 데이터 로드 및 드롭다운 리스트 업데이트 함수 정의
		    function loadAddressBookList() {
		        // AJAX를 사용하여 서버에서 주소록 데이터 가져오기
		        $.ajax({
		            url: '/getAddressBookList', // 데이터를 가져올 URL
		            type: 'GET', // HTTP 메소드 지정
		            success: function(data) {
		                // 데이터 로딩 성공 시 드롭다운 메뉴 업데이트
		                var dropdownMenu = $('#userListDropdown');
		                dropdownMenu.empty(); // 기존 내용 비우기
		
		                // 주소록 데이터를 반복하여 드롭다운 메뉴 항목 생성
		                $.each(data, function(index, user) {
		                    var listItem = $('<li>');
		                    var linkItem = $('<a>').addClass('dropdown-item')
		                                           .attr('href', '#')
		                                           .text(user.user_nic + ' (' + user.user_id + ')')
		                                           .data('userNo', user.user_no) // 사용자 번호 데이터 속성에 저장
		                                           .click(function(event) {
		                                               event.preventDefault(); // 기본 이벤트 방지
		                                               selectUser(user.user_no, user.user_nic, user.user_id); // 사용자 선택 로직 호출
		                                               dropdownMenu.hide(); // 드롭다운 메뉴 숨김
		                                           });
		                    listItem.append(linkItem);
		                    dropdownMenu.append(listItem);
		                });
		
		                // 만약 receiverId가 존재하면, 해당하는 user_nic와 user_id을 찾아서 입력 필드에 설정합니다.
		                var receiverId = $('#receiverInput').data('receiverId');
		                if(receiverId) {
		                    var user = data.find(u => u.user_no.toString() === receiverId.toString());
		                    if(user) {
		                        $('#receiverInput').val(user.user_nic + ' (' + user.user_id + ')');
		                        $('#receiverUserNos').val(user.user_no); // 숨겨진 필드에 사용자 번호 설정
		                    }
		                }
		            },
		            error: function(error) {
		                // 데이터 로딩 실패 시 오류 메시지 출력
		                console.log("Error loading address book: ", error);
		            }
		        });
		    }
		
		    // 사용자 선택 시, 선택된 사용자 정보를 userMapping 객체에 추가하는 로직을 포함한 selectUser 함수 수정
		    function selectUser(userNo, userNic, userName) {
		        var newUserText = userNic + " (" + userName + ")";
		        userMapping[newUserText] = userNo.toString(); // 사용자 정보와 user_no 매핑 추가

		        // 입력 필드와 숨겨진 입력 필드에 사용자 정보 업데이트
		        updateInputFields(newUserText, true);
		    }

		    
		    // 입력 필드와 숨겨진 입력 필드를 업데이트하는 함수
		    function updateInputFields(userText, append) {
		        var currentValues = $('#receiverInput').val().split(', ').filter(Boolean); // 현재 입력 필드의 값
		        var currentNos = Object.values(userMapping); // 현재 매핑된 모든 user_no

		        if (append) { // 새 사용자 정보 추가
		            if (!currentValues.includes(userText)) {
		                currentValues.push(userText);
		            }
		        }

		        $('#receiverInput').val(currentValues.join(', ')); // 사용자 정보 업데이트
		        $('#receiverUserNos').val(currentNos.join(', ')); // 숨겨진 입력 필드에 user_no 업데이트
		    }
		    // 사용자 리스트 드롭다운 내부 항목 클릭 이벤트 핸들러
		    $('#userListDropdown').on('click', 'li', function() {
		    	event.stopPropagation();
		        // 드롭다운을 닫고, 토글 버튼 상태 및 화살표 방향을 초기화
		        isToggleButtonClicked = false; // 드롭다운 닫힘 상태로 변경
		        toggleButton.removeClass('clicked');
		        userListDropdown.css('display', 'none');
		        toggleButton.find('.arrow').removeClass('down'); // 화살표 방향을 위로 변경
		    });
		    // 토글 버튼 클릭 시 이벤트 핸들러 정의
		    $('#toggleButton').on('click', function(event) {
		        event.stopPropagation(); // 문서 전체 클릭 이벤트 핸들러에서의 처리 방지
		    });
		    // 문서의 어디든 클릭했을 때의 이벤트 핸들러
		    $(document).on('click', function(event) {
		        // 클릭된 요소가 인풋 필드나 드롭다운 메뉴, 또는 토글 버튼이 아니라면 드롭다운을 숨김
		        if (!$(event.target).closest('#receiverInput, #userListDropdown, #toggleButton').length) {
		            // 드롭다운 메뉴 숨기기
		            $('#userListDropdown').css('display', 'none');
		            isToggleButtonClicked = false; // 토글 버튼 클릭 상태 업데이트
		            toggleButton.removeClass('clicked');
		            toggleButton.find('.arrow').removeClass('down');
		        }
		    });
		    
	});
	
	
	
	// ================ 드래그 앤 드롭 업로드================
	$(document).ready(function() {
	    var customFileBtn = $('#customFileBtn');
	    var filesInput = $('#files');
	    var fileList = $('#fileList');
	    var dragAndDropFiles = []; // 드래그 앤 드롭으로 선택된 파일들을 저장하는 배열
	    var fileSelectionMethod = null; // 파일 선택 방법 ('input' 또는 'dragAndDrop')
	
	    // 사용자 정의 파일 선택 버튼 클릭 이벤트
	    customFileBtn.on('click', function() {
	        fileSelectionMethod = 'input'; // 파일 선택 방법을 'input'으로 설정
	        filesInput.click();
	    });
	
	    // 파일 입력 필드 변경 이벤트
	    filesInput.on('change', function() {
	        updateFileListDisplay(); // 파일 목록 업데이트 함수 호출
	    });
	
	    // 드래그 앤 드롭 이벤트 핸들링
	    $('#drop_zone').on('dragover', function(e) {
	        e.preventDefault();
	    }).on('drop', function(e) {
	        e.preventDefault();
	        e.stopPropagation(); // 이벤트 버블링 중지
	
	        fileSelectionMethod = 'dragAndDrop'; // 파일 선택 방법을 'dragAndDrop'으로 설정
	        dragAndDropFiles = Array.from(e.originalEvent.dataTransfer.files); // 드롭된 파일들을 배열에 저장
	        updateFileListDisplay(); // 파일 목록 업데이트 함수 호출
	    });
	
	    // 파일 목록 업데이트 및 초기 문구, 파일 상단바 처리 함수
	    function updateFileListDisplay() {
	        fileList.empty(); // 파일 목록 초기화
	        var filesToShow = [];
	
	        // 파일 선택 방법에 따라 표시할 파일 목록 결정
	        if (fileSelectionMethod === 'input') {
	            filesToShow = Array.from(filesInput[0].files);
	        } else if (fileSelectionMethod === 'dragAndDrop') {
	            filesToShow = dragAndDropFiles;
	        }
	
	        // 파일 목록 표시 및 초기 문구, 파일 상단바 처리
	        if(filesToShow.length > 0) {
	            $('#initial_message').hide();
	            $('#file_list_bar').show(); // 파일 상단바 표시
	            filesToShow.forEach(function(file, index) {
	                var fileSize = formatBytes(file.size);
	                var listItem = $('<li class="file-list-item"></li>');
	                var deleteButton = $('<span class="delete-file">X</span>');
	
	                // 삭제 버튼 클릭 이벤트 핸들러
	                deleteButton.on('click', function() {
	                    if (fileSelectionMethod === 'dragAndDrop') {
	                        dragAndDropFiles.splice(index, 1); // 드래그 앤 드롭 파일 배열에서 파일 삭제
	                    } else {
	                        filesInput.val(''); // input 선택 파일 클리어
	                    }
	                    updateFileListDisplay(); // 파일 목록 업데이트
	                });
	
	                listItem.append(deleteButton)
	                        .append($('<span>').text(file.name))
	                        .append($('<span>').text('(' + fileSize + ')'));
	                fileList.append(listItem);
	            });
	        } else {
	            $('#initial_message').show();
	            $('#file_list_bar').hide(); // 파일 상단바 숨김
	        }
	
	        toggleFileListVisibility();
	        $('#fileCount').text(filesToShow.length + '개의 파일 선택됨');
	    }
	
	    // 전체 삭제 버튼 클릭 이벤트
	    $('#delete_all').on('click', function() {
	        if (fileSelectionMethod === 'dragAndDrop') {
	            dragAndDropFiles = [];
	        } else {
	            filesInput.val('');
	        }
	        updateFileListDisplay(); // 파일 목록 업데이트
	    });
	
	    // 파일 목록의 가시성 토글 함수 구현
	    function toggleFileListVisibility() {
	        if ($('#fileList li').length > 0) {
	            $('#file_list_bar').show();
	            $('#initial_message').hide();
	        } else {
	            $('#file_list_bar').hide();
	            $('#initial_message').show();
	        }
	    }
	
	    // 파일 용량 형식 변환 함수 구현
	    function formatBytes(bytes, decimals = 2) {
	        if (bytes === 0) return '0 Bytes';
	        const k = 1024;
	        const dm = decimals < 0 ? 0 : decimals;
	        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
	        const i = Math.floor(Math.log(bytes) / Math.log(k));
	        return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	    }
	
	    // 폼 제출 이벤트
	    $('#reply-form').on('submit', function(e) {
	        // 받는 사람의 사용자 번호를 확인
	        var receiverUserNo = $.trim($('#receiverUserNos').val());

	        // 받는 사람이 지정되지 않았는지 검사
	        if (receiverUserNo === '') {
	            alert('받는 사람을 지정해주세요.');
	            e.preventDefault(); // 폼 제출 중단
	            return false; // 함수 실행 중단
	        }
	        // 제목과 내용 입력값 검증
	        var title = $.trim($('#msg_title').val());
	        var content = $.trim($('#message').val());

	        if (title === '' || content === '') {
	            alert('제목이나 내용은 반드시 입력해야 합니다.');
	            e.preventDefault();
	            return false;
	        }
	        
	        e.preventDefault();
	
	        var formData = new FormData(this);
	
	        if (fileSelectionMethod === 'dragAndDrop') {
	            // 드래그 앤 드롭으로 선택된 파일들을 FormData에 추가
	            dragAndDropFiles.forEach(function(file) {
	                formData.append('files', file);
	            });
	        }
	        // 파일 선택 방법이 'input'일 경우, <input type="file">의 파일들은 자동으로 formData에 포함됩니다.
	
	        // 폼 데이터를 이용하여 서버에 폼 제출
	        $.ajax({
	            url: $(this).attr('action'), // '/msgSent' 또는 서버 측 엔드포인트
	            type: 'POST',
	            data: formData,
	            processData: false,
	            contentType: false,
	            success: function(response) {
	                console.log('메시지가 성공적으로 전송되었습니다.');
	                window.location.href = "/msgSentSuccessPage"; // 성공 시 리다이렉트
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                console.error('메시지 전송 실패: ', textStatus, errorThrown);
	            }
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

	<main id="main" class="main">
        <div class="pagetitle">
            <h1>답장쓰기</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">쪽지</li>
                    <li class="breadcrumb-item active">답장쓰기</li>
                </ol>
            </nav>
        </div>
        <!-- End Page Title -->
        <div class="card">
            <!-- 답장하기 세션 부분 -->
            <section class="reply-section">
                <div class="form-container">
                    <form id="reply-form" action="/msgSent" method="post" enctype="multipart/form-data">
                        <!-- 보내기 버튼 -->
                        <div class="form-group">
                            <button type="submit" class="msg-Sent-Btn">보내기</button>
                        </div>
						<!-- 취소 Button : 이전 페이지로 돌아가기 -->
						<!-- Referer 헤더는 사용자가 현재 요청을 보내기 전에 어떤 페이지에서 왔는지를 식별 -->
						<div class="form-group">
							<a href="<%=request.getHeader("Referer")%>"
								class="msg-cancel-btn">취소</a>
						</div>

                        <!-- 받는사람 -->
						<div class="form-group">
							<div class="input-group">
								<!-- 받는사람 텍스트 -->
								<div class="input-group-prepend-received">
									<span class="input-group-text">받는사람</span>
								</div>
								
								<!-- 인풋 -->
								<c:choose>
									<c:when test="${senderUserNo != null}">
								        <input id="receiverInput" type="text" class="form-control"
								               aria-label="Text input with segmented dropdown button"
								               name="msg_receivers_display" value="${receiverUser.user_nic} (${receiverUser.user_id})">
									    <!-- 사용자 번호를 서버로 보낼 히든 인풋 -->
									    <input type="hidden" id="receiverUserNos" name="msg_receivers" value="${senderUserNo}">
									</c:when>
									<c:otherwise>
										<!-- 사용자에게 보여지는 입력 필드: 사용자 닉네임(user_nic)과 아이디(user_id)을 표시합니다. -->
										<input id="receiverInput" type="text" class="form-control" aria-label="Text input with segmented dropdown button" 
										name="msg_receivers_display">
										<!-- 숨겨진 입력 필드: 실제로 서버로 전송될 사용자 번호(user_no)를 저장합니다. -->
										<input type="hidden" id="receiverUserNos" name="msg_receivers">
									</c:otherwise>
								</c:choose>
								<!-- 인풋창에 키 입력시 뜰 커스텀 알림창-->
								<div id="customAlert" class="custom-alert" style="display:none;">주소록에서 직접 선택이나 백스페이스를 통한 항목 제거만 가능합니다.</div>
								<div class="receiver-dropdown">
									<!-- 드롭다운 토글 버튼 --><!-- 토글버튼을 JS로 따로 주고있어서그런거같음 -->
									<button type="button" id="toggleButton">
									    <span class="visually-hidden">주소록 드롭다운</span>
									    <!-- 토글버튼 -->
									    <span class="arrow">&gt;</span>
									</button>

									<!-- 드롭다운 주소록 리스트 -->
									<ul class="dropdown-menu dropdown-menu-end"
										id="userListDropdown">
									</ul>
								</div>
								<!-- 주소록 버튼 -->
								<div class="receiver-addressBtn">
									<button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#addressBookModal">주소록</button>
								</div>
								<!-- 주소록 버튼 클릭시 OPEN : 주소록 모달 -->
								<div class="modal fade" id="addressBookModal" tabindex="-1" aria-labelledby="addressBookModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-lg">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="addressBookModalLabel" style="font-weight: 800;">쪽지 주소록</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        <div class="row">
								          <div class="col-6">
								          	<!-- 주소록 리스트 전체 체크박스 -->					        	
								            <h6><input type="checkbox" id="selectAllAddresses" />등록된 주소록</h6>
								            <ul id="addressList" class="list-group">
								              <!-- 주소록 리스트가 여기에 동적으로 로드됩니다 -->
								            </ul>
								          </div>
								          <div class="col-6">
								            <h6>받는사람</h6>
								            <ul id="selectedAddresses" class="list-group">
								              <!-- 선택된 주소록이 여기에 표시됩니다 -->
								            </ul>
								          </div>
								        </div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								        <button type="button" class="btn btn-primary" id="saveSelectedAddresses">저장</button>
								      </div>
								    </div>
								  </div>
								</div>
							</div>
						</div>
						<!-- 제목 -->
						<div class="form-group">
							<div class="subject-group">
								<!-- 제목 텍스트 -->
								<div class="subject-group-prepend">
									<span class="subject-group-text">제목</span>
								</div>
								<!-- 인풋 -->
								<input type="text" id="msg_title" name="msg_title" value="${replyTitle}"
									class="form-control"
									aria-label="Text input with segmented dropdown button">
							</div>
						</div>
                        <!-- 첨부파일 -->
						<div class="form-group">
							   <div class="mb-3">
						        <!-- 사용자 정의 파일 선택 버튼과 파일 개수 표시 -->
						        <div class="file-form-control">
						            <button type="button" id="customFileBtn" class="btn fileBtn">파일 선택</button>
						            <span id="fileCount">선택된 파일 없음</span>
						        </div>
						        <input type="file" id="files" name="files" multiple style="display: none;" onchange="updateFileList(this.files)">
						        <!-- 드래그 앤 드롭 영역 -->
						        <div id="drop_zone" class="file_drag">
								    <!-- 초기 안내 문구 -->
								    <div id="initial_message" style="margin-top:36px; color:#6c757d; font-weight: 700;">여기에 파일을 드래그하세요.</div>
								    <!-- 파일 목록 상단 바, 파일이 드래그 되면 표시됩니다. -->
								    <div id="file_list_bar" class="file-list-bar" style="display: none;">
								         <span id="delete_all" style="cursor: pointer;">X</span>
								         <span>파일명</span>
								         <span>용량</span>
								    </div>
								    <!-- 업로드된 파일 목록 -->
								    <ul id="fileList" class="file-list"></ul>
						        </div>
						    </div>
						</div>
                        <!-- 내용 -->
						<div class="form-group">
							<div class="content-group">
								<span class="content-group-text">내용</span>
								<textarea id="message" name="msg_content" rows="5" required></textarea>
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