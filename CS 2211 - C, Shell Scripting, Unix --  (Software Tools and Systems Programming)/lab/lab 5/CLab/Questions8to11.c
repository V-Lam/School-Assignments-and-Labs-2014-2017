#include <stdio.h>

int main(void){

    //Question 8
    //a
    printf("\n\nQuestion 8\n\n");
    printf("*%20.10d*\n*%-20.10d*\n*%.10d*\n*%-20d*\n", 123456, 123456, -123456, -123456);
    //b
    printf("%.4f\n", 83.162);
    //c
    printf("%12.5e\n", 30.253);
    //d
    printf("%-6.2g\n", .0000009979);
    
    //Question 9
    //a
    printf("\n\nQuestion 9\n\n");
    float a, b, c, d;
    a = 13 % 4;
    b = -13 % 4;
    c = 13 % -4;
    d = -13 % -4;
    printf("%f, %f, %f, %f\n", a, b, c, d);
    
    //b
    a = 13 / 4;
    b = -13 / 4;
    c = 13 / -4;
    d = -13 / -4;
    printf("%f, %f, %f, %f\n", a, b, c, d);
    
    
    //Question 10
    printf("\n\nQuestion 10\n\n");
    int i = 1, j = 2;
    int k =3, m = 4;
    i %= j++ % (k += --m);
    printf("%d %d %d %d\n", i++, ++j, k--, --m);
    
    //Question 11
    printf("\n\nQuestion 11\n\n");
    i = 1, j = 2;
    k =3, m = 4;
    i *= j / - (k -= ++m);
    printf("%d %d %d %d\n", i++, ++j, k--, --m);
    
    
    
}
