package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.components.Controller;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.web.ClientFormImpl;
import org.mdk.jdental.web.FormGenerator;
import org.mdk.jdental.web.FormValidator;
import org.mdk.jdental.web.ScheduleFormImpl;

/**
 * Servlet implementation class ScheduleForm
 */
public class ScheduleForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleForm() {
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
		String h = request.getParameter("h");
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		if(m==null) month = ServletUtils.getInstance().getCurrentDate("month");
		else month = Integer.parseInt(m);
		if(d==null) day = ServletUtils.getInstance().getCurrentDate("day");
		else day = Integer.parseInt(d);
		if(y==null) year = ServletUtils.getInstance().getCurrentDate("year");
		else year = Integer.parseInt(y);
		
		
		

		StringBuffer form = new StringBuffer();
		form.append("<div class='box'><label class='head'>Agenda: Novo compromisso</label></div>\n");
		form.append("<form method='post' action='ScheduleForm' class='box'>\n");
		form.append("<label class='title'>Título:</label> <right><input type='text' name='event' value='' class='campos'></right><br>\n");
		form.append("<label class='title'>Início:</label> <right>"+ServletUtils.getInstance().getDateTimeComboBox(d, m, y, h, "0", "i")+"</right><br>\n");
		form.append("<label class='title'>Fim:</label> <right>"+ServletUtils.getInstance().getDateTimeComboBox(d, m, y, h, "60", "f")+"</right><br>\n");
		form.append("<label class='title'>Paciente:</label> <right>"+ServletUtils.getInstance().getClientComboBox()+"</right><br>\n");
		form.append("<label class='title'>Observacões:</label> <right> <textarea name='details' rows='10' cols='20'></textarea> </right><br>\n");
		form.append("<input type='hidden' name='object' value='org.mdk.jdental.web.ScheduleFormImpl'>\n");
		form.append("<br><input type='submit' value='Salvar novo compromisso!'><br>\n");

		form.append("</form>\n");
		
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		out.println(form.toString());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		Cookie login = cookies[0];
		
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
					Map<String, String> formData = fv.getFormData();
					formData.put("login", login.getValue());
					boolean transaction = control.scheduleInsert(formData,fv.getFfList(),fv.getSql());
					if(!transaction) out.println("DB error");
				}
			} catch (TopLevelException e) {
				out.println("<label class='error'>"+e.getStackTraceElements()+"</label>");
				e.printStackTrace();
			} 
			out.println("<br><br>");
			out.println(ServletUtils.getInstance().getHTMLFooter());
			out.close();
	}
	

}
