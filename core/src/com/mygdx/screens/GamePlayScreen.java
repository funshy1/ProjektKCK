package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.ingameConsole.Console;

public class GamePlayScreen extends AbstractScreen{


	private Console console;
	private MainCharacter mainCharacter;
	public Actors mapa;

	public GamePlayScreen(ProjektKCK game) {
		super(game);
		create();
	}

	public void create() {
		mapa = new Actors(0, 0, "MapkaAlpha.png");
		mainCharacter = new MainCharacter(0, 0, "Roman.png");
		console = new Console(600, 50, 50, 40);
		stage.addActor(mapa.image);
		stage.addActor(mainCharacter.image);
		stage.addActor(console.textField);
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		GetCommendAndDoIt();
		mainCharacter.howLongGoingIncreaseAndRespawn(); //ruch naszej glownej postaci
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void ConsoleCommends(String commend) {
		
			
			mainCharacter.move(commend);
			
	}

	public void GetCommendAndDoIt() {
		if (console.PhraseEntered == true) {
			ConsoleCommends(console.GetText());
			if (mainCharacter.howLongGoing >51) mainCharacter.howLongGoing = 0;
		}
		
		if (mainCharacter.howLongGoing>50)
			console.PhraseEntered = false;
		
	}
	

}


