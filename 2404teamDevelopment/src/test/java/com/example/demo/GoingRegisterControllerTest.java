package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.GoingRegisterController;
import com.example.demo.dto.GoingRegisterRequest;
import com.example.demo.service.GoingRegisterService;

@WebMvcTest(GoingRegisterController.class)
class GoingRegisterControllerTest {

	@MockBean
	GoingRegisterService goingRegisterService;

	@Autowired
	private MockMvc mockMvc;

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
	void successfullyCreate() throws Exception  {
		GoingRegisterRequest goingRegisterRequest = new GoingRegisterRequest();
		goingRegisterRequest.setUserId(1);
		goingRegisterRequest.setGoingDate(LocalDate.of(2024, 10, 10));
		goingRegisterRequest.setGoingTime(LocalTime.of(10, 10));
		goingRegisterRequest.setRemarks("remarks");
		goingRegisterRequest.setStatus("status");
		mockMvc.perform(post("/goingRegister/create/1").flashAttr("goingRegisterRequest", goingRegisterRequest))
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("goingRegister"));
		verify(goingRegisterService, times(1)).create(goingRegisterRequest);
	}

}
