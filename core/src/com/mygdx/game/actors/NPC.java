package com.mygdx.game.actors;

import java.io.IOException;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.cloud;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public class NPC extends Actors{
	
	private Stage stage;   
	private AbstractButton button;
	private boolean CanTalk = false;
	private Parserv3 Parser;
	private Console console;
	
	public NPC(int X, int Y, String sciezka , Stage stage, Console console, Parserv3 parser) throws IOException {
		super(X, Y, sciezka);
		this.stage = stage;
		this.console = console;
		this.Parser = parser;
		button = new AbstractButton(1600,600, 200, 140);
		button.button.setDebug(true);
		stage.addActor(button.button);
		
	}
	
	public void collisionCheck(Rectangle rectangle) {
		if (button.bounds.overlaps(rectangle)) {
			this.CanTalk = true;
		}
		else
			this.CanTalk = false;
	}
	
	public void Speak(String text){
		cloud Cloud2 = new cloud(50, image.getX() + 35, image.getY() + 20);
		Cloud2.textField.setMessageText(text);
		stage.addActor(Cloud2.textField);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				Cloud2.textField.remove();
			}
		}, 3);
	}
	
	
	public void sayHello(){
		
		Boolean mcSaidHello=false;
		int b=0;
		
		if(this.CanTalk == true){
			if(console.phraseEntereddlaNPC == true){
				console.phraseEntereddlaNPC = false;
				for(int i=0;i<Parserv3.ttab.length;i++){
					if(Parserv3.ttab[i].PodajLS().equals("przywitanie")){
						if(console.LastSentenceInConsole.contains(Parserv3.ttab[i].PodajPS())){
							mcSaidHello=true;
							b=i;
							break;
						}
					}
				}
				if(b != 0){
					//Losowac przywitanie
					this.Speak(Parserv3.ttab[b].PodajPS());
					this.testPlayer();
				}
			}
		}
	}
	
	public void testPlayer(){
		this.Speak("Oblicz 2+2?");
		if(console.phraseEntereddlaNPC == true){
			console.phraseEntereddlaNPC = false;
			if(console.LastSentenceInConsole.contains("4")){
				this.Speak("Dobrze!");
			}
		}
	}
}
