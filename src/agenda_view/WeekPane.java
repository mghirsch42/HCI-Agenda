package agenda_view;

import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.MyCalendar;
import model.Agenda;
import model.Event;

/*
 * This class will define the view for a week
 * 
 * 
 */
public class WeekPane extends VBox{
	
	private Date start;			// start date for this time frame
	private Date end;			// end date for this time frame
	private Agenda agenda;		// agenda
	private GridPane gridPane;
	
	public WeekPane(Date start, Date end, Agenda a) {
		this.start = start;
		this.end = end;
		this.agenda = a;
		gridPane = new GridPane();
		init();
		addEvents();
		
		// TODO: convert to month name
		Label monthLbl = new Label(""+start.getMonth());
		this.getChildren().add(monthLbl);
		
		this.getChildren().add(gridPane);
	}
		
	private void init() {
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
	}
	
	public void addEvents(){
		for(Event e : agenda.getCalendar().getEvents(start, end)) {
			addEvent(e);
		}
	}
	
	private void addEvent(Event e) {
		WeekEventPane ep = new WeekEventPane(e, agenda);
		
		System.out.println("This event:" + e.start);
		
		int col = (e.getStart().getDay()*2) + 1;
		//if(e.getStart().getDay())
		int row = e.getStart().getHours();
		
		gridPane.add(ep, col, row);

	}
}
