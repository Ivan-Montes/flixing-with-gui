package ime.flixing.mvc.controller;

import ime.flixing.mvc.view.MainView;

public class MainController {

	public void init() {		
		
		MainView view = new MainView();		
		view.setVisible(true);		
		
	}
	
	public static final void callFlixController() {
		FlixController.initFlixController();
	}
	
	public static final void callGenreController() {
		GenreController.initGenreController();
	}
	
	public static final void callPersonController() {
		PersonController.initPersonController();
	}
	
	public static final void callPositionController() {
		PositionController.initPositionController();
	}
	
	public static final void callFlixPersonPositionController() {
		FlixPersonPositionController.initFlixPersonPositionController();
	}
	
}
