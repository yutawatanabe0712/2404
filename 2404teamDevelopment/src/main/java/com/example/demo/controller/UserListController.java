package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserListEntity;
import com.example.demo.service.UserListService;

@Controller
public class UserListController {
	  /**
	   * ユーザー情報 Service
	   */
	  @Autowired
	  UserListService userlistService;
	
	 @GetMapping("/featuer/userList")
	 public String userlist(Model model) {
		 List<UserListEntity> userlist = userlistService.searchAll();
		 model.addAttribute("userlist", userlist);
		 
		 return "userList";
	 }
}	 
	 