package agenda_view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Agenda;
import model.Note;

/**
 * 
 * @author Logan
 */
public class NotePane extends BorderPane{

	private Agenda agenda;
	private ObservableList<Note> noteList; 
	private ListView<Note> noteListView;
	private VBox blank = new VBox();
	private VBox noteDisplay;
	private HBox titleBox;
	private HBox contentBox;
	private HBox noteButtons;
	private BorderPane listInfoBar;
	private BorderPane leftDisplay;
	private TextField noteTitle;
	private TextArea noteContent;
	private Label listInfo = new Label("Notes");
	private Label titleLbl = new Label("Title");
	private Label contentLbl = new Label("Note");
	private Button newNote = new Button("New Note");
	private Button saveChanges = new Button("Save Changes");
	private Button noteClose = new Button("Close");
	private Button noteDelete = new Button("Delete");
	private int selectedNoteIndex;
	
	public NotePane(final Agenda a){
		this.agenda = a;
		init();
		
	}

	private void init() {
		//variable init
		selectedNoteIndex = -1;
		
		//Init the display items
		noteDisplay = new VBox(5);
		titleBox = new HBox(5);
		contentBox = new HBox(5);
		noteTitle = new TextField();
		noteContent = new TextArea();
		noteButtons = new HBox(5);
		listInfoBar = new BorderPane();
		leftDisplay = new BorderPane();
		
		
		//Title Box
		titleLbl.setPadding(new Insets(0,5,0,0));
		titleBox.getChildren().addAll(titleLbl, noteTitle);
		
		//Content Box
		contentBox.getChildren().addAll(contentLbl, noteContent);
		
		//Note Buttons
		noteButtons.getChildren().addAll(saveChanges, noteClose, noteDelete);
		
		//Add items to the center pane
		noteDisplay.getChildren().addAll(titleBox, contentBox, noteButtons);
		
		//ListInfoBar
		listInfoBar.setPadding(new Insets(5));
		listInfoBar.setCenter(listInfo);
		listInfoBar.setRight(newNote);
		
		//Init the list 
		noteList = FXCollections.observableArrayList(); 
		noteListView = new ListView<Note>(noteList);
		
		noteListView.setCellFactory(new Callback<ListView<Note>, ListCell<Note>>() {
			@Override
			public ListCell<Note> call(ListView<Note> param) {
				return new noteListCell();
			}
		});
		
		for(Note n: agenda.getNotepad().getNotes()){
			noteList.add(n);
		}
		
		//Text Listeners
		noteTitle.textProperty().addListener( (e) -> {
			if(saveChanges.isDisabled()){
				saveChanges.setDisable(false);
			}
		});
		
		noteContent.textProperty().addListener( (e) -> {
			if(saveChanges.isDisabled()){
				saveChanges.setDisable(false);
			}
		});
		
		//Select Listener
		noteListView.getSelectionModel().selectedItemProperty().addListener( (e) -> {
			Note selectedNote = noteListView.getSelectionModel().getSelectedItem();
			selectedNoteIndex = noteListView.getSelectionModel().getSelectedIndex();
			
			//Change the text to the selected note. 
			if(selectedNote != null){
				noteTitle.setText(selectedNote.getTitle());
				noteContent.setText(selectedNote.getMessage());
				saveChanges.setDisable(true);
				noteDelete.setDisable(false);
			}
			setCenter(noteDisplay);
		});
		
		//Select the first item if there is one
		if(noteList.size() > 0){
			noteListView.getSelectionModel().selectFirst();
			Note selectedNote = noteListView.getSelectionModel().getSelectedItem();
			
			//Change the text to the selected note. 
			noteTitle.setText(selectedNote.getTitle());
			noteContent.setText(selectedNote.getMessage());
			saveChanges.setDisable(true);
			noteDelete.setDisable(false);
			setCenter(noteDisplay);
			selectedNoteIndex = 1; 
		}else{
			setCenter(blank);
			selectedNoteIndex = -2;
		}
		
		//Alert box
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Unsaved Changes");
		alert.setHeaderText("Would you like to save the current changes?");
		
		ButtonType yesBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");
		ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn); 
		
		//Button Listeners
		saveChanges.setOnAction( (e) -> {
			saveNote();
		});
		
		
		noteClose.setOnAction( (e) -> {
			clearArea();
			setCenter(blank);
		});
		
		noteDelete.setOnAction( (e) -> {
			int index = noteListView.getSelectionModel().getSelectedIndex();
			clearArea();
			noteList.remove(index); 
			agenda.getNotepad().getNotes().remove(index);
			setCenter(blank);
		});
		
		newNote.setOnAction( (e) -> {
			if( selectedNoteIndex == -1 && (!noteTitle.getText().equals("") || !noteContent.getText().equals(""))){
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == yesBtn){
					saveNote();
					createNewNote();
				}else if(result.get() == noBtn){
					createNewNote();
				}else if(result.get() == cancelBtn){
					
				}
			}else{
				createNewNote();
			}
			noteListView.getSelectionModel().clearSelection();
			noteDelete.setDisable(true);
		});
		
		//Left Display
		leftDisplay.setTop(listInfoBar);
		leftDisplay.setCenter(noteListView);
		
		//Note Display
		noteDisplay.setPadding(new Insets(15));
		
		setLeft(leftDisplay);
	}

	private void clearArea() {
		noteTitle.setText("");
		noteContent.setText("");
		noteListView.getSelectionModel().clearSelection();
	}

	private void createNewNote() {
		Note selectedNote = new Note(); 
		selectedNoteIndex = -1;
		
		//Change the text to the selected note. 
		noteTitle.setText(selectedNote.getTitle());
		noteContent.setText(selectedNote.getMessage());
		saveChanges.setDisable(true);
		setCenter(noteDisplay);
	}

	private void saveNote() {
		if(selectedNoteIndex > -1){
			noteList.set(selectedNoteIndex, new Note(noteTitle.getText(), noteContent.getText()));
		}else{
			Note noteToAdd = new Note(noteTitle.getText(), noteContent.getText());
			noteList.add(noteToAdd);
			agenda.getNotepad().addNote(noteToAdd);
			setCenter(blank);
			noteListView.getSelectionModel().clearSelection();
			selectedNoteIndex = -2;
		}
	}
	
	static class noteListCell extends ListCell<Note>{

		@Override
		protected void updateItem(Note item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				Label title = new Label(item.getTitle());
				setGraphic(title);
			}else{
				setGraphic(null);
			}
		}
	}
}
