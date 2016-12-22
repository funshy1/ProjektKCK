package com.mygdx.game.parserCYK;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parserv3{
	static int Tlines;
	static int NTlines;
	static ZasadaGramatykidlaNT[] nttab;
	static ZasadaGramatyki[] ttab;
	
	public Parserv3() throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader("..\\core\\assets\\Grammar\\gramatyka.txt"));
		String linia;
		while((linia = reader.readLine()) != null){
			String[] wynikm = linia.split(" -> ");
			if(wynikm[1].contains(" ")){
				Parserv3.NTlines++;
			}
			else{
				Parserv3.Tlines++;
			}
		}
		reader.close();
		Parserv3.nttab = new ZasadaGramatykidlaNT[NTlines];
		Parserv3.ttab = new ZasadaGramatyki[Tlines];
	}
	
	
	/*
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 *																		 *
	 * 						WCZYTYWANIE GRAMATYKI						     *
	 * 																		 *
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	
	
	public static void Czytaj(int linie){
		FileReader plik = null;
		String linia = "";
		
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
			int ntc=0,tc=0;
			while((linia = buff.readLine()) != null){
		//Stworzyc dwie tablice jedna dla terminali druga dla nieterminali
				String fraza; 
				fraza = linia;
				String[] wynik = fraza.split(" -> ");
				
				if(wynik[1].contains(" ")){
					nttab[ntc] = new ZasadaGramatykidlaNT();
					String[] wynik2 = wynik[1].split(" ");
					nttab[ntc].UstawLS(wynik[0]);
					nttab[ntc].UstawPS1(wynik2[0]);
					nttab[ntc].UstawPS2(wynik2[1]);
					ntc++;
				}
				else{
					ttab[tc] = new ZasadaGramatyki();
					ttab[tc].UstawLSPS(wynik[0], wynik[1]);
					tc++;
				}
				i++;
				if(i==linie)
					break;
			}
		}catch(IOException e){
			System.out.println("Blad odczytu z pliku");
			System.exit(2);
		}
		try{
			plik.close();
		}catch(IOException e){
			System.out.println("Blad zamykania pliku");
			System.exit(3);
		}
	}
	public static void WyswietlGramatyke(){
		System.out.println("Terminale: ");
		for(int i=0;i<ttab.length;i++){
			System.out.println("Lewa strona: " + ttab[i].PodajLS());
			System.out.println("Prawa strona: " + ttab[i].PodajPS());
		}
		System.out.println("Nieterminale: ");
		for(int j=0;j<nttab.length;j++){
			System.out.println("Lewa strona: " + nttab[j].PodajLS());
			System.out.println("Prawa strona: " + nttab[j].PodajPS1() + " " + nttab[j].PodajPS2());
		}
	
	}

	public ZasadaGramatyki[] PodajTabliceTerminali(){
	    return(ttab);   
	}
	public ZasadaGramatykidlaNT[] PodajTabliceNieterminali(){
        return(nttab);   
	}
	
	
	/*
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 *																		 *
	 * 								PARSER									 *
	 * 																		 *
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	
	
	public static String CheckType(String a)
	{	
		
		//Przeleciec tablice z terminalami i sprawdzic czy slowo sie znajudje
		//w ktorejs z zasad
		//Temp to slowo x ze zdania (np. dla x=0 slowo z 'Ala ma kota' to Ala)
		
		String temp; temp = a;
		for(int i=0;i<ttab.length;i++){
			if(temp.equals(ttab[i].PodajPS())){
				temp = ttab[i].PodajLS();
				break;
			}
		}
		return temp;
	}
	
	public static String Przeparsuj(String tablica[], int slowa)
	{
		int X; int Y;		
		int x1;	int y1;	int x2;	int y2;	int counter;
		X = 0; Y = 0;
		
		
		
		System.out.println("Tablica przed przeparsuj: ");
		for(int i=0;i<slowa*slowa;i++){
				System.out.print(tablica[i] + " ");
		}
		
		System.out.println(" ");
		//Inicjalizacja tablicy - bez tego ryzykujemy nulle w tablicy
		for (Y = slowa; Y<(slowa*slowa); Y++){
				tablica[Y]="N";
		}		
		
		String wn;
		int xt2=slowa,a=0;
		
		
		for (int i = 0; i < slowa*slowa; i++){
		    for (int j = 0; j < slowa*slowa; j++){
		    	wn=SprawdzGramatyke(tablica,i,j);
		    	if(!(wn.equals("SG"))){
		    		for (int g = 0; g < slowa*slowa; g++){
			    		if(tablica[g].equals(wn)){
			    			wn="SG";
			    			a=1;
			    			break;
			    		}
			    	}
		    		if(a!=1){
		    			tablica[xt2]=wn;
		    			xt2++;
		    			wn="SG";
		    		}
		    		a=0;
		    	}
		    }
		}
		
		
		
		System.out.println("Tablica po przeparsuj: ");
		for(int i=0;i<slowa*slowa;i++){
				System.out.print(tablica[i] + " ");
		}
		System.out.println();
		for(int i=0;i<slowa*slowa;i++){
		if(tablica[i].contains("Z"))
			return "Zdanie jest w gramatyce";

		}
		return "Zdania nie ma w gramatyce";
	}
	
	public static String SprawdzGramatyke(String tabliczka[], int x1, int x2)
	{
		String ExitString = "SG";
		
		for(int i=0;i<nttab.length;i++){
			if (tabliczka[x1].contains(nttab[i].PodajPS1()) && tabliczka[x2].contains(nttab[i].PodajPS2())){
				ExitString = nttab[i].PodajLS();
				break;
			}
		}
		return ExitString;
	}


public static void main(String args[]) throws IOException{  
		//Wczytanie gramatyki
		new Parserv3();
		Parserv3.Czytaj(Tlines+NTlines);
		//Parser2.WyswietlGramatyke();
		
		//Wprowadzanie zdania
		String phrase; phrase = ""; 
		//System.out.println("Input phrase");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		try
			{phrase=br.readLine();}
		catch(Exception e)
			{System.out.println(e);}

		//System.out.println("The sentence is: " + phrase);
		
		//Dzielenie zdania na tokeny
		String[] result = phrase.split("\\s+");
		
		//Stworzenie tablicy o wymiarch x na x, gdzie x to ilosc slow w zdaniu
		String Symbole[] = new String[result.length*result.length];
		for (int i = 0; i <result.length; i++)
			{
				Symbole[i] = CheckType(result[i]);
			}
		
		String CzyJest = Przeparsuj(Symbole,result.length);
		System.out.println(CzyJest);
	}
}