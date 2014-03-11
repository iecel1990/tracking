package com.yesmynet.tracking.factory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yesmynet.tracking.model.Model;
import com.yesmynet.tracking.tools.PatternCode;

public interface Factory {
	 public Model buildModel(HttpServletRequest request, HttpServletResponse response,List<PatternCode> patternCodeList);
}
