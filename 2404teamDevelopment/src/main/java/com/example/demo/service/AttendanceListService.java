package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.repository.AttendanceListRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class AttendanceListService {

	 /**
	   * ユーザー情報 Repository
	   */
	  @Autowired
	  private AttendanceListRepository attendanceListRepository;
	  
	  /**
	   * 勤怠情報検索
	   * @return ユーザに紐づく勤怠情報検索結果
	   */
	  public List<AttendanceListEntity> findByUser_idEquals(Integer id) {
	    return attendanceListRepository.findByUser_idEquals(id);
	  }
}
