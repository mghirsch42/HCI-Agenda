
public class Agenda {
	
	private Calendar calendar;	
	private Notepad notepad;
	//private Budget budget;
	
	/*Constructor for a new agenda*/
	public Agenda(){
		setCalendar(new Calendar());
		setNotepad(new Notepad());
	}
	
	/*Constructor for an existing agenda*/
	public Agenda(Calendar calendar, Notepad notepad){
		setCalendar(calendar);
		setNotepad(notepad);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Notepad getNotepad() {
		return notepad;
	}

	public void setNotepad(Notepad notepad) {
		this.notepad = notepad;
	}
	
	
}

