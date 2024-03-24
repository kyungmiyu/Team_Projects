package com.blackberry.s20240130103.lhs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comm")
@Getter
@Setter
@ToString
public class Comm {
	@Id
	private int comm_big;
	@Id
	private int comm_mid;
	private String comm_content;
}
