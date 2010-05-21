package org.mdk.jdental.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdk.jdental.components.Controller;
import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.utils.GenericUtilities;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String form = 	"<div align='left'><form name=\"login\" method=\"post\" " +
		"action=\"#\" class=\"box\"> " +
		"<label>Login <input type='text' name='login' tabindex=\"1\" ><br> </label>" +
		"<label>Senha <input type='password' name='passwd' tabindex=\"2\" ></label>" +
		"&nbsp &nbsp" +
		"<label><input type=\"submit\" name=\"Submit\"" +
		"value=\"OK\" tabindex=\"3\" class=\"botao\">" +
		"</label></form></div><br>" +
		"Se você não possui uma conta no jDental, clique <a href='CreateAccount'>aqui</a><br>";
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		out.println(form);
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader(Constants.APP_NAME));
		
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");
		
		GenericUtilities gu = new GenericUtilities();
		boolean testLogin = gu.regex(login, "[a-zA-Z]{3,}");
		boolean testPasswd =gu.regex(passwd, "[a-zA-Z]{3,}");
		
		
		if(testLogin && testPasswd){
			
			Controller control = new Controller();
			boolean testSession = control.openSessionForUser(login, passwd);
			
			if(testSession){
				Cookie cookie = new Cookie("login",login);
				response.addCookie(cookie);
				out.println(ServletUtils.getInstance().getVerticalAdminMenu());
				out.println(ServletUtils.getInstance().getVerticalNormalMenu());
				
			}else{
				out.println("Invalid login or Password [0001]");
			}
			
		}else{
			out.println("Invalid login or Password [0000]");
		}
		
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

}
