package model;
import java.util.Date;
import agenda_view.MainWindow;

class Driver {

	public static void main(String[] args) {
		
		//Creates a new agenda with an default constructor values
		Agenda a = new Agenda();
		
		//Test events.
		Event e = new Event("test", new Date(), new Date(), "description test");
		Event e2 = new Event("test2", new Date(), new Date(), "description test2");
		
		//Test notes.
		Note n = new Note("Test Title", "Test Message"); 
		Note n2 = new Note("Test Title2", "Test Message2");
		 
		//Add test events to the agenda's calendar
		a.getCalendar().addEvent(e);
		a.getCalendar().addEvent(e2);
		
		//Add test notes to the agenda's notepad
		a.getNotepad().addNote(n);
		a.getNotepad().addNote(n2);
		
		//Print out the agenda data
		System.out.print(a);
		
		//Opens the GUI
		MainWindow.startGUI(args);
		
		//Runs after the GUI closes
		
	}

}