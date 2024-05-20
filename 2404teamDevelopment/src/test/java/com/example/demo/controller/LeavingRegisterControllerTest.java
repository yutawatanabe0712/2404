package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

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
	public void testCreateLeavingRegisterSucess2() throws Exception {
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(1);
		leavingRegisterRequest.setStatus("退勤");
		leavingRegisterRequest.setLeaving_date(LocalDate.of(2024, 05, 01));
		leavingRegisterRequest.setLeaving_time(LocalTime.of(19, 00));
		leavingRegisterRequest.setBreak_time(LocalTime.of(01, 00));
		leavingRegisterRequest.setRemarks(null);
		mockMvc.perform((post("/featuer/leavingRegister/create")).flashAttr("leavingRegisterRequest",leavingRegisterRequest))
//				.param("attendance_id", "1")
//				.param("user_id", "1")
//				.param("status", "退勤")
//				.param("leaving_date", "2024-05-01")
//				.param("leaving_time", "19:00")
//				.param("break_time", "01:00")
//				.param("Remarks", ""))

				.andExpect(status().isFound())
				.andExpect(model().hasNoErrors())
				.andExpect(view().name("redirect:/featuer/leavingRegister/1"));

	}

	/**
	 * 【異常系】POSTリクエストに対し必須項目であるユーザーIDがない状態で処理され、入力画面に"*ユーザーIDを入力してください"とバリデーションメッセージを持った上で表示される事を検証する
	 *  @throws Exception
	 */
	@Test
	public void testCreateLeavingRegisterError01() throws Exception {
		LeavingRegisterRequest leavingRegisterRequest = new LeavingRegisterRequest();
		leavingRegisterRequest.setAttendance_id(1);
		leavingRegisterRequest.setUser_id(null);
		leavingRegisterRequest.setStatus("退勤");
		leavingRegisterRequest.setLeaving_date(LocalDate.of(2024, 05, 01));
		leavingRegisterRequest.setLeaving_time(LocalTime.of(19, 00));
		leavingRegisterRequest.setBreak_time(LocalTime.of(01, 00));
		leavingRegisterRequest.setRemarks(null);

		//リクエストを送る事によるレスポンスをResultActions(変数名：actions)に格納し保持する
		ResultActions actions = mockMvc.perform((post("/featuer/leavingRegister/create")).flashAttr("leavingRegisterRequest",leavingRegisterRequest))
				.andExpect(model().hasErrors())
				.andExpect(model().attribute("leavingRegisterUpdateRequest", leavingRegisterRequest))
				.andExpect(view().name("leavingRegister"));

		//レスポンスのactionsを利用して画面側で持つ予定のデータ（バリデーションメッセージなど）をmnvに格納する
		ModelAndView mnv = actions.andReturn().getModelAndView();
		//mnvから特定のRequestで発生したバリデーションメッセージを取得する
		BindingResult bindingResult = (BindingResult) mnv.getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterRequest");

		//バリデーションエラー件数をcountに代入する
		int count = bindingResult.getErrorCount();
		//バリデーションエラー件数が正しいか確認
		assertEquals(1, count);
		//エラーメッセージ名が合っているか確認
		assertThat("*ユーザーIDを入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	}

}
