package agenda_view;

import java.util.Date;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Agenda;
import model.Event;
import model.Note;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class MainWindow extends Application{
	
	//This is the model, if needed pass into the function you need it for.
	private Agenda agenda;
	
	@Override
	public void start(Stage primaryStage) {
		agenda = initTestAgenda();
				
		//Border Layout Root
		BorderPane root = new BorderPane();
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
				
		// Add the Week Pane for testing.
		WeekPane wp = new WeekPane();
		root.setCenter(wp);
		
		//java.util Calendar (Used for constructing the monthview on the current date).
		java.util.Calendar c = java.util.Calendar.getInstance(TimeZone.getDefault());
		
		//Use to test dates in the MonthPane. 
		//Month is 0 based, year and day start at 1.
		//c.set(2017, 3, 31);
		
		//null as a parameter will result in the same as the c above.
		MonthPane mp = new MonthPane(c);
//		root.setCenter(mp);

        //Month View NOTE: This needed to be down here for it to work, maybe someone else has a better way of doing this
        //Week View, see above
        MenuItem monthView = new MenuItem("Month View");
        MenuItem weekView = new MenuItem("Week View");
        monthView.setOnAction( (e) -> {
            root.setCenter(mp);
            actions.getItems().add(weekView);
            actions.getItems().removeAll(monthView);
        });
        weekView.setOnAction( (e) -> {
            root.setCenter(wp);
            actions.getItems().add(monthView);
            actions.getItems().removeAll(weekView);
        });
       addEvent.setOnAction( (e) -> {
    	   //An agenda is passed in here so that the AddEventPand has a model to edit.
    	   root.setCenter(new BigEventPane(agenda));
    	   
       });


        //Add items to actions
        actions.getItems().add(monthView);

        //Add menus to bar
        menuBar.getMenus().add(actions);

		//Show the stage. 
		primaryStage.setTitle("HCI Group Project");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * This is used for testing
	 * @return An agenda with some elements.
	 */
	private Agenda initTestAgenda() {
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
		System.out.println("Default Generated Agenda Is Being Used!");
		
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
