//////////////////////////////////////////////////////////////////
#include <stdio.h>

/*Vivian Lam, CS2211 Assignment4: Program 2*/

/* Name: Program2
   Purpose: prints a nxn magic square (a square arrangement of the numbers 1-n*n in which the sum of the lements in any row, column or diagonal is the same)*/

int main(void){
	
	//declare variables
	int n = -1;
	int *nextpos;/*pointer variable to find the next position in the matrix*/
	int *currpos;/*pointer to hold the current position*/

	/*prompts user to input value for n. Loops until number entered is valid*/	
	while ((n <1) || (n>13) || (n%2=0)){
		printf("Please enter an odd positive integer between 1 and 13 (size of magic square):");
		scanf("%d", &n);	
	}
	//creates the array of integers
	int msquare[n][n];

	//initializes the pointers
	nextpos= &msquare;
	currpos = &msquare;

	/*to create the magic square start by placing 1 in the middle of row 0*/
				//point to middle
		*nextpos=1;	//store 1

	/*place the next number up one row and right one column. any attempt to go outside the bounds of the array should wrap around the opposite side of the array. if a position is occupied, put the number below the previously stored number*/
	for(int i=2; i<= (n*n);i++){
	
		/*get the next position of the pointer (right one and up one). to ensure that it is in bounds, mod it*/
		nextpos = (nextpos+1)%n;//move right
		nextpos = (nextpos-n)%n;//move up
FIX THIS, YOU CANNOT MOD A POINTER. TRY USING IF STATEMENTS
if (pointer_counter > n-1){//if pointer goes out of bounds
pointer_counter=0; //reset counter
//make the pointer point to the beginning

}







		/*if the next position is occupied store number directly below the previously soted number*/
		if(*nextpost!=null){
			nextpos= (currentpos+n)%n;//go below
ALSO FIX THIS
			*nextpos=i;//store
			currpos=nextpos;
		}
		else{
			*nextpos=i;//store the next number
			currpos = nextpos;
		}
	}


	//sets the nextpos pointer to point at the first element in the array
	nextpos = msquare;

	//print the magic square
	for(int j=0; j<(n*n); j++){
		nextpos +=j; //go to next position in array
		printf("%d", *nextpos);	
	}
TRY THIS INSTEAD FOR PRINTING
	//print the magic square
	for(int a=0; a<n; a++){//go to next row
		for(int b=0; b<n; b++){//print the row
			printf("%d ", *(msquare+(a*n)+b) );
		}
		printf("\n\n");
	}



	//return the exit status 0
	return 0;
}

/////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////GOOD COPY/////////

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

/*Vivian Lam, CS2211 Assignment4: Program 1*/
/* Name: Program1
   Purpose: find an approximation of the constant pi*/

int main(void){

        //declare variables
        double x, y, ratio, mean, standdev, sum, squaresum;
        int N =-1;
        float num_in;

        //prompts user to input value for N
        while (N <0){
                printf("Please enter a positive integer N:");
                scanf("%d", &N);
        }

        /*this is to ensure that the random numbers generated are actually
        random and different each time*/
        srand((unsigned) time(NULL));

        //loops 10 times
        for(int j=0; j<10;j++){
                //resets the counter
                num_in = 0;

                //loops for N amount of times
                for(int i =0; i<N; i++){

                        /*generate random numbers between 0.00 and 1.00 to
                        x and y*/

                        x = ((double)(rand()%10001)) /10000;
                        y = ((double)(rand()%10001)) /10000;

                        /*check if this (x,y) coordinate lies inside
                        shaded quadrant*/
                        if((x*x + y*y) <= 1){
                        /*if the coordinate lies inside the shaded
                        quadrant then increment the counter that tracks the tot$
                        coordinates in the quadrant*/

                                num_in++;
                        }
                }

                /*calculate the ratio of points located inside the circled
                to the total number of generated points*/

                //multiply the ratio by 4 to find an approximation of pi
                ratio = (num_in/N)*4;

                //prints the value of ratio
                printf("The number %d value is: %f \n", j+1, ratio);


                //computes the values to find the meand and stnd deviation
                sum += ratio;
                squaresum += (ratio*ratio);
        }//end outer loop



        mean = sum/10; //computes the mean

        /*computes the standard deviation. if the value is
        negative then multiply it by -1 to make it positive*/
        standdev = (squaresum - (sum*sum))/10;
        if(standdev<0){
                standdev = sqrt(-1*standdev);
        }
        else{
                standdev = sqrt(standdev);
        }

        //prints ht mean and standard deviation
        printf("Mean: %f \n", mean);
        printf("Standard Deviation: %f \n \n", standdev);

        //return the exit status 0
        return 0;
}


//////////////////////////////////////////////////////////////////

#include <stdio.h>

/*Vivian Lam, CS2211 Assignment4: Program 2*/

/* Name: Program2
   Purpose: prints a nxn magic square (a square arrangement of the numbers 1-n*n in which the sum of the lements in any row, column or diagonal is the same)*/

int main(void){
	
	//declare variables
	int n = -1;
	int *nextpos;/*pointer variable to find the next position in the matrix*/
	int *currpos;/*pointer to hold the current position*/
	int column_counter;//to keep track if the array goes out of bounds when moving right 
	int row_counter=0;//to keep track if the array goes out of bounds when moving up	


	/*prompts user to input value for n. Loops until number entered is valid*/	
	while ((n <1) || (n>13) || (n%2=0)){
		printf("Please enter an odd positive integer between 1 and 13 (size of magic square):");
		scanf("%d", &n);	
	}
	//creates the array of integers
	int msquare[n][n];

	//initializes the pointers
	nextpos= &msquare;
	currpos = &msquare;

	/*sets the counter to be equal to the middle position in the array*/
	column_counter=n/2;



	/*to create the magic square start by placing 1 in the middle of row 0*/
		nextpos=(nextpos+(n/2)); //point to middle
		*nextpos=1;	//store 1

	/*place the next number up one row and right one column. any attempt to go outside the bounds of the array should wrap around the opposite side of the array. if a position is occupied, put the number below the previously stored number*/
	for(int i=2; i<= (n*n);i++){
	/*get the next position of the pointer (right one and up one). to ensure that it is in bounds, evaluate the value of the counters*/
		
		column_counter++;//increment/decrement the counters
		row_counter--;


		//MOVING RIGHT
		if (column_counter >= n){//if counter goes out of bounds
			/*make the pointer go back to the first column*/		
			nextpos -= (n-1);
			column_counter=0; //reset counter
		}
		else{
			nextpos++;//move right		
		}	
		//MOVING UP
		if (row_counter <0){//if counter goes out of bounds
			/*make the pointer go back to the last row*/		
			nextpos += ;
STILL GOTTA FIX THIS
			column_counter=(n-1); //reset counter
		}
		else{
			nextpos = nextpos-n;//move up a row		
		}


		/*if the next position is occupied store number directly below the previously soted number*/
		if(*nextpost!=null){
			nextpos= (currentpos+n)%n;//go below
ALSO FIX THIS
			*nextpos=i;//store
			currpos=nextpos;
		}
		else{
			*nextpos=i;//store the next number
			currpos = nextpos;
		}
	}


	//sets the nextpos pointer to point at the first element in the array
	nextpos = msquare;

	//print the magic square
	for(int j=0; j<(n*n); j++){

		nextpos +=j; //go to next position in array
		printf("%d", *nextpos);	
	}
TRY THIS INSTEAD FOR PRINTING
	//print the magic square
	for(int a=0; a<n; a++){//go to next row
		for(int b=0; b<n; b++){//print the row
			printf("%d ", *(msquare+(a*n)+b) );
		}
		printf("\n\n");
	}



	//return the exit status 0
	return 0;
}
//////////////////////////////////////////////////
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

