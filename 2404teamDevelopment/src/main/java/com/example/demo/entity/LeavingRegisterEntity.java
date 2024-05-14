package com.example.demo.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 勤怠情報 Entity
 */
@Data
@Entity
@Table(name = "attendance_tb", schema = "public")
public class LeavingRegisterEntity {
	  /**
	  * ID
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "attendance_id")
	  private Integer attendance_id;
	   /**
	   * ユーザーID
	   */
	  @Column(name = "user_id")
	  private Integer user_id;
	   /**
	   * ステータス
	   */
	  @Column(name = "status")
	  private String status;
	   /**
	   * 出勤日
	   */
	  @Column(name = "going_date")
	  private Date going_date;
	   /**
	   * 出勤時間
	   */
	  @Column(name = "going_time")
	  private Date going_time;
	   /**
	   * 退勤日
	   */
	  @Column(name = "leaving_date")
	  private LocalDate leaving_date;
	   /**
	   * 退勤時間
	   */
	  @Column(name = "leaving_time")
	  private LocalTime leaving_time;
	   /**
	   * 稼動時間
	   */
	  @Column(name = "working_time")
	  private Date working_time;
	   /**
	   * 休憩時間
	   */
	  @Column(name = "break_time")
	  private LocalTime break_time;
	   /**
	   * 修正理由
	   */
	  @Column(name = "edit_reason")
	  private String edit_reason;
	   /**
	   * 備考
	   */
	  @Column(name = "remarks")
	  private String remarks;	
	  
}
