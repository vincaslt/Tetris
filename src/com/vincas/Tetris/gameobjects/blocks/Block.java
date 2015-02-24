package com.vincas.Tetris.gameobjects.blocks;

import org.newdawn.slick.Color;

public abstract class Block {
	// The sheet array blocks are laid out on:
	protected Square[][][] sheets; // rotation x dimension x dimension
	
	private Color color;
	private int activeSheet = 0;

	public Block(Color color) {
		sheets = new Square[4][4][4]; // 4x4 is standard
		this.color = color;
		initializeBlock();
	}

	protected abstract void initializeBlock();
	
	public void rotateRight() {
		activeSheet += activeSheet < 3 ? 1 : -3;
	}
	
	public void rotateLeft() {
		activeSheet -= activeSheet > 0 ? 1 : -3;
	}
	
	public Square[][] getActiveSheet() {
		return sheets[activeSheet];
	}
	
	public Color getColor() {
		return color;
	}
}
