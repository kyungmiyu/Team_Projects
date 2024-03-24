// ================ 드래그 앤 드롭 업로드================
$(document).ready(function() {
    var customFileBtn = $('#customFileBtn');
    var filesInput = $('#files');
    var fileList = $('#fileList');
    var dragAndDropFiles = []; // 드래그 앤 드롭으로 선택된 파일들을 저장하는 배열
    var fileSelectionMethod = null; // 파일 선택 방법 ('input' 또는 'dragAndDrop')
    var uploadFileListElement = $('#uploadFileList'); // 업로드된 파일 목록을 담을 요소
    var deleteWaitingList = []; // 삭제 대기 목록

    // 업로드된 파일 목록 표시 함수 호출
    displayUploadedFiles(uploadedFiles);
    // 파일 목록의 가시성과 파일 개수를 업데이트하는 함수를 호출
    updateFileListVisibility();

    // 업로드된 파일 목록 표시
    function displayUploadedFiles(uploadedFiles) {
        uploadedFiles.forEach(function(file) {
            var listItem = $('<li class="file-list-item"></li>').data('fileId', file.pboard_file_no).data('pboardNo', file.pboard_no);
            var deleteButton = $('<span class="delete-file">X</span>');
            
            var fileNameSpan = $('<span>').text(file.pboard_file_user_name);
            // 파일 사이즈 정보를 불러오기 위한 span
            var fileSizeSpan = $('<span class="file-size-info">Loading size...</span>');

            listItem.append(deleteButton).append(fileNameSpan).append(fileSizeSpan);
            uploadFileListElement.append(listItem);

            // 서버에서 파일 사이즈 정보를 불러와 업데이트
            displayFileSize(file.pboard_file_name, fileSizeSpan, file.pboard_no, file.pboard_file_no);
        });
    }

    // 서버에서 파일 사이즈 정보를 불러와 업데이트하는 함수
    function displayFileSize(pboardFileName, fileSizeElement, pboardNo, pboardFileNo) {
        $.ajax({
            url: '/getFileSize',
            type: 'GET',
            data: { 
                fileName: pboardFileName, 
                pboard_no: pboardNo,
                pboard_file_no: pboardFileNo
            },
            success: function(response) {
                var fileSizeInfo = formatBytes(response.fileSize);
                fileSizeElement.text("(" + fileSizeInfo + ")"); // 파일 사이즈 정보를 괄호 안에 넣어 업데이트
            },
            error: function(xhr, status, error) {
                console.error("Error fetching file size: ", status, error);
                fileSizeElement.text("(Error loading size)");
            }
        });
    }

    // 파일 용량을 읽기 좋은 형식으로 변환하는 함수
    function formatBytes(bytes, decimals = 2) {
        if (bytes === 0) return '0 Bytes';
        const k = 1024;
        const dm = decimals < 0 ? 0 : decimals;
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));
        return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
    }

    // "삭제 대기" 목록에 추가하는 함수
    function addToDeleteWaitingList(pboardNo, fileId) {
        deleteWaitingList.push({ pboardNo, fileId });
        
        // 시각적으로 목록에서 제거 (실제 삭제는 서버 요청 후)
        $('li').filter(function() {
            return $(this).data('fileId') === fileId;
        }).remove();

        // 목록 업데이트 후 상태 로깅 및 파일 목록 가시성 업데이트
        console.log("Delete waiting list: ", deleteWaitingList);
        updateFileListVisibility();
    }
	
	// 삭제 버튼 클릭 이벤트 위임
    // 파일 목록 표시 및 초기 문구, 파일 상단바 처리 함수
    function updateFileListDisplay() {
        fileList.empty(); // 파일 목록 초기화
        var filesToShow = [];
        if (fileSelectionMethod === 'input') {
            filesToShow = Array.from(filesInput[0].files);
        } else if (fileSelectionMethod === 'dragAndDrop') {
            filesToShow = dragAndDropFiles;
        }

        // 파일 목록 표시 및 초기 문구, 파일 상단바 처리
        if (filesToShow.length > 0) {
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

        // 파일 목록이 업데이트될 때마다 가시성도 함께 업데이트
        updateFileListVisibility();
    }

    // 파일 목록의 가시성을 업데이트하는 함수
    function updateFileListVisibility() {
        // 업로드된 파일 목록과 드래그 앤 드롭으로 추가된 파일 목록의 합산 길이를 검사
        var totalFiles = $('#uploadFileList .file-list-item').length + $('#fileList .file-list-item').length;

        if (totalFiles > 0) {
            // 파일이 하나라도 있으면 상단바 표시하고 초기 문구 숨김
            $('#file_list_bar').show();
            $('#initial_message').hide();
        } else {
            // 파일이 없으면 상단바 숨기고 초기 문구 표시
            $('#file_list_bar').hide();
            $('#initial_message').show();
        }
        // 파일 개수 업데이트
        $('#fileCount').text(totalFiles + '개의 파일 선택됨');
    }

    // "취소" 버튼 클릭 이벤트
    $('#cancelButton').on('click', function() {
        // 삭제 대기 목록 초기화
        deleteWaitingList = [];
        // 사용자에게 피드백 제공하거나 다른 처리 수행
    });

    // "삭제" 버튼 클릭 이벤트 위임
    uploadFileListElement.on('click', '.delete-file', function() {
        var listItem = $(this).closest('li');
        var fileId = listItem.data('fileId');
        var pboardNo = listItem.data('pboardNo');

        addToDeleteWaitingList(pboardNo, fileId); // 파일 삭제 대기 목록에 추가

        listItem.remove(); // 목록에서 시각적으로 제거
    });

	// "전체 삭제" 버튼 클릭 이벤트
	$('#delete_all').on('click', function() {
	    // 기존 업로드된 파일들에 대해서만 삭제 대기 목록에 추가
	    $('#uploadFileList .file-list-item').each(function() {
	        var pboardNo = $(this).data('pboardNo');
	        var fileId = $(this).data('fileId');
	        addToDeleteWaitingList(pboardNo, fileId); // 삭제 대기 목록에 추가
	    });
	
	    // 새로 추가된 파일 정보는 선택 해제만 처리
	    dragAndDropFiles = [];
	    $('#fileList').empty(); // 새로 추가된 파일 목록 UI 초기화
	
	    // input 파일 선택 클리어
	    filesInput.val('');
	
	    // 파일 목록 업데이트 및 가시성 업데이트
	    updateFileListDisplay();
	    updateFileListVisibility();
	});

    // 드래그 앤 드롭 이벤트 핸들링
    $('#drop_zone').on('dragover', function(e) {
        e.preventDefault();
    }).on('drop', function(e) {
        e.preventDefault();
        e.stopPropagation(); // 이벤트 버블링 중지

        fileSelectionMethod = 'dragAndDrop'; // 파일 선택 방법을 'dragAndDrop'으로 설정
        dragAndDropFiles = Array.from(e.originalEvent.dataTransfer.files); // 드롭된 파일들을 배열에 저장
        updateFileListDisplay(); // 파일 목록 업데이트
    });

    // 사용자 정의 파일 선택 버튼 클릭 이벤트
    customFileBtn.on('click', function() {
        fileSelectionMethod = 'input'; // 파일 선택 방법을 'input'으로 설정
        filesInput.click(); // 실제 파일 입력 필드를 클릭하도록 유도
    });

    // 파일 입력 필드 변경 이벤트
    filesInput.on('change', function() {
        updateFileListDisplay(); // 파일 목록 업데이트 함수 호출
    });

    // "수정" 버튼 클릭 이벤트 핸들러
    $('#updateButton').on('click', function(event) {
        event.preventDefault(); // 폼 제출 방지

        // 파일 삭제가 필요한 경우 먼저 처리
        if (deleteWaitingList.length > 0) {
            $.ajax({
                url: '/delete-file',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(deleteWaitingList),
                success: function(response) {
                    console.log('파일이 성공적으로 삭제되었습니다.');
                    deleteWaitingList = []; // 삭제 대기 목록 초기화
                    submitForm(); // 파일 삭제 후 폼 제출
                },
                error: function(xhr, status, error) {
                    console.error('파일 삭제 실패', status, error);
                }
            });
        } else {
            submitForm(); // 바로 폼 제출
        }
    });

    // 폼 제출 함수
    function submitForm() {
        var formData = new FormData($('#update-form')[0]);
        
        // 파일 선택 방법에 따라 파일 목록을 FormData에 추가
        if (fileSelectionMethod === 'dragAndDrop') {
            dragAndDropFiles.forEach(function(file) {
                formData.append('files', file);
            });
        }

        // 폼 데이터 제출
        $.ajax({
            url: $('#update-form').attr('action'),
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                console.log('폼 데이터가 성공적으로 제출되었습니다.');
                window.history.back(); // 제출 후 이전 페이지로 이동
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('폼 제출 실패: ', textStatus, errorThrown);
            }
        });
    }
});
