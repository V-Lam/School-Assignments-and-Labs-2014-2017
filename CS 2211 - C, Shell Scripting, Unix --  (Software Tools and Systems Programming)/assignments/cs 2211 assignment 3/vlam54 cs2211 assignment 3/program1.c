#include <stdio.h>
#include <stdbool.h>

/*Vivian Lam, CS2211 Assignment3: Program 1*/

/* Name: Program1
   Purpose: Asks user to input the time of day (24hour time) and time
duration and add the two together
*/

int main (void)
{

	/*Declares and initializes variables.
	 First integer represents time of day
	second integer  represents time duration.
	Third variable is the sum of the two integers.
	digitTens is used to extract the digit in the tens column of
		timeDur and timeDay
	checkCorrect is a boolean used to check if the digit in the tens 
		column of timeDay and timeDur are valid*/

	int timeDay = 0;
	int timeDur;
	int result;
	int digitTens;
	bool checkCorrect = false;

/*NOTE:
first integer must be less than 2400 and the minutes must be less than 59
(tens digit is <6)
second integer minutes must be less than 59 (tens digit < 6)
*/
	/*if the above conditions are not met then loop and keep prompting
	user to enter a correct value*/
	//loop for first value. also makes sure that time of day is positive
	while((timeDay > 2400) || (timeDay < 0)  || (checkCorrect == false))
	{
		/*resets the boolean checking variable to be false*/
		checkCorrect = false;		

		/*Asks the user to enter the first integer*/
		printf("Please enter the first integer (time of day on a 24 hour clock): ");

		/*reads the integer and stores it into timeDay*/
		scanf("%d", &timeDay);

		/*extracts the digit from the tens column from input and checks if it's under 6*/
		digitTens = (timeDay / 10) %10;
		if (digitTens <=5)
		{
			checkCorrect = true;
		}
	}
	checkCorrect = false; //resets the value of the boolean variable to be false
	
	//loop for second variable
	while(checkCorrect == false)
	{
		 /*resets the boolean checking variable to be false*/
                checkCorrect = false;

		/*prompts user to enter the second integer and stores it into timeDur*/
		printf("Please enter the second integer (time duration): ");
		scanf("%d", &timeDur);

                /*extracts the digit from the tens column from input and
checks if it's between -5 and 5 (inclusive*/
                digitTens = (timeDur / 10) %10;
                if ((digitTens <=5) && (digitTens >= -5))
                {
                        checkCorrect = true;
                }
	}

	/*adds the time duration to the time of day*/
	result = timeDay + timeDur;

	/*mods result so that it will be in proper 24 hour time*/
	result = result % 2400;

	/*if the result is a negative number, converts it to proper 24
hour time*/
	if(result < 0)
	{
		result = 2400 + result;
	}

	/*print the results to the screen in 4 digit time*/
	printf("Time of day + time duration is : %.4d", result);

	return 0;
}
