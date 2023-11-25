package ime.flixing;


import ime.flixing.exception.TheUncaughtExceptionHandler;
import ime.flixing.mvc.controller.MainController;
import ime.flixing.util.HibernateUtil;


public class App 
{
    public static void main( String[] args )
    {
    
    	Thread.setDefaultUncaughtExceptionHandler(new TheUncaughtExceptionHandler());

    	HibernateUtil.getSession();
    	
    	new MainController().init();
    	
    }
}
