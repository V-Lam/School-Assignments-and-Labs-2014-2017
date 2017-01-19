#include <stdio.h>

int main (void){
	float sf =0.0f, xf = 0.1f;
	double sd = 0.0, xd = 0.1f;
	int i = 1;
	
	for(;i<=10000; i++){
		sf = sf + xf + i;
		sd = sd + xd + i;
	}
	printf("sf = %f\n sd = %f\n", sf, sd);
	
	return 0;
}
