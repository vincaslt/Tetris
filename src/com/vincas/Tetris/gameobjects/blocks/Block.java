package com.vincas.Tetris.gameobjects.blocks;

import org.newdawn.slick.Color;

public abstract class Block {
	// The sheet array blocks are laid out on:
	protected Square[][][] sheets; // rotation x dimension x dimension
	private Color color;

	public Block(Color color) {
		sheets = new Square[4][4][4]; // 4x4 is standard
		this.color = color;
		initializeBlock();
	}

	protected abstract void initializeBlock();
	
	public Color getColor() {
		return color;
	}
	
	public Square[][] getSheet(int index) {
		return sheets[index];
	}
}
