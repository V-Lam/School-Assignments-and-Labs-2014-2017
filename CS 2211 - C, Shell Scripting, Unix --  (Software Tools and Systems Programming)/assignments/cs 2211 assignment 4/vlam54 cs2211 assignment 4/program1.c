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
	long N =-1;
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
		for(long i =0; i<N; i++){

			/*generate random numbers between 0.00 and 1.00 to
			x and y*/
			x = ((double)rand())/RAND_MAX;
			y = ((double)rand())/RAND_MAX; 

			/*check if this (x,y) coordinate lies inside
			shaded quadrant*/
			if((x*x + y*y) <= 1){
			/*if the coordinate lies inside the shaded 
			quadrant then increment the counter that tracks the total number of
			coordinates in the quadrant*/			

				num_in++;
			}
		}

		/*calculate the ratio of points located inside the circle
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
        standdev = (squaresum/10) - (mean*mean);
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


