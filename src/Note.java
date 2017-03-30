
public class Note {
	private String title;
	private String message;

	/**
	 * Default constructor for Note.
	 * Sets title and note equal to the empty string.
	 */
	public Note(){
		setTitle("");
		setMessage("");
	}
	
	/**
	 * Note constructor for predefined strings.
	 * 
	 * @param title The title of the note
	 * @param note The message of the note.
	 */
	public Note(String title, String note){
		setTitle(title);
		setMessage(note);
	}

	/**
	 * Returns the title of the Note.
	 * @return the title of the Note
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the note to the provided title.
	 * @param title The title to use
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the message of the Note.
	 * @return the message of the Note
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message of the note to the provided message.
	 * @param message The message to use
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Returns a string representing the Note object.
	 */
	public String toString(){
		String result = "";
		result += "Title: " + title + "\n"; 
		result += "Note: " + message + "\n";
		
		return result; 
	}
}
