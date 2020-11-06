Viikkoraportti 2:

4.11. klo 11.30 -17.00

Tarkastettu viikon tavoitteet ja ruvettu hahmottelemaan ohjelmaa. Tehty metodit shuffle, inversions ja isSolvable. Tehty testejä inversioiden määrän laskemiseen ja ratkaisun olemassa olon määrittämiseen.

4.11. klo 18-20 tutkittu vielä A* algoritmin toimintatapaa ja mallinnettu itselleen, miten sen tässä pelissä pitäisi toimia. Algoritmi nyt ymmärretty!

6.11. klo 17-19.00 jacoco laitettu toimimaan ja manhattan etäisyyden laskemineen tehty metodit ja näille testit. Checkstyle otettu käyttöön ja kirjoitettu viikkoraporttia.

Tällä viikolla opittu siis A* algoritmi, sekä omien testien tekeminen. Tällä viikolla hommat ovat vaikuttaneet selkeämmiltä. Paitsi pakettien kanssa ollut jostain syystä hiukka ongelmia. En muista näistä juurikaan, mutta main luokkaa ei löytynyt ennen kuin laitoin sen sitten ylemmäs hakemistossa jne. Mutta nyt saan ainakin ajettua itse ohjelman. Checkstyle otettu käyttöön, josta tällä hetkellä paljon virheitä (puuttuvia välilyöntejä suurimmaksi osaksi, joita en nyt rupea enää korjailemaan). Testikattavuus on 90% luokkaa. En tiedä miten shuffle metodia testaisi. Riittäisikö vain katsoa, että järjestys on muuttunut tämän jälkeen?

Ensi viikolla uskon alkavani toteuttamaan itse algortimia. Tähän käytän aluksi valmista priorityqueueta, joka täytyy myöhemmin tehdä sitten kokonaan itse.

Omasta mielestäni on testi tuollaiselle tilanteelle, mikä on alla keltaisella. Silti lukee 1 of 4 branches missed?

![isSolvabletesti](https://github.com/Birgitt4/TiraBK/blob/main/testikansio/testikattavuudesta.jpg)
