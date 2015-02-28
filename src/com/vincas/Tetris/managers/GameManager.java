package com.vincas.Tetris.managers;

import com.vincas.Tetris.Tetris;
import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.gameobjects.blocks.Block;
import com.vincas.Tetris.gameobjects.blocks.IBlock;
import com.vincas.Tetris.gameobjects.blocks.JBlock;
import com.vincas.Tetris.gameobjects.blocks.LBlock;
import com.vincas.Tetris.gameobjects.blocks.OBlock;
import com.vincas.Tetris.gameobjects.blocks.SBlock;
import com.vincas.Tetris.gameobjects.blocks.TBlock;
import com.vincas.Tetris.gameobjects.blocks.ZBlock;
import com.vincas.Tetris.utils.GameOverException;
import com.vincas.Tetris.utils.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Random;

public class GameManager {
	private static final int MAX_LEVEL = 9;
	
	private Timer timer;
	private int level; //TODO increasing difficulty
	private GameField field;
	private StateBasedGame game;
	private boolean isActive;
	private ScoreManager scoreManager;
	
	private int totalRowsRemoved = 0;

	public GameManager(StateBasedGame game, GameField field) {
		this(game, 1, field);
	}

	public GameManager(StateBasedGame game, int level, GameField field) {
		timer = new Timer(1000 - level * 100);
		this.level = level;
		this.field = field;
		this.game = game;
		isActive = true;
		scoreManager = new ScoreManager();
	}
	
	public void speedUp() {
		triggerGravity();
		timer.restartTimer(100);
	}
	
	public void normalizeSpeed() {
		timer.restartTimer(1000 - level * 100);
	}

	public void update(int delta) {
		if (!isActive) return;
		
		timer.update(delta);
		if (timer.isTimeComplete()) {
			triggerGravity();
			timer.resetTime();
		}
	}

	public boolean triggerGravity() {
		try {
			if(field.getActiveBlock().translate(field, 0, 1)) {
				return true;
			}
			else {
				spawnNewBlock();
				checkRows();
				return false;
			}
		} catch(GameOverException e) {
			handleGameOver();
			return false;
		}
	}
	
	private void checkRows() {
		boolean trigger;
		int rowsRemoved = 0;
		for (int r = 0; r < field.getHeight(); r++) {
			trigger = true;
			for (int c = 0; c < field.getWidth(); c++) {
				if (field.getSquares()[r][c] == null) {
					trigger = false;
					break;
				}
			}
			if (trigger) {
				removeRow(r);
				rowsRemoved++;
			}
		}
		totalRowsRemoved += rowsRemoved;
		scoreManager.addScore(rowsRemoved, level);
		tryLevelUp();
	}
	
	private void tryLevelUp() {
		if ((totalRowsRemoved >= 10 && level == 1) ||
			(totalRowsRemoved >= 25 && level == 2) ||
			(totalRowsRemoved >= 45 && level == 3) ||
			(totalRowsRemoved >= 70 && level == 4) ||
			(totalRowsRemoved >= 100 && level == 5) ||
			(totalRowsRemoved >= 135 && level == 6) ||
			(totalRowsRemoved >= 175 && level == 7) ||
			(totalRowsRemoved >= 210 && level == 8)) {
			level += 1;
			normalizeSpeed();
		}
	}

	private void removeRow(int row) {
		if (row < 0) return;
		
		for (int c = 0; c < field.getWidth(); c++) {
			field.getSquares()[row][c] = null;
		}
		pullDown(row);
	}
	
	private void pullDown(int row) {
		if (row - 1 < 0) return;
		
		for (int c = 0; c < field.getWidth(); c++) {
			if (field.getSquares()[row - 1][c] != null) {
				field.getSquares()[row][c] = field.getSquares()[row - 1][c];
			}
		}

		removeRow(row - 1);
	}

	public void spawnNewBlock() {
		int blockType = (new Random()).nextInt(7);
		Block block;
		switch (blockType) {
			case 0:
				block = new ZBlock(Color.red);
				break;
			case 1:
				block = new LBlock(Color.orange);
				break;
			case 2:
				block = new IBlock(Color.cyan);
				break;
			case 3:
				block = new TBlock(Color.magenta);
				break;
			case 4:
				block = new SBlock(Color.green);
				break;
			case 5:
				block = new OBlock(Color.yellow);
				break;
			default:
				block = new JBlock(Color.blue);
				break;
		}
		field.addActiveBlock(3, -2, block);
		timer.resetTime();
		triggerGravity();
		// Some hardcode, could be eliminated by checking each block for emptiness at the top..
		if (!(block instanceof IBlock)) 
			triggerGravity();
	}

	private void handleGameOver() {
		game.enterState(Tetris.STATE_GAMEOVER, new FadeOutTransition(), new FadeInTransition());
		isActive = false;
	}
	
	public ScoreManager getScores() {
		return scoreManager;
	}
}
