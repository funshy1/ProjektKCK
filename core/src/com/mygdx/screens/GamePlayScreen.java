package com.mygdx.screens;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ProjektKCK;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.ingameConsole.Console;

import Buttons.AbstractButton;
import Cloud.cloud;

public class GamePlayScreen extends AbstractScreen {

	private Console console;
	private MainCharacter mainCharacter;
	public AbstractButton button1;
	public Actors mapa;
	private long stoper = 0;
	public cloud Chmura;
	public GamePlayScreen(ProjektKCK game) {
		super(game);
		create();
	}
	

	public void create() {
		button1 = new AbstractButton(32,541,32,32);
		mapa = new Actors(0, 0, "forest_town.png");
		mainCharacter = new MainCharacter(0, 480, "Roman.png");
		Chmura = new cloud(100,55,550);
		console = new Console(600, 200, 50, 40);
		stage.addActor(mapa.image);
		stage.addActor(mainCharacter.image);
		stage.addActor(console.textField);
		stage.addActor(button1.button);
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (console.PhraseEntered == false)
			stoper = TimeUtils.nanoTime();
		if (mainCharacter.bounds.overlaps(button1.bounds)) {
			stage.addActor(Chmura.textField);
		}
		if (console.PhraseEntered == true) {
			if (TimeUtils.timeSinceNanos(stoper)<=500045000) {
				
				if (mainCharacter.bounds.overlaps(button1.bounds)) {
					Chmura.textField.remove();
				}
				
				else mainCharacter.move(console.GetText());
			}
			if (TimeUtils.timeSinceNanos(stoper)>=500045000) {
				console.PhraseEntered = false;
			}
			mainCharacter.updateActorBounds();
		}
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
