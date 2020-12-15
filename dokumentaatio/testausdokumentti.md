## Testausdokumentti

### JUnit yksikkötestaukset
Junit testejä on kirjoitettu sovelluslogiikasta vastaaville luokille. Näihin pystyy suorittamaan jacoco testikattavuus raportin terminaalista käsin komennolla: mvn test jacoco:report. Raportista alla kuvat:

![koko](https://github.com/Birgitt4/TiraBK/blob/main/dokumentaatio/Kuvat/jacocoFinal.jpg)

![logiikat](https://github.com/Birgitt4/TiraBK/blob/main/dokumentaatio/Kuvat/jacocoLogic.jpg)

Olen pyrkinyt testaamaan mahdollisia virhe tilanteita, kuten yrityksiä tehdä ei sallittuja siirtoja. Laskujen, kuten inversiot ja heuristiikat, toimivuutta on pyritty testaamaan muutamilla tapauksilla.

PerformanceTests luokasta on testattu, että ohjelma ei kirjoita olemassa olevan tiedoston päälle vaan tämän jatkoksi. Testien ajaksi ohjelma muodostaa tiedoston "testfile.txt", jonka pitäisi kuitenkin poistua testien jälkeen.

Se, että antaako algoritmi oikean suuruisia tuloksia on todettu testaamalla paljon manuaalisesti. Tähän on käytetty tarkastusapuna sivulta https://n-puzzle-solver.appspot.com/ löytyvää pelin ratkaisijaa. Ratkaisuiden pituudet ovat täsmänneet.

### Suorituskykytestausta
Tulokset ovat saatu parin PerformanceTests luokan suoritusten perusteella. Käyttöliittymästä käsin nämä testit voidaan ajaa uudestaan. Suoritus ajaa 1000 pienempää, eli suunnilleen 1-30 askeleen ratkaisuja ja laskee näistä keskiarvot. Vaikeampia ajetaan vain 50. Näiden odotettu ratkaisun pituus on noin 45-60. Näistäkin laketaan keskiarvot, mutta on hyvä huomata, että esim. 63 askeleen ratkaisu ei ole yhtä yleinen kuin 53 ja tämän vaikeaa ratkaisua voi olla etsitty vain yhden kerran. Kuitenkin nämä 50 vievät jo jonkin verran aikaa, joten jätin sen tarpeeksi pieneksi, jotta testien ajaminen olisi "helpompaa".

Isommat testit saadaan aikaiseksi käyttämällä sovelluksen tarjoamaa sekoitusta ja pienempiin tehdään 40 satunnaista siirtoa etsitään ratkaisu, toistetaan tämä 250 kertaa ja sitten nollataan taulukko alkuun. Tämä tehdään 4 kertaa. Pienemmät testit menee läpi silti erittäin nopeasti. Testeihin tulee silti turhan harvoin 30-40 siirron ratkaisuja.

![logaritminen](https://github.com/Birgitt4/TiraBK/blob/main/dokumentaatio/Kuvat/chart1.jpg)

X-akselilla on ratkaisun siirtojen määrä. Huomaa, että y-akseli on logaritmisella asteikolla. Sinisellä on merkittynä ratkaisun etsimiseen kulunut aika mikrosekunteina ja oranssi kertoo, kuinka monta nodea luotiin yhteensä ratkaisun etsimisessä. Kaikki nämä nodet eivät yhtä aikaa ole muistissa, joten tämä ei esitä ratkaisun tilavaativuutta, joka on O(b* d), missä d on jälleen ratkaisun siirtojen määrä ja b solmun lasten maksimi määrä (eli 3). Ratkaisun kesto riippunee vahvasti luotujen solmujen määrästä. Harmaalla on esitetty pahimman aikavaativuuden tuottama käyrä. Tämä ei täysin vastaa totuutta, sillä tämän oletuksena on, että noden luomiseen kuluu 1 mikrosekunti. Kuitenkin tähän työhön tämä on "tarpeeksi" lähellä totuutta.

Alla on vielä ratkaisun löytämiseen kuluneesta ajasta mikrosekunteina kuva, missä myös y-akseli on lineaarinen. Tästä on muutama enemmän aikaa vaatinut tulos karsittu pois, jotta kuvasta tulisi hieman tarkempi (helpompiin tapauksiin).

![lineaarinen](https://github.com/Birgitt4/TiraBK/blob/main/dokumentaatio/Kuvat/chart2.jpg)

Tästä näkee, että alle 50 siirron vaativat ratkaisut löytyvät hyvinkin nopeasti, mutta sitten alkaa tulla enemmän aikaa vaativia pelejä. Esimerkiksi tämän taulukon eniten aikaa vaatinut ratkaisu kesti melkein 30 sekuntia.

