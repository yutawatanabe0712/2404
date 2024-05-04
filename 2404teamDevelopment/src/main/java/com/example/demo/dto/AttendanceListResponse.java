package com.example.demo.dto;

import lombok.Data;

@Data
public class AttendanceListResponse {
	  /**
	   * 勤怠ID
	   */
	  private Integer attendance_id;
	 
	  private Integer user_id;
	  /**
	   * ステータス
	   */
	  private String status;
	  /**
	   * 出勤日
	   */
	  private String going_date;
	  /**
	   * 出勤時間
	   */
	  private String going_time;
	  /**
	   * 退勤日
	   */
	  private String leaving_date;
	  /**
	   * 退勤時間
	   */
	  private String leaving_time;
	  /**
	   * 稼働時間
	   */
	  private String working_time;
	  /**
	   * 休憩時間
	   */
	  private String break_time;
	  /**
	   * 修正理由
	   */
	  private String edit_reason;
	  /**
	   * 修正理由
	   */
	  private String remarks;
}
