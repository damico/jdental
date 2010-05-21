package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.utils.Constants;

/**
 * Servlet implementation class ScheduleDay
 */
public class ScheduleDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleDay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String y = request.getParameter("y");
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		if(m==null) month = ServletUtils.getInstance().getCurrentDate("month");
		else month = Integer.parseInt(m);
		if(d==null) day = ServletUtils.getInstance().getCurrentDate("day");
		else day = Integer.parseInt(d);
		if(y==null) year = ServletUtils.getInstance().getCurrentDate("year");
		else year = Integer.parseInt(y);
		
		StringBuffer dateForm = new StringBuffer();
		
		dateForm.append("<form method='post' action='ScheduleView'>\n");
		
		
		dateForm.append("<select name='d'>\n");
		dateForm.append("<option selected>"+(day)+"</option>\n");
		for(int i=1; i<32; i++){
			dateForm.append("<option>"+(i)+"</option>\n");
		}
		dateForm.append("</select>\n");
		
		dateForm.append("<select name='m'>\n");
		dateForm.append("<option selected>"+(month)+"</option>\n");
		for(int i=1; i<13; i++){
			dateForm.append("<option>"+(i)+"</option>\n");
		}
		dateForm.append("</select>\n");
		
		dateForm.append("<select name='y'>\n");
		dateForm.append("<option selected>"+(year)+"</option>\n");
		for(int i=1; i<11; i++){
			dateForm.append("<option>"+(year-i)+"</option>\n");
		}
		for(int i=1; i<11; i++){
			dateForm.append("<option>"+(year+i)+"</option>\n");
		}
		dateForm.append("</select>\n");
		
		
		
		
		dateForm.append("<input type='submit' value='Mudar data'>\n");
		dateForm.append("</form>\n"); 
		
		StringBuffer dayTable = new StringBuffer();
		
		
		
		
		dayTable.append("<table border='1' width='95%'>");
		for(int i=1; i<25; i++){
			dayTable.append("<tr><td width='20'>"+i+"h </td><td width='20'><form method='get' action='ScheduleForm'><input type='submit' value='Novo Compromisso'>" +
					"<input type='hidden' name='h' value='"+i+"'>\n" +
					"<input type='hidden' name='d' value='"+d+"'>\n" +
					"<input type='hidden' name='m' value='"+m+"'>\n" +
					"<input type='hidden' name='y' value='"+y+"'>\n" +
							"</form></td><td>&nbsp</td></tr>\n");
		}
		dayTable.append("</table>");
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		//out.println("<br><br>"+yearTitle+"<br>"+yearForm.toString());
		out.println("<br><br>"+dateForm.toString());
		out.println("<br><br>"+dayTable.toString());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
