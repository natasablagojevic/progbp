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