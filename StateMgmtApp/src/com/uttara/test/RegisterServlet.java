package com.uttara.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		//doGet(request, response);
		
		//access user inputs
		//apply validations
		//if validation succeed, write to db
		//if validation fauled, send error msg
		
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String rpass = request.getParameter("repeatpassword");
		
		StringBuilder sb = new StringBuilder();
		if(email == null || email.trim().equals(""))
			sb.append("Please enter email");
		if(pass==null || pass.trim().equals(""))
			sb.append("Please enter password");
		else
			if(!pass.equals(rpass))
				sb.append("your password and repeat password are not same");
		
		PrintWriter pw = response.getWriter();
		String msg = sb.toString();
		if(!msg.equals(""))
		{
			//validation failed
			pw.write("<html><body><h1>Error</h1><b>"+msg+"</b></body></html>");
			
		}
		else
		{
			//validation succeed\
			
			BufferedWriter bw = null;
			try
			{
				File f = new File(Constants.USERFILE);
				System.out.println("if file exists = "+f.exists()+" and File path "+f.getAbsolutePath());
				bw = new BufferedWriter(new FileWriter(Constants.USERFILE, true));
				bw.write(email +" = "+pass);
				bw.newLine();
			}
			catch(IOException e)
			{	
				
				e.printStackTrace();
				pw.write("<html><body><h1>Oops something happened</h1></body></html> " +e.getMessage());
				return;
			}
			finally {
				if(bw!=null)
					bw.close();
				
			}
			pw.write("<html><body><h1>Registration succeed</h1></body></html> ");
		}
	}

}
