package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
* 退勤情報 リクエストデータ
*/
@Data
public class LeavingRegisterRequest implements Serializable{
	 /**
	  * 勤怠ID
	  */
	  Integer attendance_id;
	  /**
	  * ユーザーID
	  */
	  @NotEmpty(message = "ユーザーIDを入力してください")
	  private String nameuser_id;
	  /**
	  * ステータス
	  */
	  @NotEmpty(message = "ステータスを入力してください")
	  private String status;
	  /**
	  * 退勤日
	  */
	  @NotEmpty(message = "退勤日を入力してください")
	  private Date leaving_date;
	  /**
	  * 退勤時間
	  */
	  @NotEmpty(message = "退勤時間を入力してください")
	  private Date leaving_time;
	  /**
	  * 休憩時間
	  */
	  @NotEmpty(message = "休憩時間を入力してください")
	  private Date break_time;
	  /**
      * 備考
	  */
	  @Size(max = 100, message = "備考は100文字以下で入力してください")
	  private String remarks;
}
