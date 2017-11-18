package nicolai.trivia.view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import nicolai.trivia.MainApp;

/**
 * Controller for the interface shown at the end of a singleplayer game
 */
public class FinishedGameSinglePlayerController {
	private MainApp mainApp;
	@FXML
	private Label lblPlayerOneName;
	
	@FXML
	private Label lblScore;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		lblPlayerOneName.setAlignment(Pos.CENTER);
		lblScore.setAlignment(Pos.CENTER);
		lblPlayerOneName.setText(this.mainApp.getPlayer1().getName() + " answered");
		lblScore.setText(String.valueOf(this.mainApp.getPlayerOnePoints()) +
				" out of " + this.mainApp.getAmountQuestions());
		this.mainApp.clearSettings();
	}
	
	@FXML
	public void handleBack() {
		mainApp.fetchQuestions();
		mainApp.showMainMenu();
	}

}
