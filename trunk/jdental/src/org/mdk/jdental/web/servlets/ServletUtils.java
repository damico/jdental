package org.mdk.jdental.web.servlets;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.mdk.jdental.components.Controller;
import org.mdk.jdental.dataobjects.SelectList;
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
				"div.box { \n" +
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
				"width: 80px; \n" +
				"}\n" +
				".head { \n" +
				"font: 20px georgia, sans-serif;\n" +
				"margin-right: 20px; \n" +
				"display:inline-block; \n" +
				"width: 300px; \n" +
				"}\n" +
				".error { \n" +
				"margin-right: 20px; \n" +
				"display:inline-block; \n" +
				"width: 350px; \n" +
				"background: red; \n" +
				"}\n" +
				".success { \n" +
				"margin-right: 20px; \n" +
				"display:inline-block; \n" +
				"width: 350px; \n" +
				"background: green; \n" +
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
	
	
	public String getTooth(String index){
		String ret = "<table width='105' border='0' cellpadding='0' cellspacing='0'>" +
		"<tr valign='top'><td>"+index+"</td><td>" +
		"<form>" +
		"<table width='105' border='0' BACKGROUND='RenderServlet?file=cubic.png'>" +
		"<tr width='105'><td width='33'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'></td></tr>" +
		"<tr width='105'><td width='33'><input type='checkbox' name='a'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'><input type='checkbox' name='a'></td></tr>" +
		"<tr width='105'><td width='33'></td><td width='33'><input type='checkbox' name='a'></td><td width='33'></td></tr>" +
		"</table>" +
		"</form>" +
		"</td></tr>" +
		"</table>";
		
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
						"<script src=\"RenderServlet?file=prototype.js\" type=\"text/javascript\"></script> " +
						getCSS() +
						"</head><body>\n" +
						"<table width='100%'>" +
						"<tr><td width='80'><img src='RenderServlet?file=logo_jdental_72x72.png' alt='logo'></td>" +
						"<td><h1>"+title+"</h1></td></tr>" +
						"</table><hr>\n";
		return ret;
	}
	
	public String getHTMLFooter(){
		String ret = 	"<hr><a href='Home'>" + Constants.APP_NAME + "</a> - " + Constants.APP_VERSION +
						"</body></html>\n";
		return ret;
	}
	
	public String getVerticalNormalMenu(){
		String ret = 	"<ul>\n" +
							"<li> <a href='ClientForm'>Cadastro de Pacientes</a>" +
							"<li> <a href='SearchClient'>Busca de Pacientes</a>" +
							"<li> <a href='cadastroPacientes'>Cadastro de Consulta</a>" +
							"<li> <a href='cadastroPacientes'>Busca de Consulta</a>" +
							"<li> <a href='cadastroPacientes'>Agenda</a>" +
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

	public String sl2HtmlTable(SelectList sl) {
		
		ArrayList<String> labels = sl.getLabels();
		ArrayList<String[]> rows = sl.getRows();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table>\n");
		sb.append("<tr bgcolor='blue'>\n");
		for(int i=0; i<labels.size(); i++){
			sb.append("<td>"+labels.get(i)+"</td>\n");
		}
		sb.append("</tr>\n");
		
		for(int i=0; i<rows.size(); i++){
			sb.append("<tr>\n");
			for(int j=0; j<labels.size(); j++){
				sb.append("<td>"+rows.get(i)[j]+"</td>\n");
			}
			sb.append("</tr>\n");
		}
		
		sb.append("</table>\n");
		return sb.toString();
	}

	public String getMonthName(int month) {
		String sMonth = null;
		switch (month) {
        case 1:  sMonth = ("January"); break;
        case 2:  sMonth = ("February"); break;
        case 3:  sMonth = ("March"); break;
        case 4:  sMonth = ("April"); break;
        case 5:  sMonth = ("May"); break;
        case 6:  sMonth = ("June"); break;
        case 7:  sMonth = ("July"); break;
        case 8:  sMonth = ("August"); break;
        case 9:  sMonth = ("September"); break;
        case 10: sMonth = ("October"); break;
        case 11: sMonth = ("November"); break;
        case 12: sMonth = ("December"); break;
        
		}
		return sMonth;
	}

	public String getValidDayOfMonth(int month, int day, int year) {
		String ret = "&nbsp";
		Calendar cal = new GregorianCalendar(year, month-1, 1);
		int eday = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		if(day <= eday){
			ret = String.valueOf(day);
			if(ret.length()==1) ret = "0"+ret;
			ret = "<a href='ScheduleDay?m="+month+"&d="+day+"&y="+year+"'>"+ret+"</a>";
		}
		
		
		
		
		return ret;
	}

	public int getCurrentDate(String period) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentDate = 0;
		if(period.equalsIgnoreCase("year")){
			currentDate = calendar.get(Calendar.YEAR);
		}else if(period.equalsIgnoreCase("month")){
			currentDate = calendar.get(Calendar.MONTH);
		}else if(period.equalsIgnoreCase("day")){
			currentDate = calendar.get(Calendar.DAY_OF_MONTH);
		}
		return currentDate;
	}
	
	public String getDateTimeComboBox(String sd, String sm, String sy, String sh, String smin, String prefix){
		StringBuffer day = new StringBuffer();
		day.append("<select name='"+prefix+"_d'>\n");
		day.append("<option selected>"+sd+"</option>\n");
		for(int i=1; i<32; i++){
			day.append("<option>"+i+"</option>\n");
		}
		day.append("</select>\n");
		
		
		day.append("<select name='"+prefix+"_m'>\n");
		day.append("<option selected>"+sm+"</option>\n");
		for(int i=1; i<13; i++){
			day.append("<option>"+i+"</option>\n");
		}
		day.append("</select>\n");
		
		
		day.append("<select name='"+prefix+"_y'>\n");
		day.append("<option selected>"+sy+"</option>\n");
		for(int i=1; i<11; i++){
			day.append("<option>"+(i+getCurrentDate("year"))+"</option>\n");
		}
		day.append("</select>\n");
		
		
		day.append(" <select name='"+prefix+"_h'>\n");
		day.append("<option selected>"+sh+"</option>\n");
		for(int i=1; i<25; i++){
			day.append("<option>"+i+"</option>\n");
		}
		day.append("</select>\n");
		
		
		
		day.append(":<select name='"+prefix+"_min'>\n");
		day.append("<option selected>"+smin+"</option>\n");
		for(int i=1; i<61; i++){
			day.append("<option>"+i+"</option>\n");
		}
		day.append("</select>\n");
		
		return day.toString();
	}

	public String getClientComboBox() {
		Controller control = new Controller();
		Map<Integer,String> clientList = control.getClientIDs();
		
		Set<Integer> kSet = clientList.keySet();
		Iterator<Integer> iter = kSet.iterator();
		StringBuffer client = new StringBuffer();
		
		client.append("<select name='client'>\n");
		client.append("<option value='-2' selected>---</option>\n");
		client.append("<option value='-1'>Ninguém</option>\n");
		while(iter.hasNext()){
			Integer element = iter.next();
			client.append("<option value='"+element+"'>"+clientList.get(element)+"</option>\n");
		}
		client.append("</select>\n");
		
		
		return client.toString();
	}
	
	
}
