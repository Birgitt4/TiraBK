## Ohjelman toteutus

### Yleisrakenne
Ohjelmassa on simppeli graafinen käyttöjärjestelmä sekä luokat pelilaudalle, IDA* algoritmille ja prioriteetti jonolle, sekä verkon solmua kuvaava luokka. Pelilaudan sekoitus, laattojen liikkeet sekä ratkaistavuuden tarkastamisesta huolehtii pelilautaa kuvaava luokka. Käyttöjärjestelmä on tehty hyödyntäen JavaFX:n tarjoamia paketteja. Järjestelmässä on napit performance testing, shuffle ja solve, solve nappia painamalla algoritmi ensin ratkaisee reitin ja sitten esittää tämän animaationa käyttäjälle.

Omassa pakkauksessaan on vielä luokka PerformanceTests, joka ratkaisee satunnaisia pelejä ja tallentaa näistä keston sekä kuinka monta verkon solmua luotiin. Testit voi ajaa käyttöliittymästä käsin, joka pyytää tiedoston nimeä, jonne tulokset tallennetaan. Testien ajamisen kesto vaihtelee, mutta kannattaa varautua 10 minuutin odotteluun.

### Algoritmi
GameSolver luokassa on toteutettuna IDA* algoritmi [tämän sivun alhaalla olevan pseudokoodin pohjalta](https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/). Samassa luokassa on myös metodit heuristiikkojen laskemiseen. Heuristiikan laskemiset ovat toteutettu itse. Algoritmi käyttää myös itse toteutettua prioriteetti jonoa. Kuten todettu määrittelydokumentissa, pahimman tapauksen aikavaativuus on O(b^d), missä b on branching factor, eli tämän pelin tapauksessa 3 ja d kertoo mihin syvyyteen asti verkossa/puussa haetaan. Tässä tapauksessa d vastaa ratkaisun pituutta. Aikavaativuus vastaa kuitenkin vain normaalia syvyyshakua puussa ilman heuristiikkoja. Heuristiikoiden kanssa aikavaativuus on lähempänä O(b^(d-k)), missä k riippuu käytetyistä heuristiikoista. Testausdokumentissa mittauksia ratkaisun löytämiseen kuluneista ajoista.

Prioriteetti jono on kaikessa yksinkertaisuudessaan taulukko, jota täytämme indeksistä 1 lähtien helpottamaan laskuja. Solmun i oikea lapsi löytyy taulukon indeksistä 2*i ja vasen lapsi indeksistä 2*i+1, solmun vanhempi löytyy indeksistä i/2 pyöristettynä alaspäin. Näin saamme esitettyä keko rakenteen taulukkona. Lisätessä solmun prioriteetti jonoon, lisäämme solmun pohjalle ja vaihdamme kyseisen solmun ja tämän vanhemman paikkaa taulukossa kunnes vanhemman heuristiikka on pienempää kuin lisätyn solmun. Poistettaessa jonon päältä solmun vaihdamme viimeisen solmun jonon päälle (taulukon ensimmäiseksi) ja vaihdamme tämän ja pienemmän lapsen paikkaa, kunnes molempien lasten heuristiikka on suurempaa kuin käsittelyssä olevan solmun. Tämän jälkeen prioriteetti jono on taas järjestyksessä.

### Käyttöliittymä
Käyttöliittymässä on yksi päänäkymä. Näkymässä on vasemmalla 15-peli, jonka paloja klikkaamalla käyttäjä voi itse pelata peliä, oikealla on napit solve, shuffle ja performance testing.

![näkymä](https://github.com/Birgitt4/TiraBK/blob/main/dokumentaatio/Kuvat/solved.jpg)

Napista performance testing uusi Stage ikkuna ponnahtaa näytölle. Tämä kysyy, minne testien tulokset tallennetaan sekä varoittaa, että testien tekemiseen kuluu aikaa. Näkymän voi sulkea napista "cancel" tai tiedoston syötettyään ajaa napista "Drive tests!". Testit tallentuu kyseiseen tiedostoon muodossa:

siirtojen määrä:aika microsekuntteina:generoidut solmut

Nämä tallentuu ilman välilyöntejä erotettuina kaksoispisteellä kaikki omille riveilleen. Testit voi ajaa useamman kerran samaan tiedostoon menettämättä aiempia tuloksia.

### Puutteet ja parannusehdotukset
Algoritmi toimii hitaasti vaikeimmilla pelin järjestyksillä, jotka vaativat noin 65 siirtoa tai enemmän. 80 siirtoa vaativia aloitustilanteita on 17, kun kaikkiaan ratkaistavissa olevia aloitustilanteita on 16!/2 eli 10 461 394 944 000. Kuitenkin keskiverto tilanteista algoritmi selviää ihan kiitettävästi. Yksi tapa nopeuttaa ratkaisun löytämistä on vaihtaa heuristiikkaa. Pattern database heuristiikka toimii tähän hyvin, mutta tämä taas vie paljon tilaa.

Olen ottanut napit käytöstä pois aina, kun jotain muuta on menossa taustalla. Tämä koska ohjelma ei enää vastannut, jos samaa aikaa teki jotain. Tähän olisi varmaan ollut parempikin ratkaisu.

#### Lähteet
https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/
https://www.sciencedirect.com/science/article/pii/S0004370201000947
