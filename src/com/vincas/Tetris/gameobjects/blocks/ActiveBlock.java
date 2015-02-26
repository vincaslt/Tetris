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

	public void rotateRight(GameField field) {
		rotate(field, activeSheet, activeSheet < 3 ? activeSheet + 1 : activeSheet - 3);
	}

	public void rotateLeft(GameField field) {
		rotate(field, activeSheet, activeSheet > 0 ? activeSheet - 1 : activeSheet + 3);
	}

	/**
	 * Rotates the active block after checking collisions and if needed moves to fit the boundaries.
	 * @param field - game field
	 * @param from - current activeSheet id
	 * @param to - target activeSheet id
	 */
	private void rotate(GameField field, int from, int to) {
		Point realPosition = new Point(position);
		
		activeSheet = to;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (getActiveSheet()[r][c] != null) {
					if (((c + position.getX()) < 0 && !translate(field, 1, 0) && !translate(field, 2, 0)) ||
						((r + position.getY()) >= field.getHeight() && !translate(field, 0, -1) && !translate(field, 0, -2)) ||
						((r + position.getY()) < 0 && !translate(field, 0, 1) && !translate(field, 0, 2)) ||
						((c + position.getX()) >= field.getWidth() && !translate(field, -1, 0) && !translate(field, -2, 0)) ||
						(field.getSquares()[position.getY() + r][position.getX() + c] != null && !translate(field, -1, 0) &&
							!translate(field, -2, 0) && !translate(field, 1, 0) && !translate(field, 2, 0))) {
						position = realPosition;
						activeSheet = from;
						return;
					}

				}
			}
		}
	}

	public Square[][] getActiveSheet() {
		return block.getSheet(activeSheet);
	}

	public Point getPosition() {
		return position;
	}

	public boolean translate(GameField field, int x, int y) {
		if (!testCollision(field, x, y)) {
			position.translate(x, y);
			return true;
		}
		return false;
	}

	/**
	 * Tests for collisions with walls and other blocks.
	 * @param field - game field
	 * @param x - position's x to test collision at
	 * @param y - position's y to test collision at
	 * @return true if collision exists
	 */
	public boolean testCollision(GameField field, int x, int y) {
		Point newPosition = new Point(this.position);
		newPosition.translate(x, y);

		int row, col;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				row = r + newPosition.getY();
				col = c + newPosition.getX();
				if ((row >= 0 && row < field.getHeight() &&
					(col < 0 || col >= field.getWidth() || field.getSquares()[row][col] != null) ||
					row >= field.getHeight()) &&
					getActiveSheet()[r][c] != null) {
					return true;
				}
			}
		}
		return false;
	}
}
