package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 出勤登録に関するリクエストデータを表すDTO。
 * フォームからの入力値を検証し、アプリケーション内でのデータ処理に使用される。
 */
@Data
public class GoingRegisterRequest implements Serializable {

	@NotNull(message = "ユーザーIDを入力してください")
	private Integer userId;

	@NotBlank(message = "ステータスの選択をしてください")
	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "出勤日を入力してください")
	private LocalDate goingDate;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "出勤時間を入力してください")
	private LocalTime goingTime;

	@Length(max = 100, message = "備考欄は100文字以内で入力してください")
	private String remarks;
}
