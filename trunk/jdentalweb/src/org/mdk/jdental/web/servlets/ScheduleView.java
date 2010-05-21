package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.utils.Constants;

/**
 * Servlet implementation class ScheduleView
 */
public class ScheduleView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int year = ServletUtils.getInstance().getCurrentDate("year");
		String syear = request.getParameter("syear");
		String yearTitle = "<h2>Ano atual: "+year+"</h2>";
		if(syear!=null){
			year = Integer.parseInt(syear);
			yearTitle = "<h2>Ano selecionado: "+year+"</h2>";
		}
		
		
		StringBuffer yearForm = new StringBuffer();
		
		yearForm.append("<form method='post' action='ScheduleView'>\n");
		yearForm.append("<select name='syear'>\n");
		yearForm.append("<option selected>"+(year)+"</option>\n");
		for(int i=1; i<11; i++){
			yearForm.append("<option>"+(year-i)+"</option>\n");
		}
		for(int i=1; i<11; i++){
			yearForm.append("<option>"+(year+i)+"</option>\n");
		}
		yearForm.append("</select><input type='submit' value='Mudar ano'>\n");
		yearForm.append("</form>\n"); 
		
		StringBuffer yearTable = new StringBuffer();
		
		
		
		
		yearTable.append("<table border='1'>");
		for(int i=1; i<13; i++){
			yearTable.append("<tr><td>"+ServletUtils.getInstance().getMonthName(i)+"</td>");
			for(int j=1; j<=31; j++){
				yearTable.append("<td>"+ServletUtils.getInstance().getValidDayOfMonth(i,j,year)+"</td>");
			}
			yearTable.append("</tr>");
		}
		yearTable.append("</table>");
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		out.println("<br><br>"+yearTitle+"<br>"+yearForm.toString());
		out.println("<br><br>"+yearTable.toString());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
