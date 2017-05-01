package agenda_view;

import java.util.ArrayList;
import java.util.GregorianCalendar;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Agenda;
import model.Event;
import model.Note;


public class MainWindow extends Application{
	
	//This is the model, if needed pass into the function you need it for.
	private Agenda agenda;
	private static BorderPane root;
	private static ScrollPane scroll;
	
	@Override
	public void start(Stage primaryStage) {
		agenda = initTestAgenda();
				
		//Border Layout Root
		root = new BorderPane();
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
		//Menu Bar
		MenuBar menuBar = new MenuBar();

		//Actions
        Menu actions = new Menu("Actions");
        MenuItem addEvent = new MenuItem("Add Event");
        actions.getItems().add(addEvent);

		//File
		Menu file = new Menu("File");
		MenuItem quit = new MenuItem("Quit"); 
		quit.setOnAction( (e) -> {
			primaryStage.close();
		});
		
		//Add items to file
		file.getItems().add(quit);
		
		//Add menus to bar
		menuBar.getMenus().add(file);
		
		
		//Add Elements to the root.
		root.setTop(menuBar);
				
		///////////////////////////////
		// TODO:
		// ADD START AND END DATES TO WEEK PANE ON CREATION
		// currently sets it up 2nd week of april 2017
		///////////////////////////////
		
		GregorianCalendar today = new GregorianCalendar();
		int currentDayInWeek = today.get(GregorianCalendar.DAY_OF_WEEK);
		int currentDayInMonth = today.get(GregorianCalendar.DAY_OF_MONTH);
		
		//BigEvent Pane
		BigEventPane bigEventPane = new BigEventPane(agenda);

		
		// Add the Week Pane for testing.
		GregorianCalendar weekStart = subtractDays((GregorianCalendar)today.clone(), currentDayInWeek - 1);
		weekStart.set(GregorianCalendar.HOUR_OF_DAY, 0);
		weekStart.set(GregorianCalendar.MINUTE, 0);
		GregorianCalendar weekEnd = addDays((GregorianCalendar)today.clone(), 7 - currentDayInWeek);
		weekStart.set(GregorianCalendar.HOUR_OF_DAY, 0);
		weekStart.set(GregorianCalendar.MINUTE, 0);
		weekStart.set(GregorianCalendar.SECOND, 0);
		WeekPane weekPane = new WeekPane(weekStart,
										weekEnd,
										agenda,
										bigEventPane);
		weekPane.prefWidthProperty().bind(scene.widthProperty());
		weekPane.prefHeightProperty().bind(scene.heightProperty());
		scroll = new ScrollPane(weekPane);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		root.setCenter(scroll);
		
		//null as a parameter will result in the same as the c above.
		GregorianCalendar monthStart = subtractDays((GregorianCalendar)today.clone(), currentDayInMonth - 1);
		monthStart.set(GregorianCalendar.HOUR_OF_DAY, 0);
		monthStart.set(GregorianCalendar.MINUTE, 0);
		GregorianCalendar monthEnd = addDays((GregorianCalendar)today.clone(), today.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - currentDayInMonth);
		monthEnd.set(GregorianCalendar.HOUR_OF_DAY, 23);
		monthEnd.set(GregorianCalendar.MINUTE, 59);
		monthEnd.set(GregorianCalendar.SECOND, 59);
		MonthPane monthPane = new MonthPane(monthStart,
											monthEnd,
											agenda, bigEventPane);

		monthPane.prefWidthProperty().bind(scene.widthProperty());
		monthPane.prefHeightProperty().bind(scene.heightProperty());
		
		//Note Pane
		NotePane notePane = new NotePane(agenda);
		
		
        //Month View NOTE: This needed to be down here for it to work, maybe someone else has a better way of doing this
        //Week View, see above
        MenuItem monthView = new MenuItem("Month View");
        MenuItem weekView = new MenuItem("Week View");
        MenuItem noteView = new MenuItem("Note View");
        monthView.setOnAction( (e) -> {
        	monthPane.updateEvents();
            root.setCenter(monthPane);
            //actions.getItems().add(weekView);
            //actions.getItems().removeAll(monthView);
        });
        weekView.setOnAction( (e) -> {
            root.setCenter(weekPane);
            weekPane.prefWidthProperty().bind(scene.widthProperty());
    		weekPane.prefHeightProperty().bind(scene.heightProperty());
    		scroll = new ScrollPane(weekPane);
    		scroll.setFitToHeight(true);
    		scroll.setFitToWidth(true);
    		root.setCenter(scroll);
            //actions.getItems().add(monthView);
            //actions.getItems().removeAll(weekView);
            weekPane.removeEvents();
            weekPane.addEvents();
        });
       addEvent.setOnAction( (e) -> {
    	   //An agenda is passed in here so that the AddEventPand has a model to edit.
    	   root.setCenter(bigEventPane);
    	   
       });
       noteView.setOnAction( (e) ->{
    	   root.setCenter(notePane);
       });


        //Add items to actions
        actions.getItems().add(monthView);
        actions.getItems().add(weekView);
        actions.getItems().add(noteView);

        //Add menus to bar
        menuBar.getMenus().add(actions);

		//Show the stage. 
		primaryStage.setTitle("HCI Group Project");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private GregorianCalendar addDays(GregorianCalendar day, int numberOfDays) {
		int daysInMonth = day.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		int date = day.get(GregorianCalendar.DATE);
		if(daysInMonth - date >= numberOfDays){ 												//If there isn't a new month
			day.set(day.get(GregorianCalendar.YEAR), day.get(GregorianCalendar.MONTH), date + numberOfDays);
		}else{																		//If there is a new month, calculate the new values for the dates. 
			int daysIn = -(daysInMonth-(date+numberOfDays));
			int month = day.get(GregorianCalendar.MONTH);
			if( month < 11){ 														//If there isn't a new year.
				day.set(day.get(GregorianCalendar.YEAR), month + 1, daysIn);
			}else{
				day.set(day.get(GregorianCalendar.YEAR)+1, 0, daysIn); // December has 31 days, is the 12th (11) month. //No checking for min year.
			}
		}
		
		return day;
	}

	private GregorianCalendar subtractDays(GregorianCalendar day, int numberOfDays) {
		int date = day.get(GregorianCalendar.DATE);
		if(date > numberOfDays){ 																//If there isn't a new month
			day.set(day.get(GregorianCalendar.YEAR), day.get(GregorianCalendar.MONTH), date - numberOfDays);
		}else{																		//If there is a new month, calculate the new values for the dates. 
			int daysBack = numberOfDays - date;
			int month = day.get(GregorianCalendar.MONTH);
			if( month > 0){ 														//If there isn't a new year.
				GregorianCalendar temp = new GregorianCalendar();					//Used to calculate how many dats will be in the new month.
				temp.set(day.get(GregorianCalendar.YEAR), month - 1, 1);			
				int numberOfDaysInNewMonth = temp.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
				day.set(day.get(GregorianCalendar.YEAR), month - 1, numberOfDaysInNewMonth - daysBack);
			}else{
				day.set(day.get(GregorianCalendar.YEAR)-1, 11, 31 - daysBack); // December has 31 days, is the 12th (11) month. //No checking for min year.
			}
		}
		
		return day;
	}
	
	public static BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	/**
	 * This is used for testing
	 * @return An agenda with some elements.
	 */
	private Agenda initTestAgenda() {
		//Creates a new agenda with an default constructor values
		Agenda a = new Agenda();
		
		//Test events.
		Event e = new Event("test", new GregorianCalendar(), new GregorianCalendar(), "description test");
		GregorianCalendar temp1 = e.getStart();
		GregorianCalendar temp2 = e.getEnd();
		e.setStart(temp1);
		e.setEnd(temp2);
		Event e2 = new Event("test2", new GregorianCalendar(), new GregorianCalendar(), "description test2");

		//Test Array for scroll purposes
		ArrayList<Event> eventList = new ArrayList<Event>();
		for(int i = 0; i < 3; i++)
		{
			Event tempEvent = new Event("testList " + i, new GregorianCalendar(), new GregorianCalendar(), "description testList");
			eventList.add(tempEvent);
		}
		
		//Test notes.
		Note n = new Note("Test Title", "Test Message"); 
		Note n2 = new Note("Test Title2", "Test Message2");
		
		//Add test events to the agenda's calendar
		//a.getCalendar().addEvent(e);
		//a.getCalendar().addEvent(e2);
		for(int i = 0; i < eventList.size(); i++)
		{
		//	a.getCalendar().addEvent(eventList.get(i));
		}
		
		//Add test notes to the agenda's notepad
		a.getNotepad().addNote(n);
		a.getNotepad().addNote(n2);
		
		//Print out the agenda data
		System.out.println("Default Generated Agenda Is Being Used!");
		System.out.println(a);
		
		return a;
	}

	private void loadAgenda(){
		
	}
	
	private void saveAgenda(){
		
	}

	public void startGUI(String[] args) {
		launch(args);
	}

}
