package com.blackberry.s20240130103.lhs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lhs.repository.EvalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvalServiceImpl implements EvalService {
	
	private final EvalRepository evalRepository;
	
	@Override
	public double avgScoreByNo(String userNo) {
		double score = evalRepository.avgScoreByNo(userNo);
		return score;
	}

}
