package agenda_view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class MonthPane extends VBox {

	private Map<Integer, String> weekdayName = new HashMap<Integer, String>();
	private Map<Integer, String> monthName = new HashMap<Integer, String>();
	private GregorianCalendar start;
	private GregorianCalendar end;
	private Agenda agenda;
	private GridPane gridPane;
	
	/**
	 * Creates a Grid Pane that represents the month/day/year set in the Calendar object.
	 * @param c, The Local Calendar and Time zone will be used if null is passed in. Otherwise the provided calendar will be used.
	 */
	public MonthPane(GregorianCalendar start, GregorianCalendar end, final Agenda agenda){
		this.start = start;
		this.end = end;
		this.agenda = agenda;
		gridPane = new GridPane();
		
		//Initializes the maps used for translating dates to strings
		initMaps();
		
		Label monthLbl = new Label("");
		monthLbl.setText(getMonthString(this.start));
		
		Button prevButton = new Button("Prev Week");
		Button nextButton = new Button("Next Week");
		
		prevButton.setOnAction(e -> {
			this.start = subtractMonth(this.start);
			this.end = subtractMonth(this.end);
			monthLbl.setText(getMonthString(this.start));
			updateGrid((GregorianCalendar) start.clone());
		});
		
		nextButton.setOnAction(e -> {
			this.start = addMonth(this.start);
			this.end = addMonth(this.end);
			monthLbl.setText(getMonthString(this.start));
			updateGrid((GregorianCalendar) start.clone());
		});
		
		BorderPane topBar = new BorderPane();
		topBar.setLeft(prevButton);
		topBar.setRight(nextButton);
		topBar.setCenter(monthLbl);
		
		GregorianCalendar c = agenda.getCalendar().getCalendar();
		
		//Sets the current day to the data if no Calendar was provided.
		if(c == null){
			agenda.setCalendar(new GregorianCalendar());
		}
		
				
		//Gather information about the current time. 
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		
		//Updates the grid
		updateGrid((GregorianCalendar) start.clone());
		
		//Testing output
		System.out.println(weekdayName.get(weekday));
		System.out.println(monthName.get(month));
		System.out.println("Day: "+day );
		System.out.println("Year: "+year );
		
		System.out.println("Week of Month: "+c.get(Calendar.WEEK_OF_MONTH));
		System.out.println("Day of Week: "+c.get(Calendar.DAY_OF_WEEK));
		
		//start = new Date(Calendar.YEAR, Calendar.MONTH, 1);
		//end = new Date(Calendar.YEAR, Calendar.MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		this.getChildren().addAll(topBar, gridPane);
		
	}

	private void updateGrid(Calendar c) {
		gridPane.getChildren().clear();
		gridPane.getRowConstraints().clear();
		gridPane.getColumnConstraints().clear();
		
		//The number of cols stays the same regardles.
		//numRows variates based off the number of 'weeks' in the month.
		final int numCols = 7;
		final int numRows = c.getActualMaximum(Calendar.WEEK_OF_MONTH)+1;
		
		//Sets up column widths
		for(int i = 0; i < numCols; i++ ) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / numCols);
			gridPane.getColumnConstraints().add(colConst);
		}
		
		for(int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(110.0 / numRows);
			if(i == 0){
				rowConst.setPercentHeight(10.0);
			}
			gridPane.getRowConstraints().add(rowConst);
		}
		
		Label sundayLbl = new Label ("Sunday");
		gridPane.add(sundayLbl, 0, 0);
		
		Label mondayLbl = new Label ("Monday");
		gridPane.add(mondayLbl, 1, 0);
		
		Label tuesdayLbl = new Label("Tuesday");
		gridPane.add(tuesdayLbl, 2, 0);
		
		Label wednesdayLbl = new Label ("Wednesday");
		gridPane.add(wednesdayLbl, 3, 0);
		
		Label thursdayLbl = new Label ("Thursday");
		gridPane.add(thursdayLbl, 4, 0);
		
		Label fridayLbl = new Label ("Friday");
		gridPane.add(fridayLbl, 5, 0);
		
		Label saturdayLbl = new Label ("Saturday");
		gridPane.add(saturdayLbl, 6, 0);
		
		c.getActualMinimum(Calendar.DAY_OF_MONTH);
		for(int i = c.getActualMinimum(Calendar.DAY_OF_MONTH); i <= c.getActualMaximum(Calendar.DAY_OF_MONTH); i++ ){
			c.set(Calendar.DAY_OF_MONTH, i);
			gridPane.add(new Label(""+i), c.get(Calendar.DAY_OF_WEEK)-1, c.get(Calendar.WEEK_OF_MONTH));
		}
		
		updateEvents();
		
		//Shows the grid. Must be toggled to redraw
		gridPane.setGridLinesVisible(false);
		gridPane.setGridLinesVisible(true);
	}
	
	private String getMonthString(GregorianCalendar start2) {
		String s = monthName.get(start.get(GregorianCalendar.MONTH)) + ", " + start.get(GregorianCalendar.YEAR);
		return s;
	}

	private GregorianCalendar addMonth(GregorianCalendar day) {
		int month = day.get(GregorianCalendar.MONTH);																		//If there is a new month, calculate the new values for the dates. 
		
		if( month < 11){ 														//If there isn't a new year.
			day.set(GregorianCalendar.MONTH, day.get(GregorianCalendar.MONTH) + 1); //Add 1 from the month.
		}else{
			day.set(GregorianCalendar.MONTH, 0); // Loop back the the start of the nexts year
			day.set(GregorianCalendar.YEAR, day.get(GregorianCalendar.YEAR)+1); // Add a year
		}
		
		return day;
	}

	private GregorianCalendar subtractMonth(GregorianCalendar day) {
		int month = day.get(GregorianCalendar.MONTH);																		//If there is a new month, calculate the new values for the dates. 
		
		if( month > 0){ 														//If there isn't a new year.
			day.set(GregorianCalendar.MONTH, day.get(GregorianCalendar.MONTH) - 1); //Subtract 1 from the month.
		}else{
			day.set(GregorianCalendar.MONTH, 11); // Loop back the the end of the last year
			day.set(GregorianCalendar.YEAR, day.get(GregorianCalendar.YEAR)-1); // Subtract a year
		}
		
		return day;
	}
	
	private void updateEvents() {
		removeEvents();
		addEvents();
	}
	
	public void addEvents() {
		System.out.println("Adding events to MonthPane");
		for(Event e : agenda.getCalendar().getEvents(start, end)) {
			System.out.println("Adding event " + e);
			addEvent(e);
		}
	}
	
	private void addEvent(Event event) {
		// TODO: Algorithm for adding events to right spot in month
		Calendar c = agenda.getCalendar().getCalendar();
		c.set(Calendar.MONTH, event.getStart().get(GregorianCalendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, event.getStart().get(GregorianCalendar.DATE));
		gridPane.add(new MonthEventPane(event, agenda), c.get(Calendar.DAY_OF_WEEK)-1, c.get(Calendar.WEEK_OF_MONTH));
		
	}
	
	private void removeEvents() {
		ArrayList<Object> objectsToRemove = new ArrayList<Object>(); 
		for(Node n: gridPane.getChildren()){
			if( n.getClass().getName() == "agenda_view.MonthEventPane"){
				objectsToRemove.add(n); 
			}
		}
		gridPane.getChildren().removeAll(objectsToRemove); 
	}
	
	private void initMaps(){
		//Set up weekdayName map
		weekdayName.put(1, "Sunday");
		weekdayName.put(2, "Monday");
		weekdayName.put(3, "Tuesday");
		weekdayName.put(4, "Wednesday");
		weekdayName.put(5, "Thursday");
		weekdayName.put(6, "Friday");
		weekdayName.put(7, "Saturday");
	
		//Set up monthName map
		monthName.put(0, "January");
		monthName.put(1, "Febuary");
		monthName.put(2, "March");
		monthName.put(3, "April");
		monthName.put(4, "May");
		monthName.put(5, "June");
		monthName.put(6, "July");
		monthName.put(7, "August");
		monthName.put(8, "September");
		monthName.put(9, "October");
		monthName.put(10, "November");
		monthName.put(11, "December");
	}
}
