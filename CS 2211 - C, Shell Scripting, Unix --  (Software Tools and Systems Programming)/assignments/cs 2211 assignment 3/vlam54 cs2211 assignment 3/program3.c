#include <stdio.h>
#include <stdbool.h>

/*Vivian Lam, CS2211 Assignment3: Program 3*/

/* Name: Program3
   Purpose: approximmates the value of the constant e*/

int main (void)
{
	/*declares and initializes variables*/
	double accuracy = -1;	//used to store the precision number the user wants 
	double eVal = 1;		//the value of e that will be approximated
	double  fact =1;		//variable to store the factorial
	long nextFact =0;		//stores the value which the next factorial should be multiplied by
	int numbTerms =1;		//variable to keep track of number of terms
	bool loopChecker = true;	//boolean to check if loop should continue looping

	/*loops to make sure the user enters a valid positive number*/
	while(accuracy<0){
		printf("Please enter a positive decimal number (to represent how precise you want e to be approximated): \n");
		scanf("%lf", &accuracy);	//stores user input into this variable
	}

	/*loops until the term to be added becomes less than a small
	positive float point numbered entered by the user*/
	while ((1/fact >= accuracy) && (loopChecker == true)){
		nextFact = nextFact + 1;	//increments the value of nextFact
		fact = fact * nextFact;		//sets the new value of fact
		eVal = eVal + ( 1 / fact );	//sets the new value of e by adding the new factorial decimal
		numbTerms++;		//increments the value of numbTerms by 1
		
		/*checks to see if the next term after this will violate the condition of the loop. if so then set the value of
loopChecker to be false so that the loop terminates*/
		if (1 / (fact*(nextFact+1)) <accuracy){
			loopChecker = false;
		}
	}

		/*prints the value of e with 15 digits after the decimal*/
		printf("this is the value of e: %.15lf \n", eVal);
		//prints the number of terms required to reach ths value
		printf("Number of terms required to read this value: %d \n", numbTerms);	

	return 0;	//returns 0
}
