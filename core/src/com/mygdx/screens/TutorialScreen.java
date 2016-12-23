package com.mygdx.screens;

import java.io.IOException;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.cloud;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;


public class TutorialScreen extends AbstractScreen {

	private Console console;
	public int ilosc_elemt_w_tablicy_przeszkod = 1;   //tworzymy tablice przeszkod taka duzo ile jest elementow na ktore nie mozna wejsc w grze
	private MainCharacter mainCharacter;
	public Actors layoutconsole;   //dodajemy layout konsoli
	public Actors statslayout;   //dodajemy layout statystyk
	public Actors map;          //mapa danej planszy    
	public Actors map2;
	public AbstractButton CantStand[];   //tablica w ktorej sa obiekty do kolizji
	public Parserv3 Parser1;
	
	public TutorialScreen(ProjektKCK game) throws IOException {
		super(game);
		create();
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);

		if (console.PhraseEntered == true) {
			
			String fraza = new String();
			fraza = console.GetText();
			
			//Tablica zwracana przez parser
			String[] wynik = new String[2];
			
			try {
				wynik = Parser1.Dzialaj(fraza);
			} catch (IOException e) {
				System.out.println("Popsules nasza gre");
			}
			
			//Wyswietla w konsoli co zwraca parser (czy fraza jest zdaniem czy nie).
			//Jesli jest to wyswietla tez jego typ
			//System.out.println(wynik[0]);
			
			switch(wynik[0]){
				case "Z_Idz":
					mainCharacter.move(wynik[1], CantStand , ilosc_elemt_w_tablicy_przeszkod);
					break;
				case "N":
					mainCharacter.Speak("Nie rozumiem Cie!");
					break;
			}
			
			console.PhraseEntered = false;
	
		}
		refreshCamera();
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	
	public void create() {
		try {
			Parser1 = new Parserv3();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CantStand = new AbstractButton[ilosc_elemt_w_tablicy_przeszkod];
		CantStandInit();
		for (int i=0 ; i<ilosc_elemt_w_tablicy_przeszkod ; i++) {
			stage.addActor(CantStand[i].button);
		}
		layoutconsole = new Actors(0, 0, "Layout\\layoutconsole.png");
		statslayout = new Actors(0, 668, "Layout\\statslayout.png");
		map = new Actors(0, 0, "Maps\\tutorial\\1.jpg");
		map2 = new Actors(0,0, "Maps\\tutorial\\2.png");
		console = new Console(600, 270, 25, 40);
		mainCharacter = new MainCharacter(400, 450, "CharacterMovement\\walking e0000.png", stage);
		stage.addActor(map.image);
		stage.addActor(mainCharacter.image);
		stage.addActor(map2.image);
		stage.addActor(layoutconsole.image);
		stage.addActor(console.textField);
		stage.addActor(statslayout.image);
		stage.addActor(mainCharacter.statistics[0].textField);
		stage.addActor(mainCharacter.statistics[1].textField);
		stage.addActor(mainCharacter.statistics[2].textField);
		stage.addActor(mainCharacter.statistics[3].textField);
		stage.act();
		
	}
	
	
	public void CantStandInit() {
		CantStand[0] = new AbstractButton(289, 320, 32, 32);
	}
	public void refreshCamera() {
		camera.position.set(mainCharacter.image.getX()+50,mainCharacter.image.getY(),0);	
		statslayout.image.setX(mainCharacter.image.getX()-470);
		statslayout.image.setY(mainCharacter.image.getY()+290);
		console.textField.setX(mainCharacter.image.getX()-260);
		console.textField.setY(mainCharacter.image.getY()-370);
		layoutconsole.image.setX(mainCharacter.image.getX()-480);
		layoutconsole.image.setY(mainCharacter.image.getY()-390);
		mainCharacter.statsPositionUpdate();
	}
	
}
