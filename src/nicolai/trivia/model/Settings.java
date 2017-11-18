package nicolai.trivia.model;

public class Settings {
	private int amountQuestions;
	private int timeLimit;
	private boolean multiplayer;
	private boolean singlePlayer;
	private int playerTurn;
	
	public Settings() {
		playerTurn = 1;
	}
	
	public int getAmountQuestions() {
		return amountQuestions;
	}

	public void setAmountQuestions(int amountQuestions) {
		this.amountQuestions = amountQuestions;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
		System.out.println("Player turn = " + playerTurn);
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void clearSettings() {
		setAmountQuestions(0);
		setTimeLimit(0);
		setSinglePlayer(false);
		setMultiplayer(false);
	}
	
}
