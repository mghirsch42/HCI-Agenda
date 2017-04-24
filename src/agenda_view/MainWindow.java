package agenda_view;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Agenda;

import model.Event;
import model.Note;

import model.MyCalendar;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class MainWindow extends Application{
	

	//This is the model, if needed pass into the function you need it for.
	private static BorderPane root;

	//public static MyCalendar calendar;
	
	protected static model.Agenda agenda = new model.Agenda();
	
	

	//the actions menu
	static Menu actions;
	//the month pane
	static MonthPane mp;
	//the week pane
	static WeekPane wp;
	//menu item to move to week view
	static MenuItem weekView = new MenuItem("Week View");
	//menu item to move to month view
	static MenuItem monthView = new MenuItem("Month View");;
	

	//sets the agenda used by the application-- should be called at beginning of program.
	public void setAgenda(model.Agenda agenda) {
		this.agenda = agenda;
	}

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
        actions = new Menu("Actions");
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
		
		// Add the Week Pane for testing.

		GregorianCalendar weekStart = new GregorianCalendar();
		weekStart.set(2017, 3, 16, 0, 00);
		GregorianCalendar weekEnd = new GregorianCalendar();
		weekEnd.set(2017, 3, 22, 23, 59);
		wp = new WeekPane(weekStart,
										weekEnd,
										agenda);
		root.setCenter(wp);

		
		//java.util Calendar (Used for constructing the monthview on the current date).
		java.util.Calendar c = java.util.Calendar.getInstance(TimeZone.getDefault());
		
		//null as a parameter will result in the same as the c above.

		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		start.set(2017, 4, 1, 00, 00);
		end.set(2017, 4, 31, 23, 59);
		mp = new MonthPane(start,
							end,
							agenda);

//		root.setCenter(mp);

        //Month View NOTE: This needed to be down here for it to work, maybe someone else has a better way of doing this
        //Week View, see above
        monthView.setOnAction( (e) -> {

            loadMonthView();
        });
        weekView.setOnAction( (e) -> {
        	loadWeekView();
        });
       addEvent.setOnAction( (e) -> {
    	   loadEventPane();

       });


        //Add items to actions
        actions.getItems().add(monthView);
        actions.getItems().add(weekView);

        //Add menus to bar
        menuBar.getMenus().add(actions);

		//Show the stage. 
		primaryStage.setTitle("HCI Group Project");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
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
		temp1.set(GregorianCalendar.DATE, temp1.get(GregorianCalendar.DATE)-1);
		temp2.set(GregorianCalendar.DATE, temp1.get(GregorianCalendar.DATE)-1);
		e.setStart(temp1);
		e.setEnd(temp2);

		Event e2 = new Event("test2", new GregorianCalendar(), new GregorianCalendar(), "description test2");
		
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
	
	protected static void loadWeekView(){
		root.setCenter(wp);
        monthView.setVisible(true);
        weekView.setVisible(false);
	}
	
	protected static void loadMonthView(){
		root.setCenter(mp);
        weekView.setVisible(true);
        monthView.setVisible(false);
	}
	
	protected static void loadEventPane(){
		weekView.setVisible(true);
		monthView.setVisible(true);
		root.setCenter(new BigEventPane(agenda));
	}

}
