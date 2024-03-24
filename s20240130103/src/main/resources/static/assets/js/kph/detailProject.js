import {calenderRender} from "./detailProject_calender.js"

$("#team-list-title").on("click", () => {
	if ($("#team-list-content").css("display") === "none") {
		$("#team-list-content").css("display", "block");
	} else {
		$("#team-list-content").css("display", "none");
	}
});

$("#setting-btn").on("click", () => {
	if ($("#setting-content").css("display") === "none") {
		$("#setting-content").css("display", "block");
	} else {
		$("#setting-content").css("display", "none");
	}
});

$(".task-list").on("click", ".task-member-title", function() {
	if ($(this).next().css("display") === "none") {
		$(this).next().css("display", "block");
		$(".task-member-title").not(this).next().css("display", "none");
	} else {
		$(this).next().css("display", "none");
	}
});

$(".task-list").on("click", ".bi-three-dots-vertical", function() {
	if ($(this).next().css("display") === "none") {
		$(this).next().css("display", "block");
		$(".bi-three-dots-vertical").not(this).next().css("display", "none");
	} else {
		$(this).next().css("display", "none");
	}
});

$(document).click(function(event) {
	if (
		!$(event.target).closest("#team-list-title").length &&
		!$(event.target).closest("#team-list-content").length
	) {
		$("#team-list-content").css("display", "none");
	}

	if (
		!$(event.target).closest("#setting-btn").length &&
		!$(event.target).closest("#setting-content").length
	) {
		$("#setting-content").css("display", "none");
	}

	if (
		!$(event.target).closest(".task-member-title").length &&
		!$(event.target).closest(".task-member-list").length
	) {
		$(".task-member-list").css("display", "none");
	}

	if (
		!$(event.target).closest(".bi-three-dots-vertical").length &&
		!$(event.target).closest(".task-setting").length
	) {
		$(".task-setting").css("display", "none");
	}
});

let compTaskCount = Number($('.comp-task .taskFilter').text());
let uncompTaskCount = Number($('.uncomp-task .taskFilter').text());

$(".task-list").on("click", '.comp-chk-btn', function() {
	if ($(this).attr("class").includes("uncomp-btn")) {
		const task_no = $(this).children('input').val();
		const btn = $(this);
		const input = $('<input>', {
			type: 'hidden',
    		name: 'task_no',
    		value: task_no
		});
		$.ajax({
			type: "post",
			url: "taskCompUpdate",
			data: {
				project_no: project_no,
				task_no: task_no
			},
			dataType: "text",
			success: function(response) {
				console.log(response);
				$(btn).removeClass("uncomp-btn");
				$(btn).addClass("comp-btn");
				$(btn).text("완료");
				$(btn).append(input);
				compTaskCount += 1;
				uncompTaskCount -= 1 ;
				$('.comp-task .taskFilter').text(compTaskCount);
				$('.uncomp-task .taskFilter').text(uncompTaskCount);
				$('.total-task .compPercent').text((Math.round((compTaskCount / (compTaskCount + uncompTaskCount))*100)) + '%');
			}
		});
	} else {
		const task_no = $(this).children('input').val();
		const btn = $(this);
		const input = $('<input>', {
			type: 'hidden',
    		name: 'task_no',
    		value: task_no
		});
		$.ajax({
			type: "post",
			url: "taskCompUpdate",
			data: {
				project_no: project_no,
				task_no: task_no
			},
			dataType: "text",
			success: function(response) {
				console.log(response);
				$(btn).removeClass("comp-btn");
				$(btn).addClass("uncomp-btn");
				$(btn).text("미완료");
				$(btn).append(input);
				compTaskCount -= 1;
				uncompTaskCount +=1;
				$('.comp-task .taskFilter').text(compTaskCount);
				$('.uncomp-task .taskFilter').text(uncompTaskCount);
				$('.total-task .compPercent').text((Math.round((compTaskCount / (compTaskCount + uncompTaskCount))*100)) + '%');
			}
		});
	}
});

$(".taskFilter").on("click", function() {
	$(".task-list").empty();
	const project_no = $(".project_no").val();
	const keyword = $(this).prev().text();

	$.ajax({
		type: "post",
		url: "taskFilter",
		data: JSON.stringify({
			project_no: project_no,
			keyword: keyword,
		}),
		contentType: "application/json",
		dataType: "json",
		success: function(taskList) {
			
			console.log(taskList);
			taskList.forEach((task) => {
				const input = $('<input>', {
					type: 'hidden',
		    		name: 'task_no',
		    		value: task.task_no
				});
				
				const users = task.users.map(user => `
		          	<li><img src="/upload/userImg/${user.user_profile== null ? '987654321487321564defaultImg.jpg' : user.user_profile}" alt="Profile" class="rounded-circle">${user.user_name}</li>
		        `).join('');

				const day = ['일', '월', '화', '수', '목', '금', '토']
				const endDate = new Date(task.task_end);
				const endDay = day[endDate.getDay()]
				
				$(".task-list").append(`
					<div class="task-list-detail">
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
								<button class="comp-chk-btn ${task.task_comp_chk == 0 ? 'uncomp-btn' : 'comp-btn'}">
        							<input type="hidden" name="task_no" value="${task.task_no}" />
        							${task.task_comp_chk == 0 ? '미완료' : '완료'}
    							</button>
							</div>
							<div class="member">
								<i class="bi bi-people"></i>
								<div class="task-member-toggle">
									<div class="task-member-title dropdown-toggle">참여자</div>
									<ul class="task-member-list">
										${users}
									</ul>
								</div>
							</div>
							<div class="due-date">
								<i class="bi bi-calendar3"></i>
								<div>${task.task_end.substring(0, task.task_end.indexOf(" "))}(${endDay}) ${task.task_end.substring(task.task_end.indexOf(" "), 16)}까지</div>
							</div>
						</div>
					</div>
		        `);
			});
		},
	});
});

function authorityTest(projectLeader_no, project_no, url) {
	$.ajax({
		type: "get",
		url: "userAuthority",
		data: {
			projectLeader_no: projectLeader_no
		},
		dataType: "json",
		success: function(userAuthority) {
			if (userAuthority != 1) {
				$(".team-member-add-box-no-authority").css('display', 'flex');
			} else {
				window.location.href = '/' + url + '?project_no=' + project_no;
			}
		}
	});
}

function authorityTestForDelete(projectLeader_no, project_no) {
	$.ajax({
		type: "get",
		url: "userAuthority",
		data: {
			projectLeader_no: projectLeader_no
		},
		dataType: "json",
		success: function(userAuthority) {
			if (userAuthority != 1) {
				console.log(userAuthority)
				$(".team-member-add-box-no-authority").css('display', 'flex');
			} else {
				console.log(userAuthority)
				$(".project-delete-box").css('display', 'flex');
			}
		}
	});
}

function authorityTestForEnd(projectLeader_no, project_no) {
	$.ajax({
		type: "get",
		url: "userAuthority",
		data: {
			projectLeader_no: projectLeader_no
		},
		dataType: "json",
		success: function(userAuthority) {
			if (userAuthority != 1) {
				$(".team-member-add-box-no-authority").css('display', 'flex');
			} else {
				$(".project-end-box").css('display', 'flex');
			}
		}
	});
}

const projectLeader_no = $('.projectLeader_no').val();
const project_no = $('.project_no').val();

$("#team-member-add-btn").on("click", function() {
	const url = 'projectMemberAddForm';
	authorityTest(projectLeader_no, project_no, url);
});

$("#project-update-btn").on("click", function() {
	const url = 'projectUpdateForm';
	authorityTest(projectLeader_no, project_no, url);
});

$("#project-delete-btn").on("click", function() {
	const url = 'projectDelete';
	authorityTestForDelete(projectLeader_no, project_no, url);
});

$("#project-end-btn").on("click", function() {
	const url = 'projectEnd';
	authorityTestForEnd(projectLeader_no, project_no, url);
});

$(".authority-cancle").on('click', function() {
	$(".team-member-add-box-no-authority").css('display', 'none');
});

$(".cancle-btn").on('click', function() {
	$(".project-delete-box").css('display', 'none');
});

$(".end-cancle-btn").on('click', function() {
	$(".project-end-box").css('display', 'none');
});

$(".check-btn").on('click', function() {
	$.ajax({
		type: "post",
		url: "projectDelete",
		data: {
			project_no: project_no
		},
		dataType: "text",
		success: function(response) {
			console.log(response);
			window.location.href = '/main';
		}
	});
});

$(".end-check-btn").on('click', function() {
	$.ajax({
		type: "post",
		url: "projectEnd",
		data: {
			project_no: project_no
		},
		dataType: "text",
		success: function(response) {
			console.log(response);
			window.location.href = '/main';
		}
	});
});

$(".member-cancle-btn").on('click', function() {
	$(".project-member-delete-box").css('display', 'none');
});

let clickedUser;

$(".team-list-content li i").on('click', function() {
	clickedUser = $(this).children('input');
	$.ajax({
		type: "get",
		url: "userAuthority",
		data: {
			projectLeader_no: projectLeader_no
		},
		dataType: "json",
		success: function(userAuthority) {
			if (userAuthority != 1) {
				$(".team-member-add-box-no-authority").css('display', 'flex');
			} else {
				$(".project-member-delete-box").css('display', 'flex');
			}
		}
	});
});

$('.member-check-btn').on('click', function() {
	const user_no = $(clickedUser).val();
	$.ajax({
		type: "post",
		url: "projectMemberDelete",
		data: {
			project_no: project_no,
			user_no: user_no
		},
		dataType: "text",
		success: function(response) {
			console.log(response);
			$(clickedUser).closest('li').remove();
			$(".project-member-delete-box").css('display', 'none');
		}
	});
});

$('.task-update-btn').on('click', function() {
	const task_no = $(this).children('input').val();
	window.location.href = 'taskUpdateForm?task_no=' + task_no + '&project_no=' + project_no;
});

let delete_task;

$('.task-delete-btn').on('click', function() {
	delete_task = $(this).children('input');
	console.log(delete_task);
	$(".task-delete-box").css('display', 'flex');
});

$(".task-delete-cancle-btn").on('click', function() {
	$(".task-delete-box").css('display', 'none');
});

$('.task-delete-check-btn').on('click', function() {
	const task_no = $(delete_task).val();
	$.ajax({
		type: "post",
		url: "taskDelete",
		data: {
			project_no: project_no,
			task_no: task_no,
		},
		dataType: "text",
		success: function(response) {
			console.log(response);
			$('#' + task_no).remove();
			$(".task-delete-box").css('display', 'none');
		}
	});
	calenderRender();
});