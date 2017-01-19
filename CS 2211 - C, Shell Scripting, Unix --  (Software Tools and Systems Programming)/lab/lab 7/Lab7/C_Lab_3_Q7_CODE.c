#include <stdio.h>
#include <time.h>

long fibonacci(long n)
{  
int i;
long int returnValue = 1, pastValue = 0, currentValue = 1;
if (n == 0 || n == 1)
      return n;
   else
                
        for (i = 0;i <= n-2;i++){
            currentValue = returnValue;
            returnValue = returnValue + pastValue;
            pastValue = currentValue;
            
        }
      return returnValue;
}

int main()
{  long result, number;
   long counter;
   clock_t start, end;

   printf("Enter an integer number: ");
   scanf( "%ld", &number );

   printf("Enter repeatitions: ");
   scanf( "%ld", &counter );

   start = clock();

   while(counter-- > 0)
     result = fibonacci(number);

   end = clock();

   printf("Fibonacci(%ld) = %ld\n", number, result);
   printf("Calculation time %f\n", ((double) (end - start))
                                   / CLOCKS_PER_SEC);

   return 0;
}
