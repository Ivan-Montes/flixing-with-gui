package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.impl.PositionDaoImpl;
import ime.flixing.entity.Position;
import ime.flixing.mvc.view.PositionView;
import ime.flixing.mvc.view.position.*;
import ime.flixing.tool.Checker;



public class PositionController {	

	private PositionDaoImpl positionDaoImpl;
	
	
	public PositionController() {
		
		this.positionDaoImpl = new PositionDaoImpl();
		
	}
	
	public PositionController(PositionDaoImpl positionDaoImpl) {
		
		this.positionDaoImpl = positionDaoImpl;
		
	}	
	
	public static final void initPositionController() {

		PositionView view = new PositionView();
		view.setVisible(true);
		
	}

	public static final void initPositionGetAllView() {

		PositionGetAllView view = new PositionGetAllView();
		view.setVisible(true);
		
	}

	public static final void initPositionGetByIdView() {

		PositionGetByIdView view = new PositionGetByIdView();
		view.setVisible(true);
		
	}

	public static final void initPositionSaveView() {

		PositionSaveView view = new PositionSaveView();
		view.setVisible(true);
		
	}

	public static final void initPositionUpdateView() {

		PositionUpdateView view = new PositionUpdateView();
		view.setVisible(true);
		
	}

	public static final void initPositionDeleteView() {

		PositionDeleteView view = new PositionDeleteView();
		view.setVisible(true);
		
	}
	
	public final Optional<List<Position>>getAllPosition(){
				
		return Optional.ofNullable( positionDaoImpl.getAll() );
		
	}
	
	public final Optional<Position>getPositionById(String strPositionCod){
		
		Optional<Position>optPosition  = Optional.empty();
		
		if ( Checker.checkDigits(strPositionCod) ) {
			
			optPosition = Optional.ofNullable( positionDaoImpl.getById(Long.parseLong(strPositionCod)));
		}
		
		return optPosition;
		
	}
	
	public final Optional<Position> savePosition(String strName, String strDescription){
		
		Position position = new Position();
		
		if ( Checker.checkName(strName)
				&& Checker.checkDescription(strDescription) ) {
			
			List<Position>listPosition = positionDaoImpl.getByName(strName) ;
			
			if ( listPosition != null && listPosition.isEmpty() ) {				
				
				position.setName(strName);
				position.setDescription(strDescription);
				position = positionDaoImpl.save(position);
				
			}else {
				position.setPositionId(-2L);
			}
			
		}else {
			position.setPositionId(-1L);
		}
		
		return Optional.ofNullable(position);
	}
	
	public final Optional<Position> updatePosition(String strPositionCod, String strName, String strDescription){
		
		Optional<Position> optPositionFound;
		Position position = new Position();
		
		if ( Checker.checkDigits(strPositionCod) 
				&& Checker.checkName(strName)
				&& Checker.checkDescription(strDescription) ) {			
			
			List<Position>listPosition = positionDaoImpl.getByName(strName) ;

			if ( ( listPosition != null && listPosition.isEmpty() )
					||  ( listPosition != null && listPosition
														.stream()
														.anyMatch( p -> p.getPositionId() == (Long.parseLong(strPositionCod) ) ) )
					) {
				
				optPositionFound = Optional.ofNullable( positionDaoImpl.getById(Long.parseLong(strPositionCod)));
				
				if( optPositionFound.isPresent() ) {

					position.setName(strName);
					position.setDescription(strDescription);
					position = positionDaoImpl.update(Long.parseLong(strPositionCod), position);
					
				}else {
					position.setPositionId(-3L);
				}
				
				
			}else {
				position.setPositionId(-2L);
			}			
			
		}else {
			position.setPositionId(-1L);
		}
		
		return Optional.ofNullable(position);
	}
	
	
	public final int deletePerson(String strPositionCod) {
		
		int returnValue;
		Optional<Position> optPositionFound;
		
		if ( Checker.checkDigits(strPositionCod) ) {
			
			optPositionFound = Optional.ofNullable( positionDaoImpl.getByIdEagger(Long.parseLong(strPositionCod)));
			
			if( optPositionFound.isPresent() ) {
			
				if ( optPositionFound.get().getFlixPersonPosition().isEmpty() ) {
					
					positionDaoImpl.delete( Long.parseLong(strPositionCod) );
					returnValue =  0;
			
				}
				else {
					returnValue = -4;
				}	
				
			}else {
				returnValue = -3;
				
			}
			
		}else {
			returnValue = -1;
			
		}
		
		return returnValue;
	}

}
