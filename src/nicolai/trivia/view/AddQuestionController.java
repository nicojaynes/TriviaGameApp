package nicolai.trivia.view;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nicolai.trivia.model.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField question;

    @FXML
    private JFXTextField alternative1;

    @FXML
    private JFXTextField alternative2;

    @FXML
    private JFXTextField alternative3;

    @FXML
    private JFXTextField alternative4;

    @FXML
    private JFXTextField subject;

    @FXML
    private JFXTextField answer;

    private DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = DatabaseHandler.getInstance();
    }

    @FXML
    private void handleAdd() {
        String questionStr = question.getText();
        String alternative1Str = alternative1.getText();
        String alternative2Str = alternative2.getText();
        String alternative3Str = alternative3.getText();
        String alternative4Str = alternative4.getText();
        String subjectStr = subject.getText();
        String answerStr = answer.getText();

        //If any field is empty show alert and return from method
        if(questionStr.isEmpty() || alternative1Str.isEmpty() || alternative2Str.isEmpty() ||
                alternative3Str.isEmpty() || alternative4Str.isEmpty() ||
                subjectStr.isEmpty() || answerStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter text in all fields");
            alert.showAndWait();
            return;
        }

        //Create the query to insert values into question table
        String query = "INSERT INTO QUESTIONS VALUES (" +
                "'" + questionStr + "'," +
                "'" + alternative1Str + "'," +
                "'" + alternative2Str + "'," +
                "'" + alternative3Str + "'," +
                "'" + alternative4Str + "'," +
                "'" + subjectStr + "'," +
                 + Integer.parseInt(answerStr) +
                ")";

        if (databaseHandler.execAction(query)) {
            //SUCCESS
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successfully added question");
            alert.showAndWait();
        } else {
            //ERROR
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
            return;
        }

        //Clear fields
        question.setText("");
        alternative1.setText("");
        alternative2.setText("");
        alternative3.setText("");
        alternative4.setText("");
        subject.setText("");
        answer.setText("");
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage)rootPane.getScene().getWindow();
        stage.close();
    }
}
