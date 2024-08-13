static char sqla_program_id[292] = 
{
 '\xac','\x0','\x41','\x45','\x41','\x56','\x41','\x49','\x64','\x41','\x43','\x62','\x4f','\x61','\x48','\x6f','\x30','\x31','\x31','\x31',
 '\x31','\x20','\x32','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x8','\x0','\x4e','\x41','\x54','\x41','\x53','\x41',
 '\x20','\x20','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
 '\x0','\x0','\x8','\x0','\x32','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
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


#line 1 "2.sqc"
/*
Napisati C/SQL program koji ispisuje indeks, ime, prezime, mesto rođenja (ukoliko je navedeno u bazi) i datum diplomiranja (ukoliko je navedeno u bazi) za studenta sa maksimalnim indeksom iz tabele ISPIT.
*/

#include <stdio.h>
#include <stdlib.h>
#include <sqladef.h>


/*
EXEC SQL INCLUDE SQLCA;
*/

/* SQL Communication Area - SQLCA - structures and constants */
#include "sqlca.h"
struct sqlca sqlca;


#line 9 "2.sqc"



/*
EXEC SQL BEGIN DECLARE SECTION;
*/

#line 11 "2.sqc"
 
sqlint32 hIndeks;
char hIme[51];
char hPrezime[51];
char hMestoRodjenja[51];
short indMestoRodjenja;
char hDatDiplomiranja[11];
short indDatDiplomiranja;

/*
EXEC SQL END DECLARE SECTION;
*/

#line 19 "2.sqc"


void checkSQL(const char *msg)
{
  if (SQLCODE < 0) {
    fprintf(stderr, "%d: %s", SQLCODE, msg);
    
/*
EXEC SQL CONNECT RESET;
*/

{
#line 25 "2.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 25 "2.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 25 "2.sqc"
  sqlastop(0L);
}

#line 25 "2.sqc"

    exit(EXIT_FAILURE);
  }
}

int main()
{ 
  
/*
EXEC SQL CONNECT TO stud2020;
*/

{
#line 32 "2.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 32 "2.sqc"
  sqlaaloc(2,1,1,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 32 "2.sqc"
      sql_setdlist[0].sqltype = 460; sql_setdlist[0].sqllen = 9;
#line 32 "2.sqc"
      sql_setdlist[0].sqldata = (void*)"stud2020";
#line 32 "2.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 32 "2.sqc"
      sqlasetdata(2,0,1,sql_setdlist,0L,0L);
    }
#line 32 "2.sqc"
  sqlacall((unsigned short)29,4,2,0,0L);
#line 32 "2.sqc"
  sqlastop(0L);
}

#line 32 "2.sqc"

  checkSQL("Connection failed");

  
/*
EXEC SQL 
    SELECT d.indeks, d.ime, d.prezime, d.mestorodjenja, d.datdiplomiranja
    INTO :hIndeks, :hIme, :hPrezime, :hMestoRodjenja :indMestoRodjenja, :hDatDiplomiranja :indDatDiplomiranja
    FROM da.dosije d
    WHERE d.indeks = (SELECT MAX(indeks) FROM da.ispit);
*/

{
#line 39 "2.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 39 "2.sqc"
  sqlaaloc(3,5,2,0L);
    {
      struct sqla_setdata_list sql_setdlist[5];
#line 39 "2.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 39 "2.sqc"
      sql_setdlist[0].sqldata = (void*)&hIndeks;
#line 39 "2.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 39 "2.sqc"
      sql_setdlist[1].sqltype = 460; sql_setdlist[1].sqllen = 51;
#line 39 "2.sqc"
      sql_setdlist[1].sqldata = (void*)hIme;
#line 39 "2.sqc"
      sql_setdlist[1].sqlind = 0L;
#line 39 "2.sqc"
      sql_setdlist[2].sqltype = 460; sql_setdlist[2].sqllen = 51;
#line 39 "2.sqc"
      sql_setdlist[2].sqldata = (void*)hPrezime;
#line 39 "2.sqc"
      sql_setdlist[2].sqlind = 0L;
#line 39 "2.sqc"
      sql_setdlist[3].sqltype = 461; sql_setdlist[3].sqllen = 51;
#line 39 "2.sqc"
      sql_setdlist[3].sqldata = (void*)hMestoRodjenja;
#line 39 "2.sqc"
      sql_setdlist[3].sqlind = &indMestoRodjenja;
#line 39 "2.sqc"
      sql_setdlist[4].sqltype = 461; sql_setdlist[4].sqllen = 11;
#line 39 "2.sqc"
      sql_setdlist[4].sqldata = (void*)hDatDiplomiranja;
#line 39 "2.sqc"
      sql_setdlist[4].sqlind = &indDatDiplomiranja;
#line 39 "2.sqc"
      sqlasetdata(3,0,5,sql_setdlist,0L,0L);
    }
#line 39 "2.sqc"
  sqlacall((unsigned short)24,1,0,3,0L);
#line 39 "2.sqc"
  sqlastop(0L);
}

#line 39 "2.sqc"

  checkSQL("SELECT INTO");

  printf("%d %s %s %s %s\n", hIndeks, hIme, hPrezime, (indMestoRodjenja < 0 ? "NULL" : hMestoRodjenja), (indDatDiplomiranja < 0 ? "NULL" : hDatDiplomiranja));

  
/*
EXEC SQL CONNECT RESET;
*/

{
#line 44 "2.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 44 "2.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 44 "2.sqc"
  sqlastop(0L);
}

#line 44 "2.sqc"

  checkSQL("Stopping connection failed");

  return 0;
}
