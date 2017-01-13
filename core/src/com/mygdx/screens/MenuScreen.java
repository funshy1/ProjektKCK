package com.mygdx.screens;

import java.io.IOException;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.actors.Actors;
import com.mygdx.ingameConsole.Console;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class MenuScreen extends AbstractScreen {

	private Console console;
	private Actors background;
	private int index = 1;
	private float delay = (float) 0.08;

	public MenuScreen(ProjektKCK game) {
		super(game);
		create();
	}

	public void create() {
		console = new Console(600, 210, 230, 1);
		background = new Actors(0, 0, "Menu\\Background.jpg");

		stage.addActor(background.image);
		Timer.schedule(new Task(){
			@Override
		    public void run() {
				if(index<10)changePicture("Menu\\Composite_0000"+index+".jpg");
				else if(index>9&&index<23) changePicture("Menu\\Composite_000"+index+".jpg");
				else index=0;
				index++;		
		    }
		}, delay,delay);
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
		if (commend.equals("rozpocznij")|commend.equals("Rozpocznij"))
			try {
				dispose();
				game.setScreen(new TutorialScreen(game));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Problem przy zmianie Menu -> Tutorial");
			}
		
	}

	public void GetCommendAndDoIt(boolean PhraseEntered) {
		if (PhraseEntered == true) {
			PhraseEntered = false;
			ConsoleCommendsMenuScreen(console.GetText());
		}
	}

	void changePicture(String sciezka) {
		background.texture = new Texture(Gdx.files.internal(sciezka));
		background.region.setRegion(background.texture);

	}
	
}
