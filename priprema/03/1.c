static char sqla_program_id[292] = 
{
 '\xac','\x0','\x41','\x45','\x41','\x56','\x41','\x49','\x36','\x41','\x6d','\x38','\x4d','\x64','\x48','\x6f','\x30','\x31','\x31','\x31',
 '\x31','\x20','\x32','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x8','\x0','\x4e','\x41','\x54','\x41','\x53','\x41',
 '\x20','\x20','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x8','\x0','\x31','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0'
};

#include "sqladef.h"

static struct sqla_runtime_info sqla_rtinfo = 
{{'S','Q','L','A','R','T','I','N'}, sizeof(wchar_t), 0, {' ',' ',' ',' '}};


static const short sqlIsLiteral   = SQL_IS_LITERAL;
static const short sqlIsInputHvar = SQL_IS_INPUT_HVAR;


#line 1 "1.sqc"
/*
Napisati C/SQL program kojim se formira izveštaj o studentima koji su padali neki ispit koji sadrži sledeće informacije: ime, prezime i broj indeksa. Za svaki studijski program formirati posebnu sekciju izveštaja sa zaglavljem koje sadrži identifikator i naziv studijskog programa. Izveštaj urediti po nazivu studijskog programa rastuće, a sadržaj svake sekcije urediti po broju indeksa rastuće.
*/

#include <stdio.h>
#include <stdlib.h>


/*
EXEC SQL INCLUDE SQLCA;
*/

/* SQL Communication Area - SQLCA - structures and constants */
#include "sqlca.h"
struct sqlca sqlca;


#line 8 "1.sqc"



/*
EXEC SQL BEGIN DECLARE SECTION;
*/

#line 10 "1.sqc"
 
sqlint32 hIndeks;
char hIme[51];
char hPrezime[51];
sqlint32 hIdPrograma;
char hNazivPrograma[51];

/*
EXEC SQL END DECLARE SECTION;
*/

#line 16 "1.sqc"


void checkSQL(const char *msg)
{
  if (SQLCODE < 0) {
    fprintf(stderr, "%d: %s", SQLCODE, msg);
    
/*
EXEC SQL CONNECT RESET;
*/

{
#line 22 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 22 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 22 "1.sqc"
  sqlastop(0L);
}

#line 22 "1.sqc"

    exit(EXIT_FAILURE);
  }
}

int main()
{ 
  
/*
EXEC SQL CONNECT TO stud2020;
*/

{
#line 29 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 29 "1.sqc"
  sqlaaloc(2,1,1,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 29 "1.sqc"
      sql_setdlist[0].sqltype = 460; sql_setdlist[0].sqllen = 9;
#line 29 "1.sqc"
      sql_setdlist[0].sqldata = (void*)"stud2020";
#line 29 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 29 "1.sqc"
      sqlasetdata(2,0,1,sql_setdlist,0L,0L);
    }
#line 29 "1.sqc"
  sqlacall((unsigned short)29,4,2,0,0L);
#line 29 "1.sqc"
  sqlastop(0L);
}

#line 29 "1.sqc"

  checkSQL("Connection failed");

  
/*
EXEC SQL 
    DECLARE cStudijskiProgram 
    CURSOR FOR 
      SELECT ID, NAZIV
      FROM DA.STUDIJSKIPROGRAM
      ORDER BY NAZIV;
*/

#line 37 "1.sqc"

  checkSQL("DECLARE CURSOR STUDPROGRAM FAILED");

  
/*
EXEC SQL  
    DECLARE cStudent 
    CURSOR FOR 
      SELECT  INDEKS, IME, PREZIME 
      FROM    DA.DOSIJE D
      WHERE   IDPROGRAMA = :hIdPrograma AND 
              EXISTS ( -- STUDENT JE PAO NEKI ISPIT
                SELECT *
                FROM DA.ISPIT I
                WHERE OCENA = 5 AND STATUS = 'o' AND D.INDEKS = I.INDEKS 
              )
      ORDER BY INDEKS;
*/

#line 51 "1.sqc"

  checkSQL("DECLARE CURSOR STUDENTI FAILED");

  
/*
EXEC SQL OPEN cStudijskiProgram;
*/

{
#line 54 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 54 "1.sqc"
  sqlacall((unsigned short)26,1,0,0,0L);
#line 54 "1.sqc"
  sqlastop(0L);
}

#line 54 "1.sqc"

  checkSQL("OPEN CURSOR STUDPROGRAM FAILED");

  while (1) {
    
/*
EXEC SQL 
      FETCH cStudijskiProgram 
      INTO :hIdPrograma, :hNazivPrograma;
*/

{
#line 60 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 60 "1.sqc"
  sqlaaloc(3,2,2,0L);
    {
      struct sqla_setdata_list sql_setdlist[2];
#line 60 "1.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 60 "1.sqc"
      sql_setdlist[0].sqldata = (void*)&hIdPrograma;
#line 60 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 60 "1.sqc"
      sql_setdlist[1].sqltype = 460; sql_setdlist[1].sqllen = 51;
#line 60 "1.sqc"
      sql_setdlist[1].sqldata = (void*)hNazivPrograma;
#line 60 "1.sqc"
      sql_setdlist[1].sqlind = 0L;
#line 60 "1.sqc"
      sqlasetdata(3,0,2,sql_setdlist,0L,0L);
    }
#line 60 "1.sqc"
  sqlacall((unsigned short)25,1,0,3,0L);
#line 60 "1.sqc"
  sqlastop(0L);
}

#line 60 "1.sqc"

    checkSQL("FETCH STUDPROGRAMI FAILED");

    if (SQLCODE == 100) 
      break;

    printf("%d %s\n", hIdPrograma, hNazivPrograma);

    
/*
EXEC SQL OPEN cStudent;
*/

{
#line 68 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 68 "1.sqc"
  sqlaaloc(2,1,3,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 68 "1.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 68 "1.sqc"
      sql_setdlist[0].sqldata = (void*)&hIdPrograma;
#line 68 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 68 "1.sqc"
      sqlasetdata(2,0,1,sql_setdlist,0L,0L);
    }
#line 68 "1.sqc"
  sqlacall((unsigned short)26,2,2,0,0L);
#line 68 "1.sqc"
  sqlastop(0L);
}

#line 68 "1.sqc"

    checkSQL("OPEN CURSOR STUDENT FAILED");

    while (1) {
      
/*
EXEC SQL 
        FETCH cStudent
        INTO :hIndeks, :hIme, :hPrezime;
*/

{
#line 74 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 74 "1.sqc"
  sqlaaloc(3,3,4,0L);
    {
      struct sqla_setdata_list sql_setdlist[3];
#line 74 "1.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 74 "1.sqc"
      sql_setdlist[0].sqldata = (void*)&hIndeks;
#line 74 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 74 "1.sqc"
      sql_setdlist[1].sqltype = 460; sql_setdlist[1].sqllen = 51;
#line 74 "1.sqc"
      sql_setdlist[1].sqldata = (void*)hIme;
#line 74 "1.sqc"
      sql_setdlist[1].sqlind = 0L;
#line 74 "1.sqc"
      sql_setdlist[2].sqltype = 460; sql_setdlist[2].sqllen = 51;
#line 74 "1.sqc"
      sql_setdlist[2].sqldata = (void*)hPrezime;
#line 74 "1.sqc"
      sql_setdlist[2].sqlind = 0L;
#line 74 "1.sqc"
      sqlasetdata(3,0,3,sql_setdlist,0L,0L);
    }
#line 74 "1.sqc"
  sqlacall((unsigned short)25,2,0,3,0L);
#line 74 "1.sqc"
  sqlastop(0L);
}

#line 74 "1.sqc"

      checkSQL("FETCH STUDENT FAILED");

      if (SQLCODE == 100)
        break;

      printf("\t%d %s %s\n", hIndeks, hIme, hPrezime);
    }

    printf("-------------------------------------------------------------\n");

    
/*
EXEC SQL CLOSE cStudent;
*/

{
#line 85 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 85 "1.sqc"
  sqlacall((unsigned short)20,2,0,0,0L);
#line 85 "1.sqc"
  sqlastop(0L);
}

#line 85 "1.sqc"

    checkSQL("CLOSE CURSOR STUDENET FAILED");
    
  }

  
/*
EXEC SQL CLOSE cStudijskiProgram;
*/

{
#line 90 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 90 "1.sqc"
  sqlacall((unsigned short)20,1,0,0,0L);
#line 90 "1.sqc"
  sqlastop(0L);
}

#line 90 "1.sqc"

  checkSQL("CLOSE CURSOR STUDPROGRAMI FAILED");

  
/*
EXEC SQL CONNECT RESET;
*/

{
#line 93 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 93 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 93 "1.sqc"
  sqlastop(0L);
}

#line 93 "1.sqc"

  checkSQL("Stopping connection failed");

  return 0;
}