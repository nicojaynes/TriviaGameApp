package nicolai.trivia.view;

import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nicolai.trivia.MainApp;
import nicolai.trivia.model.Player;
import nicolai.trivia.model.Question;
import nicolai.trivia.model.Settings;

/**
 * Controller for the subject GUI
 */
public class SubjectController {
	private MainApp mainApp;
	private Settings settings;
	private Player player1;
	private Player player2;
	private ArrayList<Question> questionList;
	private int randomNumberOne;
	private int randomNumberTwo;
	private int randomNumberThree;
	private int randomNumberFour;
	private Question questionOne;
	private Question questionTwo;
	private Question questionThree;
	private Question questionFour;


	@FXML
	private Label lblName;

	@FXML
	private Label lblPoints;

	@FXML
	private Label lblQuestions;

	@FXML
	private Button btnSubjectOne;

	@FXML
	private Button btnSubjectTwo;

	@FXML
	private Button btnSubjectThree;

	@FXML
	private Button btnSubjectFour;


	public SubjectController() {
		
	}

//	public void setNameLabel(String name) {
//		lblName.setText(name);
//	}
//
//	public void setPointsLabel(int points) {
//		lblPoints.setText(String.valueOf(points));
//	}
//
//	public void setQuestionsLabel(int questions) {
//		lblQuestions.setText(String.valueOf(questions));
//	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		settings = mainApp.getSettings();
		if (settings.isSinglePlayer()) {
			player1 = mainApp.getPlayer1();
		} else {
			player1 = mainApp.getPlayer1();
			player2 = mainApp.getPlayer2();
		}
		
		if(settings.isSinglePlayer()) {
			lblName.setText(player1.getName());
			lblPoints.setText(String.valueOf(player1.getPoints()));
			lblQuestions.setText(String.valueOf((player1.getQuestionNumber()) + 1));
		} else {
			if(settings.getPlayerTurn() == 1) {
				lblName.setText(player1.getName());
				lblPoints.setText(String.valueOf(player1.getPoints()));
				lblQuestions.setText(String.valueOf((player1.getQuestionNumber()) + 1));
			} else {
				lblName.setText(player2.getName());
				lblPoints.setText(String.valueOf(player2.getPoints()));
				lblQuestions.setText(String.valueOf((player2.getQuestionNumber()) + 1));
			}
		}
	}

	@FXML
	public void handleSubjectOne() {
		String[] alternatives = new String[5];
		alternatives[0] = questionOne.getQuestion();
		alternatives[1] = questionOne.getAlternativeOne();
		alternatives[2] = questionOne.getAlternativeTwo();
		alternatives[3] = questionOne.getAlternativeThree();
		alternatives[4] = questionOne.getAlternativeFour();
		
		mainApp.setAlternatives(alternatives);
		mainApp.setCorrectAnswer(questionOne.getCorrectAnswer());
		mainApp.removeQuestionFromList(randomNumberOne);
		mainApp.showQuestion();
	}

	@FXML
	public void handleSubjectTwo() {
		String[] alternatives = new String[5];
		alternatives[0] = questionTwo.getQuestion();
		alternatives[1] = questionTwo.getAlternativeOne();
		alternatives[2] = questionTwo.getAlternativeTwo();
		alternatives[3] = questionTwo.getAlternativeThree();
		alternatives[4] = questionTwo.getAlternativeFour();
		
		mainApp.setAlternatives(alternatives);
		mainApp.setCorrectAnswer(questionTwo.getCorrectAnswer());
		mainApp.removeQuestionFromList(randomNumberTwo);
		mainApp.showQuestion();
	}

	@FXML
	public void handleSubjectThree() {
		String[] alternatives = new String[5];
		alternatives[0] = questionThree.getQuestion();
		alternatives[1] = questionThree.getAlternativeOne();
		alternatives[2] = questionThree.getAlternativeTwo();
		alternatives[3] = questionThree.getAlternativeThree();
		alternatives[4] = questionThree.getAlternativeFour();
		
		mainApp.setAlternatives(alternatives);
		mainApp.setCorrectAnswer(questionThree.getCorrectAnswer());
		mainApp.removeQuestionFromList(randomNumberThree);
		mainApp.showQuestion();
	}

	@FXML
	public void handleSubjectFour() {
		String[] alternatives = new String[5];
		alternatives[0] = questionFour.getQuestion();
		alternatives[1] = questionFour.getAlternativeOne();
		alternatives[2] = questionFour.getAlternativeTwo();
		alternatives[3] = questionFour.getAlternativeThree();
		alternatives[4] = questionFour.getAlternativeFour();
		
		mainApp.setAlternatives(alternatives);
		mainApp.setCorrectAnswer(questionFour.getCorrectAnswer());
		mainApp.removeQuestionFromList(randomNumberFour);
		mainApp.showQuestion();
	}

	@FXML
	public void handleQuitGame() {
		if (settings.isSinglePlayer()) {
			player1.clearPlayer();
			mainApp.showSinglePlayerSettings();
		} else if (settings.isMultiplayer()){
			player1.clearPlayer();
			player2.clearPlayer();
			mainApp.showMultiPlayerSettings();
		}
	}
	
	public void setSubjects() {
		this.questionList = new ArrayList<>();
		this.questionList = mainApp.getQuestions();
		getFourQuestions();
		btnSubjectOne.setText(questionOne.getSubject());
		btnSubjectTwo.setText(questionTwo.getSubject());
		btnSubjectThree.setText(questionThree.getSubject());
		btnSubjectFour.setText(questionFour.getSubject());
	}
	
	private void getFourQuestions() {
		Random random = new Random();
		randomNumberOne = random.nextInt(questionList.size());
		randomNumberTwo = random.nextInt(questionList.size());
		randomNumberThree = random.nextInt(questionList.size());
		randomNumberFour = random.nextInt(questionList.size());
		
		questionOne = questionList.get(randomNumberOne);

		questionTwo = questionList.get(randomNumberTwo);
		while (questionTwo.getSubject().equals(questionOne.getSubject())){
			randomNumberTwo = random.nextInt(questionList.size());
			questionTwo = questionList.get(randomNumberTwo);
		}

		questionThree = questionList.get(randomNumberThree);
		while(questionThree.getSubject().equals(questionTwo.getSubject()) ||
				questionThree.getSubject().equals(questionOne.getSubject())){
			randomNumberThree = random.nextInt(questionList.size());
			questionThree = questionList.get(randomNumberThree);
		}

		questionFour = questionList.get(randomNumberFour);
		while(questionFour.getSubject().equals(questionThree.getSubject()) ||
				questionFour.getSubject().equals(questionTwo.getSubject()) ||
				questionFour.getSubject().equals(questionOne.getSubject())) {
			randomNumberFour = random.nextInt(questionList.size());
			questionFour = questionList.get(randomNumberFour);
		}
	}

}
