package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "attendance_tb")
public class AttendanceListEntity {
	  /**
	   * 勤怠ID
	   */
	  @Id
	  @Column(name = "attendance_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer attendance_id;
	 
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
	  private Date leaving_date;
	  /**
	   * 退勤時間
	   */
	  @Column(name = "leaving_time")
	  private Date leaving_time;
	  /**
	   * 稼働時間
	   */
	  @Column(name = "working_time")
	  private Date working_time;
	  /**
	   * 休憩時間
	   */
	  @Column(name = "break_time")
	  private Date break_time;
	  /**
	   * 修正理由
	   */
	  @Column(name = "edit_reason")
	  private String edit_reason;
	  /**
	   * 修正理由
	   */
	  @Column(name = "remarks")
	  private String remarks;
}