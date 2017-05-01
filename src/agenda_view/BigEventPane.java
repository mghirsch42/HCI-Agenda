package agenda_view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Agenda;
import model.Event;

public class BigEventPane extends BorderPane{
	private Agenda agenda;
	private ObservableList<Event> eventList; 
	private ListView<Event> eventListView;
	
	private ArrayList<TextField> textFieldList = new ArrayList<TextField>(); 
	private ArrayList<DatePicker> datePickerList = new ArrayList<DatePicker>(); 
	private ArrayList<ComboBox<String>> ampmList = new ArrayList<ComboBox<String>>();
	private ArrayList<ComboBox<Integer>> hourList = new ArrayList<ComboBox<Integer>>();
	private ArrayList<ComboBox<Integer>> minuteList = new ArrayList<ComboBox<Integer>>();
	
	private VBox centerBox = new VBox(5);
	private VBox blank = new VBox();
	
	private BorderPane listInfoBar = new BorderPane();
	private BorderPane leftDisplay = new BorderPane();
	
	private HBox nameBox = new HBox(5);
	private HBox descBox = new HBox(5);
	private HBox startBox = new HBox(5);
	private HBox endBox = new HBox(5);
	private HBox locBox = new HBox(5);
	private HBox colorBox = new HBox(5);
	private HBox catBox = new HBox(5);
	private HBox buttonBox = new HBox(5);
	
	private Label listInfo = new Label("Events");
	private Label nameLbl = new Label("Name of Event");
	private Label descLbl = new Label("Description");
	private Label startLbl = new Label("Start Date");
	private Label endLbl = new Label("End Date");
	private Label locLbl = new Label("Location");
	private Label colorLbl = new Label("Color");
	private Label catLbl = new Label("Category");
	
	private TextField nameField = new TextField();
	private TextField descField = new TextField();
	
	private DatePicker startPicker = new DatePicker();
	//private TextField startField = new TextField();
	private ComboBox<Integer> startHourCombo = new ComboBox<Integer>();
	private Label startColon = new Label(":");
	private ComboBox<Integer> startMinuteCombo = new ComboBox<Integer>();
	private ComboBox<String> startCombo = new ComboBox<String>();
	
	private DatePicker endPicker = new DatePicker();
	private ComboBox<Integer> endHourCombo = new ComboBox<Integer>();
	private Label endColon = new Label(":");
	private ComboBox<Integer> endMinuteCombo = new ComboBox<Integer>();
	private ComboBox<String> endCombo = new ComboBox<String>();
	
	private TextField locField = new TextField();
	private TextField colorField = new TextField();
	private TextField catField = new TextField();
	
	private Button newEvent = new Button("New Event");
	private Button editButton = new Button("Save Changes");
	private Button eventClose = new Button("Close");
	private Button eventDelete = new Button("Delete");
	
	private int selectedEventIndex;
	
	// for creating a new event
	public BigEventPane(final Agenda a) {
		this.agenda = a;
		init();
	}
	
	// for displaying/editing an event
	public BigEventPane(Event e, final Agenda a) {
		this.agenda = a;
		init();
		onListClick(e);
	}
	
	// initialize an empty form
	private void init() {
		//variable init
		selectedEventIndex = -1;

		//Init the list 
		eventList = FXCollections.observableArrayList(); 
		eventListView = new ListView<Event>(eventList);
		
		eventListView.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
			@Override
			public ListCell<Event> call(ListView<Event> param) {
				return new eventListCell();
			}
		});
		
		for(Event e: agenda.getCalendar().getEvents()){
			eventList.add(e);
		}
		
		
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		
		endCombo.getItems().addAll(
				"AM",
				"PM");		
		
		//add the hours to the hour combo boxes
		for (int i = 1; i <= 12; i++){
			startHourCombo.getItems().add(i);
			endHourCombo.getItems().add(i);
		}
		//add the minutes to the minute combo boxes
		for (int i = 0; i <= 60; i++){
			startMinuteCombo.getItems().add(i);
			endMinuteCombo.getItems().add(i);
		}
				
		
		//ListInfoBar
		listInfoBar.setPadding(new Insets(5));
		listInfoBar.setCenter(listInfo);
		listInfoBar.setRight(newEvent);
		
		//Left Display
		leftDisplay.setTop(listInfoBar);
		leftDisplay.setCenter(eventListView);

		//add the hours to the hour combo boxes
		for (int i = 1; i <= 12; i++){
			startHourCombo.getItems().add(i);
			endHourCombo.getItems().add(i);
		}
		//add the minutes to the minute combo boxes
		for (int i = 0; i <= 60; i++){
			startMinuteCombo.getItems().add(i);
			endMinuteCombo.getItems().add(i);
		}
		//give all the combo boxes default values
		startCombo.setValue("PM");
		endCombo.setValue("PM");
		startHourCombo.setValue(12);
		endHourCombo.setValue(1);
		startMinuteCombo.setValue(0);
		endMinuteCombo.setValue(0);
		
		//Aligning the test fields 
		nameLbl.setPadding(new Insets(0, 1, 0, 0));
		descLbl.setPadding(new Insets(0, 19, 0, 0));
		startLbl.setPadding(new Insets(0, 27, 0, 0));
		endLbl.setPadding(new Insets(0, 31, 0, 0));
		locLbl.setPadding(new Insets(0, 34, 0, 0));
		colorLbl.setPadding(new Insets(0, 51, 0, 0));
		catLbl.setPadding(new Insets(0, 32, 0, 0));
		
		//Padding around boxes
		nameBox.setPadding(new Insets(10, 0, 5, 5));
		descBox.setPadding(new Insets(0, 0, 5, 5));
		startBox.setPadding(new Insets(0, 0, 5, 5));
		endBox.setPadding(new Insets(0, 0, 5, 5));
		locBox.setPadding(new Insets(0, 0, 5, 5));
		colorBox.setPadding(new Insets(0, 0, 5, 5));
		catBox.setPadding(new Insets(0, 0, 5, 5));
		buttonBox.setPadding(new Insets(0, 0, 5, 5));
		
		
		ampmList.add(startCombo);
		ampmList.add(endCombo);
		
		hourList.add(startHourCombo);
		hourList.add(endHourCombo);
		
		minuteList.add(startMinuteCombo);
		minuteList.add(endMinuteCombo);
		
		datePickerList.add(startPicker);
		datePickerList.add(endPicker);
		
		textFieldList.add(nameField); 
		textFieldList.add(descField);
		textFieldList.add(locField); 
		textFieldList.add(colorField);
		textFieldList.add(catField);
		
		
		for(ComboBox<String> c : ampmList){
			c.valueProperty().addListener( (e) -> {
				if(editButton.isDisabled()){
					editButton.setDisable(false);
				}
			});	
		}
		
		for(ComboBox<Integer> c : hourList){
			c.valueProperty().addListener( (e) -> {
				if(editButton.isDisabled()){
					editButton.setDisable(false);
				}
			});	
		}
		
		for(ComboBox<Integer> c : minuteList){
			c.valueProperty().addListener( (e) -> {
				if(editButton.isDisabled()){
					editButton.setDisable(false);
				}
			});	
		}
		
		for(DatePicker d : datePickerList){
			d.valueProperty().addListener( (e) -> {
				if(editButton.isDisabled()){
					editButton.setDisable(false);
				}
			});	
		}
		
		for(TextField t : textFieldList){
			t.textProperty().addListener( (e) -> {
				if(editButton.isDisabled()){
					editButton.setDisable(false);
				}
			});
		}

		
		
		
		nameBox.getChildren().addAll(nameLbl, nameField);
		descBox.getChildren().addAll(descLbl, descField);
		startBox.getChildren().addAll(startLbl, startPicker, startHourCombo, startColon, startMinuteCombo, startCombo);
		endBox.getChildren().addAll(endLbl, endPicker, endHourCombo, endColon, endMinuteCombo, endCombo);
		locBox.getChildren().addAll(locLbl, locField);
		colorBox.getChildren().addAll(colorLbl, colorField);
		catBox.getChildren().addAll(catLbl, catField);
		buttonBox.getChildren().addAll(editButton, eventClose, eventDelete);
		
		//Select Listener
		eventListView.getSelectionModel().selectedItemProperty().addListener( (e) -> {
			Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
			selectedEventIndex = eventListView.getSelectionModel().getSelectedIndex();
			
			//Change the text to the selected event. 
			if(selectedEvent != null){
				onListClick(selectedEvent);
			}
			setCenter(centerBox);
		});
		
		//Select the first item if there is one
		if(eventList.size() > 0){
			eventListView.getSelectionModel().selectFirst();
			Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
			
			onListClick(selectedEvent);
			
			setCenter(centerBox);
			selectedEventIndex = 1; 
		}else{
			setCenter(blank);
			selectedEventIndex = -2;
		}
		
		//Alert box
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Unsaved Changes");
		alert.setHeaderText("Would you like to save the current changes?");
		
		ButtonType yesBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");
		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn); 
		
		editButton.setOnAction((e) -> {
			saveEvent();
		});
		
		eventClose.setOnAction( (e) -> {
			clearArea();
			setCenter(blank);
		});
		
		
		eventDelete.setOnAction( (e) -> {
			int index = eventListView.getSelectionModel().getSelectedIndex();
			clearArea();
			eventList.remove(index); 
			agenda.getCalendar().getEvents().remove(index);
			setCenter(blank);
		});
		
		newEvent.setOnAction( (e) -> {
			if( selectedEventIndex == -1 && (!isTextBlank())){
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == yesBtn){
					saveEvent();
					createNewEvent();
				}else if(result.get() == noBtn){
					createNewEvent();
				}else if(result.get() == cancelBtn){
					
				}
			}else{
				createNewEvent();
			}
			eventListView.getSelectionModel().clearSelection();
			eventDelete.setDisable(true);
		});
		
		
		centerBox.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, buttonBox);
		setLeft(leftDisplay);
	}
	
	private void clearArea() {
		for(TextField t: textFieldList){
			t.setText("");
		}
		
		for(ComboBox<Integer> c: hourList){
			c.getSelectionModel().select(new Integer(1));
		}
		
		for(ComboBox<Integer> c: minuteList){
			c.getSelectionModel().select(new Integer(0));
		}
		
		for(ComboBox<String> c: ampmList){
			c.getSelectionModel().select("AM");
		}
		
		for(DatePicker d: datePickerList){
			d.setValue(null);
		}

		eventListView.getSelectionModel().clearSelection();
	}

	private void createNewEvent() {
		clearArea();
		editButton.setDisable(true);
		setCenter(centerBox);
	}

	private void saveEvent() {
		if(selectedEventIndex > -1){
			Event tmpEvent = newEventFromFields();
			agenda.getCalendar().getEvents().set(agenda.getCalendar().getEvents().indexOf(eventList.get(selectedEventIndex)), tmpEvent);
			eventList.set(selectedEventIndex, tmpEvent);
			
		}else{
			Event eventToAdd = newEventFromFields();
			eventList.add(eventToAdd);
			agenda.getCalendar().getEvents().add(eventToAdd);
			setCenter(blank);
			eventListView.getSelectionModel().clearSelection();
			selectedEventIndex = -2;
		}
	}

	private Event newEventFromFields() {
		/*
		 * Parse the fields and create appropriate event objects
		 */
		
		//Name
		String eventName = nameField.getText();
		
		// Determine start dates
		String date = startPicker.getValue().toString();
		System.out.println("Here is your date string: " +
							date);
		
		// tokens will look like yyyy-mm-dd
		String[] tokens = date.toString().split("-");
		
		GregorianCalendar eventStart = new GregorianCalendar(
				Integer.parseInt(tokens[0]),	// year
				Integer.parseInt(tokens[1]) -1, 	// month - not sure why, maybe indexes months from 0?
				Integer.parseInt(tokens[2]),	// day
				startHourCombo.getValue(),		// hour
				startMinuteCombo.getValue());		// minute
		
		// Determine end dates
		date = endPicker.getValue().toString();
		tokens = date.toString().split("-");
			
		GregorianCalendar eventEnd = new GregorianCalendar(
				Integer.parseInt(tokens[0]), // year
				Integer.parseInt(tokens[1]) -1, 	// month
				Integer.parseInt(tokens[2]),		// day
				endHourCombo.getValue(),			// hour
				endMinuteCombo.getValue());			// minute
		
		String eventDesc = descField.getText();
		String eventCat = catField.getText();
		String eventCol = colorField.getText();
		String eventLoc = locField.getText();
		Event tmpEvent = new Event(eventName, eventStart, eventEnd, eventDesc, eventCat, eventCol, eventLoc);
		return(tmpEvent);
	}

	private boolean isTextBlank(){
		boolean flag = true;
		for(TextField t : textFieldList){
			if(!t.getText().equals("")){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	// initialize a form with event data

	private void onListClick(Event event) {
		nameField.setText(event.getName());
		descField.setText(event.getDescription());
		startPicker.setValue(LocalDate.of(event.getStart().get(GregorianCalendar.YEAR), 
										  event.getStart().get(GregorianCalendar.MONTH)+1, 
										  event.getStart().get(GregorianCalendar.DATE))); 
		startHourCombo.getSelectionModel().select(event.getStart().get(GregorianCalendar.HOUR));
		startMinuteCombo.getSelectionModel().select(event.getStart().get(GregorianCalendar.MINUTE));
		startCombo.getSelectionModel().select(event.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM? "AM" : "PM");
		endPicker.setValue(LocalDate.of(event.getEnd().get(GregorianCalendar.YEAR), 
										event.getEnd().get(GregorianCalendar.MONTH)+1, 
										event.getEnd().get(GregorianCalendar.DATE))); 
		endHourCombo.getSelectionModel().select(event.getEnd().get(GregorianCalendar.HOUR));
		endMinuteCombo.getSelectionModel().select(event.getEnd().get(GregorianCalendar.MINUTE));
		endCombo.getSelectionModel().select(event.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM? "AM" : "PM");	

		
		//startField.setText("" + event.getStart().get(GregorianCalendar.HOUR) + ":" + event.getStart().get(GregorianCalendar.MINUTE));
		startHourCombo.setValue(event.getStart().get(GregorianCalendar.HOUR) + 1);
		startMinuteCombo.setValue(event.getStart().get(GregorianCalendar.MINUTE));
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		//check if start time is am or pm
		if (event.getStart().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM){
			startCombo.setValue("AM");
		} else {
			startCombo.setValue("PM");
		}
		
		endPicker.setValue(LocalDate.of(event.getEnd().get(GregorianCalendar.YEAR), 
										event.getEnd().get(GregorianCalendar.MONTH)+1, 
										event.getEnd().get(GregorianCalendar.DATE))); 
		
		//endField.setText("" + event.getEnd().get(GregorianCalendar.HOUR) + ":" + event.getEnd().get(GregorianCalendar.MINUTE));
		endHourCombo.setValue(event.getEnd().get(GregorianCalendar.HOUR) + 1);
		endMinuteCombo.setValue(event.getEnd().get(GregorianCalendar.MINUTE));
		endCombo.getItems().addAll(
				"AM",
				"PM");		
		//check if end time is am or pm
		if (event.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM){
			endCombo.setValue("AM");
		} else {
			endCombo.setValue("PM");
		}
		
		locField.setText(event.getLocation());
		colorField.setText(event.getColor());
		catField.setText(event.getCategory());
		

		editButton.setDisable(true);
		eventDelete.setDisable(false);			
		
		this.setCenter(centerBox);
	}
	
	public void selectEvent(Event event) {
		if(eventList.contains(event)){
			eventListView.getSelectionModel().select(eventList.indexOf(event));
			eventDelete.setDisable(false);
		}
	}
	
	static class eventListCell extends ListCell<Event>{

		@Override
		protected void updateItem(Event item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				Label title = new Label(item.getName());
				setGraphic(title);
			}else{
				setGraphic(null);
			}
		}
	}

}
