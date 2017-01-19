#include <stdio.h>
#include <stdlib.h>
#include "operation_functions.h"

////////////////////////PROTOTYPES//////////////////////
void printOut(struct complex_tag *numba);
///////////////////////////////////////////////////////

int main(int argc, char *argv[])
{

    /*CHECK IF THERE IS THE CORRECT NUMBER OF ARGUMENTS BEFORE PRECEDING*/
    if (argc !=5){
            printf("Incorrect number of arguments \n");
            return -1; //unsuccessful
    }


    /*Declares two variables of type complex_t. The value of these two
	variables will be initialized using the command-line
    arguments as 4 separate values, two for each variable. */
    struct complex_tag compOne;
    struct complex_tag compTwo;

    /*declares structure variables and pointers to store the results
	of the functions*/

    Complex_type mult; //store the product
    struct complex_tag quotient;//store the division

    //store sum and difference. also pointers to the structures
    //and pointers to the pointers
    struct complex_tag add, sub;
    struct complex_tag *ptr1 = &add, *ptr2 = &sub;
    struct complex_tag **add1 = &ptr1, **sub1 = &ptr2;

    
    /*Initialization: since argv is an array of characters, use
    array to float function: atof() to convert*/
    compOne.real = atof(argv[1]);
    compOne.imaginary = atof(argv[2]);
    compTwo.real = atof(argv[3]);
    compTwo.imaginary = atof(argv[4]);

    mult = multiplication(compOne, compTwo);//invokes the multiplication function to initialize the value


    /*Computations which will determine the values to be printed*/    
        
    /*Return value for division function: if value returned is negative, print
	the error message and the printOut function will NOT be called*/
    int div = division(&compOne, &compTwo, &quotient);    
   
    /*Return value for sum and difference function: if value returned is negative,
	print the error message and the printOut function will NOT be called*/
    int sd = sumAndDifference(compOne, compTwo, add1, sub1);



    /*print the entered complex numbers and the results of the functions*/
    printf("First complex number: ");
    printOut(&compOne);
    printf("Second complex number: ");
    printOut(&compTwo);

    printf("The product: ");
    printf("%f + i %f \n", mult.real, mult.imaginary);

    printf("The division: ");
    if (div == 0){	//If division successful, call printOut
        printOut(&quotient);
    }
    else{			//else print division was unsuccessful
	printf("Error, can't divide by zero \n");
    }

    if (sd == 0){	//sum and difference successful
        printf("The sum: ");
	printOut(ptr1);
        printf("The difference: ");
	printOut(ptr2);
    }
    else{	//unsuccessful, print error message
	printf("Error, couldn't allocate memory for pointer \n");
    }

    printf("\n\n");
    return 0; //success
}

/*function for printing*/
void printOut(struct complex_tag *numba)
{
    double realz = numba->real;
    double imaginaryz = numba->imaginary;
    printf("%f + i %f \n", realz, imaginaryz);
}

