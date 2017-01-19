#include <stdio.h>

/*Vivian Lam, CS2211 Assignment4: Program 2*/

/* Name: Program2
   Purpose: prints a nxn magic square (a square arrangement of the numbers
1-n*n in which the sum of the lements in any row, column or diagonal is the
same)*/

int main(void){
	
	//declare variables
	int n = -1;
	int row=0, column=0, oldcol=0, oldrow=0;

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
	*((*(msquare+row))+column)=1;

	/*get the next position of the pointer (right one and up one). to ensure
	that it is in bounds, evaluate the value of the counters*/
	for(int i=2; i<= (n*n);i++){

		/*variables to store the previous position of the
		pointers. used to help place the next element under the
		previous one if (if the next position to place in is occupied*/
		oldcol=column;
		oldrow=row;

		//increment/decrement counters		
		column++;
		row--;

		/*checking if the counters will go out of array bounds*/
		//MOVING RIGHT
		if(column>=n){//counter goes outa bounds
			column=0;//reset counter (first column)
		}
		//MOVING UP	
		if(row<0){//counter goes outa bounds
			row=(n-1);//reset counter (bottom row)
		}	


		/*if the next position is occupied store number directly
		below the previously soted number. otherwise store
		normally*/
		/*not occupied and can insert:*/
		if(  (*((*(msquare+row))+column)) == 0){ //can insert
			*((*(msquare+row))+column)= i; //store
		}		
		else{//occupied, insert below previous
			/*set counters equal to previous and make pointer 
		point here. the +1 in row makes the pointer go a row below*/
			row=oldrow+1;
			column=oldcol;
	
			/*checking if counters go outa bounds*/
			//MOVING RIGHT
               		if(column>=n){//counter goes outa bounds
                       		column=0;//reset counter
                	}
                	//MOVING UP
                	if(row<0){
                        	row=(n-1);//reset counter
                	}

			*((*(msquare+row))+column)= i; //storing a row below
		}
	}//end for


	//print the magic square
	for(int a=0; a<n; a++){//go to next row
		for(int b=0; b<n; b++){//print the row (go to next column)
			printf("%6d ", *((*(msquare+a))+b) );
		}
		printf("\n\n");
	}



	//return the exit status 0
	return 0;
}
