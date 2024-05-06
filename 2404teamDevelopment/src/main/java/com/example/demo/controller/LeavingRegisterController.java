package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LeavingRegisterRequest;
import com.example.demo.dto.LeavingRegisterUpdateRequest;
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
	//@GetMapping("/featuer/leavingRegister")
	//public String leavingregister(Model model) {
	//	LeavingRegisterEntity leavingRegister = leavingRegisterService.findById(1);
	//  LeavingRegisterRequest leavingRegisterUpdateRequest = new LeavingRegisterRequest();
	//	leavingRegisterUpdateRequest.setAttendance_id(leavingRegister.getAttendance_id());
	//	leavingRegisterUpdateRequest.setNameuser_id(leavingRegister.getNameuser_id());
	//	 model.addAttribute("leavingRegisterUpdateRequest",leavingRegisterUpdateRequest);	
	//	return "leavingRegister";
	//}
	
	 /**
	 * 退勤登録画面を表示
	 * @param id 表示する勤怠ID
	 * @param model Model
	 * @return 退勤登録画面
	 */
	@GetMapping("/featuer/leavingRegister")
	public String leavingRegister(Model model) {
		LeavingRegisterEntity leavingRegister = leavingRegisterService.findById(1);
		LeavingRegisterUpdateRequest leavingRegisterUpdateRequest= new LeavingRegisterUpdateRequest();
		leavingRegisterUpdateRequest.setAttendance_id(leavingRegister.getAttendance_id());
		leavingRegisterUpdateRequest.setUser_id(leavingRegister.getUser_id());
		leavingRegisterUpdateRequest.setStatus(leavingRegister.getStatus());
		leavingRegisterUpdateRequest.setLeaving_date(leavingRegister.getLeaving_date());
		leavingRegisterUpdateRequest.setLeaving_time(leavingRegister.getLeaving_time());
		leavingRegisterUpdateRequest.setBreak_time(leavingRegister.getBreak_time());
		leavingRegisterUpdateRequest.setRemarks(leavingRegister.getRemarks());
		 model.addAttribute("leavingRegisterUpdateRequest",leavingRegisterUpdateRequest);	
		return "leavingRegister";
		
		
	}
	
	
	
	 /**
	  * 退勤登録
	  * @param  leavingRegisterRequest リクエストデータ
	  * @param  model Model
	  * @return  退勤画面
	  */
	 @PostMapping("/featuer/leavingRegister/create")
	 public String leavingRegisterCreate(@Validated LeavingRegisterRequest leavingRegisterRequest,BindingResult result, Model model) {
		 //if (result.hasErrors()) {
			// 入力チェックエラーの場合
			// List<String> errorList = new ArrayList<String>();
			// for (ObjectError error : result.getAllErrors()) {
			       // errorList.add(error.getDefaultMessage());
			    //}
			 // エラー判定後の画面遷移
			// model.addAttribute("validationError", errorList);
			// return "leavingRegister";
		 //}
		 
		 // 勤怠一覧の更新
		 leavingRegisterService.create(leavingRegisterRequest);
		 return "redirect:/featuer/leavingRegister";
		 
	 }
	 
}














