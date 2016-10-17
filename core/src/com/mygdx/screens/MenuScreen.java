package com.mygdx.screens;

import com.mygdx.actors.Actors;
import com.mygdx.game.ProjektKCK;
import com.mygdx.ingameConsole.Console;
public class MenuScreen extends AbstractScreen{
	
	private Actors bg;
	private Console console;
	public MenuScreen(ProjektKCK game) {
		super(game);
		create();
	}
	
	public void create() {
		console = new Console(600,225,250,false);
		bg = new Actors(0,0,"bg.jpg");
		stage.addActor(bg.image);
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
