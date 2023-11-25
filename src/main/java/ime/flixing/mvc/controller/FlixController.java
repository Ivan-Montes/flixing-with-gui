package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.FlixDao;
import ime.flixing.dao.GenreDao;
import ime.flixing.dao.impl.FlixDaoImpl;
import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Flix;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.FlixView;
import ime.flixing.mvc.view.flix.*;
import ime.flixing.tool.*;

public class FlixController {
	
	private static FlixView flixView;
	private static FlixGetAllView flixGetAllView;
	private static FlixGetByIdView flixGetByIdView;
	private static FlixSaveView flixSaveView;
	private static FlixUpdateView flixUpdateView;
	private static FlixDeleteView flixDeleteView;
	
	public static final void initFlixController(){
		
		flixView = new FlixView();
		flixView.setVisible(true);
		
	}
	
	 public static final void closeFlixView(){
		 
		 flixView.setVisible(false);
		 flixView = null;
		 
	 }

	 public static final void initFlixGetAllView(){
			
		 flixGetAllView = new FlixGetAllView();
		 flixGetAllView.setVisible(true);
		
	 }

	 public static final void initFlixGetByIdView(){
			
		 flixGetByIdView = new FlixGetByIdView();
		 flixGetByIdView.setVisible(true);
		
	 }
	 
	 public static final void initFlixSaveView(){
			
		 flixSaveView = new FlixSaveView();
		 flixSaveView.setVisible(true);
		
	 }
	 

	 public static final void initFlixUpdateView(){
			
		 flixUpdateView = new FlixUpdateView();
		 flixUpdateView.setVisible(true);
		
	 }
	 

	 public static final void initFlixDeleteView(){
			
		 flixDeleteView = new FlixDeleteView();
		 flixDeleteView.setVisible(true);
		
	 }
	 
	 public static final Optional<Flix> searchFlixCod(String cod) {
		 
		 if ( Checker.checkDigits(cod) ) {
			 
			FlixDao flixDao = new FlixDaoImpl();
			return Optional.ofNullable( flixDao.getFlixById( Long.parseLong(cod) ) ); 
		 }		
		 
		 return Optional.ofNullable(null);
	 }

	 public static final Optional<List<Flix>> searchAll() {
		 
		FlixDao flixDao = new FlixDaoImpl();
		return Optional.ofNullable(flixDao.getAllFlix());	
		
		
	 }
	 
	 public static final Optional<Flix>saveFlix(String strFlixName, String strGenreId){
		 
		 Optional<Flix>result = Optional.empty();
		 
		 GenreDao genreDaoImpl = new GenreDaoImpl();
		 Optional<Genre> optGenre = Optional.ofNullable( genreDaoImpl.getGenreById( Long.parseLong(strGenreId) ) );
		 
		 if ( optGenre.isPresent() ) {
			 
			 FlixDao flixDao = new FlixDaoImpl();
			 Flix flix = new Flix();
			 flix.setTitle(strFlixName);
			 flix.setGenre( optGenre.get() );
			 result = Optional.ofNullable(flixDao.saveFlix(flix));
		 }		 
		  
		 return result;
	 }
	 
}
