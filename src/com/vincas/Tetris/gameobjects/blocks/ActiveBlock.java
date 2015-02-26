package com.vincas.Tetris.gameobjects.blocks;

import com.vincas.Tetris.gameobjects.GameField;

import org.lwjgl.util.Point;

public class ActiveBlock<T extends Block> {
	private int activeSheet = 0;
	private Point position; // Position on the game field
	private T block;

	public ActiveBlock(Point position, T block) {
		this.position = position;
		this.block = block;
	}

	public void rotateRight() {
		// Test rotation
		activeSheet += activeSheet < 3 ? 1 : -3;
	}

	public void rotateLeft() {
		// Test rotation
		activeSheet -= activeSheet > 0 ? 1 : -3;
	}

	public Square[][] getActiveSheet() {
		return block.getSheet(activeSheet);
	}

	public Point getPosition() {
		return position;
	}

	public void translate(GameField field, int x, int y) {
		if (testCollision(field, x, y))
			position.translate(x, y);
	}

	private boolean testCollision(GameField field, int x, int y) {
		Point position = new Point(this.position);
		position.translate(x, y);

		int row, col;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				row = r + position.getY();
				col = c + position.getX();
				if (row > 0 && row < field.getHeight() &&
					(col < 0 || col >= field.getWidth() || field.getSquares()[row][col] != null) &&
					getActiveSheet()[r][c] != null)
					return false;
			}
		}
		return true;
	}
}
