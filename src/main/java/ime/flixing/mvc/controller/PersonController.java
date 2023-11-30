package ime.flixing.mvc.controller;

import ime.flixing.mvc.view.PersonView;
import ime.flixing.mvc.view.person.*;


public class PersonController {

	public static final void initPersonController() {
		
		PersonView personView = new PersonView();
		personView.setVisible(true);
		
	}
	
	public static final void initPersonDeleteView() {
		
		PersonDeleteView view = new PersonDeleteView();
		view.setVisible(true);
		
	}
	
	public static final void initPersonGetAllView() {
		
		PersonGetAllView view = new PersonGetAllView();
		view.setVisible(true);
		
	}

	public static final void initPersonGetByIdView() {
		
		PersonGetByIdView view = new PersonGetByIdView();
		view.setVisible(true);
		
	}

	public static final void initPersonSaveView() {
		
		PersonSaveView view = new PersonSaveView();
		view.setVisible(true);
		
	}

	public static final void initPersonUpdateView() {
		
		PersonUpdateView view = new PersonUpdateView();
		view.setVisible(true);
		
	}


	

}
