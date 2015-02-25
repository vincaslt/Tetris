package com.vincas.Tetris.states;

import com.vincas.Tetris.Tetris;
import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.managers.GameManager;
import com.vincas.Tetris.managers.GraphicsManager;
import com.vincas.Tetris.managers.InputManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	GameField gameField;
	InputManager inputManager;
	GraphicsManager graphicsManager;
	GameManager gameManager;

	@Override
	public int getID() {
		return Tetris.STATE_GAMEPLAY;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		gameField = new GameField(10, 20, 400, 10);
		
		gameManager = new GameManager(stateBasedGame, gameField);
		graphicsManager = new GraphicsManager(gameField);
		inputManager = new InputManager(gameField, gameManager);
		
		gameManager.spawnNewBlock();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		graphicsManager.render(graphics);
		
		//Temp:
		graphics.setColor(Color.white);
		graphics.drawString("Left: <-", 50, 50);
		graphics.drawString("Right: ->", 50, 70);
		graphics.drawString("Rotate Left: ,", 50, 90);
		graphics.drawString("Rotate Right: .", 50, 110);

		TextureImpl.bindNone();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		gameManager.update(delta);
	}

	@Override
	public void keyPressed(int key, char c) {
		inputManager.keyPressed(key);
	}

	@Override
	public void keyReleased(int key, char c) {
		inputManager.keyReleased(key);
	}
}
