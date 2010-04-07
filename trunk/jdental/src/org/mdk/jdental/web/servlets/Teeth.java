package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.utils.Constants;

public class Teeth extends HttpServlet {

	private static final long serialVersionUID = -6461775433588106293L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println("<table width='210' border='0' cellpadding='0' cellspacing='0'>" +
					"<tr valign='top'><td><img src='RenderServlet?file=n11.png' alt='11'></td><td>" +
					"<form>" +
					"<table width='105' border='0' BACKGROUND='RenderServlet?file=cubic.png'>" +
					"<tr width='105'><td width='33'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'></td></tr>" +
					"<tr width='105'><td width='33'><input type='checkbox' name='a'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'><input type='checkbox' name='a'></td></tr>" +
					"<tr width='105'><td width='33'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'></td></tr>" +
					"</table>" +
					"</form>" +
					"</td><td>" +
					"<img src='RenderServlet?file=11.png' alt='11'>" +
					"</td></tr>" +
					"</table>");
		
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	
		
}
