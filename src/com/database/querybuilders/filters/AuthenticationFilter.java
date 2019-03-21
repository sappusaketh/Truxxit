package com.database.querybuilders.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.querybuilders.beans.UserX;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
			
		HttpSession session = req.getSession(false);
		
		if(null!=session) {
			UserX user = (UserX) session.getAttribute("User");
			
			if(null!=user) {
				String userName = user.getUserName();
				chain.doFilter(request, response);
			}else if(!(uri.endsWith("LoginServlet") || uri.endsWith("login.jsp"))){
				res.sendRedirect("login.jsp");
			}else {
				chain.doFilter(request, response);
			}
		}else if(session == null && !(uri.endsWith("jsp") || uri.endsWith("LoginServlet") || uri.endsWith("Register"))){
			res.sendRedirect("login.jsp");
		}else{
			chain.doFilter(request, response);
		}	
		
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
