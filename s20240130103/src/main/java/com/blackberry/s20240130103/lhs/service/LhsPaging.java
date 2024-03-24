package com.blackberry.s20240130103.lhs.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LhsPaging {
	private int currentPage = 1; //현재페이지 객체 생성시 1로 초기화
	private int rowPage = 10; //한페이지에 찍어줄 게시물 갯수
	private int pageBlock = 10; //한페이지에 찍어줄 페이지 넘버 갯수
	private int start; //시작 row
	private int end; //끝 row
	private int startPage; //페이지 시작번호
	private int endPage; //페이지 끝 번호
	private int total; //총 row 갯수
	private int totalPage; //총 page갯수
	
	public LhsPaging(int total, String currentPage1) {
		this.total = total;
		if(currentPage1 != null && currentPage1 !="" ) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		start = (currentPage -1) * rowPage + 1;
		end = start + rowPage -1;
		totalPage = (int) Math.ceil((double)total/rowPage);
		startPage = currentPage - (currentPage -1) % pageBlock;
		endPage = startPage + pageBlock-1;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
	}
}
