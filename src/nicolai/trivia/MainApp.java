package nicolai.trivia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nicolai.trivia.model.DatabaseHandler;
import nicolai.trivia.model.Player;
import nicolai.trivia.model.Question;
import nicolai.trivia.model.Settings;
import nicolai.trivia.view.FinishedGameMultiPlayerController;
import nicolai.trivia.view.FinishedGameSinglePlayerController;
import nicolai.trivia.view.MainMenuController;
import nicolai.trivia.view.MultiplayerSettingsController;
import nicolai.trivia.view.QuestionController;
import nicolai.trivia.view.RootLayoutController;
import nicolai.trivia.view.SinglePlayerSettingsController;
import nicolai.trivia.view.SubjectController;

/**
 * Class responsible for running and loading up different interfaces of the program.
 * Also responsible for the main logic of the game
 */
public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Player player1;
	private Player player2;
	private Settings settings;
	private ArrayList<Question> questionList;
	private String[] alternatives;
	private int correctChoice;
	private DatabaseHandler databaseHandler;

	public MainApp() throws IOException {
		settings = new Settings();
		player1 = new Player();
		player2 = new Player();
		databaseHandler = DatabaseHandler.getInstance();
		fetchQuestions();
	}

	public Settings getSettings() {
		return settings;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Trivia Game");

		initRootLayout();

		showMainMenu();
	}

	private void initRootLayout() {
		try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class
	                .getResource("view/RootLayout.fxml"));
	        rootLayout = loader.load();

	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);

	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	public void showMainMenu() {
		try {
            // Load main menu
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
            AnchorPane mainMenu = loader.load();

            // Set main menu into the center of root layout.
            rootLayout.setCenter(mainMenu);

            // Give the controller access to the main app.
            MainMenuController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void showSinglePlayerSettings() {
		try{
			// Load singleplayer settings
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SinglePlayerSettings.fxml"));
			AnchorPane singlePlayer = loader.load();

			//Set singleplayer settings to the center of root layout
			rootLayout.setCenter(singlePlayer);

			//Give the controller access to the main app
			SinglePlayerSettingsController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMultiPlayerSettings() {
		try{
			// Load multiplayer settings
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MultiplayerSettings.fxml"));
			AnchorPane multiPlayer = loader.load();

			//Set multiplayer settings to the center of root layout
			rootLayout.setCenter(multiPlayer);

			//Give the controller access to the main app
			MultiplayerSettingsController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSubject() {
		try{
			// Load singleplayer settings
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Subject.fxml"));
			AnchorPane subject = loader.load();

			//Set subject to the center of root layout
			rootLayout.setCenter(subject);

			//Give the controller access to the main app
			SubjectController controller = loader.getController();
			controller.setMainApp(this);
			controller.setSubjects();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showQuestion() {
		try{
			// Load singleplayer settings
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Question.fxml"));
			AnchorPane question = loader.load();

			//Set subject to the center of root layout
			rootLayout.setCenter(question);

			//Give the controller access to the main app
			QuestionController controller = loader.getController();
			controller.setMainApp(this);
			
			controller.setQuestion(alternatives[0]);
			controller.setAlternativeOne(alternatives[1]);
			controller.setAlternativeTwo(alternatives[2]);
			controller.setAlternativeThree(alternatives[3]);
			controller.setAlternativeFour(alternatives[4]);
			controller.setCorrectChoice(correctChoice);
			
			if(settings.isSinglePlayer()) {
				controller.setQuestionNumber(player1.getQuestionNumber() + 1);
			} else {
				if(getPlayerTurn() == 1) {
					controller.setQuestionNumber(player1.getQuestionNumber() + 1);
				} else {
					controller.setQuestionNumber(player2.getQuestionNumber() + 1);
				}
			}
			controller.countDown();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEndOfGameSinglePlayer() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FinishedGameSinglePlayer.fxml"));
			AnchorPane endOfGame = loader.load();

			//Set subject to the center of root layout
			rootLayout.setCenter(endOfGame);

			//Give the controller access to the main app
			FinishedGameSinglePlayerController controller = loader.getController();
			controller.setMainApp(this);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEndOfGameMultiPlayer() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FinishedGameMultiPlayer.fxml"));
			AnchorPane endOfGame = loader.load();

			//Set subject to the center of root layout
			rootLayout.setCenter(endOfGame);

			//Give the controller access to the main app
			FinishedGameMultiPlayerController controller = loader.getController();
			controller.setMainApp(this);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadWindow(String loc, String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
		}
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }

	/**
	 * Fetches the questions of the game from the database
	 */
	public void fetchQuestions() {
		questionList = new ArrayList<Question>();
		questionList = databaseHandler.getQuestions();
	}
    
    public void removeQuestionFromList(int questionToRemove) {
		questionList.remove(questionToRemove);
	}
    
    public ArrayList<Question> getQuestions() {
		System.out.println("Current amount of questions in list: " + questionList.size());
		return questionList;
	}
    
    public void setAlternatives(String[] alternatives) {
    	this.alternatives = alternatives;
    }
    
    public int getPlayerTurn() {
		return settings.getPlayerTurn();
	}
    
    public void setPlayerTurn(int playerTurn) {
    	settings.setPlayerTurn(playerTurn);
    }

	public static void main(String[] args) {
		launch(args);
	}

	public boolean isSinglePlayer() {
		return settings.isSinglePlayer();
	}
	
	public boolean isMultiPlayer() {
		return settings.isMultiplayer();
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctChoice = correctAnswer;
	}
	
	public void setPlayerOnePoints(int points) {
		player1.setPoints(points);
	}
	
	public void setPlayerTwoPoints(int points) {
		player2.setPoints(points);
	}
	
	public int getPlayerOnePoints() {
		return player1.getPoints();
	}
	
	public int getPlayerTwoPoints() {
		return player2.getPoints();
	}

	public void incrementPlayerOneQuestionNumber() {
		player1.incrementQuestionNumber();
	}
	
	public void incrementPlayerTwoQuestionNumber() {
		player2.incrementQuestionNumber();
	}
	
	public int getPlayerOneQuestionNumber() {
		return player1.getQuestionNumber();
	}
	
	public int getPlayerTwoQuestionNumber() {
		return player2.getQuestionNumber();
	}
	
	public int getAmountQuestions() {
		return settings.getAmountQuestions();
	}

	public int getTimeLimit() {
		return settings.getTimeLimit();
	}
	
	public void clearSettings() {
		settings.clearSettings();
		player1.clearPlayer();
		player2.clearPlayer();
	}
	
}
