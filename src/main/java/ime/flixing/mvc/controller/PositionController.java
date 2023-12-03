package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.PositionDao;
import ime.flixing.dao.impl.PositionDaoImpl;
import ime.flixing.entity.Position;
import ime.flixing.mvc.view.PositionView;
import ime.flixing.mvc.view.position.*;
import ime.flixing.tool.Checker;

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
		
		Optional<Position>optPosition  = Optional.empty();
		Position position = new Position();
		
		if ( Checker.checkName(strName)
				&& Checker.checkDescription(strDescription) ) {
			
			PositionDao positionDao = new PositionDaoImpl();
			Optional<List<Position>>optListPosition = Optional.ofNullable( positionDao.getPositionByNameId(strName) );
			
			if ( optListPosition.isPresent() && optListPosition.get().size() == 0 ) {				
				
				position.setName(strName);
				position.setDescription(strDescription);
				optPosition = Optional.ofNullable(positionDao.savePosition(position) );
				
			}else {
				position.setPositionId(-1L);
				optPosition = Optional.ofNullable(position);
			}
			
		}else {
			position.setPositionId(-2L);
			optPosition = Optional.ofNullable(position);
		}
		
		return optPosition;
	}

}
