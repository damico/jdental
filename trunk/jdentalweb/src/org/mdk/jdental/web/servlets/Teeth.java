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
		out.println("<div align='center'><img src='RenderServlet?file=periograma.png' alt='Periograma'></div>");
		out.println("<hr>");
		String index = "";
		for(int i=1; i<5; i++){
			
			out.println("<table width='100%'><tr>");
			for(int j=1; j<9; j++){
				index = String.valueOf(i) + String.valueOf(j);
				out.println("<td>"+ServletUtils.getInstance().getTooth(index)+"</td>");
			}
			out.println("</tr></table>");
			
		}
		
		
		
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	
		
}
