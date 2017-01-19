<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/TEMP/TimesOldRoman.jpg?raw=true">
<h3 align="center">Uniwersytet im. Adama Mickiewicza w Poznaniu</h3>
<h4 align="center">Wydział Matematyki i Informatyki</h4>
<h5 align="center">Informatyka,  semestr III</h5>


<br><br><br><br>
<h1 align="center">Komunikacja człowiek - komputer</h1>
<h4 align="center">Gra RPG - TimesOldRoman</h4>
<p align="right">
<br><br><br><br>
<br>
Rafał Ciećwierz<br>
Adrian Witczak<br>
Jakub Wawrzyniak<br>
Szymon Nedzi<br>
Miłosz Przybysz<br>
</p><br><br>
---------------


# `Opis projektu`
## 1. Fabuła i opis świata:
W naszej grze wcielamy się w postać rycerza o imieniu Roman, który przemierza świat i zgładza wszelkie zło, które staje mu na drodze. Roman pochodzi z przyszłości, gdzie wiódł normalne życie jako przeciętny obywatel. Los sprawił jednak, że wpadł w wir czasoprzestrzenny i wylądował w starodawnej krainie Fontu.  Kraina ta osadzona jest w czasach średniowiecza, która zamieszkuje wiele potworów i gdzie znajduje się wiele innych niebezpieczeństw. Naszym zadaniem jest pomóc bohaterowi w odnalezieniu pradawnych artefaktów, które pomogą mu wrócić do jego dawnego życia. Aby to zrobić, będziemy musieli stawić czoła niebezpieczeństwom krainy Fontu. Nazwa gry nawiązuje do popularnej czcionki “TimesNewRoman”, która jest nieodzownym elementem gry.
## 2. Technologie i rozwiązania użyte w grze: 
Gra została napisana w całości w języku JAVA z użyciem framework’u o nazwie LIBGDX. Jest to gra typu RPG, w której bohater porusza się po dwuwymiarowej planszy. Głównym punktem projektu jest komunikacja pomiędzy człowiekiem a komputerem ( w tym wypadku bohaterem gry). Użyliśmy parsera składni językowej CYK do obsługi zdań w języku polskim. Gra posiada konsole, do której możemy wpisać dowolne zdanie. Wpisane zdanie będzie poleceniem wykonywanym przez naszego bohatera.
## 3. Główne elementy gry: 
* __Konsola__:<br>
<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/TEMP/layoutconsolecroped.png?raw=true"><br>
Jest to pole służące do wpisywania poleceń bohaterowi. Przyjmuje zdania w języku polskim. Za pomocą klawiszy strzałki w górę i w dół można wyświetlić wcześniej wpisane polecenia.

* __Bohater__<br>
<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/CharacterMovement/walking%20s0000.png?raw=true"><br>
Grafika przedstawia postać naszego bohatera. Podczas poruszania się postać jest animowana. 

* __NPC__<br>
<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/NPCMovement/stopped0000.png?raw=true"><br>
Grafika przedstawia postać NPC, z którą komunikuje się nasz bohater. Postać odpowiada nam na nasze pytania oraz sama zadaje swoje. Ruch postaci w każdym kierunku jest zaanimowany.

* __Przykładowy przeciwnik__<br>
<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/OrcMovement/stopped0000.png?raw=true"><br>
Grafika przedstawia przeciwnika, w tym wypadku Orka, z którym przyjdzie walczyć naszej postaci. Ruch postaci jest zaanimowany w każdym kierunku.

* __Ekran statystyk__<br>
<img style="width: 200px; height: 150px;" src="https://github.com/drakar1903/ProjektKCK/blob/master/core/assets/Layout/statslayout.png?raw=true"><br>
Powyższa grafika to układ statystyk bohatera. Każde z kolorowych kółek zawiera 
w grze liczbę odpowiedzialną za poziom konkretnej statystyki. 











Jest to projekt zrobiony na zaliczenie przedmiotu Komunikacja Człowiek-Komputer. Program jest napisany w Javie z użyciem platformy programistycznej libGDX.



- - -
# `2DO List:`
- [ ] Rozwiniecie gramatyki (idz przed siebie do przeszkody)
- [ ] Rozwiniecie interakcji użytkownika z gra -- zaprogramowanie pytan do uzytkownika, jeśli uzytkownik nie wpisał zdania, ale parser cos znalazl (cos, czyli jakas zasade nieterminalna (np. kierunek)).
- [ ] Druga mapa z aktorami
- [ ] Dodajemy NPCta(startowy, który będzie stał przed bohaterem na początku gry)
- [ ] Rozbudowanie walkiz z przeciwnikiem o: dodanie mnożnika przez statystyki, zadawanie obrażeń przez przeciwnika
- [ ] Komenda "Odpocznij" i jej synonimy odnawia się życie ( można dodać np że 30 min albo 2h wtedy odnowi inną ilość życia i można zamrozić grę na tyle czasu)
- [ ] Dodanie możliości zakupu przedmiotu (np lepszej broni) u Kowala
- [ ] Dodanie możliwości pracy u kowala w zamian za podniesienie statystyk (siły) i zdobycia monet
- [ ] Rozwinięcie gramatyki rozmowy z Kowalem ( zapytanie o dowcip, jaki dziś dzień, jak się czuje itp. itd. - wszystkie pomysły mile widziane
- [ ] Dodanie zapytania postaci o statystyki oraz przedmioty noszące przez nią (np. jaki mam miecz? ) 
- [ ] Rozbudowanie Dokumentacji o podstawowe komendy które można użyc w grze 
- [ ] 15 rozgrzewka i rozciąganie przed kodowaniem! + kubek kawy :)


<br>
- [X] Chodzenie postaci po mapie o x kratek
- [X] NPC Kowal i możliwość przywitania się z nim
- [X] Dodanie pierwszego nieruchomego przeciwnika
- [X] Rozbudowana walka z nieruchomym przeciwnikiem
- [X] Animacja MENU i postaci
- [X] Zaprojektowanie mapy startowej
- [X] Dodanie ekranu statystyk i konsoli
- [X] Obsługa polskich znaków w konsoli
- [X] Implementacja parsera CYK


