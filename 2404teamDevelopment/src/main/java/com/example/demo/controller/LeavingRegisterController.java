package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LeavingRegisterController {
	  /**
	   * 退勤登録画面を表示
	   * @param id 表示する勤怠ID
	   * @param model Model
	   * @return 退勤登録画面
	   */
	@GetMapping("/featuer/leavingRegister")
	 public String leavingregister(@PathVariable Integer id,Model model) {
		
		return "leavingRegister";

	}
}
