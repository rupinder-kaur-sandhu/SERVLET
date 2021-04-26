package com.uttara.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletOne
 */
public class ServletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//get hold of request dispatcher and point it to /s2 also store data:req,session and context
		//forward the control
		
		RequestDispatcher rd = request.getRequestDispatcher("/two");
		
		HttpSession session = request.getSession(true);

        ServletContext ctx = getServletContext();
        
        request.setAttribute("reqData", "Data from Request");
        session.setAttribute("sessionData", "Data from session");
        ctx.setAttribute("ctxData", "Data from context");
		
		
		//include example
		//PrintWriter pw = response.getWriter();
		//pw.write("<html><body><h1> hii......");
		//rd.include(request, response);
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
