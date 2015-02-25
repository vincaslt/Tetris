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

	public GameManager(StateBasedGame game, GameField field) {
		this(game, 1, field);
	}

	public GameManager(StateBasedGame game, int level, GameField field) {
		timer = new Timer(1000 - level * 100);
		this.level = level;
		this.field = field;
		this.game = game;
		isActive = true;
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
	
	public boolean testGravity() {
		Block block = field.getActiveBlock();
		int row = field.getActivePosition().getY();
		int col = field.getActivePosition().getX();

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (block.getActiveSheet()[r][c] != null && row + r + 1 >= 0 &&
					(row + r + 1 >= field.getHeight() ||
						field.getSquares()[row + r + 1][col + c] != null)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean triggerGravity() {
		try {
			if (testGravity()) {
				field.getActivePosition().translate(0, 1);
				return true;
			} else {
				spawnNewBlock();
				return false;
			}
		} catch(GameOverException e) {
			handleGameOver();
			return false;
		}
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
		spawnNewBlock(3, -2, block);
		triggerGravity();
		triggerGravity();
	}

	public void spawnNewBlock(int x, int y, Block block) {
		field.addActiveBlock(x, y, block);
		timer.resetTime();
	}
	
	private void handleGameOver() {
		System.out.println("Game Over");
		game.enterState(Tetris.STATE_GAMEOVER, new FadeOutTransition(), new FadeInTransition());
		isActive = false;
	}
}
