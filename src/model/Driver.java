package model;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import agenda_view.MainWindow;

class Driver {

	public static void main(String[] args) {

		//Opens the GUI
		MainWindow window = new MainWindow(); 
		window.startGUI(args);
		
		//Runs after the GUI closes

		//Unit test for EventSaveHandler
		System.out.println("Starting EventSaveHandler test...");
		eventSaveTest();
		System.out.println("Ending test.");
    
	}

	public static void eventSaveTest()
	{
		EventSaveHandler handler = new EventSaveHandler("TestFileSaves.txt");
		Event testEvent1 = new Event("Test One", new GregorianCalendar(), new GregorianCalendar(), "YOLO", "Fish", "THIS SHOULD NOT BE A STRING", "OU");
		Event testEvent2 = new Event("Test Two", new GregorianCalendar(), new GregorianCalendar(), "YOLOSWAG", "Sausage", "THIS SHOULD NOT BE A STRING", "OSU");
		Event testEvent3 = new Event("Test Three", new GregorianCalendar(), new GregorianCalendar(), "SWAGGERDOSE", "Apple", "THIS SHOULD NOT BE A STRING", "UCO");

		ArrayList<Event> list = new ArrayList<Event>();
		list.add(testEvent1);
		list.add(testEvent2);
		list.add(testEvent3);

		//Writing this stuff
		handler.writeFromList(list);

		//Hopefully that worked, make new list and read to it
		ArrayList<Event> testList = new ArrayList<Event>();
		handler.readToList(testList);

		//Moment of truth
		for(int i = 0; i < testList.size(); i++)
		{
			Event temp = testList.get(i);
		}

	}

}