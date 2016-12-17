package com.mygdx.game.parserCYK;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WczytajGramatyke{
	public static void Czytaj(ZasadaGramatyki[] ttab,ZasadaGramatykidlaNT[] nttab,int linie){
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
	public static void WyswietlGramatyke(ZasadaGramatyki[] tarr,ZasadaGramatykidlaNT[] ntarr){
		System.out.println("Terminale: ");
		for(int i=0;i<tarr.length;i++){
			System.out.println("Lewa strona: " + tarr[i].PodajLS());
			System.out.println("Prawa strona: " + tarr[i].PodajPS());
		}
		System.out.println("Nieterminale: ");
		for(int j=0;j<ntarr.length;j++){
			System.out.println("Lewa strona: " + ntarr[j].PodajLS());
			System.out.println("Prawa strona: " + ntarr[j].PodajPS1() + " " + ntarr[j].PodajPS2());
		}
	
	}
	public static void main(String []args) throws IOException{
		//Licze ile jest zasad w pliku, grupuje po typie (Terminale, Nieterminale)
		BufferedReader reader = new BufferedReader(new FileReader("..\\core\\assets\\Grammar\\gramatyka.txt"));
		int Tlines = 0,NTlines = 0;
		String linia;
		while((linia = reader.readLine()) != null){
			String[] wynikm = linia.split(" -> ");
			if(wynikm[1].contains(" ")){
				NTlines++;
			}
			else{
				Tlines++;
			}
		}
		
		reader.close();
		//Tworze dwie tablice o wielkosciach policzonych wczesniej
		ZasadaGramatyki[] ter = new ZasadaGramatyki[Tlines];
		ZasadaGramatykidlaNT[] nter= new ZasadaGramatykidlaNT[NTlines];
		Czytaj(ter,nter,Tlines+NTlines);
		WyswietlGramatyke(ter,nter);
	}
}