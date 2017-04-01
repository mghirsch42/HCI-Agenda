package agenda_view;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MonthPane extends GridPane {

	private Map<Integer, String> weekdayName = new HashMap<Integer, String>();
	private Map<Integer, String> monthName = new HashMap<Integer, String>();
	
	/**
	 * Creates a Grid Pane that represents the month/day/year set in the Calendar object.
	 * @param c, The Local Calendar and Time zone will be used if null is passed in. Otherwise the provided calendar will be used.
	 */
	public MonthPane(Calendar c){
		//Initializes the maps used for translating dates to strings
		initMaps();
		
		//Shows the grid.
		this.setGridLinesVisible(true);
		
		
		//Sets the current day to the data if no Calendar was provided.
		if(c == null){
			c = Calendar.getInstance(TimeZone.getDefault());
		}
		
		//c.set(2017, 3, 15); //Dates can be changed here for testing.
		
		//Gather information about the current time. 
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		
		//The number of cols stays the same regardles.
		//numRows variates based off the number of 'weeks' in the month.
		final int numCols = 7;
		final int numRows = c.getActualMaximum(Calendar.WEEK_OF_MONTH)+1;
		
		//Sets up column widths
		for(int i = 0; i < numCols; i++ ) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / numCols);
			this.getColumnConstraints().add(colConst);
		}
		
		for(int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(110.0 / numRows);
			if(i == 0){
				rowConst.setPercentHeight(10.0);
			}
			this.getRowConstraints().add(rowConst);
		}
		
		Label sundayLbl = new Label ("Sunday");
		this.add(sundayLbl, 0, 0);
		
		Label mondayLbl = new Label ("Monday");
		this.add(mondayLbl, 1, 0);
		
		Label tuesdayLbl = new Label("Tuesday");
		this.add(tuesdayLbl, 2, 0);
		
		Label wednesdayLbl = new Label ("Wednesday");
		this.add(wednesdayLbl, 3, 0);
		
		Label thursdayLbl = new Label ("Thursday");
		this.add(thursdayLbl, 4, 0);
		
		Label fridayLbl = new Label ("Friday");
		this.add(fridayLbl, 5, 0);
		
		Label saturdayLbl = new Label ("Saturday");
		this.add(saturdayLbl, 6, 0);
		
		//Offset the displayed day of week on the calendar
		int offset = Math.abs(day%7 - c.get(Calendar.DAY_OF_WEEK )+1);
		for(int i = day; i <= c.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			this.add(new Label(""+i), (i+offset)%7, 1+(i+offset)/7);
		}
		for(int i = day-1; i >= c.getActualMinimum(Calendar.DAY_OF_MONTH); i--){
			this.add(new Label(""+i), (i+offset)%7, 1+(i+offset)/7);
		}
		
		System.out.println(weekdayName.get(weekday));
		System.out.println(monthName.get(month));
		System.out.println(""+day );
		System.out.println(""+year );
		
		System.out.println(""+c.get(Calendar.WEEK_OF_MONTH));
		
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