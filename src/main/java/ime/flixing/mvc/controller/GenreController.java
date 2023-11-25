package ime.flixing.mvc.controller;

import ime.flixing.mvc.view.GenreView;

public class GenreController {

	private static GenreView genreView;
	
	public static final void initGenreController() {
		
		genreView = new GenreView();
		genreView.setVisible(true);
		
	}
	
	public static final void closeGenreView() {
		
		genreView.setVisible(false);
		genreView = null;
		
	}
}
