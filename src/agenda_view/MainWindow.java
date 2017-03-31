package agenda_view;

import java.util.TimeZone;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class MainWindow extends Application{
	
	@Override
	public void start(Stage primaryStage) {

		//Border Layout Root
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
		//Menu Bar
		MenuBar menuBar = new MenuBar();
		
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
		
		//java.util Calendar (we might want to refactor our Calendar Class to avoid duplicate names).
		java.util.Calendar c = java.util.Calendar.getInstance(TimeZone.getDefault());
		
		//Use to test dates in the MonthPane. 
		//Month is 0 based, year and day start at 1.
		c.set(2017, 3, 31);
		
		//null as a parameter will result in the same as the c above.
//		MonthPane mp = new MonthPane(c);
//		root.setCenter(mp);
		
		//Show the stage. 
		primaryStage.setTitle("HCI Group Project");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args); 
	}

}
