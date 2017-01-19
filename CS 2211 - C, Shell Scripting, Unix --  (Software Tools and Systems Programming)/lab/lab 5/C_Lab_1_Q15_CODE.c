#include <stdio.h> 

int main(void)
{ int i;

  for(i=1; i<=8; i++)
  {
   switch(i)
    {
      case 1:
      case 2:  printf("%d ==> a\n", i);
               break;

      case 3: case 4:
      case 5:  printf("%d ==> b\n", i);
               break;

      case 6:  printf("%d ==> c\n", i);
               break;

      default: printf("%d ==> d\n", i);
    }
  }
  return 0;
}


