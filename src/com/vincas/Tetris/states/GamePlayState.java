package com.vincas.Tetris.states;

import com.vincas.Tetris.Tetris;
import com.vincas.Tetris.gameobjects.GameField;
import com.vincas.Tetris.managers.GameManager;
import com.vincas.Tetris.managers.GraphicsManager;
import com.vincas.Tetris.managers.InputManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
		/*Block activeBlock = new JBlock(Color.blue);
		gameField.addActiveBlock(2, 0, activeBlock);
		gameField.mapBlock(3, 18, new ZBlock(Color.red));
		gameField.mapBlock(-1, 15, new LBlock(Color.orange));
		gameField.mapBlock(6, 18, new IBlock(Color.cyan));
		gameField.mapBlock(1, 15, new TBlock(Color.magenta));
		gameField.mapBlock(7, 17, new SBlock(Color.green));
		gameField.mapBlock(0, 17, new OBlock(Color.yellow));*/

		gameManager = new GameManager(gameField);
		graphicsManager = new GraphicsManager(gameField);
		inputManager = new InputManager(gameField);
		
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
		inputManager.keyPressed(key, c);
		if (key == Input.KEY_SPACE) {
			gameManager.spawnNewBlock();
		}
	}
}
