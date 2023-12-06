package ime.flixing.mvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ime.flixing.dao.FlixDao;
import ime.flixing.dao.FlixPersonPositionDao;
import ime.flixing.dao.PersonDao;
import ime.flixing.dao.PositionDao;
import ime.flixing.dao.impl.FlixDaoImpl;
import ime.flixing.dao.impl.FlixPersonPositionDaoImpl;
import ime.flixing.dao.impl.PersonDaoImpl;
import ime.flixing.dao.impl.PositionDaoImpl;
import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.tool.Checker;
import ime.flixing.mvc.view.FlixPersonPositionView;
import ime.flixing.mvc.view.flix_person_position.FlixPersonPositionEditView;
import ime.flixing.mvc.view.flix_person_position.FlixPersonPositionSaveView;


public class FlixPersonPositionController {
	
	public static final void initFlixPersonPositionController() {
		
		FlixPersonPositionView view = new FlixPersonPositionView();
		view.setVisible(true);
	}
	
	public static final void initFlixPersonPositionEditView() {

		FlixPersonPositionEditView view = new FlixPersonPositionEditView();
		view.setVisible(true);
	}
	

	public static final void initFlixPersonPositionSaveView() {
		
		FlixPersonPositionSaveView view = new FlixPersonPositionSaveView();
		view.setVisible(true);
	}

	public static final Optional<FlixPersonPosition> saveFlixPersonPosition(String strFlixCod, String strPersonCod, String strPositionCod){
		
		Optional<FlixPersonPosition>optFlixPersonPosition = Optional.empty();		
		List<Boolean>checkCodsList = Arrays.asList(Checker.checkDigits(strFlixCod),Checker.checkDigits(strPersonCod),Checker.checkDigits(strPositionCod));
		
		if ( checkCodsList.stream().allMatch( b -> b.equals(true) ) ) {
			
			FlixDao flixDao = new FlixDaoImpl();
			Flix flixFound = flixDao.getFlixById(Long.parseLong(strFlixCod));
			
			PersonDao personDao = new PersonDaoImpl();
			Person personFound = personDao.getPersonById(Long.parseLong(strPersonCod));			

			PositionDao positionDao = new PositionDaoImpl();
			Position positionFound = positionDao.getPositionById(Long.parseLong(strPositionCod));
			
			if ( flixFound != null && personFound != null && positionFound != null ) {
				
				FlixPersonPositionDao flixPersonPositionDao = new FlixPersonPositionDaoImpl();
				FlixPersonPosition flixPersonPositionFound = flixPersonPositionDao.getFlixPersonPositionById(Long.parseLong( strFlixCod ), 
																											Long.parseLong( strPersonCod ),																									
																											Long.parseLong( strPositionCod ) );
				
					if( flixPersonPositionFound == null ) {
					
					FlixPersonPosition flixPersonPositionSaved = flixPersonPositionDao.saveFlixPersonPosition(Long.parseLong(strFlixCod), 
							Long.parseLong(strPersonCod), 
							Long.parseLong(strPositionCod) );
					
					optFlixPersonPosition = Optional.ofNullable(flixPersonPositionSaved);
					}
			}			
			
			
		}
		
		return optFlixPersonPosition;
	}

	public static final Optional<List<FlixPersonPosition>> getAllFlixPersonPosition() {
		
		return Optional.ofNullable(new FlixPersonPositionDaoImpl().getAllFlixPersonPosition());
		
	}
	
	public static final int deleteFlixPersonPosition(String strFlixCod, String strPersonCod, String strPositionCod) {
		
		int returnValue = -5;
		
		if ( Checker.checkDigits(strFlixCod) && Checker.checkDigits(strPersonCod) && Checker.checkDigits(strPositionCod)  ) {
			
			FlixDao flixDao = new FlixDaoImpl();
			Flix flixFound = flixDao.getFlixById(Long.parseLong(strFlixCod));
			
			PersonDao personDao = new PersonDaoImpl();
			Person personFound = personDao.getPersonById(Long.parseLong(strPersonCod));			

			PositionDao positionDao = new PositionDaoImpl();
			Position positionFound = positionDao.getPositionById(Long.parseLong(strPositionCod));
			
			if ( flixFound != null && personFound != null && positionFound != null ) {
				
				FlixPersonPositionDao flixPersonPositionDao = new FlixPersonPositionDaoImpl();
				FlixPersonPosition flixPersonPositionFound = flixPersonPositionDao.getFlixPersonPositionById(Long.parseLong(strFlixCod), 
																										Long.parseLong(strPersonCod), 
																										Long.parseLong(strPositionCod) );
				
				if ( flixPersonPositionFound != null) {	
					
					flixPersonPositionDao.deleteFlixPersonPosition(Long.parseLong(strFlixCod), 
							Long.parseLong(strPersonCod), 
							Long.parseLong(strPositionCod) );
					returnValue = 0;
					
				}else {
					returnValue = -3;
					
				}
				
			}else {
				
					returnValue = -6;
					
				}		
			
		}else {
			returnValue = -1;
			
		}
		
		return returnValue;		
	}
}
