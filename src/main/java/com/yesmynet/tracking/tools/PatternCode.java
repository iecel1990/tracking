package com.yesmynet.tracking.tools;

import java.util.regex.Pattern;

public class PatternCode {
	private Pattern pattern;
	private String pageGroupCode;
	
	public void setPageGroupCode(String pageGroupCode) {
		this.pageGroupCode = pageGroupCode;
	}
	
	public String getPageGroupCode() {
		return pageGroupCode;
	}
	
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
	public Pattern getPattern() {
		return pattern;
	}
}
