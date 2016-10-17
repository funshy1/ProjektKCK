package com.mygdx.ingameConsole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.ingameConsole.Console;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class Console {
	///////////////////////////////////////////////////////////////
	///////YOU ONLY NEED TO CREATE OBJECT OF THIS CLASS////////////
	///////THEN ADD OBJECT.textFieldStyle TO YOUR STAGE////////////
	///////////////////////////////////////////////////////////////
	///////OBJECT.GetText RETURNS LAST STRING IN CONSOLE///////////
	///////////////////////////////////////////////////////////////
	private String[] TableOfStrings;
	private String LastSentenceInConsole;
	private TextFieldStyle textFieldStyle;
	private BitmapFont bitmapFont;
	public TextField textField;
	public boolean PhraseEntered = false;
	public int countIN = 0;
	public int countOUT = 0;

	public Console( int WIDTH , float X , float Y, boolean Debug ) {
		bitMapFontInit();
		textFieldStyleInit();
		textFieldInit( WIDTH , X , Y , Debug );
		setListener();
		TableOfStrings = new String[100];
		
	}

	public String GetText() {
		return LastSentenceInConsole;
	}

	public void setListener() {
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {

				if (keycode == Input.Keys.ENTER) {
					if (textField.getText().length()>0) {
					textField.cut();
					LastSentenceInConsole = textField.getText();
					TableOfStrings[countIN] = textField.getText();
					countIN++;
					
					if (countIN>99) countIN = 0;
					
					PhraseEntered = true;
					textField.selectAll();
					textField.cut();
					countOUT = countIN;
				}
				}
				return false;
			}
		});
		
		
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{  if (keycode == Input.Keys.UP) {
				if (countOUT-1 >=0) countOUT--;
				
				if (keycode == Input.Keys.UP) {
					textField.setText(TableOfStrings[countOUT]);
					textField.setCursorPosition(TableOfStrings[countOUT].length());
				}
				
			}
				return false;
			}
		});
		
		
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{  if (keycode == Input.Keys.DOWN) {
				if (countOUT+1 < countIN) countOUT++;
				
				if (keycode == Input.Keys.DOWN) {
					textField.setText(TableOfStrings[countOUT]);
					textField.setCursorPosition(TableOfStrings[countOUT].length());
				}
			}
				return false;
			}
		});
	}
	
	
	
	

	//////////////////////////////////////////////////////////////
	//////////// JUST INIT ///////////// DONT READ LOWER /////////
	//////////////////////////////////////////////////////////////

	private void textFieldStyleInit() {
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.font = bitmapFont;
	}

	public void textFieldInit( int WIDTH, float X , float Y , boolean Debug ) {
		textField = new TextField("", textFieldStyle);
		textField.setMessageText("...Wpisz co mam zrobic...");
		textField.setWidth(WIDTH);
		textField.setX(X);
		textField.setY(Y);
		textField.setDebug(Debug);
		textField.setClipboard(Gdx.app.getClipboard());
	}

	public void bitMapFontInit() {
		bitmapFont = new BitmapFont();
	}
}
