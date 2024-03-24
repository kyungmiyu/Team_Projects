package com.blackberry.s20240130103.lhs.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Schedule {
	private Long project_no;
	private Long sch_no;
	private String sch_title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date sch_start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date sch_end;
	private Long user_no;
	private Date sch_date;
	private Date sch_update_date;
	
	//----------조회용----------
	private String user_name;
	private String user_id;
}
