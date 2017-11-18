package nicolai.trivia.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Trivia Game");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Nicolai Jaynes \n"
    			+ "How to play:\n"
    			+ "Choose whether you want to play single player or multiplayer.\n"
    			+ "On the next screen choose amount of questions in game and the time limit for each question."
    			+ "\nChoose subject and answer the question. Correct answer gives you 1 point. Wrong"
    			+ " answer gives you 0 points. \nGood Luck! ");

    	alert.showAndWait();
    }

}
