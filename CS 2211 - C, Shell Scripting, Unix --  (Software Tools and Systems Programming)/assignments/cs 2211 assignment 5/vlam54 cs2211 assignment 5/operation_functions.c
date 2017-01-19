
#include "operation_functions.h"

#include <stdio.h>
#include <stdlib.h>

/*NOTE: a+ib: a is the real part, b is the imaginary part*/



//part c)
Complex_type multiplication(struct complex_tag c1, struct complex_tag c2)
{
    
    /*computes the value for the product:
     c1 * c2 = (a1*a2 - b1*b2) + i (a2*b1 + a1*b2)*/
    Complex_type multz;
    multz.real = (c1.real * c2.real) - (c1.imaginary * c2.imaginary);
    multz.imaginary = (c2.real * c1.imaginary) + (c1.real * c2.imaginary );
    
    return multz; //returns the product
}

//part d)
int division(struct complex_tag *pointerOne, struct complex_tag *pointerTwo, struct complex_tag *pointerThree){
    
    /*if(a2*a2 + b2*b2) = 0, (the denominator is zero) return -2,*/
    if( ((pointerTwo->real * pointerTwo->real)+(pointerTwo->imaginary * pointerTwo->imaginary))==0 ){
        return -2;
    }
    
    /*pointerOne and pointerTwo point to the complex numbers*/
    
    /*the value of pointerThree is the division result of the first two pointers. Computes the value for the division:
     c1/c2 = (a1*a2 + b1*b2)/(a2*a2 + b2*b2) + i (a2*b1 - a1*b2)/(a2*a2 + b2*b2)*/
    
    //computes numerator for real:(a1*a2 + b1*b2)
    (*pointerThree).real = (pointerOne->real * pointerTwo->real) + (pointerOne->imaginary * pointerTwo->imaginary);
    //computes denominator for real:(a2*a2 + b2*b2)
    (*pointerThree).real = pointerThree->real / ((pointerTwo->real * pointerTwo->real) + (pointerTwo->imaginary * pointerTwo->imaginary));
    ////
    //computes numerator for imaginary: (a2*b1 - a1*b2)
    (*pointerThree).imaginary = (pointerTwo->real * pointerOne->imaginary ) - (pointerOne->real * pointerTwo->imaginary);
    //computes denominator for imaginary: (a2*a2 + b2*b2)
    (*pointerThree).imaginary = pointerThree->imaginary /((pointerTwo->real * pointerTwo->real) + (pointerTwo->imaginary * pointerTwo->imaginary));
    
    return 0;//operation successful, return 0
}

//part e)
int sumAndDifference(struct complex_tag c1, struct complex_tag c2, struct
                     complex_tag **pointerOne, struct complex_tag **pointerTwo)
{

        /*allocates memory for the two pointers*/
    (*pointerOne) = malloc(sizeof(struct complex_tag));
    (*pointerTwo) = malloc(sizeof(struct complex_tag));
    
    /*Memory allocation operation unsuccessful, return -1*/
    if(pointerOne == NULL || pointerTwo == NULL){
        return -1;
    }
    
    /*value is the sum of the first two parameters:
     c1+c2=(a1+a2)+i(b1+b2)*/
    (*pointerOne)->real=(c1.real+c2.real);
    (*pointerOne)->imaginary=(c1.imaginary+c2.imaginary);
    
    /*value is the differenc ebtween the first two parameterrs:
     c1-c2=(a1-a2)+i(b1-b2)*/
    (*pointerTwo)->real=(c1.real - c2.real);
    (*pointerTwo)->imaginary=(c1.imaginary - c2.imaginary);
    
    
    return 0;//successful return 0
}
