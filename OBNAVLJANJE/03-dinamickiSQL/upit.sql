UPDATE DA.PREDMET 
SET ESPB = ESPB + 1;

-- ------------------------------

SELECT ID, OZNAKA, NAZIV
FROM DA.STUDIJSKIPROGRAM
WHERE ID = ?