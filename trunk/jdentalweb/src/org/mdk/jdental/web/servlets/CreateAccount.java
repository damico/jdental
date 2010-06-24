package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.utils.LoggerManager;
import org.mdk.jdental.web.FormGenerator;
import org.mdk.jdental.web.FormValidator;
import org.mdk.jdental.web.RegFormImpl;
import org.mdk.netterklinik.components.Controller;

public class CreateAccount extends HttpServlet {

	private static final long serialVersionUID = 8580722875973702484L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		FormGenerator fg = new FormGenerator("RegFormImpl", "CreateAccount", new RegFormImpl());
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(fg.displayForm());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Controller control = new Controller();
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));

		
		
		out.println("<br><br>");
		
			try {
				FormValidator fv = new FormValidator(request);
				
				if(fv.getError()){
					out.println(fv.getErrorMsg());
				}else if(request.getParameter("Senha").equals(request.getParameter("Repita+a+Senha"))){
					out.println(fv.getSuccessMsg());
					control.regUser(fv.getFormData());
				}else{
					out.println("<label class='error'>As senhas não conferem.</label>");
				}
			} catch (TopLevelException e) {
				out.println("<label class='error'>"+e.getStackTraceElements()+"</label>");
				LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getStackTraceElements());
			} 
			out.println("<br><br>");
			out.println(ServletUtils.getInstance().getHTMLFooter());
			out.close();
	}
}
