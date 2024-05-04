package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "attendance_tb")
public class GoingRegisterEntity {

	@Id
	@Column(name = "attendance_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendanceId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "status")
	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "going_date")
	private LocalDate goingDate;

	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "going_time")
	private LocalTime goingTime;

	@Column(name = "remarks")
	private String remarks;
}
