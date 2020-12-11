## Määrittelydokumentti:

### Sovelluksesta:
Sovellus toimii Helsingin yliopiston kurssin Tietorakenteet ja algoritmit harjoitustyönä. Työn pääasiallinen toiminta on ratkaista 15-peli. Ohjelma on koodattu javalla (englanniksi) ja dokumentaatiot kirjoitan suomeksi. Projektin tekijä opiskelee Matemaattisten tieteiden kandiohjelmassa, tietojenkäsittelytiede *sivuaineena*.

### Algoritmit ja tietorakenteet
Sovellus ratkaisee 15-pelin käyttämällä iterative deepening A* (IDA*) -algoritmia. Algoritmi hyödyntää heuristiikana Manhattan etäisyyttä sekä linear conflict -heuristiikkaa, mikä tarkentaa manhattan etäisyyden antamaa lukua. Ideana on, että jos samalla rivillä tai sarakkeella ovat luvut, jotka ovat oikealla rivillä/sarakkeella, mutta toisiinsa nähden väärässä järjestyksessä, täytyy toisen kiertää toinen ja näin kasvattaa siirtojen määrää. IDA* algoritmin käyttöön päädyin todettuani pelkän A* algoritmin vievän aivan liikaa tilaa, sekä muiden samankaltaisen projektin tehneiden kokemuksilla.

IDA* on lyhyimmän polun etsimiseen tarkoitettu algoritmi. IDA* käyttää A* algoritmista tuttua tapaa valita heuristiikan mukaan parhaimman oloinen solmu seuraavaksi, mutta haku etenee syvyyshaun lailla käyttäen paljon vähemmän muistia kuin A*. Haussa on mukana myös "raja-arvo", jonka mukaan palataan taaksepäin hakupuussa. Tarkoittaen, että jos vastaan tulee vain raja-arvoa suuremman heuristiikan omaavia solmuja, palaamme taaksepäin. Jos tällä raja-arvolla ei maalia löytynyt, arvoa kasvatetaan ja aloitetaan haku alusta.

Vaativissa tilanteissa tämä hidastaa ratkaisun löytämistä, koska haku aloitetaan aina alusta. Emme kuitenkaan voi asettaa tätä raja-arvoa alkuun suuremmaksi, koska algoritmi ei tällöin takaa minimi ratkaisua. Algoritmi käyttää prioriteetti jonoa valitsemaan seuraavaksi solmuksi pienimmän heuristiikan omaavan solmun.

15-pelin voi esittää verkkona siten, että pelin tyhjä ruutu on aina tämänhetkinen solmu ja naapurit ovat mahdolliset suunnat mihin tyhjä paikka liikutetaan (tai naapurit, jotka voidaan liikuttaa tyhjään paikkaan).

Kaikki mahdolliset tavat järjestää 15 lukua ja tyhjä ruutu eivät kuitenkaan ole mahdollista ratkaista. Pystymme tarkastamaan, onko tietty järjestys ratkaistavissa laskemalla inversioiden määrän laudalla. (Tästä selkeä kirjoitus ensimmäisessä lähteessä.)

### Aika- ja tilavaativuudet

Optimaalisen ratkaisun löytäminen 15-pelille kuuluu luokan NP ongelmiin, eli ongelmalle ei ole olemassa polynomisen ajan vaativaa ratkaisua. Minimi ratkaisuiden polkujen pituuden vaihtelevat luvusta 0 lukuun 80. Keskivertaisesti ratkaisun pituudeksi tulee 53 siirtoa.

#### Lähteet:

https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/ \
https://stackoverflow.com/questions/23630620/which-is-the-best-algorithm-to-provide-moves-to-solve-15-puzzle
https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/
