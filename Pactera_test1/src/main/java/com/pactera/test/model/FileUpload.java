package com.pactera.test.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private MultipartFile csvfile;
	private MultipartFile jsonfile;
	public MultipartFile getCsvfile() {
		return csvfile;
	}
	public void setCsvfile(MultipartFile csvfile) {
		this.csvfile = csvfile;
	}
	public MultipartFile getJsonfile() {
		return jsonfile;
	}
	public void setJsonfile(MultipartFile jsonfile) {
		this.jsonfile = jsonfile;
	}
}
