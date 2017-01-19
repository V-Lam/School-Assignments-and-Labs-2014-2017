#ifndef operation_functions_h
#define operation_functions_h

#include <stdio.h>

struct complex_tag
{
    double real;
    double imaginary;
};

typedef struct
{
    double real;
    double imaginary;
}Complex_type;



//part c)
Complex_type multiplication(struct complex_tag c1, struct complex_tag c2);


//part d)
int division(struct complex_tag *pointerOne, struct complex_tag *pointerTwo, struct complex_tag *pointerThree);


//part e)
int sumAndDifference(struct complex_tag c1, struct complex_tag c2, struct complex_tag **pointerOne, struct complex_tag **pointerTwo);

#endif /* operation_function_h */
