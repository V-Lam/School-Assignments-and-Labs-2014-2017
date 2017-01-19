#include <stdio.h>
#define SIZE 10

int main(void)
{
  int a[SIZE] = {2, 6, 4, 8, 10, 12, 89, 68, 45, 37};
  int pass; // passes counter
  size_t i; // comparisons counter
  int hold; // temporary location used to swap array elements

int counter = 0;
  printf("Data items in original order\n");
  // output original array
  for(i = 0; i < SIZE; ++i)
    printf("%4d", a[i]);

  printf("\n");

  // bubble sort
  // loop to control number of passes
  for(pass = 1; pass < SIZE; ++pass){
    counter = 0;
     // loop to control number of comparisons per pass
     for(i = pass; i < SIZE-1; ++i){
        // compare adjacent elements
        if(a[i] > a[i+1])
        { // swap a[i] and a[i+1]
          hold   = a[i];
          a[i]   = a[i+1];
          a[i+1] = hold;
          counter++;
        } 
        }
        if (counter == 0) break;
        }


  printf("Data items in ascending order\n");
  // output sorted array
  for(i = 0; i < SIZE; ++i)
    printf("%4d", a[i]);

  printf("\n");
  return 0;
}
