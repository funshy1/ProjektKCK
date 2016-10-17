package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.ingameConsole.Console;

public class GameplayScreen extends AbstractScreen {

	private Console console;
	public GameplayScreen(ProjektKCK game) {
		super(game);
		create();
	}
	
	public void create() {
		console = new Console(600,225,50,false);
		stage.addActor(console.textField);
		stage.act();
		
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}



}
