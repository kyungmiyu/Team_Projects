/**
 * color: "#1987546e", //스케줄 색상
 * #0d6efdb5 //과업색상
 */
const schColor = "#1987546e";
const taskColor = "#0d6efdb5";

function calenderRender(){
	let scheduleArray = [];
	let taskArray = [];
	scheduleArray.splice(0);
	taskArray.splice(0);
	$.post("getTaskAndSchedule",{
		"project_no" : $(".project_no").val()
	},function(result){
		const jsonValue = JSON.parse(result);
		if(Object.keys(jsonValue['schedule']).length !==0){
			for(let sc of jsonValue['schedule']){
				const inputDate = new Date(sc['sch_end'].substr(0,10));
				inputDate.setDate(inputDate.getDate() + 1);
				const year = inputDate.getFullYear();
				const month = String(inputDate.getMonth() + 1).padStart(2, '0');
				const day = String(inputDate.getDate()).padStart(2, '0');
				const endday = `${year}-${month}-${day}`;
				scheduleArray.push({
					title:sc['sch_title'],
					start:sc['sch_start'].substr(0,10),
					end:endday,
					color: schColor
				});
			}
		}
		if(Object.keys(jsonValue['task']).length !==0){
			for(let ta of jsonValue['task']){
				const inputDate = new Date(ta['task_end'].substr(0,10));
				inputDate.setDate(inputDate.getDate() + 1);
				const year = inputDate.getFullYear();
				const month = String(inputDate.getMonth() + 1).padStart(2, '0');
				const day = String(inputDate.getDate()).padStart(2, '0');
				const endday = `${year}-${month}-${day}`;
				taskArray.push({
					title:ta['task_title'],
					start:ta['task_start'].substr(0,10),
					end:endday,
					color:taskColor
				});
			}
		}
	}).done(()=>{
		var calendarEl = document.getElementById("calender");
  		var calendar = new FullCalendar.Calendar(calendarEl, {
	    initialView: "dayGridMonth",
	    height: 600,
	    events: [...scheduleArray,...taskArray],
	  });
	  calendar.render();
	})
}

document.addEventListener("DOMContentLoaded", calenderRender);
export {calenderRender}