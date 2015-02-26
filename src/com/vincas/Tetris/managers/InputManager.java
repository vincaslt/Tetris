package com.vincas.Tetris.managers;

import com.vincas.Tetris.gameobjects.GameField;

import org.newdawn.slick.Input;

public class InputManager {
	GameField field;
	GameManager gameManager;
	
	public InputManager(GameField field, GameManager gameManager) {
		this.field = field;
		this.gameManager = gameManager;
	}

	public void keyPressed(int key) {
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
			case Input.KEY_SPACE:
				while (gameManager.triggerGravity());
				break;
			case Input.KEY_DOWN:
				gameManager.speedUp();
				break;
		}
	}
	
	public void keyReleased(int key) {
		if (key == Input.KEY_DOWN)
			gameManager.normalizeSpeed();
	}
	
	private void handleMoveLeft() {
		field.getActiveBlock().translate(field, -1, 0);
	}
	
	private void handleMoveRight() {
		field.getActiveBlock().translate(field, 1, 0);
	}
	
	private void handleRotateLeft() {
		field.getActiveBlock().rotateLeft(field);
	}

	private void handleRotateRight() {
		field.getActiveBlock().rotateRight(field);
	}
}
