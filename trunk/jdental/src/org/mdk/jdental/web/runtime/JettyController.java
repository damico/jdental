package org.mdk.jdental.web.runtime;


import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.servlet.ServletHandler;
import org.mdk.jdental.utils.Constants;


public class JettyController implements Runnable {

	private Server server = null;
	private Thread t = null;
	public static ServletHandler handler = new ServletHandler(); /* needs to be static to be used by ComplexPacketCommandManager.openRequest */
	
	public JettyController() throws Exception{
		int port = (Constants.WEB_SERVER_PORT);
		server = new Server();
		Connector connector = new SocketConnector();
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		handler.addServletWithMapping("org.mdk.jdental.web.servlets.Home",				"/home");
		handler.addServletWithMapping("org.mdk.jdental.web.servlets.RenderServlet",	"/RenderServlet");
		handler.addServletWithMapping("org.mdk.jdental.web.servlets.CreateAccount",	"/CreateAccount");
		handler.addServletWithMapping("org.mdk.jdental.web.servlets.ClientForm",	"/ClientForm");
		handler.addServletWithMapping("org.mdk.jdental.web.servlets.Teeth",	"/Teeth");

		
		
		HashSessionManager hsm = new HashSessionManager();
		hsm.setSessionPath("/tmp");
		
		server.setSessionIdManager(hsm.getIdManager());
		
		
		server.setHandler(handler);
		
		

		
		
		
	}
	
	

	public void init() {
		t = new Thread(this);
		t.start();
	}

	public int stopServer() {
		t = new Thread(this);
		t.interrupt();
		return 1;
	}

	public void run() {
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
