#include <stdio.h>
int main(void)
{ double a;
  long int i = 87654321, j;
  a = i;
  j = a;
  printf("i = %ld, j = %ld, a = %f\n", i, j, a);
  return 0;
}
