#include <stdio.h>

void f1(int *pppppp) {(*pppppp) = (*pppppp) * 2;}
	int main(){
	int h = 5;
	f1(&h);
	printf("Q15:\n%d\n\n", h);
	return 0;
	}
