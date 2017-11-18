package nicolai.trivia.model;

/**
 * Class that represents a player in the game
 */
public class Player {
	private String name;
	private int points;
	private int questionNumber;
	private int amountQuestions;
	private int choice;
	
	public Player() {
		name = "";
		points = 0;
		questionNumber = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public void incrementQuestionNumber() {
		questionNumber++;
	}

	public int getAmountQuestions() {
		return amountQuestions;
	}

	public void setAmountQuestions(int amountQuestions) {
		this.amountQuestions = amountQuestions;
	}
	
	public void clearPlayer() {
		setName("");
		setPoints(0);
		setQuestionNumber(0);
		setAmountQuestions(0);
	}

}
