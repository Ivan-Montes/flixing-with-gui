package ime.flixing.mvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ime.flixing.dao.FlixPersonPositionDao;
import ime.flixing.dao.GenericDao;
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
import ime.flixing.mvc.view.flix_person_position.*;


public class FlixPersonPositionController {
	
	private FlixPersonPositionDao flixPersonPositionDaoImpl;
	private GenericDao<Flix> flixDao;	
	private GenericDao<Person> personDao;
	private GenericDao<Position> positionDao;
	
	public FlixPersonPositionController() {
		super();
		this.flixPersonPositionDaoImpl = new FlixPersonPositionDaoImpl();
		this.flixDao = new FlixDaoImpl();
		this.personDao = new PersonDaoImpl();
		this.positionDao = new PositionDaoImpl();
	}

	public FlixPersonPositionController(FlixPersonPositionDao flixPersonPositionDaoImpl, FlixDaoImpl flixDao,
			PersonDaoImpl personDao, PositionDaoImpl positionDao) {
		super();
		this.flixPersonPositionDaoImpl = flixPersonPositionDaoImpl;
		this.flixDao = flixDao;
		this.personDao = personDao;
		this.positionDao = positionDao;
	}

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
	
	public final void initFlixPersonPositionDetailsView(String strFlixCod, String strPersonCod, String strPositionCod) {
		
		FlixPersonPosition flixPersonPosition = flixPersonPositionDaoImpl.getFlixPersonPositionById(
				Long.parseLong(strFlixCod), 
				Long.parseLong(strPersonCod), 
				Long.parseLong(strPositionCod) ) ;
		
		FlixPersonPositionDetailsView view = new FlixPersonPositionDetailsView();
		view.loadData(flixPersonPosition != null? flixPersonPosition:new FlixPersonPosition());
		view.setVisible(true);
	}

	public final Optional<FlixPersonPosition> saveFlixPersonPosition(String strFlixCod, String strPersonCod, String strPositionCod){
		
		Optional<FlixPersonPosition>optFlixPersonPosition = Optional.empty();		
		List<Boolean>checkCodsList = Arrays.asList(Checker.checkDigits(strFlixCod),Checker.checkDigits(strPersonCod),Checker.checkDigits(strPositionCod));
		
		if ( checkCodsList.stream().allMatch( b -> b.equals(true) ) ) {
			
			Flix flixFound = flixDao.getById(Long.parseLong(strFlixCod));
			
			Person personFound = personDao.getById(Long.parseLong(strPersonCod));			

			Position positionFound = positionDao.getById(Long.parseLong(strPositionCod));
			
			if ( flixFound != null && personFound != null && positionFound != null ) {
				
				FlixPersonPosition flixPersonPositionFound = flixPersonPositionDaoImpl.getFlixPersonPositionById(Long.parseLong( strFlixCod ), 
																											Long.parseLong( strPersonCod ),																									
																											Long.parseLong( strPositionCod ) );
				
					if( flixPersonPositionFound == null ) {
					
					FlixPersonPosition flixPersonPositionSaved = flixPersonPositionDaoImpl.saveFlixPersonPosition(Long.parseLong(strFlixCod), 
							Long.parseLong(strPersonCod), 
							Long.parseLong(strPositionCod) );
					
					optFlixPersonPosition = Optional.ofNullable(flixPersonPositionSaved);
					}
			}			
			
			
		}
		
		return optFlixPersonPosition;
	}

	public final Optional<List<FlixPersonPosition>> getAllFlixPersonPosition() {
		
		return Optional.ofNullable(flixPersonPositionDaoImpl.getAllFlixPersonPosition());
		
	}
	
	public final int deleteFlixPersonPosition(String strFlixCod, String strPersonCod, String strPositionCod) {
		
		int returnValue;
		
		if ( Checker.checkDigits(strFlixCod) && Checker.checkDigits(strPersonCod) && Checker.checkDigits(strPositionCod)  ) {
			
			Flix flixFound = flixDao.getById(Long.parseLong(strFlixCod));
			
			Person personFound = personDao.getById(Long.parseLong(strPersonCod));			

			Position positionFound = positionDao.getById(Long.parseLong(strPositionCod));
			
			if ( flixFound != null && personFound != null && positionFound != null ) {
				
				FlixPersonPosition flixPersonPositionFound = flixPersonPositionDaoImpl.getFlixPersonPositionById(Long.parseLong(strFlixCod), 
																										Long.parseLong(strPersonCod), 
																										Long.parseLong(strPositionCod) );
				
				if ( flixPersonPositionFound != null) {	
					
					flixPersonPositionDaoImpl.deleteFlixPersonPosition(Long.parseLong(strFlixCod), 
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
