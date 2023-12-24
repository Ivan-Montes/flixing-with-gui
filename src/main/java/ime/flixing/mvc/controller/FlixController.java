package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.impl.FlixDaoImpl;
import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Flix;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.FlixView;
import ime.flixing.mvc.view.flix.*;
import ime.flixing.tool.*;


public class FlixController {
	
	private FlixDaoImpl flixDaoImpl;
	private GenreDaoImpl genreDaoImpl;
	
	public FlixController() {
		super();
		this.flixDaoImpl = new FlixDaoImpl();
		this.genreDaoImpl = new GenreDaoImpl();
	}

	public FlixController(FlixDaoImpl flixDaoImpl, GenreDaoImpl genreDaoImpl) {
		super();
		this.flixDaoImpl = flixDaoImpl;
		this.genreDaoImpl = genreDaoImpl;
	}

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
	 
	 public final Optional<List<Flix>> getAllFlix() {
		
		return Optional.ofNullable(flixDaoImpl.getAll());			
		
	 }

	 public final Optional<Flix> getFlixById(String strFlixCod) {
		 
		 if ( Checker.checkDigits(strFlixCod) ) {
			
			return Optional.ofNullable( flixDaoImpl.getById( Long.parseLong(strFlixCod) ) ); 
		 }		
		 
		 return Optional.ofNullable(null);
	 }

	 public final Optional<Flix>saveFlix(String strFlixName, String strGenreId){
		 
		 Optional<Flix>result = Optional.empty();
		 
		 if ( Checker.checkFlixTitle( strFlixName ) 
					&& Checker.checkDigits( strGenreId) ) {

			 Optional<Genre> optGenre = Optional.ofNullable( genreDaoImpl.getById( Long.parseLong(strGenreId) ) );
			 
			 if ( optGenre.isPresent() ) {
				 
				 Flix flix = new Flix();
				 flix.setTitle(strFlixName);
				 flix.setGenre( optGenre.get() );
				 result = Optional.ofNullable(flixDaoImpl.save(flix));
			 } 
		 }
		 
		 return result;
		 
	 }
	 
	 public final Optional<Flix>updateFlix(String strFlixCod, String strFlixName, String strGenreId){
		 
		 Optional<Flix>result = Optional.empty();
		 
		 if ( Checker.checkDigits( strFlixCod ) 
					&& Checker.checkFlixTitle( strFlixName ) 
					&& Checker.checkDigits( strGenreId) ) {
			 
			 
			 Optional<Genre> optGenre = Optional.ofNullable( genreDaoImpl.getById( Long.parseLong(strGenreId) ) );
			 
			 if ( optGenre.isPresent() ) {
				 
				 Flix flix = new Flix();
				 flix.setTitle( strFlixName );
				 flix.setGenre( optGenre.get() );
				 result = Optional.ofNullable(flixDaoImpl.update(Long.parseLong(strFlixCod), flix));
			 }	 
		 }
		 
		 return result;
	 }
	 
	public final int deleteFlix(String strFlixCod) {
		
		int returnValue = -1;
		
		if ( Checker.checkDigits( strFlixCod ) ) {
			
			Optional<Flix> optFlixFound = Optional.ofNullable(flixDaoImpl.getByIdEagger(Long.parseLong(strFlixCod)));
			
			if ( optFlixFound.isPresent() ) {
				
				if ( optFlixFound.get().getFlixPersonPosition().isEmpty() ) {
					
					flixDaoImpl.delete( Long.parseLong(strFlixCod) );
					returnValue =  0;
					
				}else {
					returnValue = -2;
				}
			}
		}
		
		return returnValue;
		
	}
	 
	 
}
