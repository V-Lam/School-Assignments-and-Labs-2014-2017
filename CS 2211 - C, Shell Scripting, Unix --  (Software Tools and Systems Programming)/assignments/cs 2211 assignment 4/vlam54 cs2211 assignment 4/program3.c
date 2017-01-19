#include <stdio.h>

/*Vivian Lam, CS2211 Assignment4: Program 3*/
/* Name: Program3
   Purpose: determines the smallest number of $20, $10, $5, $2, and $1
bills/coins necessary to pay a dollar amount.*/



/*prototype for the function*/
void pay_amount (int dollars, int *twenties, int *tens, int *fives, int
*toonies, int *loonie);



int main(void){
	//declares and initialize variables
	int dollar_amount =-1;
	int a=0 , b=0, c=0, d=0, e=0;
	int *twenties=&a, *tens=&b, *fives=&c, *toonies=&d, *loonie=&e;


	/*prompts user to enter an integer value (dollar amount). loops
	until the value entered is positive*/
	while (dollar_amount <0){
		printf("Please enter a positive integer for the dollar amount:");
		scanf("%d", &dollar_amount);	
	}


	/*calls pay_amount method to modify the values for each of the
	pointers*/
	pay_amount(dollar_amount, twenties, tens, fives, toonies, loonie);

	//prints the results
	printf("Number of...\n Twenties: %d \n Tens: %d \n Fives: %d \n Toonies: %d \n Loonies: %d\n", a, b, c, d, e);


	//return the exit status 0
	return 0;

}//end main


//pay_amount function, returns the number of bills of each
void pay_amount(int dollars, int *twenties, int *tens, int *fives, int
*toonies, int *loonie){

	/*modify the values of the variables by finding how many of each
are needed (divide and mod)*/
	*twenties=dollars /20;
	*tens=(dollars%20)/10;
	*fives=(dollars%10)/5;
	*toonies=(dollars%5)/2;
	*loonie=(dollars%5)%2;
}
