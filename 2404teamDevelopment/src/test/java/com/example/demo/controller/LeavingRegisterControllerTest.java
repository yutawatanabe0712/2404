package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.LeavingRegisterRequest;
import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.service.LeavingRegisterService;

@WebMvcTest(LeavingRegisterController.class)
public class LeavingRegisterControllerTest {

	@MockBean
	LeavingRegisterService leavingRegisterService;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 【正常系】GETリクエストが正常に処理され、勤怠ID,ユーザーIDを表示して、退勤登録画面が表示されレスポンスとして正しく返ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void testDisplaySucess1() throws Exception {
		LeavingRegisterEntity leavingRegisterEntity = new LeavingRegisterEntity();
		leavingRegisterEntity.setAttendance_id(1);
		leavingRegisterEntity.setUser_id(1);
		leavingRegisterEntity.setStatus(null);
		leavingRegisterEntity.setLeaving_date(null);
		leavingRegisterEntity.setLeaving_time(null);
		leavingRegisterEntity.setBreak_time(null);
		leavingRegisterEntity.setRemarks(null);
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(1);
		leavingRegisterRequest.setStatus(null);
		leavingRegisterRequest.setLeaving_date(null);
		leavingRegisterRequest.setLeaving_time(null);
		leavingRegisterRequest.setBreak_time(null);
		leavingRegisterRequest.setRemarks(null);
		doReturn(leavingRegisterEntity).when(leavingRegisterService).findById(any());

		mockMvc.perform(get("/featuer/leavingRegister/1"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("leavingRegisterUpdateRequest", leavingRegisterRequest))
				.andExpect(view().name("leavingRegister"));
		Mockito.verify(leavingRegisterService, times(1)).findById(1);
	}

	/**
	 * 【正常系】POSTリクエストが正常に処理され、バリデーションエラーなく退勤登録が行われ、退勤登録画面が表示されることを検証する
	 *  @throws Exception
	 */
	@Test
	public void testCreateUserSucess2() throws Exception {

	}

}
