package com.example.demo;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.GoingRegisterController;
import com.example.demo.dto.GoingRegisterRequest;
import com.example.demo.service.GoingRegisterService;

@WebMvcTest(GoingRegisterController.class)
class GoingRegisterControllerTest {

	@MockBean
	GoingRegisterService goingRegisterService;

	@Autowired
	private MockMvc mockMvc;

	private GoingRegisterRequest goingRegisterRequest;

	 @BeforeEach
	    void setUp() {
	        // リクエストオブジェクトの初期化
	       	goingRegisterRequest = new GoingRegisterRequest();
	        goingRegisterRequest.setUserId(1);
	        goingRegisterRequest.setGoingDate(LocalDate.of(2024, 10, 10));
	        goingRegisterRequest.setGoingTime(LocalTime.of(10, 10));
	        goingRegisterRequest.setRemarks("remarks");
	        goingRegisterRequest.setStatus("status");
	    }

	/**
	 * 【正常系】GETリクエストが正常に処理され、出勤画面が表示されること,modelの中身を検証するテストケース
	 * @throws Exception
	 */
	@Test
	public void successfullyDisplayAdd() throws Exception {
		mockMvc.perform(get("/goingRegister/display/1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("goingRegisterrequest"))
				.andExpect(model().attributeExists("userId"))
				.andExpect(view().name("goingRegister"));
	}

	/**
	 * 【正常系】POSTリクエストが正常に処理され、goingRegisterService.createが呼び出されること、出勤画面が表示されることを検証するテストケース
	 * @throws Exception
	 */
	@Test
	void successfullyCreate() throws Exception {
		mockMvc.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
				.andExpect(model().hasNoErrors())
				.andExpect(view().name("goingRegister"));
		verify(goingRegisterService, times(1)).create(goingRegisterRequest);
	}

	/**
	 * 【異常系】出勤日がnullの場合、エラーメッセージ「出勤日を入力してください」を所持していることを検証するテストケース
	 * @throws Exception
	 */
	@Test
	void errorCreate01() throws Exception {
		goingRegisterRequest.setGoingDate(null);
		ResultActions actions = mockMvc
				.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
				.andExpect(model().hasErrors())
				.andExpect(view().name("goingRegister"));

		ModelAndView mnv = actions.andReturn().getModelAndView();
		BindingResult bindingResult = (BindingResult) mnv.getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "goingRegisterRequest");

		assertThat("出勤日を入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	}

	/**
	 * 【異常系】出勤時間がnullの場合、エラーメッセージ「出勤時間を入力してください」を所持していることを検証するテストケース
	 * @throws Exception
	 */
	@Test
	void errorCreate02() throws Exception {
		goingRegisterRequest.setGoingTime(null);
		ResultActions actions = mockMvc
				.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
				.andExpect(model().hasErrors())
				.andExpect(view().name("goingRegister"));

		ModelAndView mnv = actions.andReturn().getModelAndView();
		BindingResult bindingResult = (BindingResult) mnv.getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "goingRegisterRequest");

		assertThat("出勤時間を入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	}

	/**
	 * 【異常系】備考が100文字以上の場合、エラーメッセージ「備考欄は100文字以内で入力してください」を所持していることを検証するテストケース
	 * @throws Exception
	 */
	@Test
	void errorCreate03() throws Exception {
		goingRegisterRequest.setRemarks(random(101));
		ResultActions actions = mockMvc
				.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
				.andExpect(model().hasErrors())
				.andExpect(view().name("goingRegister"));

		ModelAndView mnv = actions.andReturn().getModelAndView();
		BindingResult bindingResult = (BindingResult) mnv.getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "goingRegisterRequest");

		assertThat("備考欄は100文字以内で入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	}

	/**
	 * 【異常系】ステータスがnullの場合、エラーメッセージ「ステータスの選択をしてください」を所持していることを検証するテストケース
	 * @throws Exception
	 */
	@Test
	void errorCreate04() throws Exception {
		goingRegisterRequest.setStatus(null);
		ResultActions actions = mockMvc
				.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
				.andExpect(model().hasErrors())
				.andExpect(view().name("goingRegister"));

		ModelAndView mnv = actions.andReturn().getModelAndView();
		BindingResult bindingResult = (BindingResult) mnv.getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "goingRegisterRequest");

		assertThat("ステータスの選択をしてください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	}

	/**
	 * 指定した文字数分ランダムで文字列を作成するメソッド
	 * @param length 文字数を指定する
	 * @return 指定した文字数分のランダムな文字列
	 */
	private String random(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			stringBuilder.append(random.nextInt(10));
		}
		return stringBuilder.toString();
	}
}
