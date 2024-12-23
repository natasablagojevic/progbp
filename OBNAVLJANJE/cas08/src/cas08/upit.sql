-- Napisati Java program u kojem se naredbe izvršavaju dinamički koji sa standardnog ulaza učitava ceo broj N i izdvaja indeks, ime, prezime i naziv studijskog programa svih studenata koji su položili tačno N predmeta, kao i spisak tih predmeta (naziv i ocena).

WITH POMOCNA AS (
  SELECT INDEKS, COUNT(*) BROJPOLOZENIH
  FROM DA.ISPIT I
  WHERE OCENA > 5 AND STATUS = 'o'
  GROUP BY INDEKS
  HAVING COUNT(*) > 5
)
SELECT D.INDEKS, IME, PREZIME, SP.NAZIV, P.NAZIV, I.OCENA
FROM  DA.DOSIJE D JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA 
      JOIN POMOCNA POM ON POM.INDEKS = D.INDEKS 
      JOIN DA.ISPIT I ON I.INDEKS = POM.INDEKS
      JOIN DA.PREDMET P ON P.ID = I.IDPREDMETA
WHERE I.OCENA > 5 AND I.STATUS = 'o' 
ORDER BY D.INDEKS, IME, PREZIME, SP.NAZIV, P.NAZIV


-- --------------------------------------------------------------------------------------------

--  Napisati Java program u kojem se naredbe izvršavaju dinamički koji izdvaja ime, prezime i datum diplomiranja za sve studentkinje (pol = ‘z’) programa čiji je identifikator 202 iz tabele DOSIJE. Ukoliko datum diplomiranja nije poznat, ispisati Nije diplomirala.

SELECT IME, PREZIME, DATDIPLOMIRANJA
FROM DA.DOSIJE D 
WHERE POL = 'z' AND IDPROGRAMA = 202;

-- ------------------------------------------------------------------------------------------

SELECT IDPREDMETA, COUNT(*) BROJ_POLOZENIH 
FROM DA.ISPIT I 
WHERE OCENA > 5 AND STATUS = 'o' AND 
      IDPREDMETA NOT IN (
        SELECT UP.IDPREDMETA
        FROM DA.UNETIPREDMETI UP
      )
GROUP BY IDPREDMETA

-- ---------------------------------------------------------------------- 

-- nepolozeni ispit

SELECT INDEKS, OZNAKAROKA, SKGODINA, IDPREDMETA
FROM DA.ISPIT
WHERE OCENA = 5 AND STATUS = 'o' AND SKGODINA = 2015

-- ----------------------------------------------------

INDEKS, IME, PREZIME, NAZIV_STUD_PROGRAMA 

--> STUDENTI KOJI SUPOLOZILI TACNO N PREDMETA 

KURSOR1:
WITH POMOCNA AS (
  SELECT INDEKS, COUNT(*) BROJ_POLOZENIH
  FROM DA.ISPIT 
  WHERE OCENA > 5 AND STATUS = 'o'
  GROUP BY INDEKS 
  HAVING COUNT(*) = 10
)
SELECT INDEKS, IME, PREZIME, NAZIV  
FROM DA.DOSIJE D JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA 
WHERE EXISTS(
  SELECT *
  FROM POMOCNA P 
  WHERE P.INDEKS = D.INDEKS
)
-- -----------------------------------------------

SELECT D.INDEKS, D.IME, D.PREZIME, SP.NAZIV 
FROM DA.DOSIJE D JOIN DA.ISPIT I ON  D.INDEKS = I.INDEKS
     JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA 
WHERE I.OCENA > 5 AND I.STATUS = 'o'
GROUP BY D.INDEKS, D.IME, D.PREZIME, SP.NAZIV 
HAVING COUNT(*) = ?




SELECT NAZIV, OCENA
FROM DA.ISPIT I JOIN DA.PREDMET P ON P.ID = I.IDPREDMETA 
WHERE I.INDEKS = ? AND OCENA > 5 AND STATUS = 'o'
