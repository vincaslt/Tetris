package com.vincas.Tetris.gameobjects.blocks;

import org.newdawn.slick.Color;

public class IBlock extends Block {
	
	public IBlock(Color color) {
		super(color);
	}

	@Override
	protected void initializeBlock() {
		sheets[0][1][0] = sheets[0][1][1] = sheets[0][1][2] = sheets[0][1][3] = new Square(getColor());
		sheets[1][0][2] = sheets[1][1][2] = sheets[1][2][2] = sheets[1][3][2] = new Square(getColor());
		sheets[2][2][0] = sheets[2][2][1] = sheets[2][2][2] = sheets[2][2][3] = new Square(getColor());
		sheets[3][0][1] = sheets[3][1][1] = sheets[3][2][1] = sheets[3][3][1] = new Square(getColor());
	}
}
