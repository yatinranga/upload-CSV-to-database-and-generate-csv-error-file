package com.digilytics.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digilytics.assignment.batch.JobRunner;
import com.digilytics.assignment.batch.UserProcessor;
import com.digilytics.assignment.entity.view.DigilyticsResponse;

@RestController
public class JobController {

	private JobRunner jobRunner;

	@Autowired
	public JobController(JobRunner jobRunner) {
		this.jobRunner = jobRunner;
	}

	@RequestMapping(value = "/register")
	public DigilyticsResponse runJob() {
		jobRunner.runBatchJob();
		String.format("Job submitted successfully.");

		if (UserProcessor.rowFailed != 0) {
			return new DigilyticsResponse(UserProcessor.rowParsed, UserProcessor.rowFailed, "/download/errorFile.csv");
		} else {
			return new DigilyticsResponse(UserProcessor.rowParsed, UserProcessor.rowFailed);
		}

	}

}
