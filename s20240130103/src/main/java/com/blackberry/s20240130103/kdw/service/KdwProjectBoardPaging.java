package com.blackberry.s20240130103.kdw.service;

import lombok.Data;

@Data
public class KdwProjectBoardPaging {
    // 현재 페이지
    private int currentPage = 1;

    // 한 페이지에 보여질 쪽지 수
    private int rowPage = 15;

    // 페이지네이션 블록에 보여질 페이지 수
    private int pageBlock = 5;

    // 현재 페이지에 보여질 쪽지 시작과 끝 인덱스
    private int start;
    private int end;

    // 현재 페이지네이션 블록의 시작과 끝 페이지
    private int startPage;
    private int endPage;

    // 전체 쪽지 수
    private int total;

    // 전체 페이지 수
    private int totalPage;

    // 생성자
    public KdwProjectBoardPaging(int total, String currentPage1) {
        this.total = total;
        if (currentPage1 != null) {
            this.currentPage = Integer.parseInt(currentPage1);
        }

        // 한 페이지에 보여질 쪽지 시작과 끝 인덱스 계산
        // 시작 인덱스는 1부터이기때문에 아래 코드는 0부터 시작이기때문에 오류
        // start = (currentPage - 1) * rowPage;
        start = (currentPage - 1) * rowPage + 1;
        end = start + rowPage - 1;

        // 전체 페이지 수 계산
        totalPage = (int) Math.ceil((double) total / rowPage);

        // 현재 페이지네이션 블록의 시작과 끝 페이지 계산
        startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        endPage = Math.min(startPage + pageBlock - 1, totalPage);

        // 현재 페이지네이션 블록의 끝 페이지가 전체 페이지 수보다 크면 조정
        if (endPage > totalPage) {
            endPage = totalPage;
        }
    }
}