#include <stdio.h>

int main(void){
	printf("size of int: %ld\n", sizeof(int));
	printf("size of short: %ld\n",sizeof(short));
	printf("size of long: %ld\n", sizeof(long));
	printf("size of 2: %ld\n", sizeof(2));
	printf("size of 87654321: %ld\n", sizeof(87654321));
	printf("size of float: %ld\n", sizeof(float));
	printf("size of double: %ld\n", sizeof(double));
	printf("size of long double: %ld\n", sizeof(long double));
	printf("size of 2.0f: %ld\n", sizeof(2.0f));
	printf("size of 2.0: %ld\n" , sizeof(2.0));
	printf("size of char: %ld\n", sizeof(char));
	printf("size of a: %ld\n", sizeof('a'));
	
	return 0;
}
