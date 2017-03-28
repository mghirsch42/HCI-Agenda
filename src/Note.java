
public class Note {
	private String title;
	private String note;

	public Note(){
		setTitle("");
		setNote("");
	}
	
	public Note(String title, String note){
		setTitle(title);
		setNote(note);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String toString(){
		String result = "";
		result += "Title: " + title + "\n"; 
		result += "Note: " + note;
		
		return result; 
	}
}
