## Ohjelman toteutus

### Yleisrakenne
Ohjelmassa on simppeli graafinen käyttöjärjestelmä sekä luokat pelilaudalle, IDA* algoritmille ja prioriteetti jonolle, sekä verkon solmua kuvaava luokka. Pelilaudan sekoitus, laattojen liikkeet sekä ratkaistavuuden tarkastamisesta huolehtii pelilautaa kuvaava luokka. Käyttöjärjestelmä on tehty hyödyntäen JavaFX:n tarjoamia paketteja. Järjestelmässä on napit shuffle ja solve, solve nappia painamalla algoritmi ensin ratkaisee reitin ja sitten esittää tämän animaationa käyttäjälle.

### Algoritmi
GameSolver luokassa on toteutettuna IDA* algoritmi [tämän sivun alhaalla olevan pseudokoodin pohjalta](https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/). Samassa luokassa on myös metodit heuristiikkojen laskemiseen. Heuristiikan laskemiset ovat toteutettu itse. Algoritmi käyttää itse toteutettua prioriteetti jonoa. 

Prioriteetti jono on kaikessa yksinkertaisuudessaan taulukko, jota täytämme indeksistä 1 lähtien helpottamaan laskuja. Solmun i oikea lapsi löytyy taulukon indeksistä 2*i ja vasen lapsi indeksistä 2*i+1, solmun vanhempi löytyy indeksistä i/2 pyöristettynä alaspäin. Näin saamme esitettyä keko rakenteen taulukkona. Lisätessä solmun prioriteetti jonoon, lisäämme solmun pohjalle ja vaihdamme kyseisen solmun ja tämän vanhemman paikkaa taulukossa kunnes vanhemman heuristiikka on pienempää kuin lisätyn solmun. Poistettaessa jonon päältä solmun vaihdamme viimeisen solmun jonon päälle (taulukon ensimmäiseksi) ja vaihdamme tämän ja pienemmän lapsen paikkaa, kunnes molempien lasten heuristiikka on suurempaa kuin käsittelyssä olevan solmun. Tämän jälkeen prioriteetti jono on taas järjestyksessä.

### Puutteet ja parannusehdotukset
Algoritmi toimii hitaasti vaikeimmilla pelin järjestyksillä, jotka vaativat noin 70 siirtoa. 80 siirtoa vaativia aloitustilanteita on 17, kun kaikkiaan ratkaistavissa olevia aloitustilanteita on 16!/2 eli 10 461 394 944 000. Kuitenkin keskiverto tilanteista algoritmi selviää ihan kiitettävästi. Yksi tapa nopeuttaa ratkaisun löytämistä on vaihtaa heuristiikkaa. Pattern database heuristiikka toimii tähän hyvin, mutta tämä taas vie paljon tilaa.

#### Lähteet
https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/
