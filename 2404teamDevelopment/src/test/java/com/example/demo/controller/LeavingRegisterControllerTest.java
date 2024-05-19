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
		LeavingRegisterEntity leavingRegister = new LeavingRegisterEntity();
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegister.setAttendance_id(1);
		leavingRegister.setUser_id(1);
		leavingRegister.setStatus(null);
		leavingRegister.setLeaving_date(null);
		leavingRegister.setLeaving_time(null);
		leavingRegister.setBreak_time(null);
		leavingRegister.setRemarks(null);
		leavingRegisterRequest.setAttendance_id(leavingRegister.getAttendance_id());
		leavingRegisterRequest.setUser_id(leavingRegister.getUser_id());
		leavingRegisterRequest.setStatus(leavingRegister.getStatus());
		leavingRegisterRequest.setLeaving_date(leavingRegister.getLeaving_date());
		leavingRegisterRequest.setLeaving_time(leavingRegister.getLeaving_time());
		leavingRegisterRequest.setBreak_time(leavingRegister.getBreak_time());
		leavingRegisterRequest.setRemarks(leavingRegister.getRemarks());
		doReturn(leavingRegister).when(leavingRegisterService).findById(any());

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
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(1);
		leavingRegisterRequest.setStatus("退勤");
		leavingRegisterRequest.setLeaving_date(null);
		leavingRegisterRequest.setLeaving_time(null);
		leavingRegisterRequest.setBreak_time(null);
		leavingRegisterRequest.setRemarks(null);
		

	
	}

}
