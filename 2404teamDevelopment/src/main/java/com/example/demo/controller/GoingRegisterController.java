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
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.GoingRegisterRequest;
import com.example.demo.service.GoingRegisterService;

@Controller
public class GoingRegisterController {

	@Autowired
	private GoingRegisterService goingRegisterService;

	/**
	 * 出勤登録画面表示コントローラ
	 * @param model
	 * @return goingRegister 出勤登録画面
	 */
	@GetMapping("goingRegister/display")
	public String displayAdd(Model model) {
		model.addAttribute("goingRegisterrequest", new GoingRegisterRequest());
		return "goingRegister";
	}

	/**
	 * 出勤登録処理コントローラ
	 * @param model
	 * @param goingRegisterRequest 出勤登録フォームクラス
	 * @return goingRegister 出勤登録画面
	 */
	@PostMapping("/goingRegister/create")
	public String create(@Validated @ModelAttribute GoingRegisterRequest goingRegisterRequest, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("ValidationError", errorList);
			model.addAttribute("goingRegisterrequest", goingRegisterRequest);
			return "goingRegister";
		}

		goingRegisterService.create(goingRegisterRequest);
		model.addAttribute("goingRegisterrequest", goingRegisterRequest);
		return "goingRegister";
	}
}
