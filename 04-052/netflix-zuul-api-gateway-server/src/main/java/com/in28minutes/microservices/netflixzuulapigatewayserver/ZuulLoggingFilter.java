/**
 * 
 */
package com.in28minutes.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author SEMPR
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
//		return false;
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		this.logger.info("request -> {} request URI -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
//		return null;
		return "pre";
	}

	@Override
	public int filterOrder() {
//		return 0;
		return 1;
	}

}