package com.vincas.Tetris.gameobjects;

import com.vincas.Tetris.gameobjects.blocks.ActiveBlock;
import com.vincas.Tetris.gameobjects.blocks.Block;
import com.vincas.Tetris.gameobjects.blocks.Square;
import com.vincas.Tetris.utils.GameOverException;

import org.lwjgl.util.Point;

public class GameField {
	private int width;
	private int height;
	private int x;
	private int y;

	private ActiveBlock<Block> activeBlock;
	private Square[][] squares;

	public GameField(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		activeBlock = null;
		squares = new Square[height][width];
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ActiveBlock<Block> getActiveBlock() {
		return activeBlock;
	}

	public Square[][] getSquares() {
		return squares;
	}

	/**
	 * Maps an active block on the field.
	 */
	public void mapActiveBlock() throws GameOverException {
		if (activeBlock == null) return;

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (activeBlock.getActiveSheet()[r][c] != null) {
					if ((activeBlock.getPosition().getY() + r) < 0)
						throw new GameOverException();
					else
						squares[activeBlock.getPosition().getY() + r][activeBlock.getPosition().getX() + c]
							= activeBlock.getActiveSheet()[r][c];
				}
			}
		}
	}

	/**
	 * Adds a new block to a field. The new block is set to active,
	 * and last active block is mapped to the field.
	 *
	 * @param block - block to become an active block
	 */
	public void addActiveBlock(Point position, Block block) throws GameOverException {
		if (activeBlock != null)
			mapActiveBlock();
		activeBlock = new ActiveBlock<Block>(position, block);
	}

	public void addActiveBlock(int x, int y, Block block) throws GameOverException {
		addActiveBlock(new Point(x, y), block);
	}
}
