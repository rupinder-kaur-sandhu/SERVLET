package com.uttara.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		StringBuilder sb = new StringBuilder();
		if(email == null || email.trim().equals(""))
			sb.append("Please enter email");
		if(pass==null || pass.trim().equals(""))
			sb.append("Please enter password");
		
		PrintWriter pw = response.getWriter();
		
		String msg = sb.toString();
		if(!msg.equals(""))
		{
			//validation failed
			pw.write("<html><body><h1>Error</h1><b>"+msg+"</b></body></html>");
			
		}
		else
		{
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(Constants.USERFILE));
				String line;
				while((line = br.readLine())!=null) {
					System.out.println("line = " +line);
					String[] sa = line.split("=");
					System.out.println("check email "+email+" String array  "+sa[0]+" and their equality "+email.equals(sa[0]));
					if(email.trim().equals(sa[0].trim()) && pass.trim().equals(sa[1].trim())) {
						System.out.println("is it coming here");
						HttpSession session = request.getSession(true);
						session.setAttribute(Constants.USER, email);
						pw.write("<html><body><h1>Welcome</h1><b>"+email+"</b><a href='logout'>Click to logout</a><br/><a href='ChangePassword.html'>Click to change password</a></body></html>");
						return;
					}
					
				}
				
					pw.write("<html><body><h1>Error</h1><b>Your email password combination does not match</b></body></html>");
	
				
			}
			catch(IOException e) {
				pw.write("<html><body><h1>Error</h1><b>oops something went wrong</b></body></html>"+e.getMessage());
				return;
			}
			finally {
				if(br!=null)
					br.close();
				
			}
			
		}
	}

}
