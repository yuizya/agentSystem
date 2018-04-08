package com.susanna.agentSystem.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CrossDomainFilter implements Filter{
//	private Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
		String []  allowDomain= {"http://localhost:63342","http://localhost:8080"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set allowedOrigins= new HashSet(Arrays.asList(allowDomain)); 
		String originHeader=((HttpServletRequest) request).getHeader("Origin"); 
		if (allowedOrigins.contains(originHeader)){ 
			httpResponse.setHeader("Access-Control-Allow-Origin", originHeader);  
			httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
			httpResponse.setHeader("Access-Control-Max-Age", "3600");  
			httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
			System.out.println("====Message====>>>域名："+originHeader+"可用");  
		}
		
        System.out.println("====Message====>>>过滤器被使用");  
        chain.doFilter(request, response); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
