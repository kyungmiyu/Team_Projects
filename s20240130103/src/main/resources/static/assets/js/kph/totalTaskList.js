let clickedNav = $('#borderedTab .nav-link.active').val();

$("#task-search-btn").on("click", () => {
	const keyword = $("#task-search-text").val();
	const searchFilter = $(".search-filter").val();
	const sortFilter = $(".sort-filter").val();

	$(".table-nav .table tbody").empty();
	$(".table-nav .page-navigation .pagination").empty();

	$.ajax({
		type: "post",
		url: "taskSearch",
		data: JSON.stringify({
			keyword: keyword,
			searchFilter: searchFilter,
			sortFilter: sortFilter,
			clickedNav: clickedNav
		}),
		contentType: "application/json",
		dataType: 'json',
		success: function(response) {

			let projectTaskList = response.projectTaskList;
			let kphPaging = response.kphPaging;
			let num = kphPaging.start;

			projectTaskList.forEach((projectTask) => {
				$(".table-nav .table tbody").append(`
                <tr>
                    <th>${num}</th>
                    <td><a href='#'>${projectTask.project_title}<a></td>
                    <td>${projectTask.task_title}</td>
                    <td>${projectTask.task_start.substring(0, projectTask.task_start.indexOf(" "))}</td>
                    <td>${projectTask.task_end.substring(0, projectTask.task_end.indexOf(" "))}</td>
                </tr>
            `);
				num = num + 1;
			});

			if (kphPaging.startPage > kphPaging.pageBlock) {
				$(".pagination").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${response.kphPaging.startPage - response.kphPaging.pageBlock}&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}"><span>&laquo;</span></a></li>
            `);
			}

			for (let i = kphPaging.startPage; i <= kphPaging.endPage; i++) {
				$(".pagination").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${i}&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}">${i}</a></li>
            `);
			}

			if (kphPaging.endPage < kphPaging.totalPage) {
				$(".pagination").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${kphPaging.startPage + kphPaging.pageBlock}&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}"><span>&raquo;</span></a></li>
            `);
			}
		}
	});

});

$('#task-search-text').on('keyup', (event) => {
	if (event.keyCode === 13) {
		$('#task-search-btn').click();
	}
});

$('.sort-filter').on('change', () => {
	$('#task-search-btn').click();
});

$('#all-tab').on('click', () => {
	clickedNav = $('#all-tab').val();
	$('#task-search-btn').click();
});

$('#comp-tab').on('click', () => {
	clickedNav = $('#comp-tab').val();
	$('#task-search-btn').click();
});

$('#uncomp-tab').on('click', () => {
	clickedNav = $('#uncomp-tab').val();
	$('#task-search-btn').click();
});
