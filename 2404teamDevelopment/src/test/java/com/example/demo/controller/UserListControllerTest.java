package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.UserListEntity;
import com.example.demo.service.UserListService;


@WebMvcTest(UserListController.class)
public class UserListControllerTest {

	@MockBean
	UserListService userListService;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 【正常系】GETリクエストが正常に処理され、ユーザー情報が0件の場合にユーザー一覧画面が表示されuserlistがレスポンスとして正しく返っていることを検証する
	 * @throws Exception
	 */
	@Test
	public void testDisplaySucess1() throws Exception {
		List<UserListEntity> userlist = new ArrayList<UserListEntity>();
		when(userListService.searchAll()).thenReturn(userlist);

		mockMvc.perform(get("/featuer/userList"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("userlist", userlist))
				.andExpect(view().name("userList"));

		int count = userListService.searchAll().size();
		assertEquals(0, count);
		Mockito.verify(userListService, times(2)).searchAll();
	}

	/**
	 * 【正常系】GETリクエストが正常に処理され、ユーザー情報が1件の場合にユーザー一覧画面が表示されuserlistがレスポンスとして正しく返ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void testDisplaySucess2() throws Exception {
		List<UserListEntity> userlist = new ArrayList<UserListEntity>();
		UserListEntity userListEntity = new UserListEntity();

		userListEntity.setUser_id(1);
		userListEntity.setName("山田太郎");
		userListEntity.setFurigana("ヤマダタロウ");
		userListEntity.setMail("aaaa@gmail.com");
		userListEntity.setPassword("123456789");
		userlist.add(userListEntity);
		when(userListService.searchAll()).thenReturn(userlist);

		mockMvc.perform(get("/featuer/userList"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("userlist", userlist))
				.andExpect(view().name("userList"));

		int count = userListService.searchAll().size();
		assertEquals(1, count);
		Mockito.verify(userListService, times(2)).searchAll();
	}

	/**
	 * 【正常系】GETリクエストが正常に処理され、ユーザー情報が2件の場合にユーザー一覧画面が表示されuserlistがレスポンスとして正しく返ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void testDisplaySucess3() throws Exception {
		List<UserListEntity> userlist = new ArrayList<UserListEntity>();
		UserListEntity userListEntity = new UserListEntity();
		UserListEntity userListEntity1 = new UserListEntity();

		userListEntity.setUser_id(1);
		userListEntity.setName("山田太郎");
		userListEntity.setFurigana("ヤマダタロウ");
		userListEntity.setMail("aaaa@gmail.com");
		userListEntity.setPassword("123456789");
		userlist.add(userListEntity);
		userListEntity1.setUser_id(2);
		userListEntity1.setName("鈴木一郎");
		userListEntity1.setFurigana("スズキイチロウ");
		userListEntity1.setMail("bbbb@gmail.com");
		userListEntity1.setPassword("abcdefg");
		userlist.add(userListEntity1);
		when(userListService.searchAll()).thenReturn(userlist);

		mockMvc.perform(get("/featuer/userList"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("userlist", userlist))
				.andExpect(view().name("userList"));

		int count = userListService.searchAll().size();
		assertEquals(2, count);
		Mockito.verify(userListService, times(2)).searchAll();
		

	}
	
	
	
	
	
	
	
 }
