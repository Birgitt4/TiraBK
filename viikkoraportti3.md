### Viikkoraportti 3:

12.11. klo 15-18

Solver luokka luotu melkein kokonaisuudessaan

13.11. klo 12-13

Solver saatu valmiiksi, mutta toimii vain tietyissä tilanteissa ei kaikilla ratkaistavissa olevilla.

13.11. klo 13-16

Solver bugin etsintää. Bugi löytyi. Ongelma johtui siitä, että tallensin reittiä pinoon, mutta toimivampi tapa on tallentaa jokaiselle nodelle siihen asti kuljettu reitti. Aiemmassa mentiin pieleen siinä vaiheessa, kun lähdettiin alusta yhteen suuntaan, mutta sitten palattiin ihan alkuun ja yritettiin toista suuntaa, mutta kävikin niin, että alkuperäinen suunta oli parempi. Alkuperäisen suunnan nodea ei ollut enää missään tallessa, joten priorityquen päällimäisellä nodella ei ollut reittiä siihen.

13.11. klo 18-23

Uusi kokeilu: GameSolver. Eteni vauhdilla kunnes vastaan tuli ongelmia. GameOfFifteen metodi goDown muutti myös Nodelle asetettua int[] grid muuttujaa. Tämä oli iso mysteeri, mutta tämänkin sain vihdoin selvitettyä. Ongelma johtuu yhtäsuuruusmerkistä taulukoiden välillä. Sitten kun muutat toista taulukkoa toinenkin muuttuu, vaikka olisi kaksi eri muuttujaa. Taulukko täytyy vain kopioida arvo arvolta.


##### Yleisesti:

Testauksien kanssa ei ole tehty mitään tällä viikolla ja koodi ei ole kaunista katsottavaa. Algoritmin toteutuksessa tuli niin paljon muuttujia, että se vei kaiken ajan (eikä valitettavasti ole edes valmis). Olin ymmärtänyt toteutuksen paperilla, mutta itse toteutuksessa tuli vastaan yllättävän paljon ongelmia. Tällä viikolla siis opittu kantapään kautta javan objektien (pätee myös taulukoihin) viittauksista. Ohjelma ei ole edistynyt yhtäpaljon kuin oli tällä viikolla tarkoitus. Suoraan sanottuna hävettää koodin laatu tällä hetkellä ja ensi viikolle riittää töitä!
