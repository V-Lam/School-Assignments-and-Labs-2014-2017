#include <stdio.h>
#include <stdbool.h>
/*Vivian Lam, CS2211 Assignment3: Program 3*/

/* Name: Program3
   Purpose: approximmates the value of the constant e*/

int main (void)
{
	/*declares and initializes variables*/
	double accuracy = -1;	//used to store the precision number the user wants 
	long e;					//the value of e that will be approximated
	long fact =1;
	long nextFact =0;
	double counter = 1;			//counter variable to control the loop
	int numbTerms =0;			//variable to keep track of number of terms

	/*loops to make sure the user enters a valid positive number*/
	while(accuracy<0){
		printf("Please enter a positive decimal number (to represent how precise you want e to be approximated)");
		scanf("%f", accuracy);
	}

	/*loops until the term to be added becomes less than a small positive float point numbered entered by the user*/
	while (counter >= accuracy){
		nextFact = nextFact + 1;
		fact = fact * nextFact;		
		e = e + 1/ fact;

		counter = counter /10;//divides the counter by 10 to move down a decimal place
	}
	
		/*prints the value of e with 15 digits after the devcimal*/
		printf("%.15f \n", e);
		printf("Number of terms required to read this value: \n", numbTerms);	//prints the number of terms required to reach this value

	return 0;
}
