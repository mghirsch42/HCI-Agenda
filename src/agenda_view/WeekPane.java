package agenda_view;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import model.Agenda;
import model.Event;

/*
 * This class will define the view for a week
 * 
 * 
 */
public class WeekPane extends VBox{
	
	private Map<Integer, String> weekdayName = new HashMap<Integer, String>();
	private Map<Integer, String> monthName = new HashMap<Integer, String>();
	private GregorianCalendar start;			// start date for this time frame
	private GregorianCalendar end;			// end date for this time frame
	private Agenda agenda;		// agenda
	private GridPane gridPane;
	//private ScrollPane scrollPane;
	private Label dateLbl = new Label("");
	private VBox[][] boxes; //14x25
	
	public WeekPane(GregorianCalendar start, GregorianCalendar end, final Agenda a) { 
		this.start = start;
		this.end = end;
		this.agenda = a;
		gridPane = new GridPane();
		//scrollPane = new ScrollPane(gridPane);
		boxes = new VBox[14][25];
		//scrollPane.setFitToHeight(true);
		init();
		addEvents();
		
		Button prevButton = new Button("Prev Week");
		Button nextButton= new Button("Next Week");
		
		prevButton.setOnAction(e -> {
			this.start = subtractWeek(this.start);
			this.end = subtractWeek(this.end);
			dateLbl.setText(getDateString(this.start));
			updateEvents();
		});
		
		nextButton.setOnAction(e -> {
			this.start = addWeek(this.start);
			this.end = addWeek(this.end);
			dateLbl.setText(getDateString(this.start));
			updateEvents();
		});
		
		dateLbl.setText(getDateString(this.start));
		dateLbl.setAlignment(Pos.CENTER);
		
		BorderPane topBar = new BorderPane();
		topBar.setCenter(dateLbl);
		topBar.setLeft(prevButton);
		topBar.setRight(nextButton);
		
		this.getChildren().addAll(topBar, gridPane);
	}

	private String getDateString(GregorianCalendar day) {
		String s = "Week of "+monthName.get(day.get(GregorianCalendar.MONTH)) + " " + day.get(GregorianCalendar.DATE) + ", " + day.get(GregorianCalendar.YEAR);
		return s;
	}
	
	private GregorianCalendar addWeek(GregorianCalendar day) {
		int daysInMonth = day.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		int date = day.get(GregorianCalendar.DATE);
		if(daysInMonth - date >= 7){ 												//If there isn't a new month
			day.set(day.get(GregorianCalendar.YEAR), day.get(GregorianCalendar.MONTH), date + 7);
		}else{																		//If there is a new month, calculate the new values for the dates. 
			int daysIn = -(daysInMonth-(date+7));
			int month = day.get(GregorianCalendar.MONTH);
			if( month < 11){ 														//If there isn't a new year.
				day.set(day.get(GregorianCalendar.YEAR), month + 1, daysIn);
			}else{
				day.set(day.get(GregorianCalendar.YEAR)+1, 0, daysIn); // December has 31 days, is the 12th (11) month. //No checking for min year.
			}
		}
		
		return day;
	}

	private GregorianCalendar subtractWeek(GregorianCalendar day) {
		int date = day.get(GregorianCalendar.DATE);
		if(date > 7){ 																//If there isn't a new month
			day.set(day.get(GregorianCalendar.YEAR), day.get(GregorianCalendar.MONTH), date - 7);
		}else{																		//If there is a new month, calculate the new values for the dates. 
			int daysBack = 7 - date;
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
	
	private void updateEvents() {
		removeEvents();
		addEvents();
	}
	
	public void addEvents(){
		System.out.println(agenda);
		for(Event e : agenda.getCalendar().getEvents(start, end)) {
			addEvent(e);
		}
	}
	
	private void addEvent(Event e) {
		WeekEventPane ep = new WeekEventPane(e, agenda);
		
		System.out.println("This event:" + e.start);
		
		int col = (e.getStart().get(GregorianCalendar.DAY_OF_WEEK)*2) - 1;

		int row = e.getStart().get(GregorianCalendar.HOUR);
		
		//gridPane.add(ep, col, row);
		boxes[col][row].getChildren().add(ep);
	}
	
	public void removeEvents(){
//		gridPane.getChildren().remove(gridPane.getChildren().size() - 1);
		ArrayList<Object> objectsToRemove = new ArrayList<Object>(); 
		for(Node n: gridPane.getChildren()){
			if( n instanceof VBox){
				((VBox) n).getChildren().removeAll(((VBox) n).getChildren());
			}
		}
		//gridPane.getChildren().removeAll(objectsToRemove);
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
	
	private void init() {
		initMaps();
		gridPane.setGridLinesVisible(true);
		final int numCols = 14;
		final int numRows = 24;
		for(int i = 0; i < numCols; i++ ) {
			ColumnConstraints colConst = new ColumnConstraints();
			if(i % 2 == 0) {
				colConst.setPercentWidth(100.0 / numCols /2);
			}
			else {
				colConst.setPercentWidth(100.0 / numCols * 2);
			}
			gridPane.getColumnConstraints().add(colConst);
		}
		for(int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / numRows);
			gridPane.getRowConstraints().add(rowConst);
		}
		
		for(int i = 0; i < numCols; i+=2) {
			for(int j = 1; j <= numRows/2; j++) {
				Label hour = new Label(""+j);
				gridPane.add(hour, i, j);
			}
			for(int j = 1; j <= numRows/2; j++) {
				Label hour = new Label(""+j);
				gridPane.add(hour, i, j+12);
			}
		}
		
		
		Label sundayLbl = new Label ("Sunday");
		gridPane.add(sundayLbl, 1, 0);
		
		Label mondayLbl = new Label ("Monday");
		gridPane.add(mondayLbl, 3, 0);
		
		Label tuesdayLbl = new Label("Tuesday");
		gridPane.add(tuesdayLbl, 5, 0);
		
		Label wednesdayLbl = new Label ("Wednesday");
		gridPane.add(wednesdayLbl, 7, 0);
		
		Label thursdayLbl = new Label ("Thursday");
		gridPane.add(thursdayLbl, 9, 0);
		
		Label fridayLbl = new Label ("Friday");
		gridPane.add(fridayLbl, 11, 0);
		
		Label saturdayLbl = new Label ("Saturday");
		gridPane.add(saturdayLbl, 13, 0);

		//Setup boxes
		for(int i = 1; i <= 13; i = i + 2)
		{
			for(int j = 1; j <= 24; j++)
			{
				boxes[i][j] = new VBox();
				gridPane.add(boxes[i][j], i, j);
			}
		}
	}
}
