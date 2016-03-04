package com.zhongtian.datascene;

public class LogstashConfigEntity {
	private String filePath;
	private String keyword;
	private String alertType;
	private String alertLevel;
	private String regular;
	private String lowerThreshold;
	private String highThreshold;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	public String getRegular() {
		return regular;
	}
	public void setRegular(String regular) {
		this.regular = regular;
	}
	public String getLowerThreshold() {
		return lowerThreshold;
	}
	public void setLowerThreshold(String lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}
	public String getHighThreshold() {
		return highThreshold;
	}
	public void setHighThreshold(String highThreshold) {
		this.highThreshold = highThreshold;
	}

}
