package com.mygdx.game.parserCYK;

public class ZasadaGramatyki {
	String LS="",PS="";
	public void UstawLSPS(String lewo, String prawo){
		LS=lewo;
		PS=prawo;
	}
	public String PodajLS(){
		return LS;
	}
	public String PodajPS(){
		return PS;
	}
}
