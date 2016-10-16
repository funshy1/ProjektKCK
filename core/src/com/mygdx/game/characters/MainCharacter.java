package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.ingameConsole.Console;

public class MainCharacter extends Actor {
	private Texture texture;
	private TextureRegion textureRegion;
	public Image image;

	public MainCharacter(String ImgDirectory, float X, float Y) {
		texture = new Texture(Gdx.files.internal(ImgDirectory));
		this.setX(X);
		this.setY(Y);
		textureRegion = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		image = new Image(textureRegion);
		image.setPosition(X, Y);
		image.setWidth(texture.getWidth());
		image.setHeight(texture.getHeight());
	}

	public void move(String whereToMove, int HowMuchSteps) {

		if (whereToMove.equals("do przodu")) moveUP();
		
		if (whereToMove.equals("do tylu")) moveDOWN();
		
		if (whereToMove.equals("w lewo")) moveLEFT();
		
		if (whereToMove.equals("w prawo")) moveRIGHT();
	}

	public void moveUP() {
		this.setY(this.getY() + 1F);
		image.setPosition(this.getX(), this.getY());
	}

	public void moveDOWN() {
		this.setY(this.getY() - 1F);
		image.setPosition(this.getX(), this.getY());
	}

	public void moveLEFT() {
		this.setX(this.getX() - 1F);
		image.setPosition(this.getX(), this.getY());
	}

	public void moveRIGHT() {
		this.setX(this.getX() + 1F);
		image.setPosition(this.getX(), this.getY());
	}

}
