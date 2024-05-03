package com.example.demo.entity;

import java.util.Date;

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
	private Integer attendance_id;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "status")
	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "going_date")
	private Date going_date;

	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "going_time")
	private Date going_time;

	@Column(name = "remarks")
	private String remarks;
}
