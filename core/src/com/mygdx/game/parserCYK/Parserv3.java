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
		BufferedReader reader = new BufferedReader(new FileReader("Grammar\\gramatyka.txt"));
		String linia;
		
		//Liczenie ile jest zasad wg. typu
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
		
		//Inicjalizacja tablic dla zasad gramatyki
		Parserv3.nttab = new ZasadaGramatykidlaNT[NTlines];
		Parserv3.ttab = new ZasadaGramatyki[Tlines];
		
		//Wczytanie gramatyki do tablic
		Parserv3.Wczytaj(Tlines+NTlines);
		
	}
	
	
	/*
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 *																		 *
	 * 						WCZYTYWANIE GRAMATYKI						     *
	 * 																		 *
	 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	
	
	public static void Wczytaj(int linie){
		FileReader plik = null;
		String linia = "";
		
		//Otworz plik
		try {
			plik = new FileReader("Grammar\\gramatyka.txt");
		}catch (FileNotFoundException e){
			System.out.println("Nie znaleziono pliku.");
			System.exit(1);
		}
		
		//Wczytaj linie z pliku, w zaleznosci od typu zasady wczytaj do odpowiedniej tablicy
		
		BufferedReader buff = new BufferedReader(plik);
		
		try {
			int i=0;
			int ntc=0,tc=0;
			while((linia = buff.readLine()) != null){
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
	
	
	public static String CheckType(String a){
		
		//Sprawdzenie czy slowo ze zdania znajduje sie w ktorejs z zasad terminalnych
		//Jesli tak to zamiana tego slowa na lewa strone z tej zasady
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
	
	
	public static String ReverseCheckType(String a){
		
		//Odwrocenie metody CheckType
		//Chyba bezuzyteczne
		
		String temp; temp = a;
		for(int i=0;i<ttab.length;i++){
			if(temp.equals(ttab[i].PodajLS())){
				temp = ttab[i].PodajPS();
				break;
			}
		}
		return temp;
	}
	
	public static String[] Przeparsuj(String tablica[], int slowa){
		
		int Y;
		Y = 0;
		
		//int X; X = 0;		
		//int x1; int y1; int x2; int y2; int counter;
		
		/* 
		
		//Przydatne do debugowania
	
		System.out.println("Tablica przed przeparsuj: ");
		for(int i=0;i<slowa*slowa;i++){
				System.out.print(tablica[i] + " ");
		}
		
		System.out.println(" ");
		*/
		
		
		//Inicjalizacja tablicy - bez tego ryzykujemy nulle w tablicy
		for (Y = slowa; Y<(slowa*slowa); Y++){
				tablica[Y]="N";
		}		
		
		String wn;
		int xt2=slowa,a=0;
		for (int i = 0; i < slowa-1; i++){
			wn=SprawdzGramatyke(tablica,i,i+1);
			if(!(wn.equals("SG"))){
				tablica[xt2]=wn;
				xt2++;
				wn="SG";
			}
		}
		
		for (int i = 0; i < slowa*slowa; i++){
		    for (int j = slowa; j < slowa*slowa; j++){
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
		
		/* 
		 
		//Przydatne do debugowania
		 
		System.out.println("Tablica po przeparsuj: ");
		for(int i=0;i<slowa*slowa;i++){
				System.out.print(tablica[i] + " ");
		}
		System.out.println();
		
		//Stwierdza czy zdanie jest w gramatyce
		
		for(int i=0;i<slowa*slowa;i++){
		if(tablica[i].contains("Z"))
			return "Zdanie jest w gramatyce";

		}
		*/
		return tablica;
	}
	
	public static String SprawdzGramatyke(String tabliczka[], int x1, int x2){
		
		//Sprawdza czy dwa elementy znajduja si� w ktorejs z zasad nieterminalnych gramatyki
		//Jesli tak to zwraca lewa strone tej zasady
		
		String ExitString = "SG";
		
		for(int i=0;i<nttab.length;i++){
			if (tabliczka[x1].contains(nttab[i].PodajPS1()) && tabliczka[x2].contains(nttab[i].PodajPS2())){
				ExitString = nttab[i].PodajLS();
				break;
			}
		}
		return ExitString;
	}
	
	//Metoda wykorzystywana przez konsole
	
	public String[] Dzialaj(String zdanie) throws IOException{  
		
		//Dzielenie zdania na tokeny
		String[] result = zdanie.split("\\s+");
		
		int dl_tab = result.length*result.length;
			
		//Stworzenie tablicy o wymiarch x na x, gdzie x to ilosc slow w zdaniu
		String Symbole[] = new String[dl_tab];
		
		for (int i = 0; i <result.length; i++)
			{
				Symbole[i] = CheckType(result[i]);
			}
		
		String[] wynik = Przeparsuj(Symbole,result.length);
		//System.out.println(CzyJest);
		
		int b=0;
		
		for(int i=0;i<dl_tab;i++){
			if(wynik[i].contains("Z")){
				b=1;
			}
		}
		
		String typ_zdania[] = new String[result.length];
		int tz=0;
		int c=0;
		
		for(int i=0;i<result.length;i++){
			typ_zdania[i] = "N";
		}
		
		if(b==1){
			b=0;
			for(int i=0;i<dl_tab;i++){
				if(wynik[i].contains("Z")){
					typ_zdania[tz]=wynik[i];
					tz++;
					c=1;
				}
			}
		}
		
		String k_ruchu = new String();
		
		//Zwracam tablice, aby mozna bylo zwrocic wiecej niz jedna informacje
		//W zaleznsci od typu zdania bedzie mozna tworzyc tablice o odpowiedniej wielkosci itd.
		
		String zwroc[] = new String[2];
		
		for(int i=0;i<zwroc.length;i++){
			zwroc[i]="N";
		}
		
		if(c==1){
			c=0;
			for(int i=0;i<typ_zdania.length;i++){
				switch(typ_zdania[i]){
					case "Z_Idz":
						zwroc[0]="Z_Idz";
						for(int g=0;g<result.length;g++){
							if(wynik[g].contains("orientacja")){
								k_ruchu=result[g];
								if(k_ruchu.contains("lew"))
									k_ruchu="lewo";
								if(k_ruchu.contains("pra"))
									k_ruchu="prawo";
								if(k_ruchu.contains("gor"))
									k_ruchu="gora";
								if(k_ruchu.contains("dol"))
									k_ruchu="dol";
								zwroc[1]=k_ruchu;
								return zwroc; 
							}
						}
						break;
					case "Z_Atakuj":
				}
			}
		}
		return zwroc;
	}

	/* 
	
	//Pozostalosci po main

	public static void main(String []args) throws IOException{
		
		new Parserv3();
	
		Parser2.WyswietlGramatyke();
	
		//Wprowadzanie zdania
		String phrase; phrase = ""; 
		System.out.println("Input phrase");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		try
			{phrase=br.readLine();}
		catch(Exception e)
			{System.out.println(e);}

		System.out.println("The sentence is: " + phrase);
		
		//Przydatne do debugowania
		new Parserv3();
		System.out.println(dzialaj("idz w lewo"));
	}
	*/
}
