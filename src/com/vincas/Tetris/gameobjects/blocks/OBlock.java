package com.vincas.Tetris.gameobjects.blocks;

import org.newdawn.slick.Color;

public class OBlock extends Block {
	
	public OBlock(Color color) {
		super(color);
	}

	@Override
	protected void initializeBlock() {
		sheets[0][0][0] = sheets[0][0][1] = sheets[0][1][0] = sheets[0][1][1] = new Square(getColor());
		sheets[1] = sheets[0].clone();
		sheets[2] = sheets[0].clone();
		sheets[3] = sheets[0].clone();
	}
}
