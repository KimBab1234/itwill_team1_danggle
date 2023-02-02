package com.itwillbs.team1_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itwillbs.team1_final.svc.WhLocationService;

@Controller
public class WhLocationController {

	@Autowired
	private WhLocationService service;
	
}
