package com.yesmynet.tracking.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据跟踪模型
 * @author jrose_shaw
 *	
 */
public class TrackingModel implements Model{
	
	
	private String cookieToken;
	
	private String isNewPv;
	
	private String host;
	
	private String uri;
	
	private String query;
	
	//会员ID
	private String memberId;
	//访问的时间
	private Date time;
	
	private Long loadTime;

	private String type;
	
	private String eventCode;
	
	private String eventParam;
	
	private String pageGroupCode;
	
	private String referePageUrl;

	private String referePageBlock;
	
	private String referePageIndex;
	
	private String adType;
	
	private String adSource;
	
	private String adMedium;
	
	private String adCampaign;
	
	private String adTerm;
	
	private String adGroup;

	private String adContent;
	
	private String goodsId;
	
	private String cityId;
	
	private String sessionid;
	
	//用户IP
	private String ip;
	
	//客户端信息
	private String userAgent;
	
	//操作系统
	private String osInfo;
	
	//浏览器
	private String browserInfo;
	
	//客户端分辨率
	private String resolutionInfo;
	
	//浏览器语言
	private String langInfo;
	
	//备用
	private String arg1;
	private String arg2;
	private String arg3;
	private String arg4;
	private String arg5;
	private String arg6;
	private String arg7;
	private String arg8;
	private String arg9;
	private String arg10;
	
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//页面属性
	private Map<Integer, String> pagePropertypeMap;
	
	private int pagePropertypeLength = 50;
	
	private boolean skip = false;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(300);
		
		
		sb.append(TrackingModel.replace(this.cookieToken)).append("\t");
		sb.append(TrackingModel.replace(this.isNewPv)).append("\t");
		sb.append(TrackingModel.replace(this.host)).append("\t");
		sb.append(TrackingModel.replace(this.uri)).append("\t");
		sb.append(TrackingModel.replace(this.query)).append("\t");
		sb.append(TrackingModel.replace(this.memberId)).append("\t");
		sb.append(TrackingModel.replace(df.format(this.time))).append("\t");
		sb.append(TrackingModel.replace(this.loadTime)).append("\t");
		sb.append(TrackingModel.replace(this.type)).append("\t");
		sb.append(TrackingModel.replace(this.eventCode)).append("\t");
		sb.append(TrackingModel.replace(this.eventParam)).append("\t");
		sb.append(TrackingModel.replace(this.pageGroupCode)).append("\t");
		sb.append(TrackingModel.replace(this.referePageUrl)).append("\t");
		sb.append(TrackingModel.replace(this.referePageBlock)).append("\t");
		sb.append(TrackingModel.replace(this.referePageIndex)).append("\t");
		sb.append(TrackingModel.replace(this.adType)).append("\t");
		sb.append(TrackingModel.replace(this.adSource)).append("\t");
		sb.append(TrackingModel.replace(this.adMedium)).append("\t");
		sb.append(TrackingModel.replace(this.adCampaign)).append("\t");
		sb.append(TrackingModel.replace(this.adTerm)).append("\t");
		sb.append(TrackingModel.replace(this.adGroup)).append("\t");
		sb.append(TrackingModel.replace(this.adContent)).append("\t");
		sb.append(TrackingModel.replace(this.goodsId)).append("\t");
		sb.append(TrackingModel.replace(this.cityId)).append("\t");
		sb.append(TrackingModel.replace(this.sessionid)).append("\t");
		sb.append(TrackingModel.replace(this.ip)).append("\t");
		sb.append(TrackingModel.replace(this.userAgent)).append("\t");
		sb.append(TrackingModel.replace(this.osInfo)).append("\t");
		sb.append(TrackingModel.replace(this.browserInfo)).append("\t");
		sb.append(TrackingModel.replace(this.resolutionInfo)).append("\t");
		sb.append(TrackingModel.replace(this.langInfo)).append("\t");
		
		sb.append(TrackingModel.replace(this.arg1)).append("\t");
		sb.append(TrackingModel.replace(this.arg2)).append("\t");
		sb.append(TrackingModel.replace(this.arg3)).append("\t");
		sb.append(TrackingModel.replace(this.arg4)).append("\t");
		sb.append(TrackingModel.replace(this.arg5)).append("\t");
		sb.append(TrackingModel.replace(this.arg6)).append("\t");
		sb.append(TrackingModel.replace(this.arg7)).append("\t");
		sb.append(TrackingModel.replace(this.arg8)).append("\t");
		sb.append(TrackingModel.replace(this.arg9)).append("\t");
		sb.append(TrackingModel.replace(this.arg10)).append("\t");
		
		if (pagePropertypeMap != null) {
			for(int i = 0, j = pagePropertypeLength; i < j; i++){
				if (pagePropertypeMap.size() > 0) {
					sb.append(TrackingModel.replace(pagePropertypeMap.get(i)));
				}
				if(i < j - 1){
					sb.append("\t");
				}
			}
		}
		
		
		return sb.toString();
	}
	
	public static String replace(Object o) {
		if (o==null) return "";
		o = o.toString().replace('\t', ' ');
		o = o.toString().replace("&", "& ");
		return o.toString();
	}

	public String getCookieToken() {
		return cookieToken;
	}

	public void setCookieToken(String cookieToken) {
		this.cookieToken = cookieToken;
	}

	public String getIsNewPv() {
		return isNewPv;
	}

	public void setIsNewPv(String isNewPv) {
		this.isNewPv = isNewPv;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Long getLoadTime() {
		return loadTime;
	}
	
	public void setLoadTime(Long loadTime) {
		this.loadTime = loadTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventParam() {
		return eventParam;
	}

	public void setEventParam(String eventParam) {
		this.eventParam = eventParam;
	}

	public String getPageGroupCode() {
		return pageGroupCode;
	}

	public void setPageGroupCode(String pageGroupCode) {
		this.pageGroupCode = pageGroupCode;
	}

	public String getReferePageUrl() {
		return referePageUrl;
	}

	public void setReferePageUrl(String referePageUrl) {
		this.referePageUrl = referePageUrl;
	}

	public String getReferePageBlock() {
		return referePageBlock;
	}

	public void setReferePageBlock(String referePageBlock) {
		this.referePageBlock = referePageBlock;
	}

	public String getReferePageIndex() {
		return referePageIndex;
	}

	public void setReferePageIndex(String referePageIndex) {
		this.referePageIndex = referePageIndex;
	}

	public String getAdType() {
		return adType;
	}
	
	public void setAdType(String adType) {
		this.adType = adType;
	}
	
	public String getAdSource() {
		return adSource;
	}

	public void setAdSource(String adSource) {
		this.adSource = adSource;
	}

	public String getAdMedium() {
		return adMedium;
	}

	public void setAdMedium(String adMedium) {
		this.adMedium = adMedium;
	}

	public String getAdCampaign() {
		return adCampaign;
	}

	public void setAdCampaign(String adCampaign) {
		this.adCampaign = adCampaign;
	}

	public String getAdTerm() {
		return adTerm;
	}

	public void setAdTerm(String adTerm) {
		this.adTerm = adTerm;
	}

	public String getAdGroup() {
		return adGroup;
	}

	public void setAdGroup(String adGroup) {
		this.adGroup = adGroup;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getCityId() {
		return cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	public String getBrowserInfo() {
		return browserInfo;
	}

	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}

	public String getResolutionInfo() {
		return resolutionInfo;
	}

	public void setResolutionInfo(String resolutionInfo) {
		this.resolutionInfo = resolutionInfo;
	}

	public String getLangInfo() {
		return langInfo;
	}

	public void setLangInfo(String langInfo) {
		this.langInfo = langInfo;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public String getArg3() {
		return arg3;
	}

	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}

	public String getArg4() {
		return arg4;
	}

	public void setArg4(String arg4) {
		this.arg4 = arg4;
	}

	public String getArg5() {
		return arg5;
	}

	public void setArg5(String arg5) {
		this.arg5 = arg5;
	}

	public String getArg6() {
		return arg6;
	}

	public void setArg6(String arg6) {
		this.arg6 = arg6;
	}

	public String getArg7() {
		return arg7;
	}

	public void setArg7(String arg7) {
		this.arg7 = arg7;
	}

	public String getArg8() {
		return arg8;
	}

	public void setArg8(String arg8) {
		this.arg8 = arg8;
	}

	public String getArg9() {
		return arg9;
	}

	public void setArg9(String arg9) {
		this.arg9 = arg9;
	}

	public String getArg10() {
		return arg10;
	}

	public void setArg10(String arg10) {
		this.arg10 = arg10;
	}

	public void setPagePropertypeMap(Map<Integer, String> pagePropertypeMap) {
		this.pagePropertypeMap = pagePropertypeMap;
	}
	
	public Map<Integer, String> getPagePropertypeMap() {
		return pagePropertypeMap;
	}
	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
}
