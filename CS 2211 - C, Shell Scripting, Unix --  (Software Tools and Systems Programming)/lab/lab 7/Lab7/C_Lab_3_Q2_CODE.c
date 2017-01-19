#include <stdio.h>
int main(void)
{ double a = 87654321.0, b;
  b = a + 1;
  printf("a %c= a + 1\n", a == b ? '=' : '!');
  return 0;
}
