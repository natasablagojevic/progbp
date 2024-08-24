#include "stdio.h"
#include "stdlib.h"

void loadFromFile(char *filename, char *statement)
{
  FILE *f = fopen(filename, "r");
    if (f == NULL) {
      fprintf(stderr, "fopen failed\n");
      exit(EXIT_FAILURE);
    }

  char c;
  int i = 0;

  while (fscanf(f, "%c", &c) != EOF) {
    if (c == 'EOF' || c == ';') {
      statement[i] = '\0';
      break;
    }

    statement[i++] = c;
  }
}

int main() 
{
  char statement[1024];

  sprintf(statement, "INSERT INTO DA.ISPITNIROK VALUES (?,?,?,?,?)");

  printf("%s\n", statement);

  loadFromFile("./upit.sql", statement);

  printf("%s\n", statement);

  return 0;
}