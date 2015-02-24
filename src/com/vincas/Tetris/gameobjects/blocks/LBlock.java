package com.vincas.Tetris.gameobjects.blocks;

import org.newdawn.slick.Color;

public class LBlock extends Block {
	
	public LBlock(Color color) {
		super(color);
	}

	@Override
	protected void initializeBlock() {
		sheets[0][0][2] = sheets[0][1][0] = sheets[0][1][1] = sheets[0][1][2] = new Square(getColor());
		sheets[1][0][1] = sheets[1][1][1] = sheets[1][2][1] = sheets[1][2][2] = new Square(getColor());
		sheets[2][1][0] = sheets[2][1][1] = sheets[2][1][2] = sheets[2][2][0] = new Square(getColor());
		sheets[3][0][0] = sheets[3][0][1] = sheets[3][1][1] = sheets[3][2][1] = new Square(getColor());
	}
}
