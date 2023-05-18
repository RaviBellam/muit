package com.techwl.stn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class STNReportsController {

	@GetMapping("/getReports")
	public String getReportDetails() {
		return "reports";
	}
}
