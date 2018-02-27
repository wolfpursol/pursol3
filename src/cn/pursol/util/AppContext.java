package cn.pursol.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppContext implements ServletContextListener {

    private static WebApplicationContext springContext;
    public static String realPath ;
    
    public AppContext() {
        super();
    }
    
    public void contextDestroyed(ServletContextEvent arg0) {
	

    }

    public void contextInitialized(ServletContextEvent arg0) {
	springContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
	realPath = arg0.getServletContext().getRealPath("/");	
    }
    
    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

}
