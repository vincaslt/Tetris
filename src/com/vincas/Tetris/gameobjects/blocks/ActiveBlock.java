package com.vincas.Tetris.gameobjects.blocks;

import org.lwjgl.util.Point;

public class ActiveBlock<T extends Block>{
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
	
	public void translate(int x, int y) {
		position.translate(x, y);
	}
}
