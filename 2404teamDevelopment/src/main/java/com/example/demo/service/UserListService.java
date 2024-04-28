package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserListEntity;
import com.example.demo.repository.UserListRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class UserListService {
	
	 /**
	   * ユーザー情報 Repository
	   */
	  @Autowired
	  private UserListRepository userlistRepository;
	  
	  
	  /**
	   * ユーザー情報 全検索
	   * @return 検索結果
	   */
	  public List<UserListEntity> searchAll() {
	    return userlistRepository.findAll();
	  }
	

}
