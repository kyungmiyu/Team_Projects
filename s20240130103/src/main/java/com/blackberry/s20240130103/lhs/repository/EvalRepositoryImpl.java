package com.blackberry.s20240130103.lhs.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EvalRepositoryImpl implements EvalRepository {
	
	private final EntityManager entityManager;

	@Override
	public double avgScoreByNo(String userNo) {
		Double score = 0.0;
		String jpql = "select avg(e.eval_score) FROM Eval e WHERE e.puser = :userNo order by e.puser";
		try {
			score = (Double)entityManager.createQuery(jpql).setParameter("userNo", userNo).getSingleResult();
			return score;
		}catch (NullPointerException npe) {
			System.out.println("evalrepository exception e : " + npe.getMessage());
		}catch (Exception e) {
			System.out.println("evalrepository exception e : " + e.getMessage());
		}
		return 0;
	}

}
