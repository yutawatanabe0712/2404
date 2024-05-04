package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GoingRegisterRequest;
import com.example.demo.entity.GoingRegisterEntity;
import com.example.demo.repository.GoingRegisterRepository;

/**
 * 出勤登録に関連するビジネスロジックを提供するサービスクラス。
 * {@link GoingRegisterRepository} を使用して、出勤データの永続化を行う。
 */
@Service
public class GoingRegisterService {

	@Autowired
	private GoingRegisterRepository goingRegisterRepository;

	/**
	 * 出勤データをデータベースに登録する。
	 * このメソッドは、受け取った {@link GoingRegisterRequest} オブジェクトから {@link GoingRegisterEntity} オブジェクトを生成し、
	 * データベースに保存する。
	 *
	 * @param goingRegisterRequest 出勤登録のリクエストデータ。ユーザーID、ステータス、出勤日、出勤時間、備考を含む。
	 */
	public void create(GoingRegisterRequest goingRegisterRequest) {
		GoingRegisterEntity goingRegisterEntity = new GoingRegisterEntity();
		goingRegisterEntity.setUserId(goingRegisterRequest.getUserId());
		goingRegisterEntity.setStatus(goingRegisterRequest.getStatus());
		goingRegisterEntity.setGoingDate(goingRegisterRequest.getGoingDate());
		goingRegisterEntity.setGoingTime(goingRegisterRequest.getGoingTime());
		goingRegisterEntity.setRemarks(goingRegisterRequest.getRemarks());
		goingRegisterRepository.save(goingRegisterEntity);
	}
}
