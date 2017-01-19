#include <stdio.h>
#include <stdbool.h>

int main(void){
    int x = 0, y = 0, a = 0, b = 0,  g = 0, i = 0, j = 0;
    
    printf("\n\nCase A\n");
    if( !(x < 5) && !(y >= 7) )
        printf("True\n");
    else
        printf("False\n");
    
    if( !((x < 5) || (y >= 7)) )
        printf("True\n");
    else
        printf("False\n");
    
    printf("\n\nCase B\n");
    
    if( !(a == b) || !(g != 5) )
        printf("True\n");
    else
        printf("False\n");
    
    if( !((a == b) && (g != 5)) )
        printf("True\n");
    else
        printf("False\n");
    
    printf("\n\nCase C\n");
    
    if( !((x <= 8) && (y > 4)) )
        printf("True\n");
    else
        printf("False\n");
    
    if( !(x <= 8) || !(y > 4) )
        printf("True\n");
    else
        printf("False\n");
        
    printf("\n\nCase D\n");    
        
    if( !((i > 4) || (j <= 6)) )
        printf("True\n");
    else
        printf("False\n");
    
    if( !(i > 4) && !(j <= 6) )
        printf("True\n");
    else
        printf("False\n");

return 0;

}
