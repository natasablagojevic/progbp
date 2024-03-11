# Aplikacije sa dinamickim SQL naredbama 

- imamo razlicite nivoe onoga sto treba da radimo 

- Dve faze izvrsavanja
  - **PRIPREMA NAREDME** = iz matine promenljive koja predstavlja izvlacimo naredbu i pravimo izvrsivi objekat (bice upit neki)
  - **IZVRSAVANJE** = naredba koja je pripremljena i drugoj fazi se izvrsava 

    - ne smemo koristiti host promenljive prilikom upita, vec cemo koristiti prametre tj. pisacemo parametrizovane upite 

- **EXECUTE IMMEDIATE** = priprema + izvrsavanje 
  - ne koristimo uvek = samo ako nemamo parametre, kada je sve cisto
  - ne mozemo koristi u SELECT-u 
  - nema mogucnost da cita, vec samo prosledjuje naredbu 
  - mozemo izvrsiti sledece naredbe:
    1. DELETE 
    2. INSERT 
    3. UPDATE 
    4. ALTER 
    - sve sto prosledjujemo tabeli, a ne citamo nazad 
  
  - ne sme da pocinje sa EXEC SQL i da se zavrsava sa ; 

- **PREPARE**
  - uzima tekstualni oblik naredbe i pretvara je u izvrsivu naredbu 
  - priprema naredbu iz maticne promenljive 
  - mozemo da koristimo parametre
  - u fazi izvrsavanja cu zameniti sa konkretnom host promenljivom 
  - parametri mogu da budu:
    1. PARAMETRIZOVANI
    2. NEPARAMETRIZOVANI 
  - ako iz konteksta ne mogu da zakljucim moram da koristim neparametrizovane upite 

- **EXECUTE**
  - menja parametar iz pripreme 
  - 

- **DESCRIBE**
  - sluzi za opstiji 



- menjamo funkciju greske
  - ne menjaju se upiti 
  - da nam izvuce i opis greske 
