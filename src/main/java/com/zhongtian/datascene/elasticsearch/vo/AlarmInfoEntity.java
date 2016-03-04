package com.zhongtian.datascene.elasticsearch.vo;

public class AlarmInfoEntity {
    private String alarmId;
    
    private String timestamp;
    
    private String filePath;
    
    private String keyword;
    
    private String buSysName;
    
    private String host;
    
    private String ip;
    
    private String risk;
    
    private String prdType;
    
    private String suggest;
    
    private String message;

	public String getAlarmId() {
		return alarmId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

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

	public String getBuSysName() {
		return buSysName;
	}

	public void setBuSysName(String buSysName) {
		this.buSysName = buSysName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getPrdType() {
		return prdType;
	}

	public void setPrdType(String prdType) {
		this.prdType = prdType;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	@Override
	public String toString() {
		return "AlarmInfoEntity [alarmId=" + alarmId + ", timestamp=" + timestamp + ", filePath=" + filePath
				+ ", keyword=" + keyword + ", buSysName=" + buSysName + ", host=" + host + ", ip=" + ip + ", risk="
				+ risk + ", prdType=" + prdType + ", suggest=" + suggest + ", message=" + message + "]";
	}
	
	

}