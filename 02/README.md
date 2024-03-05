# Kursori 

- Koraci:
  1. deklaracija kursora
  2. otvaranje kursora
  3. iteriranje kroz kursor 
  4. zatvaranje kursora

- Postoje 4 vrste kursora:
  1. citajuci
  2. pisuci
  3. brisuci - u from klauzuli da imamo jednu tabelu, ne sme having, groupby, intersect, union, 
  4. azurirajuci - ukoliko eksplicitno navedemo **FOR UPDATE ALL**

#### Otvaranje kursora

```
OPEN <IME_KURSORA>

```

#### Iteriranje kroz kursor 
- citanje se zavrsava, ukoliko se **SQLCODE** dostigne vrednost 100

```
FETCH <IME_KURSORA>
INTO <LISTA_MATICNIH_PROMENLJIVIH>
```

#### Zatvaranje kursora
```
CLOSE <IME_KURSORA>
[WITH RELEASE]
```

#### PREVODJENJE

```
./prevodjenje 2
```

#### Neke db2 komande iz terminala koje mogu biti korisne:

```
db2 "select * form da.dosije"
db2 "desctribe table da.ispit"
```
