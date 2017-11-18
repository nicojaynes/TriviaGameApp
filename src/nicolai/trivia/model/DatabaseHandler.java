package nicolai.trivia.model;

import nicolai.trivia.alert.AlertMaker;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Database Handler implemented with singleton model so as to only have
 * one instance of the handler
 */
public class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement statement = null;

    private ArrayList<Question> questions;

    private DatabaseHandler() {
        createConnection();
        setupQuestionsTable();
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            AlertMaker.showErrorMessage("Error", "Unable to load database");
            System.exit(0);
        }
    }

    private void setupQuestionsTable() {
        String TABLE_NAME = "QUESTIONS";

        try {
            statement = conn.createStatement();
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet tables = dmd.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists!");
            } else {
                statement.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "question varchar(200) primary key, \n"
                        + "alternative_1 varchar(200) not null, \n"
                        + "alternative_2 varchar(200) not null, \n"
                        + "alternative_3 varchar(200) not null, \n"
                        + "alternative_4 varchar(200) not null, \n"
                        + "subject varchar(30) not null, \n"
                        + "answer int"
                        + ")");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "---setupDatabase");
        }
    }

    private ResultSet execQuery(String query) {
        ResultSet result;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Exception at execQuery:DatabaseHandler " + e.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execAction(String query) {
        try {
            statement = conn.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<Question> getQuestions() {
        questions = new ArrayList<>();

        //Loads a file with some questions to use in play
        //Your own questions can be added manually to the database
        try {
            FileInputStream fis = new FileInputStream("somequestions.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            questions = (ArrayList<Question>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String sql = "SELECT QUESTION, ALTERNATIVE_1, ALTERNATIVE_2 , "
                    + "ALTERNATIVE_3, ALTERNATIVE_4, SUBJECT, ANSWER FROM QUESTIONS";
            ResultSet rs = execQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Question question = new Question();
                question.setQuestion(rs.getString("QUESTION"));
                question.setAlternativeOne(rs.getString("ALTERNATIVE_1"));
                question.setAlternativeTwo(rs.getString("ALTERNATIVE_2"));
                question.setAlternativeThree(rs.getString("ALTERNATIVE_3"));
                question.setAlternativeFour(rs.getString("ALTERNATIVE_4"));
                question.setSubject(rs.getString("SUBJECT"));
                question.setCorrectAnswer(rs.getInt("ANSWER"));

                questions.add(question);
            }
            rs.close();
            System.out.println("The questions are fetched");

        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        printListSize();
        return questions;
    }

    private void printListSize() {
        System.out.println("Current amount of questions in list: " + questions.size());
    }

}
