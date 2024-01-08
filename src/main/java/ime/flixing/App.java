package ime.flixing;


import ime.flixing.exception.TheUncaughtExceptionHandler;
import ime.flixing.mvc.controller.MainController;
import ime.flixing.util.HibernateUtil;


public class App 
{
    public static void main( String[] args ){
    
    	App app = new App();   
    	app.init();    	 	
    	
    }
    
    void init() {
    	
    	initExceptionHandler();
    	initHibernate();
    	initGui();
    	
    }
    
     void initExceptionHandler() {
    	
    	Thread.setDefaultUncaughtExceptionHandler(new TheUncaughtExceptionHandler());
    }

     void initHibernate() {
    	
    	HibernateUtil.getSession();    	
    }

     void initGui() {    	
    	
    	new MainController().init();
    }
    
}
