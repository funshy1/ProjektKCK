package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.ingameConsole.Console;

public class GamePlayScreen extends AbstractScreen{


	private Console console;
	private MainCharacter mainCharacter;

	public GamePlayScreen(ProjektKCK game) {
		super(game);
		create();
	}

	public void create() {
		
		mainCharacter = new MainCharacter(3000, 3000, "badlogic.jpg");
		console = new Console(600, mainCharacter.image.getX()-150, mainCharacter.image.getY()-200, 40);
		camera.position.x = mainCharacter.image.getX()+ mainCharacter.image.getWidth()/2;
		camera.position.y = mainCharacter.image.getY()+ mainCharacter.image.getHeight()/2;
		stage.addActor(mainCharacter.image);
		stage.addActor(console.textField);
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		GetCommendAndDoIt();
		cameraUpdate();
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
			if (mainCharacter.howLongGoing >41) mainCharacter.howLongGoing = 0;
		}
		
		if (mainCharacter.howLongGoing>40)
			console.PhraseEntered = false;
		
	}
	
	public void cameraUpdate() {
		camera.position.x = mainCharacter.image.getX()+ mainCharacter.image.getWidth()/2;
		camera.position.y = mainCharacter.image.getY()+ mainCharacter.image.getHeight()/2;
		console.setPosition(mainCharacter.image.getX()-150, mainCharacter.image.getY()-200);
	}

}


