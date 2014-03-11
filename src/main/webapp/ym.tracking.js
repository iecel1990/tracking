/**
 * author:shaw_jrose
 * version:2.0
 * date:2013-12-01
 * copyright:yesmywine.com
 */
try {
	var Tracking = {
		start : function(options){
			this.trackingEndTime = new Date().getTime();
			
			this.settings = {
				url: ('https:' == document.location.protocol ? 'https://' : 'http://') + "tracking.yesmywine.com/tracking",
				hoverMilliSec : 1500
			};
			this.tracking = {};
			
			if(options) {
				jQuery.extend(settings, options);
			}
			//alert(this.tracking.goodsId);
			this.initParam();
			this.monitor();
			this.processUrl();
		},
		initParam : function(){
			this.tracking = {			
				dts : '',
				index : '',
				pageGroupCode : '',
				eventCode : '',
				eventParam : '',
				type : '',
				goodsId : '',
				loadTime : '',
				pt1:'',
				pt2:'',
				pt3:'',
				pt4:'',
				pt5:'',
				pt6:'',
				pt7:'',
				pt8:'',
				pt9:'',
				pt10:'',
				pt11:'',
				pt12:'',
				pt13:'',
				pt14:'',
				pt15:'',
				pt16:'',
				pt17:'',
				pt18:'',
				pt19:'',
				pt20:'',
				pt21:'',
				pt22:'',
				pt23:'',
				pt24:'',
				pt25:'',
				pt26:'',
				pt27:'',
				pt28:'',
				pt29:'',
				pt30:'',
				pt31:'',
				pt32:'',
				pt33:'',
				pt34:'',
				pt35:'',
				pt36:'',
				pt37:'',
				pt38:'',
				pt39:'',
				pt40:'',
				pt41:'',
				pt42:'',
				pt43:'',
				pt44:'',
				pt45:'',
				pt46:'',
				pt47:'',
				pt48:'',
				pt49:'',
				pt50:''
			};
			if(typeof _tracking != "undefined" &&_tracking) {
				jQuery.extend(this.tracking, _tracking);
			}
		},
		monitor : function(){			
			$(document).delegate("body","keydown",function(e,name,value){
				var $this = $(this);
				e = e || window.event;
				
				if(e.keyCode != 13) {
					return;
				}
				
				var currEle = $(e.target || e.srcElement);
				
				//获取有"data-dts"的父节点，包括自己
				var parentDts = currEle.closest("[data-dts]");
				
				var dataDts = parentDts.data("dts");
				
				//查找没有data-dts的元素,并且没有编制过DTS属性，
				if(!(parentDts && dataDts)) {
					return;
				}
				
				
				Tracking.processDelegate(e, name, value, $this, currEle, parentDts, dataDts);
			});
			
			$(document).delegate("body","mouseover",function(e,name,value){
				var $this = $(this);
				
				e = e || window.event;			
				var currEle = $(e.target || e.srcElement);
				
				
				//获取有"data-dts"的父节点，包括自己
				var parentDts = currEle.closest("[data-hdts]");
				
				var dataDts = parentDts.data("hdts");
				
				//查找没有data-dts的元素,并且没有编制过DTS属性，
				if(!(parentDts && dataDts)) {
					return;
				}
				
				currEle.attr("oTime",new Date().getTime());
			});
			
			$(document).delegate("body","mouseout",function(e,name,value){
				var $this = $(this);
				
				e = e || window.event;			
				var currEle = $(e.target || e.srcElement);
				
				
				//获取有"data-dts"的父节点，包括自己
				var parentDts = currEle.closest("[data-hdts]");
				
				var dataDts = parentDts.data("hdts");
				
				//查找没有data-dts的元素,并且没有编制过DTS属性，
				if(!(parentDts && dataDts)) {
					return;
				}
				var oTime = currEle.attr("oTime");
				var now = new Date().getTime();
				if (now - (parseFloat(oTime)) >= Tracking.settings.hoverMilliSec) {
					Tracking.processDelegate(e, name, value, $this, currEle, parentDts, dataDts,"HOVEREVENT");
				}
			});
			
			$(document).delegate("body","click",function(e,name,value){
				var $this = $(this);
				
				e = e || window.event;
				var currEle = $(e.target || e.srcElement);
				
				
				//获取有"data-dts"的父节点，包括自己
				var parentDts = currEle.closest("[data-dts]");
				
				//获取父元素是a的元素，包括自己
				var parentDtsByLink = currEle.closest("a");
				
				var dataDts = parentDts.data("dts");
				
				//查找没有data-dts的元素,并且没有编制过DTS属性，
				if(!(parentDts && dataDts)) {
					return;
				}
				
				
				if(parentDtsByLink && Tracking.equals(Tracking.toUpperCase(parentDtsByLink.attr("tagName")), "A")) {
					Tracking.processLink(e, name, value, $this, currEle,parentDts,dataDts);
				} else {
					Tracking.processDelegate(e, name, value, $this, currEle, parentDts, dataDts);
				}
			});
			
			
			$(document).delegate("body","mousedown",function(e,name,value){
				var $this = $(this);
				
				e = e || window.event;
				
				//当不是鼠标右键的时候，不处理
				if(e.button !=2) {
					return;
				}
				var currEle = $(e.target || e.srcElement);
				
				
				//获取有"data-dts"的父节点，包括自己
				var parentDts = currEle.closest("[data-dts]");
				
				//获取父元素是a的元素，包括自己
				var parentDtsByLink = currEle.closest("a");
				
				var dataDts = parentDts.data("dts");
				
				//查找没有data-dts的元素,并且没有编制过DTS属性，
				if(!(parentDts && dataDts)) {
					return;
				}
				
				
				if(parentDtsByLink && Tracking.equals(Tracking.toUpperCase(parentDtsByLink.attr("tagName")), "A")) {
					Tracking.processLink(e, name, value, $this, currEle,parentDts,dataDts);
				} else {
					Tracking.processDelegate(e, name, value, $this, currEle, parentDts, dataDts);
				}
			});
		},
		processLink : function (e,name,value,$this, currEle, parentDts,dataDts){
			this.initParam();
			
			//找到父节点最近一个a标签，包含自己
			currEle = currEle.closest("a");
			
			//需要统计下标的元素,默认查找所有的a
			var findTag = "a";
			if(parentDts.data("findTag")){
				findTag = parentDts.data("findTag");
			}
			
			var href = Tracking.trim(currEle.attr("href"));
			var hrefUpperCase = Tracking.toUpperCase(href);
			
			//是容器中的第几个元素，0为当前自己
			var index = this.getElementIndex(parentDts,$this, currEle, findTag);
			this.tracking.index = index;
			this.tracking.type = "PAGE";
			if(!href) {
				//合并业务参数和位置参数
				//dataDts = this.mergerBus(dataDts, index);
				Tracking.pushEvent(dataDts);
			} else if(hrefUpperCase.indexOf("#") == 0
				|| hrefUpperCase.indexOf("JAVASCRIPT") == 0
				|| hrefUpperCase.indexOf("ONCLICK") == 0
				||hrefUpperCase.indexOf("MAILTO") == 0) {
				
				//dataDts = this.mergerBus(dataDts, index);
				Tracking.pushEvent(dataDts);
			} else if(href && !currEle.attr("mdts") ){//有链接的，没有mark过dts，
				var newHref = href;
				var anchor = "";
				var params = "";				
				
				var paramsPos = href.indexOf("?");
				var anchorPos = href.indexOf("#");
				
				if(anchorPos >= 0){
					anchor = href.substring(anchorPos);
					newHref = href.substring(0,anchorPos);
					
				}
				//如果有参数，截取参数,获取新的URL
				if(paramsPos >= 0){
					//获取参数
					params = href.substring(paramsPos,(anchorPos > 0 ? anchorPos : href.length));
					var dtsPos = params.indexOf("dts=");
					if(dtsPos > 0){
						params = params.substring(0,dtsPos - 1);
						dataDts = "dts=" + dataDts;
						newHref = href.substring(1,paramsPos);
					} else {
						dataDts = "&dts=" + dataDts;
						newHref = href.substring(0,paramsPos);
					}
				} else {
					dataDts = "?dts=" + dataDts;
				}
				dataDts = this.mergerBus(dataDts, index);
				newHref = newHref + params + dataDts + anchor;
				currEle.attr("href",newHref);
				currEle.attr("mdts","true");
			} else {						
				//合并业务参数和位置参数
				//dataDts = this.mergerBus(dataDts, index);
				Tracking.pushEvent(dataDts);
			}
		},
		processDelegate : function (e,name,value,$this, currEle, parentDts,dataDts,eventType){
			this.initParam();
			if(eventType) {
				this.tracking.type = eventType;
			} else {
				this.tracking.type = "EVENT";
			}
			
			var findTag = currEle.attr("tagName");
			if(parentDts.data("findTag")){
				findTag = parentDts.data("findTag");
			}
			
			
			//是容器中的第几个元素，0为当前自己
			var index = this.getElementIndex(parentDts, $this, currEle, findTag);
			
			this.tracking.index = index;
			//合并业务参数和位置参数
			//dataDts = this.mergerBus(dataDts, index);
			
			if(this.tracking.type == "EVENT"){
				this.pushEvent(dataDts);
			} else if(this.tracking.type == "HOVEREVENT") {
				this.pushHoverEvent(dataDts);
			}
		},
		processUrl : function(){
			this.tracking.type = "PAGE";
			var dts = this.getParam("dts");
			var index = "";
			
			if(this.isNotBlank(dts) && dts.indexOf("\.") >= 0) {
				index = this.getEnd(dts, "\.");
				dts = this.getFront(dts, "\.");
			}
			this.tracking.dts = dts;
			this.tracking.index = index;
			this.post();
		},
		post : function(){
			this.processLoadTime();
			
			var src;
			
			src = this.settings.url + "?r=" + new Date().getTime();
			
			var url = window.location.href;
			var refere = document.referrer;
			
			for (var trackParam in this.tracking) {
				if (this.isNotBlank(this.tracking[trackParam])) {
					src += "&" + trackParam + "=" + escape(this.tracking[trackParam]);
				}
			}
			url = escape(url);
			refere = escape(refere);
			
			var res = "&url=" + url + "&refere=" + refere +"&res=" + screen.width + "*" + screen.height;
			src += res;
			var scripts = document.createElement("script");
			scripts.type = "text/javascript";
			scripts.src = src;
			scripts.onload = function () {
				$(scripts).remove();
			};
			document.body.appendChild(scripts);
		},
		push : function(dts){
			this.tracking.dts = dts;
			this.post();
		},
		processEvent : function (dts,eventParam,index){
			if (this.isBlank(dts)) {
				return;
			}
			
			var dataDtsJson = null;
			try {
				dataDtsJson = eval("(" + dts + ")");
			} catch (e) {
			}
			
			if(dataDtsJson) {
				jQuery.extend(this.tracking, dataDtsJson);
			} else {
				var oDts = dts;
				
				var index = "";
				var eventCode = "";
				
				if (oDts.indexOf("-") >= 0) {
					eventCode = this.getFront(oDts, "-");
					dts = eventCode;
					eventParam = this.getEnd(oDts, "-");
					
					if (this.isNotBlank(eventCode)) {
						eventCode = this.getFront(eventCode, "\.");
						index = this.getEnd(eventCode, "\.");
					}
				} else if (oDts.indexOf("\.") >= 0) {
					dts = this.getFront(oDts, "\.");
					index = this.getEnd(oDts, "\.");
					eventCode = dts;
				}
				
				this.tracking.dts = dts;
				this.tracking.eventCode = dts;
				
				if (this.isNotBlank(index)) {
					this.tracking.index = index;
				}
				
				if (this.isNotBlank(eventParam)) {
					this.tracking.eventParam = eventParam;
				}
			}
			
			this.post();
		},
		pushEvent: function(dts,eventParam,index){
			this.tracking.type = "EVENT";
			this.processEvent(dts,eventParam,index);
		},
		pushHoverEvent : function (dts,eventParam,index){
			this.tracking.type = "HOVEREVENT";
			this.processEvent(dts,eventParam,index);
		},
		pushErrTip : function (evenCode,eventParam,index){
			this.tracking.type = "ERRTIP";
			this.processEvent(dts,eventParam,index);
		},
		getParam : function(strName) {
			var strHref = document.location.toString();
			
			if(strHref.indexOf("#") > 0){
				strHref = strHref.substring(0,strHref.indexOf("#"));
			}
			
			var intPos = strHref.indexOf("?");
			
			//获取到右边的参数部分
			var strRight = strHref.substring(intPos + 1); 
			var arrTmp = strRight.split("&");
			
		
			for (var i = 0; i < arrTmp.length; i++)
			{
				var dIntPos = arrTmp[i].indexOf("=");
				var paraName = arrTmp[i].substr(0, dIntPos);
				var paraData = arrTmp[i].substr(dIntPos + 1);
				
				if (paraName.toUpperCase() == strName.toUpperCase()) {
					return paraData;
				}
			}
			return "";
		},
		processLoadTime : function () {
			if (typeof _ym_page_loadtime  != "undefined" && this.trackingEndTime) {
				this.tracking.loadTime = this.trackingEndTime - _ym_page_loadtime;
			}
		},
		mergerBus : function(dataDts,index){
			//判断是否有业务参数，如果有，将位置参数加载业务参数前面
			if(dataDts.indexOf("-") >= 0) {
				dataDts = this.getFront(dataDts, "-") + "." + index + "-" + this.getEnd(dataDts, "-");
			} else {
				dataDts += "." + index;
			}
			return dataDts;
		},
		getElementIndex : function(parentDts, $this,currEle,findTag) {
			
			var index = 0;
			if(parentDts != $this){
				var parentDtsA = parentDts.find(findTag);
				parentDtsA.each(function(i){
					if(parentDtsA[i] == currEle.get(0)){
						index = i + 1;
						return false;
					}
				});
			}
			return index;
		},
		getFront : function (mainStr,searchStr){
			if(!mainStr || !searchStr) {
				return;
			}
		    var foundOffset = mainStr.indexOf(searchStr);
		    if(foundOffset == -1){
		       return null;  
		    }
		    return mainStr.substring(0,foundOffset);
		},
		getEnd : function (mainStr,searchStr){
			if(!mainStr || !searchStr) {
				return;
			}
		    var foundOffset = mainStr.indexOf(searchStr);  
		    if(foundOffset == -1){
		       return null;  
		    }  
		    return mainStr.substring(foundOffset + searchStr.length, mainStr.length);
		},
		equals : function (obj1,obj2) {
			if(obj1 == null || obj1 == undefined || obj2 == null || obj2 == undefined) {
				return false;
			}
			if(obj1 == obj2) {
				return true;
			}
		},
		trim : function (str){
			
			if(!str || str == null) {
				return null;
			}
			str += "";
			return str.replace(/(^\s*)|(\s*$)/g, ""); 
		},
		toUpperCase : function (str) {
			if(!str || str == null) {
				return null;
			}
			
			str = this.trim(str);
			return str.toUpperCase();
		},
		isBlank : function (str) {
			return !this.isNotBlank(str);
		},
		isNotBlank : function (str) {
			if(!str || str == undefined || this.trim(str) == ""){
				return false;
			}
			return true;
		}
	};
	
	$().ready(function(){
		Tracking.start();
	});
} catch (e){}