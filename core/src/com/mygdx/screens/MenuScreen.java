package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.characters.MainCharacter;
import com.mygdx.ingameConsole.Console;
public class MenuScreen extends AbstractScreen{
	
	private Console console;
	public MainCharacter postka;
	
	public MenuScreen(ProjektKCK game) {
		super(game);
		create();
	}
	
	public void create() {
		console = new Console(600,200,50,true);
		postka = new MainCharacter("badlogic.jpg",0,0);
		stage.addActor(postka.image);
		stage.addActor(console.textField);
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		GetCommendAndDoIt(console.PhraseEntered);
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public void ConsoleCommendsMenuScreen(String commend) {
		if (commend.equals("start gry")) {
			game.setScreen(new GamePlayScreen(game));
		}
		if (commend.equals("pauza")) {
			
		}
		if (commend.equals("wyjscie")) {
		}
	
	}
	
	public void GetCommendAndDoIt(boolean PhraseEntered) {
		if (PhraseEntered == true) {
			PhraseEntered = false;
			ConsoleCommendsMenuScreen(console.GetText());
		}
	}

}
