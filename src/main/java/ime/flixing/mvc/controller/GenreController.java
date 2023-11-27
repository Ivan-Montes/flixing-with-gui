package ime.flixing.mvc.controller;

import ime.flixing.mvc.view.GenreView;
import ime.flixing.mvc.view.genre.*;

public class GenreController {
	
	public static final void initGenreController() {
		
		GenreView genreView = new GenreView();
		genreView.setVisible(true);
		
	}
	
	public static final void initGenreDeleteView() {
		
		GenreDeleteView genreDeleteView = new GenreDeleteView();
		genreDeleteView.setVisible(true);
		
	}
	
	public static final void initGenreGetAllView() {
		
		GenreGetAllView genreGetAllView = new GenreGetAllView();
		genreGetAllView.setVisible(true);
		
	}

	public static final void initGenreGetByIdView() {
		
		GenreGetByIdView genreGetByIdView = new GenreGetByIdView();
		genreGetByIdView.setVisible(true);
		
	}

	public static final void initGenreSaveView() {
		
		GenreSaveView genreSaveView = new GenreSaveView();
		genreSaveView.setVisible(true);
		
	}

	public static final void initGenreUpdateView() {
		
		GenreUpdateView genreUpdateView = new GenreUpdateView();
		genreUpdateView.setVisible(true);
		
	}
	
	
}
