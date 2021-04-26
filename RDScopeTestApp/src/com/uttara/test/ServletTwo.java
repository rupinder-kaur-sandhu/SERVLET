package com.uttara.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletTwo
 */
public class ServletTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter pw = response.getWriter();
		pw.write("<html><body><h1>Response from S2</h1></body></html>");
		
		
		HttpSession session = request.getSession(false);
		ServletContext ctx = getServletContext();
		
		String reqData="";
		String sessData="";
		String ctxData="";
		
		if(request.getAttribute("reqData") != null) {
			reqData= (String)request.getAttribute("reqData");
		}
		
		if(session!= null && session.getAttribute("sessionData")!=null) {
			sessData = (String)session.getAttribute("sessionData");
			
		}
		
		if(ctx.getAttribute("ctxData") != null) {
			ctxData= (String)ctx.getAttribute("ctxData");
		}
		
		pw.write("<html><body><h1>Response from S2 reqData = "+reqData+ "<br/> session data = "+sessData+" <br/> context data = "+ctxData+"</h1></body></html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
