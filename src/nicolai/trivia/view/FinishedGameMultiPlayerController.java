package nicolai.trivia.view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nicolai.trivia.MainApp;

/**
 * Controller for the interface shown at the end of a multiplayer game
 */
public class FinishedGameMultiPlayerController {
	private MainApp mainApp;
	@FXML
	private Label lblPlayerOneName;
	
	@FXML
	private Label lblPlayerOneScore;
	
	@FXML
	private Label lblPlayerTwoName;
	
	@FXML
	private Label lblPlayerTwoScore;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		lblPlayerOneName.setAlignment(Pos.CENTER);
		lblPlayerTwoName.setAlignment(Pos.CENTER);
		lblPlayerOneScore.setAlignment(Pos.CENTER);
		lblPlayerTwoScore.setAlignment(Pos.CENTER);
		lblPlayerOneName.setText(this.mainApp.getPlayer1().getName() + " answered");
		lblPlayerOneScore.setText(String.valueOf(this.mainApp.getPlayerOnePoints()) +
				" out of " + this.mainApp.getAmountQuestions());
		lblPlayerTwoName.setText(this.mainApp.getPlayer2().getName() + " answered" +
								" out of " + this.mainApp.getAmountQuestions());
		lblPlayerTwoScore.setText(String.valueOf(this.mainApp.getPlayerTwoPoints()) +
				" out of " + this.mainApp.getAmountQuestions());
		this.mainApp.clearSettings();
	}
	
	@FXML
	public void handleBack() {
		mainApp.fetchQuestions();
		mainApp.showMainMenu();
	}

}
