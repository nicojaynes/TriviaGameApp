package nicolai.trivia.view;

import javafx.fxml.FXML;
import nicolai.trivia.MainApp;

public class MainMenuController {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleSinglePlayer() {
		mainApp.showSinglePlayerSettings();
	}

	@FXML
	private void handleMultiplayer() {
		mainApp.showMultiPlayerSettings();
	}

	@FXML
	private void handleAddQuestion() {
		mainApp.loadWindow("/nicolai/trivia/view/AddQuestion.fxml", "Add Question");
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

}
