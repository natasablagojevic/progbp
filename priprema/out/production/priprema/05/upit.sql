-- ISPITNI ROK ZA KOJI POSTOJI BAREM JEDNO POLAGANJE 

SELECT SKGODINA, NAZIV
FROM DA.ISPITNIROK IR 
WHERE EXISTS (
  SELECT *
  FROM DA.ISPIT I
  WHERE I.SKGODINA = IR.SKGODINA AND I.OZNAKAROKA = IR.OZNAKAROKA
);

-- -------------------------------------------------------------------

SELECT ID, NAZIV, ESPB
FROM DA.PREDMET P
WHERE ID NOT IN (SELECT IDPREDMETA FROM DA.OBRADJENIPREDMETI);

-- ------------------------------------------------------------------

-- 6.sql 

SELECT  SKGODINA, OZNAKAROKA, IDPREDMETA,
        SUM(
          CASE
            WHEN OCENA > 5 AND STATUS = 'o' THEN 1.0
            ELSE 0.0
          END
        ) / COUNT(*) * 100 USPESNOST 
FROM DA.ISPIT I
WHERE NOT EXISTS (
  SELECT *
  FROM DA.STATISTIKAPOLAGANJA SP 
  WHERE SP.SKGODINA = I.SKGODINA AND SP.OZNAKAROKA = I.OZNAKAROKA AND SP.IDPREDMETA = I.IDPREDMETA
)
GROUP BY SKGODINA, OZNAKAROKA, IDPREDMETA
ORDER BY IDPREDMETA, SKGODINA, OZNAKAROKA;