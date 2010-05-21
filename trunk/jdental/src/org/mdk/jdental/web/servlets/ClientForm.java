package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.components.Controller;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.web.ClientFormImpl;
import org.mdk.jdental.web.FormGenerator;
import org.mdk.jdental.web.FormValidator;

public class ClientForm extends HttpServlet {
	
	private static final long serialVersionUID = -1022121095896474600L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormGenerator fg = new FormGenerator("ClientFormImpl", "ClientForm", new ClientFormImpl());
	
		
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		out.println(fg.displayForm());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SecurityException {
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		
		
		out.println("<br><br>");
		
			try {
				FormValidator fv = new FormValidator(request);
				
				if(fv.getError()){
					out.println(fv.getErrorMsg());
				}else{
					out.println(fv.getSuccessMsg());
					Controller control =  new Controller();
					boolean transaction = control.genericInsert(fv.getFormData(),fv.getFfList(),fv.getSql());
					if(!transaction) out.println("DB error");
				}
			} catch (TopLevelException e) {
				out.println("<label class='error'>"+e.getStackTraceElements()+"</label>");
			} 
			out.println("<br><br>");
			out.println(ServletUtils.getInstance().getHTMLFooter());
			out.close();
	}
}
