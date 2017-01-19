#include <stdio.h>

/*---------------------------------*/
void print_variable(int i);

void print_variable_2(void);

int f1(int i);

void f2(void);
/*---------------------------------*/

int i = 0;

/*---------------------------------*/

int main(void)
{
  print_variable_2();

  f2();

  i=f1(i);
  i=f1(50);

  print_variable(i);

  return 0;
}

/*---------------------------------*/

void print_variable(int i)
{
  printf("%d\n", i++);
}

/*---------------------------------*/


void print_variable_2(void)
{
  printf("%d\n", i++);
}

/*---------------------------------*/

int f1(int i)
{
  printf("Begining of f1\n");
  print_variable_2();
  {
    int i = 100;
    print_variable(i);
  }

  print_variable(i);

  printf("Ending of f1\n\n");

  return ++i;
}

/*---------------------------------*/

void f2(void)
{ 
  printf("Begining of f2\n");
  print_variable_2();
  {
    int i = 200;
    print_variable(i);
  }

  print_variable(i);

  printf("Ending of f2\n\n");
}

/*---------------------------------*/
