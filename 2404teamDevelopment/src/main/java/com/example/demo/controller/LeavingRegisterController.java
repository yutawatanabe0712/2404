package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.LeavingRegisterRequest;
import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.service.LeavingRegisterService;

@Controller
public class LeavingRegisterController {
	
	 /**
	    * 勤怠情報 Service
	    */
	   @Autowired
	   LeavingRegisterService leavingRegisterService;
	
	  /**
	   * 退勤登録画面を表示
	   * @param id 表示する勤怠ID
	   * @param model Model
	   * @return 退勤登録画面
	   */
	@GetMapping("/featuer/leavingRegister")
	 public String leavingregister(@PathVariable  Integer attendance_id,Model model) {
		LeavingRegisterEntity leavingRegister = leavingRegisterService.findById(attendance_id);
	    LeavingRegisterRequest leavingRegisterUpdateRequest = new LeavingRegisterRequest();
		leavingRegisterUpdateRequest.setAttendance_id(leavingRegister.getAttendance_id());
		leavingRegisterUpdateRequest.setNameuser_id(leavingRegister.getNameuser_id());
		 model.addAttribute("leavingRegisterUpdateRequest",leavingRegisterUpdateRequest);	
		return "leavingRegister";

	}
}
