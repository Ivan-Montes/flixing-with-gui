package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.GenreDao;
import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.GenreView;
import ime.flixing.mvc.view.genre.*;
import ime.flixing.tool.Checker;

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
	
	public static Optional<Genre> getGenreById(String strGenreCod){
		
		Optional<Genre>optGenre = Optional.empty();
		
		if ( Checker.checkDigits(strGenreCod) ) {
			
			GenreDao genreDao = new GenreDaoImpl();
			optGenre = Optional.ofNullable( genreDao.getGenreById(Long.parseLong(strGenreCod)) );			
			
		}
		
		return optGenre;
		
	}
	
	public static Optional<List<Genre>> getAllGenre(){
		
		return Optional.ofNullable(new GenreDaoImpl().getAllGenre() );
		
	}
	
	public static Optional<Genre> saveGenre(String genreName, String genreDescription){
		
		Optional<Genre>optGenre = Optional.empty();
		Genre genre = new Genre();
		
		if( Checker.checkName(genreName) && Checker.checkDescription(genreDescription) ) {			
			
			if ( new GenreDaoImpl().getGenreByName(genreName).isEmpty() ) {
				
				genre.setName(genreName);
				genre.setDescription(genreDescription);
				optGenre = Optional.ofNullable( new GenreDaoImpl().saveGenre(genre) );
				
			}else {
				genre.setGenreId(-1L);
				optGenre = Optional.ofNullable(genre);
			}
			
		}else{
			genre.setGenreId(-2L);
			optGenre = Optional.ofNullable(genre);
		}
		
		return optGenre;
	}
	
	public static Optional<Genre> updateGenre(String strGenreCod, String genreName, String genreDescription){
		
		Optional<Genre>optGenre = Optional.empty();
		Genre genre = new Genre();
		
		if( Checker.checkDigits(strGenreCod)  &&
				Checker.checkName(genreName) 
				&& Checker.checkDescription(genreDescription) ) {
			
			GenreDao genreDao = new GenreDaoImpl();			
			List<Genre>list = genreDao.getGenreByName(genreName);
			
			if ( list.isEmpty() || list.stream()
									.filter( g -> g.getGenreId().equals(Long.parseLong(strGenreCod) ) )
									.findFirst()
									.isPresent() ) {
				
				genre.setName(genreName);
				genre.setDescription(genreDescription);
				optGenre = Optional.ofNullable( new GenreDaoImpl().updateGenre( Long.parseLong(strGenreCod), genre) );
				
			}else {
				
				genre.setGenreId(-1L);
				optGenre = Optional.ofNullable(genre);
				
			}
			
		}else{
			
			genre.setGenreId(-2L);
			optGenre = Optional.ofNullable(genre);
			
		}
		
		return optGenre;
	}
}
