#include <stdio.h>

/*Vivian Lam, CS2211 Assignment3: Program 2*/
/* Name: Program2
   Purpose: calculates the remaining balance on a load after each of the
first n monthly payments*/

int main (void)
{
	/*declares and intializes variables*/
	float loan = -1;
	float yearlyInterest = -1;
	float monthlyInterest = -1;
	float monthlyPayment =-1 ;
	int n = -1;		//number of monthly payments
	float balance;		//calculates the balance
	int counter = 1;	//counter to increment and keep track of the loop iterations
	float lastPay;		//variable to display the last payment if loan is paid off

/*promots the user to enter the amounf of loan, yearly interest rate,
monthly payment, and n (number of monthly payments) */
	/*loops to make sure the entered values are positive*/
	while(loan < 0)
	{
		printf("Please enter the loan value: ");
		scanf("%f", &loan);
	}

	while(yearlyInterest < 0)
	{
		printf("Please enter the yearly interest value: ");
		scanf("%f", &yearlyInterest);
	}

	while(monthlyPayment < 0)
	{
		printf("Please enter the monthly payment value: ");
		scanf("%f", &monthlyPayment);
	}

	while(n < 0)	//note: if the value entered is a float, rounds down to nearest integer
	{
		printf("Please enter the value of the number of monthly payments: ");
		scanf("%d", &n);
	}

	/*makes the yearlyInterest a percent and sets the values fo monthlyInterest and balance*/
	yearlyInterest = yearlyInterest / 100;
	monthlyInterest = yearlyInterest / 12;
	balance = loan;

	/*loop to display for all the monthly payments, or until loan is paid off*/
	while((counter <=n) && (balance >=0)){
		/*sets the value of the last payment*/		
		lastPay = balance + (balance *monthlyInterest);

		/*calculates the value for balance*/
		balance = balance + (balance * monthlyInterest) - monthlyPayment;
		
		/*displays each balance with two digits after the decial point*/
		printf ("The balance after %d payment(s): \n", counter);		

		/*these conditional statements ensure that only positive values and 0 are printed*/		
		if(balance<0){	//if the balance is negative, it will display 0.00
			printf("0.00 \n");
		}		
		else{	//otherwise print the amount for balance
			printf("%.2f \n",balance);
		}
		/*if the loan is paid off then reports the amount of the
		last payment with two digits after the decimal*/		
		if(balance <=0) 
		{
			printf("The amount of the last payment is: %.2f \n", lastPay);	
		}
		counter++;	//increments counter
	}
	return 0;
}
