package com.blackberry.s20240130103.lhs.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "eval")
@Entity
@Getter
@Setter
@ToString
public class Eval {
	@Id
	private Long project_no;
	@Id
	private Long user_no;
	@Id
	private Long puser;
	private double eval_score;
	private LocalDateTime eval_date;
}
