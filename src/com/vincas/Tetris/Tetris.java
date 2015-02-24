package com.vincas.Tetris;

import com.vincas.Tetris.states.GameOverState;
import com.vincas.Tetris.states.GamePlayState;
import com.vincas.Tetris.states.MainMenuState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tetris extends StateBasedGame {
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 576;
	
	public static final int STATE_MAINMENU = 0;
	public static final int STATE_GAMEPLAY = 1;
	public static final int STATE_GAMEOVER = 2;

	public static final int SQUARE_SIZE = 28;

	public Tetris() {
		super("Vinco Tetris");
	}

	public Tetris(String name) {
		super(name);
	}

	public static void main(String[] args) {
		try {
			Tetris game = new Tetris("Tetris");
			AppGameContainer app = new AppGameContainer(new ScalableGame(game, SCREEN_WIDTH, SCREEN_HEIGHT));
			app.setDisplayMode(Settings.SCALED_WIDTH, Settings.SCALED_HEIGHT, Settings.FULLSCREEN);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		addState(new MainMenuState());
		addState(new GamePlayState());
		addState(new GameOverState());
	}
}
