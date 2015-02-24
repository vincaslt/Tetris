package com.vincas.Tetris.managers;

import com.vincas.Tetris.gameobjects.GameField;

import org.newdawn.slick.Input;

public class InputManager {
	
	GameField field;
	
	public InputManager(GameField field) {
		this.field = field;
	}

	public void keyPressed(int key, char c) {
		switch (key) {
			case Input.KEY_UP:
			case Input.KEY_COMMA:
				handleRotateLeft();
				break;
			case Input.KEY_PERIOD:
				handleRotateRight();
				break;
			case Input.KEY_LEFT:
				handleMoveLeft();
				break;
			case Input.KEY_RIGHT:
				handleMoveRight();
				break;
		}
	}
	
	private void handleMoveLeft() {
		field.getActivePosition().translate(-1, 0);
	}
	
	private void handleMoveRight() {
		field.getActivePosition().translate(1, 0);
	}
	
	private void handleRotateLeft() {
		field.getActiveBlock().rotateLeft();
	}

	private void handleRotateRight() {
		field.getActiveBlock().rotateRight();
	}
}
