package com.mygdx.game.actors;

import java.io.IOException;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.cloud;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZasadaGramatyki;
import com.mygdx.ingameConsole.Console;
import java.util.Random;

public class NPC extends Actors{
	
	private Stage stage;   
	private AbstractButton button;
	private boolean CanTalk = false;
	private Parserv3 Parser;
	private Console console;
	Random generator;
	
	public NPC(int X, int Y, String sciezka , Stage stage, Console console, Parserv3 parser) throws IOException {
		super(X, Y, sciezka);
		this.stage = stage;
		this.console = console;
		this.Parser = parser;
		button = new AbstractButton(1600,600, 200, 140);
		button.button.setDebug(true);
		generator = new Random();
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
		
		int licznik=0;
	
		
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
				int l=0;
				if(b != 0){
					//Licze ile jest przywitan
					for(int g=0;g<Parserv3.ttab.length;g++){
						if(Parserv3.ttab[g].PodajLS().equals("przywitanie")){
							licznik++;
						}
					}
					//Wpisuje przywitania do tablicy
					String[] temptab = new String [licznik];
					for(int h=0;h<Parserv3.ttab.length;h++){
						if(Parserv3.ttab[h].PodajLS().equals("przywitanie")){
							temptab[l]=Parserv3.ttab[h].PodajPS();
							l++;
						}
					}
					int wyl=0;
					//Losuje liczbe
					wyl = generator.nextInt(licznik);
					
					this.Speak(temptab[wyl]);
					//this.testPlayer();
				}
			}
		}
	}
	
	/*
	public void testPlayer(){
		this.Speak("Oblicz 2+2!");
		if(console.phraseEntereddlaNPC == true){
			console.phraseEntereddlaNPC = false;
			if(console.LastSentenceInConsole.contains("4")){
				this.Speak("Dobrze!");
			}
		}
	}
	*/
	
}
