package org.mdk.jdental.web.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.web.SessionManager;

public class ServletUtils {
	private static ServletUtils INSTANCE = null;
	public static ServletUtils getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ServletUtils();
		}
		return INSTANCE;
	}
	
	public String getCSS(){
		String css = "\n<style type=\"text/css\">" +
				"<!-- \n" +
				"form.box { \n" +
				"background-color: #EFEFEF;\n" +
				"width:380px;\n" +
				"font: 11px Verdana, sans-serif;\n" +
				"color: #003399;\n" +
				"border: 0px solid #0000FF;\n" +
				"padding-left:30px;\n" +
				"padding-right:30px;\n" +
				"padding-top:30px;\n" +
				"padding-bottom:30px;\n" +
				"}\n" +
				".campos {\n" +
				"background-color:#B0E0E6;\n" +
				"font: 11px georgia, sans-serif;\n" +
				"color:#0033CC;margin-right: 20px;\n" +
				"}\n" +
				".title { \n" +
				"margin-right: 20px; \n" +
				"display:inline-block; \n" +
				"width: 50px; \n" +
				"}\n" +
				".botao {\n" +
				"background-color: #EFEFEF;\n" +
				"font: 10px Arial, sans-serif;\n" +
				"color: #0000FF;\n" +
				"}\n" +
				"-->\n" +
				"</style>\n";
		return css;
	}
	
	public String jsRedirect(String to){
		String ret = "<SCRIPT LANGUAGE=\"JavaScript\">window.location=\""+to+"\";</script>";
		return ret;
	}
	
	
	
	
	public String getHTMLalert(String msg){
		String ret = 	"<table width='400' bgcolor = '#CCFFFF' border='0' align='center' cellpadding='8' cellspacing='8'>" +
						"<tr valign='top'><td> "+msg+" </td></tr>" +
						"</table>";
		return ret;
	}
	
	public String getHTMLHeader(String title){
		String ret = 	"<html><head><title>"+title+"</title>" +
						getCSS() +
						"</head><body>\n" +
						"<h1>"+title+"</h1><hr>\n";
		return ret;
	}
	
	public String getHTMLFooter(){
		String ret = 	"<hr><a href='home'>" + Constants.APP_NAME + "</a> - " + Constants.APP_VERSION +
						"</body></html>\n";
		return ret;
	}
	
	public String getVerticalNormalMenu(){
		String ret = 	"<ul>\n" +
							"<li> <a href='ClientForm'>Cadastro de Pacientes</a>" +
							"<li> <a href='cadastroPacientes'>Busca de Pacientes</a>" +
							"<li> <a href='cadastroPacientes'>Cadastro de Consulta</a>" +
							"<li> <a href='cadastroPacientes'>Busca de Consulta</a>" +
						"</ul>\n";
		return ret;
	}
	public String getVerticalAdminMenu(){
		String ret = 	"<ul>\n" +
							"<li> <a href='cadastroPacientes'>Gestão de Usuários</a>" +
						"</ul>\n";
		return ret;
	}

	public String getUserMenu(HttpServletRequest request) {
		SessionManager sm = new SessionManager();
		Cookie[] cookies = request.getCookies();
		Cookie login = cookies[0];
		String ret = 	"[<b>Usuário: </b>"+login.getValue()+" - "+sm.getLastLogin(login.getValue())+"]\n";
		return ret;
	}
	
	
}
