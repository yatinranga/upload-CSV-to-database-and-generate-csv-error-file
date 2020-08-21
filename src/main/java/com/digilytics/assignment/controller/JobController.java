package com.digilytics.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digilytics.assignment.batch.JobRunner;

@RestController
public class JobController {

	private JobRunner jobRunner;

	@Autowired
	public JobController(JobRunner jobRunner) {
		this.jobRunner = jobRunner;
	}

	@RequestMapping(value = "/register")
	public String runJob() {
		jobRunner.runBatchJob();
		return String.format("Job Demo1 submitted successfully.");
	}

}
