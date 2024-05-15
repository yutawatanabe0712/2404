package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.LeavingRegisterRequest;
import com.example.demo.service.LeavingRegisterService;

@WebMvcTest(LeavingRegisterController.class)
public class LeavingRegisterControllerTest {
	
	@MockBean
	LeavingRegisterService leavingRegisterService;
	
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * 【正常系】POSTリクエストが正常に処理され、退勤登録画面が表示されレスポンスとして正しく返ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void Test01() throws Exception {
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(1);
		leavingRegisterRequest.setStatus("出勤");
		leavingRegisterRequest.setLeaving_date(null);
		leavingRegisterRequest.setLeaving_time(null);
		leavingRegisterRequest.setBreak_time(null);
		leavingRegisterRequest.setRemarks(null);
		mockMvc.perform(get("/featuer/leavingRegister/1"))
		       .andExpect(status().isOk())
		       .andExpect(model().attribute("leavingRegisterUpdateRequest", leavingRegisterRequest))
		       .andExpect(view().name("leavingRegister"));
	}
	
	/**
	 * 【正常系】POSTリクエストが正常に処理され、退勤登録画面が表示されることを検証する
	 *  @throws Exception
	 */
	@Test
	public void Test02() throws Exception{
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(1);
		leavingRegisterRequest.setStatus("退勤");
		leavingRegisterRequest.setLeaving_date();
		leavingRegisterRequest.setLeaving_time();
		leavingRegisterRequest.setBreak_time("01:00");
		leavingRegisterRequest.setRemarks(null);
		
		
		
		
	}
	

}
