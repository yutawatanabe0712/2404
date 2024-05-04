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
	 * 出勤登録画面を表示する
	 * @param model モデルオブジェクト
	 * @return goingRegister 「goingRegister」という名前のビューへのパス
	 */
	@GetMapping("goingRegister/display")
	public String displayAdd(Model model) {
		model.addAttribute("goingRegisterrequest", new GoingRegisterRequest());
		return "goingRegister";
	}

	/**
	 * フォームから受け取った出勤登録情報を処理する。エラーがある場合はエラーメッセージと共に出勤登録画面を再表示する。
	 * @param mode モデルオブジェクト。バリデーションエラーがある場合はエラーメッセージを含む。
	 * @param goingRegisterRequest フォームから送信された出勤登録データ
	 * @param result バリデーション結果を保持する {@link BindingResult} オブジェクト。
	 * @return 成功時には勤怠一覧確認画面へリダイレクト、エラーがある場合は出勤登録画面へのパス。
	 */
	@PostMapping("/goingRegister/create")
	public String create(@Validated @ModelAttribute GoingRegisterRequest goingRegisterRequest, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			addErrorMessagesToModel(result, model);
			model.addAttribute("goingRegisterrequest", goingRegisterRequest);
			return "goingRegister";
		}

		goingRegisterService.create(goingRegisterRequest);
		model.addAttribute("goingRegisterrequest", goingRegisterRequest);
		return "goingRegister";
	}

	/**
	 * バリデーション結果からエラーメッセージを抽出し、モデルに追加する。
	 * このメソッドは、フォーム入力のバリデーションで発生したすべてのエラーをリストにまとめ、
	 * そのリストをモデルに "ValidationError" というキーで追加する。
	 * @param result バリデーション結果を保持する {@link BindingResult} オブジェクト。
	 * @param model データをビューに渡すための {@link Model} オブジェクト。
	 */
	private void addErrorMessagesToModel(BindingResult result, Model model) {
	    List<String> errorList = new ArrayList<>();
	    for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	    }
	    model.addAttribute("ValidationError", errorList);
	}
}
