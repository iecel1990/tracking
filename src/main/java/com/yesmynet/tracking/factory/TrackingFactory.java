package com.yesmynet.tracking.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.lang3.StringUtils;

import com.yesmynet.tracking.model.Model;
import com.yesmynet.tracking.model.TrackingModel;
import com.yesmynet.tracking.tools.PatternCode;
import com.yesmynet.tracking.tools.TrackingTools;
import com.yesmynet.tracking.tools.TrackingTypeEmun;

public class TrackingFactory implements Factory {

	private int pagePropertypeLength = 50;
	
	public Model buildModel(HttpServletRequest request, HttpServletResponse response,List<PatternCode> patternCodeList) {
		String dts = request.getParameter("dts");

		TrackingModel tm = new TrackingModel();
		tm.setCookieToken(TrackingTools.getCookie(request, "cookie_token"));
		tm.setIsNewPv(TrackingTools.getCookie(request, "is_new_pv"));
		this.processUrl(tm, request);
		tm.setMemberId(TrackingTools.getCookie(request, "login_member_id"));
		tm.setTime(new Date());
		
		if (StringUtils.isNotBlank(request.getParameter("loadTime"))) {
			try {
				tm.setLoadTime(Long.parseLong(request.getParameter("loadTime")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tm.setEventCode(request.getParameter("eventCode"));
		tm.setEventParam(request.getParameter("eventParam"));
		tm.setPageGroupCode(this.getPageGroupCode(request, patternCodeList));
		tm.setReferePageUrl(request.getParameter("refere"));
		tm.setReferePageIndex(request.getParameter("index"));
		
		tm.setType(request.getParameter("type"));
		if(StringUtils.equals(tm.getType(), TrackingTypeEmun.PAGE.toString())) {
			tm.setReferePageBlock(dts);
		} else {
			tm.setEventCode(dts);
		}
		
		tm.setAdType(TrackingTools.getCookie(request, "keytype"));
		tm.setAdSource(TrackingTools.getCookie(request, "keyname"));
		tm.setAdCampaign(TrackingTools.getCookie(request, "keysource"));
		tm.setArg1(TrackingTools.getCookie(request, "keyrefer"));
		
		this.processAdDetails(tm, request);
		tm.setGoodsId(request.getParameter("goodsId"));
		tm.setCityId(TrackingTools.getCookie(request, "single_goods_area_name"));
		tm.setSessionid(TrackingTools.getCookie(request, "cache_token"));
		tm.setIp(TrackingTools.getIp(request));
		tm.setUserAgent(request.getHeader("User-Agent"));
		
		if (StringUtils.isNotBlank(tm.getUserAgent())) {
			UserAgent userAgent = UserAgent.parseUserAgentString(tm.getUserAgent());
			if (userAgent != null) {
				tm.setOsInfo(userAgent.getOperatingSystem() == null ? null : userAgent.getOperatingSystem().toString());
				tm.setBrowserInfo(userAgent.getBrowser() == null ? null : userAgent.getBrowser().toString());
			}
		}

		tm.setResolutionInfo(request.getParameter("res"));
		tm.setLangInfo(request.getHeader("Accept-Language"));
		
		if (StringUtils.isNotBlank(tm.getUserAgent())) {
			UserAgent userAgent = UserAgent.parseUserAgentString(tm.getUserAgent());
			if (userAgent != null) {
				tm.setOsInfo(userAgent.getOperatingSystem() == null ? null : userAgent.getOperatingSystem().toString());
				tm.setBrowserInfo(userAgent.getBrowser() == null ? null : userAgent.getBrowser().toString());
			}
		}

		tm.setResolutionInfo(request.getParameter("res"));
		tm.setLangInfo(request.getHeader("Accept-Language"));
		
		HashMap<Integer, String> pagePropertypeMap = new HashMap<Integer, String>(5);
		for(int i = 0,j = pagePropertypeLength; i < j; i++) {
			String pt = request.getParameter("pt" + (i + 1));
			if (StringUtils.isNotBlank(pt)) {
				pagePropertypeMap.put(i, pt);
			}
		}
		tm.setPagePropertypeMap(pagePropertypeMap);
		
		return tm;
	}
	
	private void processAdDetails(TrackingModel trackingModel,HttpServletRequest request) {
		String adDetails = TrackingTools.getCookie(request,"keydetails");
		if (StringUtils.isNotBlank(adDetails)) {
			String [] adDetailArr = adDetails.split("/",4);
		
			trackingModel.setAdMedium(adDetailArr[0]);
			trackingModel.setAdTerm(adDetailArr[1]);
			trackingModel.setAdGroup(adDetailArr[2]);
			trackingModel.setAdContent(adDetailArr[3]);
		}
	}
	private String getPageGroupCode(HttpServletRequest request,List<PatternCode> patternCodeList) {
		return request.getParameter("pageGroupCode"); 
	}
	private void processUrl(TrackingModel trackingModel,HttpServletRequest request) {
		String url = request.getParameter("url");
		try {
			URL urlObj = new URL(url);
			if (urlObj != null) {
				trackingModel.setHost(urlObj.getHost());
				trackingModel.setUri(urlObj.getPath());
				trackingModel.setQuery(urlObj.getQuery());
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) throws MalformedURLException {
		Long startTime = new Date().getTime();
		for (int i = 0; i < 100000;i ++) {
			URL urlObj = new URL("http://tracking.yesmywine.com/tracking" + i + "/tracking?r=1390361361696&dts=I10&index=3&url=http://www.yesmywine.com/goods/2.html?dts=I10.3");
			String host =  urlObj.getHost();
			String query = urlObj.getQuery();
			String path = urlObj.getPath();
			
		}
		Long endTime = new Date().getTime();
		System.out.println("time:" + (endTime - startTime));
	}*/
	
	public void setPagePropertypeLength(int pagePropertypeLength) {
		this.pagePropertypeLength = pagePropertypeLength;
	}
	
	public int getPagePropertypeLength() {
		return pagePropertypeLength;
	}
}
