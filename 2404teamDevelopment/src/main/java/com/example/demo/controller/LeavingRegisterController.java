package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	@GetMapping("/featuer/leavingRegister/{id}")
	public String leavingRegister(@PathVariable Integer id,Model model) {
		LeavingRegisterEntity leavingRegister = leavingRegisterService.findById(id);
		LeavingRegisterRequest leavingRegisterRequest= new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(leavingRegister.getAttendance_id());
		leavingRegisterRequest.setUser_id(leavingRegister.getUser_id());
		leavingRegisterRequest.setStatus(leavingRegister.getStatus());
		leavingRegisterRequest.setLeaving_date(leavingRegister.getLeaving_date());
		leavingRegisterRequest.setLeaving_time(leavingRegister.getLeaving_time());
		leavingRegisterRequest.setBreak_time(leavingRegister.getBreak_time());
		leavingRegisterRequest.setRemarks(leavingRegister.getRemarks());
		 model.addAttribute("leavingRegisterUpdateRequest",leavingRegisterRequest);	
		return "leavingRegister";
		
		

	}
	
	
	
	 /**
	  * 退勤登録
	  * @param  leavingRegisterRequest リクエストデータ
	  * @param  model Model
	  * @return  退勤画面
	  */
	 @PostMapping("/featuer/leavingRegister/create")
	 public String leavingRegisterCreate(@Validated @ModelAttribute LeavingRegisterRequest leavingRegisterRequest,BindingResult result, Model model) {
		 if (result.hasErrors()) {
			// 入力チェックエラーの場合
			 List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
			       errorList.add(error.getDefaultMessage());
			    }
			 // エラー判定後の画面遷移
			 model.addAttribute("ValidationError", errorList);
			 model.addAttribute("leavingRegisterUpdateRequest", leavingRegisterRequest);
			 return "leavingRegister";
		 }
		 
		 // 勤怠一覧の更新
		 leavingRegisterService.create(leavingRegisterRequest);
		  return String.format("redirect:/featuer/leavingRegister/%d", leavingRegisterRequest.getAttendance_id());
		 
	 }
	 
}














