
public class Agenda {
	
	private Calendar calendar;	
	private Notepad notepad;
	//private Budget budget;
	
	/**
	 * Constructor for a new agenda
	 */
	public Agenda(){
		setCalendar(new Calendar());
		setNotepad(new Notepad());
	}
	
	/**
	 * Constructor for an existing calendar and notepad
	 * 
	 * @param calendar The Calendar to use
	 * @param notepad The Notepad to use
	 */
	public Agenda(Calendar calendar, Notepad notepad){
		setCalendar(calendar);
		setNotepad(notepad);
	}

	/**
	 * Returns the current calendar.
	 * @return The current calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Sets this.calendar equal to a new one.
	 * @param calendar the new Calendar. 
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * Returns the current notepad.
	 * @return The current notepad
	 */
	public Notepad getNotepad() {
		return notepad;
	}

	/**
	 * Sets this.calendar equal to a new one.
	 * @param notepad the new Notepad
	 */
	public void setNotepad(Notepad notepad) {
		this.notepad = notepad;
	}
	
	/**
	 * Returns a string representing the Agenda object.
	 */
	public String toString(){
		String result = ""; 
		result += "Calendar: \n" + calendar.toString() + "\n\n";
		result += "Notepad: \n" + notepad.toString() + "\n\n";
		
		return result;
	}
	
}

