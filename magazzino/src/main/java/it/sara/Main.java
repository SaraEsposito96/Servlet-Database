package it.sara;


import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import srv.ArticoloSrv;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Server web java
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        Context ctx = tomcat.addContext("/magazzino", new File(".").getAbsolutePath());
        
        ArticoloSrv dbServlet = new ArticoloSrv();
        
        Tomcat.addServlet(ctx, "magazzino", dbServlet);

        ctx.addServletMapping("/*", "magazzino");

        try {
			tomcat.start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        tomcat.getServer().await();
	}

}
