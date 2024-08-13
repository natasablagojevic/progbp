static char sqla_program_id[292] = 
{
 '\xac','\x0','\x41','\x45','\x41','\x56','\x41','\x49','\x69','\x41','\x4e','\x62','\x4f','\x61','\x48','\x6f','\x30','\x31','\x31','\x31',
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
/* Napisati C-SQL program koji ispisuje maksimali indeks iz tabele ISPIT */

#include <stdio.h>
#include <stdlib.h>


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
    fprintf(stderr, "%d: %s", SQLCODE, msg);
    
/*
EXEC SQL CONNECT RESET;
*/

{
#line 16 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 16 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 16 "1.sqc"
  sqlastop(0L);
}

#line 16 "1.sqc"

    exit(EXIT_FAILURE);
  }
}

int main()
{ 
  
/*
EXEC SQL CONNECT TO stud2020 USER natasa USING 12345@Natasa;
*/

/*
SQL0104N  An unexpected token "12345" was found following 
"USING".  Expected tokens may include:  "ADD".

*/

  checkSQL("Connection failed");

  
/*
EXEC SQL 
    SELECT MAX(indeks)
    INTO :hIndeks
    FROM da.ispit;
*/

{
#line 29 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 29 "1.sqc"
  sqlaaloc(3,1,1,0L);
    {
      struct sqla_setdata_list sql_setdlist[1];
#line 29 "1.sqc"
      sql_setdlist[0].sqltype = 496; sql_setdlist[0].sqllen = 4;
#line 29 "1.sqc"
      sql_setdlist[0].sqldata = (void*)&hIndeks;
#line 29 "1.sqc"
      sql_setdlist[0].sqlind = 0L;
#line 29 "1.sqc"
      sqlasetdata(3,0,1,sql_setdlist,0L,0L);
    }
#line 29 "1.sqc"
  sqlacall((unsigned short)24,1,0,3,0L);
#line 29 "1.sqc"
  sqlastop(0L);
}

#line 29 "1.sqc"
 
  checkSQL("Select-Into failed");

  printf("Najveci indeks je %d\n", hIndeks);

  
/*
EXEC SQL CONNECT RESET;
*/

{
#line 34 "1.sqc"
  sqlastrt(sqla_program_id, &sqla_rtinfo, &sqlca);
#line 34 "1.sqc"
  sqlacall((unsigned short)29,3,0,0,0L);
#line 34 "1.sqc"
  sqlastop(0L);
}

#line 34 "1.sqc"

  checkSQL("Stopping connection failed");

  return 0;
}