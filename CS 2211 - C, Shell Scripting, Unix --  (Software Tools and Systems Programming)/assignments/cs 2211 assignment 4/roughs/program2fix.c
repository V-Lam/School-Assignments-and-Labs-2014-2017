//////////////////////////////////////////////////////////////////
#include <stdio.h>

/*Vivian Lam, CS2211 Assignment4: Program 2*/

/* Name: Program2
   Purpose: prints a nxn magic square (a square arrangement of the numbers 1-n*n in which the sum of the lements in any row, column or diagonal is the same)*/

int main(void){
	
	//declare variables
	int n = -1;
	int row=0, column=0;

	//creates and inializes the array of integers
	int msquare[13][13]={0};


/*prompts user to input value for n. Loops until number entered is valid*/	
	while ((n <1) || (n>13) || (n%2 == 0)){
		printf("Please enter an odd positive integer between 1 and 13 (size of magic square):");
		scanf("%d", &n);	
	}


/*to create the magic square start by placing 1 in the middle of row 0*/
	row =0;
	column = n/2;
	*((*(msquare-row)+column)=1;

/*get the next position of the pointer (right one and up one). to ensure that it is in bounds, evaluate the value of the counters*/
	
	for(int i=2; i<= (n*n);i++){
		//increment/decrement counters		
		column++;
		row--;

		
		//MOVING RIGHT
		if(column>=n){//counter goes outa bounds
			column=0;//reset counter
		}
		//MOVING UP	
		if(row<0){
			row=(n-1);//reset counter
		}	


		/*if the next position is occupied store number directly below the previously soted number*/
		if(*((*(msquare-row)+column) != null){
			*((*(msquare-row)+column)=i; //store
		}		
		else{
			row++;//increment row
			*((*(msquare-row)+column)=i; //storing a row below
		}
	}//end for


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
