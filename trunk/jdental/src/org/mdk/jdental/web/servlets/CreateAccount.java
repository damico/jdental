package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.utils.Constants;

public class CreateAccount extends HttpServlet {

	private static final long serialVersionUID = 8580722875973702484L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String form = 	"<div align='left'><form name=\"login\" method=\"post\" " +
		"action=\"#\" class=\"login\"> " +
		"<label>" +
		"Login <input type='text' name='login' ><br> " +
		"Email <input type='text' name='email' value='@'><br> " +
		"Senha <input type='password' name='passwd'><br>" +
		"Repita Senha <input type='rpassword' name='passwd'><br>" +
		"&nbsp &nbsp" +
		"<input type=\"submit\" name=\"Submit\"" +
		"value=\"OK\" tabindex=\"3\" class=\"botao\">" +
		"</label></form></div><br>";
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(form);
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
