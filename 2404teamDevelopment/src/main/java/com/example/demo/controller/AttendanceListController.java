package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.AttendanceListResponse;
import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.service.AttendanceListService;

@Controller
public class AttendanceListController {
	  /**
	   * 勤怠情報 Service
	   */
	  @Autowired
	  AttendanceListService attendanceListService;
	 
	 @GetMapping("/featuer/attendanceLIst/list/{user_id}")
	 public String attendancelist(@PathVariable("user_id") Integer user_id, Model model) {
		 List<AttendanceListEntity> attendancelist = attendanceListService.findByUser_idEquals(user_id);
		 
		 //表示用DTO
		 List<AttendanceListResponse> attendanceListResponse= new ArrayList<AttendanceListResponse>(); 
		 
		 SimpleDateFormat sdf1
	        = new SimpleDateFormat("MM/dd");
		 SimpleDateFormat sdf2
	        = new SimpleDateFormat("hh:mm");
		 SimpleDateFormat sdf3
	        = new SimpleDateFormat("hh:mm");

	     for (AttendanceListEntity attendance: attendancelist ) {
	    	 AttendanceListResponse target= new AttendanceListResponse();
	    	 //表示レスポンスへ内容の詰め替え
	    	 BeanUtils.copyProperties(attendance , target);
	    	 if(attendance.getGoing_date() != null) {
	    		 target.setGoing_date(sdf1.format(attendance.getGoing_date()));
	    	 }
	    	 if(attendance.getGoing_time() != null) {
	    		 target.setGoing_time(sdf2.format(attendance.getGoing_time()));
	    	 }
	    	 if(attendance.getLeaving_date() != null) {
	    		 target.setLeaving_date(sdf1.format(attendance.getLeaving_date()));
	    	 }
	    	 if(attendance.getLeaving_time() != null) {
	    		 target.setLeaving_time(sdf2.format(attendance.getLeaving_time()));
	    	 }
	    	 if(attendance.getWorking_time() != null) {
	    		 target.setWorking_time(sdf3.format(attendance.getWorking_time()));
	    	 }
	    	 if(attendance.getBreak_time() != null) {
	    		 target.setBreak_time(sdf3.format(attendance.getBreak_time()));
	    	 }
	        attendanceListResponse.add(target);
	     }
	     
		 model.addAttribute("attendancelist", attendanceListResponse);
		 
		 return "attendanceList";
	 }
}
