package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GoingRegisterRequest;
import com.example.demo.entity.GoingRegisterEntity;
import com.example.demo.repository.GoingRegisterRepository;

@Service
public class GoingRegisterService {

	@Autowired
	private GoingRegisterRepository goingRegisterRepository;

	/**
	 * 出勤登録処理サービス
	 * @param goingRegisterRequest
	 */
	public void create(GoingRegisterRequest goingRegisterRequest) {
		GoingRegisterEntity goingRegisterEntity = new GoingRegisterEntity();
		goingRegisterEntity.setUser_id(goingRegisterRequest.getUser_id());
		goingRegisterEntity.setStatus(goingRegisterRequest.getStatus());
		goingRegisterEntity.setGoing_date(goingRegisterRequest.getGoing_date());
		goingRegisterEntity.setGoing_time(goingRegisterRequest.getGoing_time());
		goingRegisterEntity.setRemarks(goingRegisterRequest.getRemarks());
		goingRegisterRepository.save(goingRegisterEntity);
	}
}
