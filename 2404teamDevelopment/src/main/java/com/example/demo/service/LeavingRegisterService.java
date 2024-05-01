package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.repository.LeavingRegisterRepository;

/**
 * 勤怠情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LeavingRegisterService {
	  /**
	   * 勤怠情報 Repository
	   */
	  @Autowired
	  private LeavingRegisterRepository leavingRegisterRepository;
	  
	  /**
	   * 勤怠情報 全検索
	   * @return 検索結果
	   */
	  public List<LeavingRegisterEntity> searchAll() {
	    return leavingRegisterRepository.findAll();
	  }
	  
	   /**
	    * 勤怠情報 主キー検索
	    * @return  検索結果
	    */
	   public LeavingRegisterEntity findById(Integer attendance_id) {
	       return leavingRegisterRepository.getOne(attendance_id);
	   }

}
