package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.GenericDao;
import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.GenreView;
import ime.flixing.mvc.view.genre.*;
import ime.flixing.tool.Checker;

public class GenreController {
	
	private GenericDao<Genre> dao;
	
	public GenreController() {
		super();
		this.dao = new GenreDaoImpl();
	}
	
	public GenreController(GenericDao<Genre> dao) {
		super();
		this.dao = dao;
	}

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
	
	public Optional<List<Genre>> getAllGenre(){
		
		return Optional.ofNullable( dao.getAll() );
		
	}

	public Optional<Genre> getGenreById(String strGenreCod){
		
		Optional<Genre>optGenre = Optional.empty();
		
		if ( Checker.checkDigits(strGenreCod) ) {
			
			optGenre = Optional.ofNullable( dao.getById(Long.parseLong(strGenreCod)) );			
			
		}
		
		return optGenre;
		
	}
	
	public Optional<Genre> saveGenre(String genreName, String genreDescription){
		
		Optional<Genre>optGenre;
		Genre genre = new Genre();
		
		if( Checker.checkName(genreName) && Checker.checkDescription(genreDescription) ) {			
			
			if ( dao.getByName(genreName).isEmpty() ) {
				
				genre.setName(genreName);
				genre.setDescription(genreDescription);
				optGenre = Optional.ofNullable( dao.save(genre) );
				
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
	
	public Optional<Genre> updateGenre(String strGenreCod, String genreName, String genreDescription){
		
		Optional<Genre>optGenre;
		Genre genre = new Genre();
		
		if( Checker.checkDigits(strGenreCod)  &&
				Checker.checkName(genreName) 
				&& Checker.checkDescription(genreDescription) ) {
				
			List<Genre>list = dao.getByName(genreName);
			
			if ( list.isEmpty() || list.stream()
									.filter( e -> e.getGenreId() != null )
									.anyMatch( g -> g.getGenreId().equals(Long.parseLong(strGenreCod) ) ) ) {
				
				genre.setName(genreName);
				genre.setDescription(genreDescription);
				optGenre = Optional.ofNullable( dao.update( Long.parseLong(strGenreCod), genre) );
				
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
	
	public final int deleteGenre(String strGenreCod) {

		int returnValue = -1;
		
		if ( Checker.checkDigits(strGenreCod) ) {			
			
			Optional<Genre> optGenre = Optional.ofNullable( dao.getByIdEagger(Long.parseLong(strGenreCod)) );
			
			if (optGenre.isPresent() ) {
				
				if ( optGenre.get().getFlixes().isEmpty() ) {
					
					dao.delete( Long.parseLong(strGenreCod) );
					returnValue = 0;
				
				}
				else {
					returnValue = -2;
				}				
			}		
		}
		
		return returnValue;
	}
		
}
