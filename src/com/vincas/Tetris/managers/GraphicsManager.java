package com.vincas.Tetris.managers;

import com.vincas.Tetris.Tetris;
import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.gameobjects.blocks.Square;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GraphicsManager {

	private GameField field;

	public GraphicsManager(GameField field) {
		this.field = field;
	}

	public void render(Graphics gfx) {
		drawField(gfx);
		drawActiveBlock(gfx);
		drawGrid(gfx);
	}
	
	private void drawGrid(Graphics gfx) {
		gfx.setColor(Color.darkGray);
		for (int r = 0; r < field.getHeight(); r++)
			for (int c = 0; c < field.getWidth(); c++)
				gfx.drawRect(field.getX() + c * Tetris.SQUARE_SIZE, field.getY() + r * Tetris.SQUARE_SIZE,
					Tetris.SQUARE_SIZE, Tetris.SQUARE_SIZE);
	}

	private void drawActiveBlock(Graphics gfx) {
		if (field.getActiveBlock() == null) return;
		
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				Square square = field.getActiveBlock().getActiveSheet()[r][c];
				if (square != null && (r + field.getActivePosition().getY()) >= 0) {
					gfx.setColor(square.getColor());
					gfx.fillRect(field.getX() + (c + field.getActivePosition().getX()) * Tetris.SQUARE_SIZE,
						field.getY() + (r + field.getActivePosition().getY()) * Tetris.SQUARE_SIZE,
						Tetris.SQUARE_SIZE, Tetris.SQUARE_SIZE);
				}
			}
		}
	}
	
	private void drawField(Graphics gfx) {
		for (int r = 0; r < field.getHeight(); r++) {
			for (int c = 0; c < field.getWidth(); c++) {
				Square square = field.getSquares()[r][c];
				if (square != null) {
					gfx.setColor(square.getColor());
					gfx.fillRect(field.getX() + c * Tetris.SQUARE_SIZE,
						field.getY() + r * Tetris.SQUARE_SIZE,
						Tetris.SQUARE_SIZE, Tetris.SQUARE_SIZE);
				}
			}
		}
	}
}
