import java.util.ArrayList;

public class Notepad {
	private ArrayList<Note> notes;
	
	/**
	 * The Empty constructor for Notepad.
	 * Sets notes equal to an empty ArrayList<Note>.
	 */
	public Notepad(){
		setNotes(new ArrayList<Note>());
	}
	
	/**
	 * Adds a new note to the notes ArrayList.
	 * 
	 * @param note The note to add.
	 * @return True if the note was added successfully, false if otherwise.
	 */
	public boolean addNote(Note note){
		return notes.add(note); 
	}

	/**
	 * Returns an ArrayList of notes.
	 * @return An ArrayList of notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * Sets the notes ArrayList equal to a new one.
	 * @param notes The new ArrayList<Note>.
	 */
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	
	/**
	 * Returns a string representing the Notepad object.
	 */
	public String toString(){
		return notes.toString();
	}
}
