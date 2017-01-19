#include <stdio.h>

int main(void){

    int pH;
    printf("Enter a pH value from 0-14 rounded to the nearest integer: ");
    scanf("%d", &pH);
    
    if (pH > 7) {
        if (pH < 12) {
            printf("alkaline");
        } else {
            printf("very alkaline");
        }
    } else {
        if (pH == 7) {
            printf("neutral");
        } 
        else
        if (pH > 2 ) {
            printf("acidic");
        }   else 
         printf("very acidic");
   
    } 
       
    
        
    
    
    
    return 0;
}
