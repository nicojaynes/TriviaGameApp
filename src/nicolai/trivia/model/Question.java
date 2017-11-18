package nicolai.trivia.model;

import java.io.Serializable;

/**
 * Class that represents a question in the game
 */
public class Question implements Serializable {
	private String question;
	private String alternativeOne;
	private String alternativeTwo;
	private String alternativeThree;
	private String alternativeFour;
	private String subject;
	private int correctAnswer;

	public Question() {
		
	}

	public String getAlternativeOne() {
		return alternativeOne;
	}

	public void setAlternativeOne(String alternativeOne) {
		this.alternativeOne = alternativeOne;
	}

	public String getAlternativeTwo() {
		return alternativeTwo;
	}

	public void setAlternativeTwo(String alternativeTwo) {
		this.alternativeTwo = alternativeTwo;
	}

	public String getAlternativeThree() {
		return alternativeThree;
	}

	public void setAlternativeThree(String alternativeThree) {
		this.alternativeThree = alternativeThree;
	}

	public String getAlternativeFour() {
		return alternativeFour;
	}

	public void setAlternativeFour(String alternativeFour) {
		this.alternativeFour = alternativeFour;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setCorrectAnswer(int correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return subject;
	}
}
