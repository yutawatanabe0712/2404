package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退勤情報更新リクエストデータ
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LeavingRegisterUpdateRequest extends LeavingRegisterRequest implements Serializable{
	  /**
	   * 勤怠ID
	   */
	 @NotNull
	 private Integer attendance_id;

}
