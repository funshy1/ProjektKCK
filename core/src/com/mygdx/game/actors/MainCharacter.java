package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;

public class MainCharacter extends Actors {

	private float speed = 200.0f; // 10 pixels per second
	public int howLongGoing = 0;

	public MainCharacter(int X, int Y, String sciezka) {
		super(X, Y, sciezka);
		// TODO Auto-generated constructor stub
	}

	public void move(String gdzie) {


			if (gdzie.equals("do gory"))
				image.setY(image.getY() + Gdx.graphics.getDeltaTime() * speed);

			if (gdzie.equals("na dol"))
				image.setY(image.getY() - Gdx.graphics.getDeltaTime() * speed);

			if (gdzie.equals("w lewo"))
				image.setX(image.getX() - Gdx.graphics.getDeltaTime() * speed);

			if (gdzie.equals("w prawo"))
				image.setX(image.getX() + Gdx.graphics.getDeltaTime() * speed);
		
		if (howLongGoing > 999)
			howLongGoing = 990;
	}

	public void howLongGoingIncreaseAndRespawn() {
		howLongGoing++;
	}
}
