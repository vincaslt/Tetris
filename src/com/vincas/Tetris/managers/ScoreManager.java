package com.vincas.Tetris.managers;

public class ScoreManager {
	private int score;
	
	public void addScore(int rows, int level) {
		int score = 0;
		switch (rows) {
			case 4:
				score += 400;
			case 3:
				score += 300;
			case 2:
				score += 200;
			case 1:
				score += 100;
			default:
				score = (int)(score * (0.9f + level / 10f));
		}
		this.score += score;
	}

	public int getScore() {
		return score;
	}
}
