package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
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
	 * 【正常系】GETリクエストが正常に処理され、科目一覧画面が表示されListがレスポンスとして正しく帰ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void Test01() throws Exception {
		List<UserListEntity> userlist = new ArrayList<UserListEntity>();
		UserListEntity userListEntity = new UserListEntity();
		
	    userListEntity.setUser_id(null);
	    userListEntity.setName(null);
	    userListEntity.setFurigana(null);
	    userListEntity.setMail(null);
	    userListEntity.setPassword(null);
	    userlist.add(userListEntity);
	    when(userListService.searchAll()).thenReturn(userlist);

	    mockMvc.perform(get("/featuer/userList"))
	            .andExpect(status().isOk())
	            .andExpect(model().attribute("userlist",userlist))
	            .andExpect(view().name("userList"));
	}

	/**
	 * 【正常系】GETリクエストが正常に処理され、ユーザー一覧画面が表示されListがレスポンスとして正しく帰ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void Test02() throws Exception {
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
	            .andExpect(model().attribute("userlist",userlist))
	            .andExpect(view().name("userList"));
	}
	
	/**
	 * 【正常系】GETリクエストが正常に処理され、ユーザー一覧画面が表示されListがレスポンスとして正しく帰ってきていることを検証する
	 * @throws Exception
	 */
	@Test
	public void Test03() throws Exception {
		List<UserListEntity> userlist = new ArrayList<UserListEntity>();
		UserListEntity userListEntity = new UserListEntity();
		
	    userListEntity.setUser_id(1,2);
	    userListEntity.setName("山田太郎","鈴木一郎");
	    userListEntity.setFurigana("ヤマダタロウ","スズキイチロウ");
	    userListEntity.setMail("aaaa@gmail.com","bbbb@gmail.com");
	    userListEntity.setPassword("123456789","abcdefg");
	    userlist.add(userListEntity);
	    when(userListService.searchAll()).thenReturn(userlist);
	    
	    mockMvc.perform(get("/featuer/userList"))
	            .andExpect(status().isOk())
	            .andExpect(model().attribute("userlist",userlist))
	            .andExpect(view().name("userList"));

   }
	
	
}
