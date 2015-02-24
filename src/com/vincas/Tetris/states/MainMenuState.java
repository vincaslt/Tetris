package com.vincas.Tetris.states;

import com.vincas.Tetris.Tetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {
	StateBasedGame game;
	
	@Override
	public int getID() {
		return Tetris.STATE_MAINMENU;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		this.game = stateBasedGame;
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		graphics.drawString("Press any key to start.", 100, 100);
		TextureImpl.bindNone();
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

	}

	@Override
	public void keyPressed(int key, char c) {
		this.game.enterState(Tetris.STATE_GAMEPLAY, new FadeOutTransition(), new FadeInTransition());
	}
}
