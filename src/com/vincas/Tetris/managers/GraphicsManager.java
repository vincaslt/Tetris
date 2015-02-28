package com.vincas.Tetris.managers;

import com.vincas.Tetris.Tetris;
import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.gameobjects.blocks.Square;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class GraphicsManager {

	private GameField field;
	private TrueTypeFont scoreFont;

	public GraphicsManager(GameField field) {
		this.field = field;
		scoreFont = new TrueTypeFont(new Font("Courier New", Font.PLAIN, 26), true);
	}

	public void render(Graphics gfx) {
		drawField(gfx);
		drawActiveBlock(gfx);
		drawGrid(gfx);
	}
	
	public void drawScore(Graphics gfx, int score) {
		gfx.setColor(Color.yellow);
		gfx.drawRect(750, 20, 200, 32);
		gfx.setFont(scoreFont);
		gfx.drawString("" + score, 760, 22);
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
				if (square != null && (r + field.getActiveBlock().getPosition().getY()) >= 0) {
					gfx.setColor(square.getColor());
					gfx.fillRect(field.getX() + (c + field.getActiveBlock().getPosition().getX()) * Tetris.SQUARE_SIZE,
						field.getY() + (r + field.getActiveBlock().getPosition().getY()) * Tetris.SQUARE_SIZE,
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
