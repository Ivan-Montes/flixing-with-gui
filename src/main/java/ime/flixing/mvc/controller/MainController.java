package ime.flixing.mvc.controller;

import ime.flixing.mvc.view.MainView;

public class MainController {

	public void init() {
		
		
		MainView viewJFrametype = new MainView();		
		viewJFrametype.setVisible(true);
		
		
	}
	
	public static final void callFlixController() {
		FlixController.initFlixController();
	}
	
	public static final void callGenreController() {
		GenreController.initGenreController();
	}
}
