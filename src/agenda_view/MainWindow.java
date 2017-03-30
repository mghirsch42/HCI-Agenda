package agenda_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		Scene scene = new Scene(root, 500, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//launches the window
	public void launchWindow() {
		launch();
	}

}
