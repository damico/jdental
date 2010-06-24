package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.web.ClientFormImpl;
import org.mdk.jdental.web.FormGenerator;
import org.mdk.jdental.web.FormType;
import org.mdk.jdental.web.FormValidator;
import org.mdk.jdental.web.SearchClientFormImpl;
import org.mdk.netterklinik.components.Controller;

/**
 * Servlet implementation class SearchClient
 */
public class SearchClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormGenerator fg = new FormGenerator("SearchClientFormImpl", "SearchClient", new SearchClientFormImpl());
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		out.println(fg.displayForm());
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(ServletUtils.getInstance().getUserMenu(request));
		
		
		out.println("<br><br>");
		
			try {
				FormValidator fv = new FormValidator(request);
				
				if(fv.getError()){
					out.println(fv.getErrorMsg());
				}else{
					FormType ft = new ClientFormImpl();
					Controller control =  new Controller();
					SelectList sl = control.genericSearch(fv.getFormData(),fv.getFfList(),fv.getSql(), ft);
					out.println(ServletUtils.getInstance().sl2HtmlTable(sl));
				}
			} catch (TopLevelException e) {
				out.println("<label class='error'>"+e.getStackTraceElements()+"</label>");
			} 
			out.println("<br><br>");
			out.println(ServletUtils.getInstance().getHTMLFooter());
			out.close();
	}

}
