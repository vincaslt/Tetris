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
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;

public class GamePlayState extends BasicGameState {
	GameField gameField;
	InputManager inputManager;
	GraphicsManager graphicsManager;
	GameManager gameManager;
	
	TrueTypeFont textFont;

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
		textFont = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 14), true);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		graphicsManager.render(graphics);
		graphicsManager.drawScore(graphics, gameManager.getScores().getScore());
		
		//Temp:
		graphics.setColor(Color.white);
		graphics.setFont(textFont);
		graphics.drawString("Right: Right arrow", 50, 50);
		graphics.drawString("Left: Left arrow", 50, 70);
		graphics.drawString("Speed up: Down arrow", 50, 90);
		graphics.drawString("Rotate Right: . or Up arrow", 50, 110);
		graphics.drawString("Rotate Left: ,", 50, 130);
		graphics.drawString("Drop block: Space", 50, 150);
		
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
