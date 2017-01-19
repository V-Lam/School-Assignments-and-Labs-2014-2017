#include <stdio.h>

int main(void){
	/* QUESTION 10 */
	int a, *p;
	a =2;
	p = &a;
	a = a + 1;
	printf("Q10:\n%d\n\n", *p);
	
	/* QUESTION 11 */
	/*int b, *pp;
	b =2;
	pp = b;
	b = b + 2;
	printf("Q11:\n%d\n\n", *pp);*/
	
	/* QUESTION 12 */
	int c, d, *ppp;
	ppp = &c;
	*ppp = 4;
	ppp = &d;
	*ppp = 3;
	printf("Q12:\n%d %d\n\n", c, d);
	
	/* QUESTION 13 */
	int e, f, *q, *pppp;
	e =3;
	pppp = &e;
	q = pppp;
	*q = *q + 5;
	printf("Q13:\n%d\n\n", *pppp);
	
	/* QUESTION 14 */
	int g; 
	int *ppppp;
	g =4;
	ppppp = &g;
	printf("Q14:\n%d\n\n", (*ppppp)/g);
	
	/* QUESTION 15 */
	void f1(int *pppppp) {(*pppppp) = (*pppppp) * 2;}
	int main(){
	int h = 5;
	f1(&h);
	printf("Q15:\n%d\n\n", h);
	}
	
	/* QUESTION 16 */
	int i=3, j=4;
	int *ppppppp, *qq;
	ppppppp = &i;
	qq = ppppppp;
	*qq = j;
	printf("Q16:\n%d %d\n\n", *ppppppp, i);
	
	/* QUESTION 17 */
	int k = 4, *pppppppp;
	pppppppp = &k;
	printf("Q17:\n%d\n\n", *pppppppp+1);
	
	/* QUESTION 18 */
	int l=4, *ppppppppp;
	ppppppppp = &l;
	printf("Q18:\n%d\n\n", *(ppppppppp+1));
	
	
	return 0;
}
