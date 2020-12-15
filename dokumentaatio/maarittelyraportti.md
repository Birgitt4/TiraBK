## Määrittelydokumentti:

### Sovelluksesta:
Sovellus toimii Helsingin yliopiston kurssin Tietorakenteet ja algoritmit harjoitustyönä. Työn pääasiallinen toiminta on ratkaista 15-peli. Ohjelma on koodattu javalla (englanniksi) ja dokumentaatiot kirjoitan suomeksi. Projektin tekijä opiskelee Matemaattisten tieteiden kandiohjelmassa, tietojenkäsittelytiede *"sivuaineena"*.

### Algoritmit ja tietorakenteet
Sovellus ratkaisee 15-pelin käyttämällä iterative deepening A* (IDA*) -algoritmia. Algoritmi hyödyntää heuristiikana Manhattan etäisyyttä sekä linear conflict -heuristiikkaa, mikä tarkentaa manhattan etäisyyden antamaa lukua. Ideana on, että jos samalla rivillä tai sarakkeella ovat luvut, jotka ovat oikealla rivillä/sarakkeella, mutta toisiinsa nähden väärässä järjestyksessä, täytyy toisen kiertää toinen ja näin kasvattaa siirtojen määrää. IDA* algoritmin käyttöön päädyin todettuani pelkän A* algoritmin vievän aivan liikaa tilaa, sekä muiden samankaltaisen projektin tehneiden kokemuksilla.

IDA* on lyhyimmän polun etsimiseen tarkoitettu algoritmi. IDA* käyttää A* algoritmista tuttua tapaa valita heuristiikan mukaan parhaimman oloinen solmu seuraavaksi, mutta haku etenee syvyyshaun lailla käyttäen paljon vähemmän muistia kuin A*. Haussa on mukana myös "raja-arvo", jonka mukaan palataan taaksepäin hakupuussa. Tarkoittaen, että jos vastaan tulee vain raja-arvoa suuremman heuristiikan omaavia solmuja, palaamme taaksepäin. Jos tällä raja-arvolla ei maalia löytynyt, arvoa kasvatetaan ja aloitetaan haku alusta.

Vaativissa tilanteissa tämä hidastaa ratkaisun löytämistä, koska haku aloitetaan aina alusta. Emme kuitenkaan voi asettaa tätä raja-arvoa alkuun suuremmaksi, koska algoritmi ei tällöin takaa minimi ratkaisua. Algoritmi käyttää prioriteetti jonoa valitsemaan seuraavaksi solmuksi pienimmän heuristiikan omaavan solmun.

15-pelin voi esittää verkkona siten, että pelin tyhjä ruutu on aina tämänhetkinen solmu ja naapurit ovat mahdolliset suunnat mihin tyhjä paikka liikutetaan (tai naapurit, jotka voidaan liikuttaa tyhjään paikkaan).

Kaikki mahdolliset tavat järjestää 15 lukua ja tyhjä ruutu eivät kuitenkaan ole mahdollista ratkaista. Pystymme tarkastamaan, onko tietty järjestys ratkaistavissa laskemalla inversioiden määrän laudalla. (Tästä selkeä kirjoitus ensimmäisessä lähteessä.)

### Aika- ja tilavaativuudet

Optimaalisen ratkaisun löytäminen 15-pelille kuuluu luokan NP ongelmiin, eli ongelmalle ei ole olemassa polynomisessa ajassa toimivaa ratkaisua. Minimi ratkaisuiden polkujen pituuden vaihtelevat luvusta 0 lukuun 80. Keskivertaisesti ratkaisun pituudeksi tulee 53 siirtoa.

IDA* algoritmin pahimman (paino sanalla pahimman) tapauksen aikavaativuus on O(b^d), missä b on branching factor eli solmun lasten määrä. 15-pelin tapauksessa suurin solmun lasten määrä on 3, kun emme ota mukaan juuri tekemäämme siirtoa. Luku d kuvaa optimaalisen siirtojen määrää pelin ratkaisemiseksi, eli d on suurimmillaan 80. Aikavaativuutta parantaa heuristiikat sekä tarkemmat arviot solmun lasten määrästä.

Algoritmin tilavaativuus on O(bd), mikä johtuu siitä, että haku etenee puussa syvyyshaun lailla, eli rekursiopinossa tulee olemaan enintään d kutsua ja jokaisessa tasossa/syvyydessä tallennat kaikki solmun lapset talteen.

#### Lähteet:

https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/ \
https://stackoverflow.com/questions/23630620/which-is-the-best-algorithm-to-provide-moves-to-solve-15-puzzle
https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/
https://www.sciencedirect.com/science/article/pii/S0004370201000947?via%3Dihub
