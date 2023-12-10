package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.PositionDao;
import ime.flixing.dao.impl.PositionDaoImpl;
import ime.flixing.entity.Position;
import ime.flixing.mvc.view.PositionView;
import ime.flixing.mvc.view.position.*;
import ime.flixing.tool.Checker;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionController {

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
	
	public static final Optional<List<Position>>getAllPosition(){
		
		return Optional.ofNullable( new PositionDaoImpl().getAllPosition() );
		
	}
	
	public static final Optional<Position>getPositionById(String strPositionCod){
		
		Optional<Position>optPosition  = Optional.empty();
		
		if ( Checker.checkDigits(strPositionCod) ) {
			
			optPosition = Optional.ofNullable( new PositionDaoImpl().getPositionById(Long.parseLong(strPositionCod)));
		}
		
		return optPosition;
		
	}
	
	public static final Optional<Position> savePosition(String strName, String strDescription){
		
		Optional<Position>optPosition;
		Position position = new Position();
		
		if ( Checker.checkName(strName)
				&& Checker.checkDescription(strDescription) ) {
			
			PositionDao positionDao = new PositionDaoImpl();
			Optional<List<Position>>optListPosition = Optional.ofNullable( positionDao.getPositionByNameId(strName) );
			
			if ( optListPosition.isPresent() && optListPosition.get().isEmpty() ) {				
				
				position.setName(strName);
				position.setDescription(strDescription);
				optPosition = Optional.ofNullable(positionDao.savePosition(position) );
				
			}else {
				position.setPositionId(-2L);
				optPosition = Optional.ofNullable(position);
			}
			
		}else {
			position.setPositionId(-1L);
			optPosition = Optional.ofNullable(position);
		}
		
		return optPosition;
	}
	
	public static final Optional<Position> updatePosition(String strPositionCod, String strName, String strDescription){
		
		Optional<Position>optPosition;
		Optional<List<Position>>optListPosition;
		Optional<Position> optPositionFound;
		Position position = new Position();
		
		if ( Checker.checkDigits(strPositionCod) 
				&& Checker.checkName(strName)
				&& Checker.checkDescription(strDescription) ) {
			
			PositionDao positionDao = new PositionDaoImpl();
			optListPosition = Optional.ofNullable( positionDao.getPositionByNameId(strName) );
			
			if ( ( optListPosition.isPresent() && optListPosition.get().isEmpty() )
					||  ( optListPosition.isPresent() && optListPosition.get()
														.stream()
														.anyMatch( p -> p.getPositionId() == (Long.parseLong(strPositionCod) ) ) )
					) {
				
				optPositionFound = Optional.ofNullable( positionDao.getPositionById(Long.parseLong(strPositionCod)));
				
				if( optPositionFound.isPresent() ) {

					position.setName(strName);
					position.setDescription(strDescription);
					optPosition = Optional.ofNullable(positionDao.updatePosition(Long.parseLong(strPositionCod), position));
					
				}else {
					position.setPositionId(-3L);
					optPosition = Optional.ofNullable(position);
				}
				
				
			}else {
				position.setPositionId(-2L);
				optPosition = Optional.ofNullable(position);
			}
			
			
		}else {
			position.setPositionId(-1L);
			optPosition = Optional.ofNullable(position);
		}
		
		return optPosition;
	}
	
	
	public static final int deletePerson(String strPositionCod) {
		
		int returnValue;
		Optional<Position> optPositionFound;
		
		if ( Checker.checkDigits(strPositionCod) ) {
			
			PositionDao positionDao = new PositionDaoImpl();
			optPositionFound = Optional.ofNullable( positionDao.getPositionByIdEagger(Long.parseLong(strPositionCod)));
			
			if( optPositionFound.isPresent() ) {
			
				if ( optPositionFound.get().getFlixPersonPosition().isEmpty() ) {
					
					positionDao.deletePosition( Long.parseLong(strPositionCod) );
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
