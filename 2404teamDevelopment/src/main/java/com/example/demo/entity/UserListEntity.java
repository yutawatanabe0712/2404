package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_tb")
public class UserListEntity {
	  /**
	   * ID
	   */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "user_id")
	  private Integer user_id;
	  /**
	   * 名前
	   */
	  @Column(name = "name")
	  private String name;
	  /**
	   * フリガナ
	   */
	  @Column(name = "furigana")
	  private String furigana;
	  /**
	   * メールアドレス
	   */
	  @Column(name = "mail")
	  private String mail;
	  /**
	   * パスワード
	   */
	  @Column(name = "password")
	  private String password;
}
