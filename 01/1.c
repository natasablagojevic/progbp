static char sqla_program_id[292] = 
{
 '\xac','\x0','\x41','\x45','\x41','\x56','\x41','\x49','\x66','\x41','\x58','\x70','\x4e','\x4a','\x44','\x6f','\x30','\x31','\x31','\x31',
 '\x31','\x20','\x32','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x20','\x8','\x0','\x44','\x42','\x32','\x49','\x4e','\x53',
 '\x54','\x36','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0','\x0',
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
// napisati C/SQL program koji ispisuje maksimalni indeks iz tabele ISPIT 

#include "stdio.h"
#include "stdlib.h"


/*
EXEC SQL INCLUDE SQLCA;
*/

/* SQL Communication Area - SQLCA - structures and constants */
#include "sqlca.h"
struct sqlca sqlca;


#line 6 "1.sqc"



/*
EXEC SQL BEGIN DECLARE SECTION;
*/

#line 8 "1.sqc"

sqlint32 hIndeks;

/*
EXEC SQL END DECLARE SECTION;
*/

#line 10 "1.sqc"


void checkSQL(const char *msg)
{
  if (SQLCODE < 0) { 
    // obrada greske 
    fprintf(stderr, "Greska: %d %s", SQLCODE, msg);

    
/*
EXEC SQL CONNECT RESET;
*/

{
#line 18 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 18 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 18 "1.sqc"
  sqlastop(0L);
}

#line 18 "1.sqc"
 // prekid konekcije sa bazom

    exit(EXIT_FAILURE);
  }
}

int main() 
{
  
/*
EXEC SQL CONNECT TO stud2020;
*/

{
#line 26 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 26 "1.sqc"
  sqlaaloc(2,1,1,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 26 "1.sqc"
      sql_setdlist[0].sqltype = 460; sql_setdlist[0].sqllen = 9;
#line 26 "1.sqc"
      sql_setdlist[0].sqldata = (void*)"stud2020";
#line 26 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 26 "1.sqc"
      sqlasetdata(2,0,1,sql_setdlist,0L,0L);
    }
#line 26 "1.sqc"
  sqlacall((unsigned short)29,4,2,0,0L);
#line 26 "1.sqc"
  sqlastop(0L);
}

#line 26 "1.sqc"
 // USER student USING abcdef;
  checkSQL("Konekcija");


  
/*
EXEC SQL 
    SELECT MAX(INDEKS)
    INTO :hIndeks
    FROM da.ispit;
*/

{
#line 33 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 33 "1.sqc"
  sqlaaloc(3,1,2,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 33 "1.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 33 "1.sqc"
      sql_setdlist[0].sqldata = (void*)&hIndeks;
#line 33 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 33 "1.sqc"
      sqlasetdata(3,0,1,sql_setdlist,0L,0L);
    }
#line 33 "1.sqc"
  sqlacall((unsigned short)24,1,0,3,0L);
#line 33 "1.sqc"
  sqlastop(0L);
}

#line 33 "1.sqc"

  checkSQL("SELECT upit");

  printf("%d\n", hIndeks);

  
/*
EXEC SQL CONNECT RESET;
*/

{
#line 38 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 38 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 38 "1.sqc"
  sqlastop(0L);
}

#line 38 "1.sqc"

  checkSQL("Prekidanje konekcije");

  return 0;
}


/*
  terminal:
  db2 "connect to stud2020"
  db2 "DESCRIBE TABLE da.ispit"

*/