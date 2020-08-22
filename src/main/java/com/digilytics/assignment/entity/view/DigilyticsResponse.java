package com.digilytics.assignment.entity.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DigilyticsResponse {

	private int rowParsed;
	private int rowFailed;

	@JsonInclude(Include.NON_ABSENT)
	private String errorFileUrl;

	public DigilyticsResponse(int rowParsed, int rowFailed) {

		super();
		this.rowParsed = rowParsed;
		this.rowFailed = rowFailed;
	}

	public DigilyticsResponse(int rowParsed, int rowFailed, String errorFileUrl) {

		super();
		this.rowParsed = rowParsed;
		this.rowFailed = rowFailed;
		this.errorFileUrl = errorFileUrl;
	}

	public int getRowParsed() {
		return rowParsed;
	}

	public void setRowParsed(int rowParsed) {
		this.rowParsed = rowParsed;
	}

	public int getRowFailed() {
		return rowFailed;
	}

	public void setRowFailed(int rowFailed) {
		this.rowFailed = rowFailed;
	}

	public String getErrorFileUrl() {
		return errorFileUrl;
	}

	public void setErrorFileUrl(String errorFileUrl) {
		this.errorFileUrl = errorFileUrl;
	}

}
