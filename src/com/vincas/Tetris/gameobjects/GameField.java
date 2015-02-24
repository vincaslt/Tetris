package com.vincas.Tetris.gameobjects;

import com.vincas.Tetris.gameobjects.blocks.Block;
import com.vincas.Tetris.gameobjects.blocks.Square;

import org.lwjgl.util.Point;

public class GameField {
	private int width;
	private int height;
	private int x;
	private int y;
	
	private Point activePosition;
	private Block activeBlock;
	private Square[][] squares;

	public GameField(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		
		activeBlock = null;
		squares = new Square[height][width];
	}

	/**
	 * @return position of the active block
	 */
	public Point getActivePosition() {
		return activePosition;
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

	public Block getActiveBlock() {
		return activeBlock;
	}

	public Square[][] getSquares() {
		return squares;
	}

	/**
	 * Maps a block on the field at the position specified through x and y parameters.
	 * @param x
	 * @param y
	 * @param block
	 */
	public void mapBlock(int x, int y, Block block) {
		if (block == null) return;
		
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (block.getActiveSheet()[r][c] != null)
					squares[y + r][x + c] = block.getActiveSheet()[r][c];
			}
		}
	}

	/**
	 * Adds a new block to a field. The new block is set to active,
	 * and last active block is mapped to the field.
	 * 
	 * @param block 
	 * @param position
	 */
	public void addActiveBlock(Point position, Block block) {
		if (activePosition != null)
			mapBlock(activePosition.getX(), activePosition.getY(), activeBlock);
		activeBlock = block;
		activePosition = position;
	}
	
	public void addActiveBlock(int x, int y, Block block) {
		addActiveBlock(new Point(x, y), block);
	}
}
