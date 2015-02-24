package com.vincas.Tetris.managers;

import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.gameobjects.blocks.Block;
import com.vincas.Tetris.gameobjects.blocks.IBlock;
import com.vincas.Tetris.gameobjects.blocks.JBlock;
import com.vincas.Tetris.gameobjects.blocks.LBlock;
import com.vincas.Tetris.gameobjects.blocks.OBlock;
import com.vincas.Tetris.gameobjects.blocks.SBlock;
import com.vincas.Tetris.gameobjects.blocks.TBlock;
import com.vincas.Tetris.gameobjects.blocks.ZBlock;
import com.vincas.Tetris.utils.Timer;

import org.newdawn.slick.Color;

import java.util.Random;

public class GameManager {
	private Timer timer;
	private int level; //TODO increasing difficulty
	private GameField field;

	public GameManager(GameField field) {
		this(1, field);
	}
	
	public GameManager(int level, GameField field) {
		timer = new Timer(800 - level * 100);
		this.level = level;
		this.field = field;
	}
	
	public void update(int delta) {
		timer.update(delta);
		if (timer.isTimeComplete()) {
			field.getActivePosition().translate(0, 1);
			timer.resetTime();
		}
	}
	
	public void spawnNewBlock() {
		int blockType = (new Random()).nextInt(7);
		Block block;
		switch (blockType) {
			case 0:
				block =  new ZBlock(Color.red);
				break;
			case 1:
				block =  new LBlock(Color.orange);
				break;
			case 2:
				block =  new IBlock(Color.cyan);
				break;
			case 3:
				block =  new TBlock(Color.magenta);
				break;
			case 4:
				block =  new SBlock(Color.green);
				break;
			case 5:
				block =  new OBlock(Color.yellow);
				break;
			default:
				block =  new JBlock(Color.blue);
				break;
		}
		spawnNewBlock(3, 0, block);
	}
	
	public void spawnNewBlock(int x, int y, Block block) {
		field.addActiveBlock(x, y, block);
	}
}
