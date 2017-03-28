import java.util.ArrayList;

public class Notepad {
	private ArrayList<Note> notes;
	
	public Notepad(){
		setNotes(new ArrayList<Note>());
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
}
