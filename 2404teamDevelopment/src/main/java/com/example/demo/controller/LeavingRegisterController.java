package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LeavingRegisterController {
	
	@GetMapping("/featuer/leavingRegister")
	 public String leavingregister(@PathVariable Integer id,Model model) {
		
		return "leavingRegister";

	}
}
