
#include <stdio.h>
#include <stdlib.h>
double square(double x)
{
	return x * x;
}
double integral(double (*fp)(double) ,double min, double max, double  steps)
{
	double x = 0,sum = 0;
	for (x = min + steps / 2.0; x < max; x+=steps)
	{
		sum += fp(x) * steps;
	}
	return sum;
}
int main()
{
	double passo;
	printf("Test di integrazione di una funzione\n");
	printf("Integrale della f da 0 a 1.0 in funzione del passo:\n\n");
	printf(" passo integrale\n");
	for (passo = 0.1; passo > 1.0e-7; passo /= 10.0) {
		printf("%10.1e %16.14f\n", passo,
			integral(square, 0.0, 1.0, passo));
	}
	return 0;
}

