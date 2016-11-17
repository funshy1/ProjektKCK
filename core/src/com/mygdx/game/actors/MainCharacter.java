package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;

public class MainCharacter extends Actors {

	private float speed = 32.0f; // 32 pixels per second
	public MainCharacter(int X, int Y, String sciezka) {
		super(X, Y, sciezka);
		
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
	}
	public void updateActorBounds() {
		bounds.set(image.getX(), image.getY(), 32, 32);
		
	}


}
