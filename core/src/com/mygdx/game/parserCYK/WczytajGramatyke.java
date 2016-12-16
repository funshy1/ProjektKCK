package com.mygdx.game.parserCYK;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.ls.LSException;

public class WczytajGramatyke{
	public static void Czytaj(ZasadaGramatyki[] tab,int linie){
		FileReader plik = null;
		String linia = "";
	
		//System.out.println("Jestem w: ");
		//System.out.println(Parser.class.getProtectionDomain().getCodeSource().getLocation());
		
		//Otworz plik
		try {
			plik = new FileReader("..\\core\\assets\\Grammar\\gramatyka.txt");
		}catch (FileNotFoundException e){
			System.out.println("Nie znaleziono pliku.");
			System.exit(1);
		}

		BufferedReader buff = new BufferedReader(plik);
		//czytaj linie
		try {
			int i=0;
			while((linia = buff.readLine()) != null){
				String fraza; 
				fraza = linia;
				String[] wynik = fraza.split(" -> ");
				tab[i] = new ZasadaGramatyki();
				tab[i].UstawLSPS(wynik[0], wynik[1]);
				i++;
				if(i==linie)
					break;
			}
		}catch(IOException e){
			System.out.println("blad odczytu");
			System.exit(2);
		}
		try{
			plik.close();
		}catch(IOException e){
			System.out.println("blad zamykania pliku");
			System.exit(3);
		}
	}
	public static void WyswietlGramatyke(ZasadaGramatyki[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.println("Lewa strona: " + arr[i].PodajLS());
			System.out.println("Prawa strona: " + arr[i].PodajPS());
		}
	
	}
	public static void main(String []args) throws IOException{
		//Licze ile jest zasad w pliku
		BufferedReader reader = new BufferedReader(new FileReader("..\\core\\assets\\Grammar\\gramatyka.txt"));
		int lines = 0;
		while(reader.readLine() != null){ 
			lines++;
		}
		reader.close();
		//Tworze tablice o takiej wielkosci ile jest zasad w pliku
		ZasadaGramatyki[] arr = new ZasadaGramatyki[lines];
		Czytaj(arr,lines);
		WyswietlGramatyke(arr);
	}
}