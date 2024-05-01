package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

/**
* 科目情報 リクエストデータ
*/
@Data
public class LeavingRegisterRequest {
	 /**
	  * 勤怠ID
	  */
	  Integer attendance_id;
	  /**
	  * ユーザーID
	  */
	  private String nameuser_id;
	  /**
	  * ユーザーID
	  */
	  private String status;
	  /**
	  * 退勤日
	  */
	  private Date leaving_date;
	  /**
	  * 退勤時間
	  */
	  private Date leaving_time;
	  /**
	  * 休憩時間
	  */
	  private Date break_time;
	  /**
      * 備考
	  */
	  private String remarks;
}
