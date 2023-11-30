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
	
	public static final void initFlixController(){
		
		FlixView flixView = new FlixView();
		flixView.setVisible(true);
		
	}
	
	 public static final void initFlixGetAllView(){
			
		 FlixGetAllView flixGetAllView = new FlixGetAllView();
		 flixGetAllView.setVisible(true);
		
	 }

	 public static final void initFlixGetByIdView(){
			
		 FlixGetByIdView flixGetByIdView = new FlixGetByIdView();
		 flixGetByIdView.setVisible(true);
		
	 }
	 
	 public static final void initFlixSaveView(){
			
		 FlixSaveView flixSaveView = new FlixSaveView();
		 flixSaveView.setVisible(true);
		
	 }
	 

	 public static final void initFlixUpdateView(){
			
		 FlixUpdateView flixUpdateView = new FlixUpdateView();
		 flixUpdateView.setVisible(true);
		
	 }
	 

	 public static final void initFlixDeleteView(){
			
		 FlixDeleteView flixDeleteView = new FlixDeleteView();
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
		 
		 if ( Checker.checkFlixTitle( strFlixName ) 
					&& Checker.checkDigits( strGenreId) ) {

			 GenreDao genreDaoImpl = new GenreDaoImpl();
			 Optional<Genre> optGenre = Optional.ofNullable( genreDaoImpl.getGenreById( Long.parseLong(strGenreId) ) );
			 
			 if ( optGenre.isPresent() ) {
				 
				 FlixDao flixDao = new FlixDaoImpl();
				 Flix flix = new Flix();
				 flix.setTitle(strFlixName);
				 flix.setGenre( optGenre.get() );
				 result = Optional.ofNullable(flixDao.saveFlix(flix));
			 } 
		 }
		 
		 return result;
		 
	 }
	 
	 public static final Optional<Flix>updateFlix(String strFlixCod, String strFlixName, String strGenreId){
		 
		 Optional<Flix>result = Optional.empty();
		 
		 if ( Checker.checkDigits( strFlixCod ) 
					&& Checker.checkFlixTitle( strFlixName ) 
					&& Checker.checkDigits( strGenreId) ) {
			 
			 GenreDao genreDaoImpl = new GenreDaoImpl();
			 Optional<Genre> optGenre = Optional.ofNullable( genreDaoImpl.getGenreById( Long.parseLong(strGenreId) ) );
			 
			 if ( optGenre.isPresent() ) {
				 
				 FlixDao flixDao = new FlixDaoImpl();
				 Flix flix = new Flix();
				 flix.setTitle( strFlixName );
				 flix.setGenre( optGenre.get() );
				 result = Optional.ofNullable(flixDao.updateFlix(Long.parseLong(strFlixCod), flix));
			 }	 
		 }
		 
		 return result;
	 }
	 
	public static final int deleteFlix(String strFlixCod) {
		
		int returnValue = -1;
		
		if ( Checker.checkDigits( strFlixCod ) ) {
			
			FlixDao flixDao = new FlixDaoImpl();
			Optional<Flix> optFlixFound = Optional.ofNullable(flixDao.getFlixByIdEagger(Long.parseLong(strFlixCod)));
			
			if ( optFlixFound.isPresent() ) {
				
				if ( optFlixFound.get().getFlixPersonPosition().isEmpty() ) {
					
					flixDao.deleteFlix( Long.parseLong(strFlixCod) );
					returnValue =  0;
					
				}else {
					returnValue = -2;
				}
			}
		}
		
		return returnValue;
		
	}
	 
	 
}
