package nicolai.trivia.view;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import nicolai.trivia.MainApp;

/**
 * Controller for the question interface
 */
public class QuestionController {
    private MainApp mainApp;
    private int countDown;

    @FXML
    private Label lblQuestionNumber;

    @FXML
    private TextArea taQuestion;

    @FXML
    private Label lblTimeLeft;

    @FXML
    private JFXButton btnAlternativeOne;

    @FXML
    private JFXButton btnAlternativeTwo;

    @FXML
    private JFXButton btnAlternativeThree;

    @FXML
    private JFXButton btnAlternativeFour;

    @FXML
    private JFXButton okButton;

    @FXML
    private Label answerLabel;

    private int correctChoice;
    private Timeline timeline;

    public QuestionController() {

    }

    public void setAlternativeOne(String alternativeOne) {
        btnAlternativeOne.setText(alternativeOne);
    }

    public void setAlternativeTwo(String alternativeTwo) {
        btnAlternativeTwo.setText(alternativeTwo);
    }

    public void setAlternativeThree(String alternativeThree) {
        btnAlternativeThree.setText(alternativeThree);
    }

    public void setAlternativeFour(String alternativeFour) {
        btnAlternativeFour.setText(alternativeFour);
    }

    public void setQuestion(String question) {
        taQuestion.setEditable(false);
        taQuestion.setWrapText(true);
        taQuestion.setText(question);
    }

    public void setQuestionNumber(int questionNumber) {
        lblQuestionNumber.setText(String.valueOf(questionNumber));
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    private void disableButtons() {
        btnAlternativeOne.setDisable(true);
        btnAlternativeTwo.setDisable(true);
        btnAlternativeThree.setDisable(true);
        btnAlternativeFour.setDisable(true);
    }

    @FXML
    public void handleAlternativeOne() {
        timeline.stop();        //Stop the timer

        if (mainApp.isSinglePlayer()) {
            if (1 == correctChoice) {
                mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                btnAlternativeOne.setStyle("-fx-background-color: #76FF03;");
                answerLabel.setTextFill(Color.web("#76FF03"));  //Set the color to green indicating correct answer
                answerLabel.setText("Correct answer!");
            } else {
                btnAlternativeOne.setStyle("-fx-background-color: #FF3D00;");
                answerLabel.setTextFill(Color.web("#FF3D00")); //Set the color to red indicating wrong answer
                answerLabel.setText("Wrong answer!");
            }
            mainApp.incrementPlayerOneQuestionNumber();
            disableButtons();
            okButton.setVisible(true);

        } else if (mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTurn() == 1) {
                if (1 == correctChoice) {
                    mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                    btnAlternativeOne.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeOne.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerOneQuestionNumber();
                mainApp.setPlayerTurn(2);
                disableButtons();
                okButton.setVisible(true);
            } else if (mainApp.getPlayerTurn() == 2) {
                if (1 == correctChoice) {
                    mainApp.setPlayerTwoPoints(mainApp.getPlayerTwoPoints() + 1);
                    btnAlternativeOne.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeOne.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerTwoQuestionNumber();
                mainApp.setPlayerTurn(1);
                disableButtons();
                okButton.setVisible(true);
            }
        }
    }

    @FXML
    public void handleAlternativeTwo() {
        timeline.stop();
        if (mainApp.isSinglePlayer()) {
            if (2 == correctChoice) {
                mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                btnAlternativeTwo.setStyle("-fx-background-color: #76FF03;");
                answerLabel.setTextFill(Color.web("#76FF03"));
                answerLabel.setText("Correct answer!");
            } else {
                btnAlternativeTwo.setStyle("-fx-background-color: #FF3D00;");
                answerLabel.setTextFill(Color.web("#FF3D00"));
                answerLabel.setText("Wrong answer!");
            }
            mainApp.incrementPlayerOneQuestionNumber();
            disableButtons();
            okButton.setVisible(true);

        } else if (mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTurn() == 1) {
                if (2 == correctChoice) {
                    mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                    btnAlternativeTwo.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeTwo.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerOneQuestionNumber();
                mainApp.setPlayerTurn(2);
                disableButtons();
                okButton.setVisible(true);
            } else if (mainApp.getPlayerTurn() == 2) {
                if (2 == correctChoice) {
                    mainApp.setPlayerTwoPoints(mainApp.getPlayerTwoPoints() + 1);
                    btnAlternativeTwo.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeTwo.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerTwoQuestionNumber();
                mainApp.setPlayerTurn(1);
                disableButtons();
                okButton.setVisible(true);
            }
        }
    }

    @FXML
    public void handleAlternativeThree() {
        timeline.stop();
        if (mainApp.isSinglePlayer()) {
            if (3 == correctChoice) {
                mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                btnAlternativeThree.setStyle("-fx-background-color: #76FF03;");
                answerLabel.setTextFill(Color.web("#76FF03"));
                answerLabel.setText("Correct answer!");
            } else {
                btnAlternativeThree.setStyle("-fx-background-color: #FF3D00;");
                answerLabel.setTextFill(Color.web("#FF3D00"));
                answerLabel.setText("Wrong answer!");
            }
            mainApp.incrementPlayerOneQuestionNumber();
            disableButtons();
            okButton.setVisible(true);

        } else if (mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTurn() == 1) {
                if (3 == correctChoice) {
                    mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                    btnAlternativeThree.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeThree.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerOneQuestionNumber();
                mainApp.setPlayerTurn(2);
                disableButtons();
                okButton.setVisible(true);
            } else if (mainApp.getPlayerTurn() == 2) {
                if (3 == correctChoice) {
                    mainApp.setPlayerTwoPoints(mainApp.getPlayerTwoPoints() + 1);
                    btnAlternativeThree.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeThree.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerTwoQuestionNumber();
                mainApp.setPlayerTurn(1);
                disableButtons();
                okButton.setVisible(true);
            }
        }
    }

    @FXML
    public void handleAlternativeFour() {
        timeline.stop();
        if (mainApp.isSinglePlayer()) {
            if (4 == correctChoice) {
                mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                btnAlternativeFour.setStyle("-fx-background-color: #76FF03;");
                answerLabel.setTextFill(Color.web("#76FF03"));
                answerLabel.setText("Correct answer!");
            } else {
                btnAlternativeFour.setStyle("-fx-background-color: #FF3D00;");
                answerLabel.setTextFill(Color.web("#FF3D00"));
                answerLabel.setText("Wrong answer!");
            }
            mainApp.incrementPlayerOneQuestionNumber();
            disableButtons();
            okButton.setVisible(true);

        } else if (mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTurn() == 1) {
                if (4 == correctChoice) {
                    mainApp.setPlayerOnePoints(mainApp.getPlayerOnePoints() + 1);
                    btnAlternativeFour.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeFour.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerOneQuestionNumber();
                mainApp.setPlayerTurn(2);
                disableButtons();
                okButton.setVisible(true);
            } else if (mainApp.getPlayerTurn() == 2) {
                if (4 == correctChoice) {
                    mainApp.setPlayerTwoPoints(mainApp.getPlayerTwoPoints() + 1);
                    btnAlternativeFour.setStyle("-fx-background-color: #76FF03;");
                    answerLabel.setTextFill(Color.web("#76FF03"));
                    answerLabel.setText("Correct answer!");
                } else {
                    btnAlternativeFour.setStyle("-fx-background-color: #FF3D00;");
                    answerLabel.setTextFill(Color.web("#FF3D00"));
                    answerLabel.setText("Wrong answer!");
                }
                mainApp.incrementPlayerTwoQuestionNumber();
                mainApp.setPlayerTurn(1);
                disableButtons();
                okButton.setVisible(true);
            }
        }
    }

    @FXML
    private void handleOK() {
        //Show end of game screen if all questions are answered
        if (mainApp.isSinglePlayer()) {
            if (mainApp.getPlayerOneQuestionNumber() == mainApp.getAmountQuestions()) {
                mainApp.showEndOfGameSinglePlayer();
            } else {
                mainApp.showSubject();
            }
        } else if(mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTwoQuestionNumber() == mainApp.getAmountQuestions()) {
                mainApp.showEndOfGameMultiPlayer();
            } else {
                mainApp.showSubject();
            }
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        okButton.setVisible(false);
    }

    /**
     * Method that starts the countdown of seconds to
     * answer the current question
     */
    public void countDown() {
        countDown = mainApp.getTimeLimit();
        lblTimeLeft.setText("Time Left: " + String.valueOf(countDown)); // update timerLabel
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            countDown--;
            lblTimeLeft.setText("Time Left: " + String.valueOf(countDown)); // update timerLabel
            if (countDown == 0) {
                timeline.stop();
                timeUp();
            }
        }));
        timeline.playFromStart();
    }

    /**
     * Method that gets called when the countdown has
     * reached 0
     */
    private void timeUp() {
        disableButtons();

        answerLabel.setTextFill(Color.web("#FF3D00"));
        answerLabel.setText("Time is up!");
        okButton.setVisible(true);

        if (mainApp.isSinglePlayer()) {
            mainApp.incrementPlayerOneQuestionNumber();
        } else if (mainApp.isMultiPlayer()) {
            if (mainApp.getPlayerTurn() == 1) {
                mainApp.incrementPlayerOneQuestionNumber();
                mainApp.setPlayerTurn(2);
            } else if (mainApp.getPlayerTurn() == 2) {
                mainApp.incrementPlayerTwoQuestionNumber();
                mainApp.setPlayerTurn(1);
            }
        }
    }
}

