package com.yesmynet.tracking;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yesmynet.tracking.factory.Factory;
import com.yesmynet.tracking.model.Model;
import com.yesmynet.tracking.tools.PatternCode;

/**
 * 
 * @author jrose_shaw
 * @date: 2014-01-15
 */
public class TrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger  = Logger.getLogger("com.yesmynet.tracking.TrackingServlet");
	
	private static Factory factory; 
	private static String factoryName;
	private List<PatternCode> patternCodeList;
    /**
     * Default constructor. 
     */
    public TrackingServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		factoryName = config.getInitParameter("factoryName");
		if(StringUtils.isBlank(factoryName)) {
			throw new ServletException("factoryName can't be null,set on web.xml.");
		}
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath*:spring/*.xml"); 

		factory = (Factory) applicationContext.getBean(factoryName);
		if(null == factory) {
			throw new ServletException("factory can't be load.");
		}
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = factory.buildModel(request, response,patternCodeList);
		logger.info(model); 
		response.setContentType("text/javascript");
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
