package nicolai.trivia.view;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import nicolai.trivia.MainApp;
import nicolai.trivia.model.Player;
import nicolai.trivia.model.Settings;

/**
 * Controller for multiplayer settings screen
 */
public class MultiplayerSettingsController {
	private MainApp mainApp;
	private Settings settings;
	private Player player1;
	private Player player2;

	@FXML
	private JFXTextField tfName;

	@FXML
	private JFXTextField tfName2;

	@FXML
	private ToggleGroup questionGroup;

	@FXML
	private ToggleGroup timeLimitGroup;

	public MultiplayerSettingsController() {

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		settings = mainApp.getSettings();
		player1 = mainApp.getPlayer1();
		player2 = mainApp.getPlayer2();
	}

	private boolean validName() {
		boolean validName;
		validName = tfName.getText().trim().length() != 0 &&
				tfName2.getText().trim().length() != 0;
		return validName;
	}

	private boolean questionChosen() {
		boolean questionChosen;
		questionChosen = questionGroup.getSelectedToggle() != null;
		return questionChosen;
	}

	private boolean timeLimitChosen() {
		boolean timeLimitChosen;
		timeLimitChosen = timeLimitGroup.getSelectedToggle() != null;
		return timeLimitChosen;
	}

	@FXML
	public void handleBack() {
		mainApp.showMainMenu();
		player1.clearPlayer();
		player2.clearPlayer();
	}

	@FXML
	public void handleStart() {
		if(!validName()) {
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Name");
            alert.setHeaderText("Invalid Name");
            alert.setContentText("Please enter a name");
            alert.showAndWait();
		}

		if(!questionChosen()) {
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Questions");
            alert.setHeaderText("Choose question amount");
            alert.setContentText("Please choose amount of questions in game");
            alert.showAndWait();
		}

		if(!timeLimitChosen()) {
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Time limit");
            alert.setHeaderText("Choose time limit");
            alert.setContentText("Please choose time limit per question");
            alert.showAndWait();
		}

		if (validName() && questionChosen() && timeLimitChosen()) {
			settings.setMultiplayer(true);

			player1.setName(tfName.getText());
			player2.setName(tfName2.getText());

			RadioButton questions = (RadioButton) questionGroup.getSelectedToggle();
			player1.setAmountQuestions(Integer.parseInt(questions.getText()));
			player2.setAmountQuestions(Integer.parseInt(questions.getText()));

			RadioButton time = (RadioButton) timeLimitGroup.getSelectedToggle();
			char[] seconds = new char[2];
			time.getText().getChars(0, 2, seconds, 0);
			String string = new String(seconds);
			settings.setTimeLimit(Integer.parseInt(string));
			settings.setAmountQuestions(Integer.parseInt(questions.getText()));
			mainApp.showSubject();
			System.out.println("Amount questions: " + player1.getAmountQuestions());
			System.out.println("Player1 name is: " + tfName.getText());
			System.out.println("Player2 name is: " + tfName2.getText());
			System.out.println("Time limit is: " + settings.getTimeLimit());
		}
	}

}
