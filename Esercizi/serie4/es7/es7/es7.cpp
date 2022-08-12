// es7.cpp : Questo file contiene la funzione 'main', in cui inizia e termina l'esecuzione del programma.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void print(int v[], const int elem)
{
	for (int i = 0; i < elem; i++)
	{
		printf("%d\t", v[i]);
	}
	printf("\n");
}
int compareDesc(const void* a, const void* b)
{
	return *(int*)b - *(int *)a;
}
int compareOddEven(const void* a, const void* b)
{
	int n1 = *(int*)a;
	int n2 = *(int*)b;
	if (n2 % 2==0&&n1%2==1)
	{
		return 1;
	}
	else if (n2 % 2 == 1 && n1 % 2 == 0)
	{
		return -1;
	}
	else
	{
		return n1 - n2;
	}

	
}
int main()
{
	const int n = 10;
	int numbers[n];
	srand(time(NULL));
	for (int i = 0; i < n; i++)
	{
		numbers[i] = rand() % 100;
	}
	print(numbers, n);
	qsort(numbers, n, sizeof(int), compareDesc);
	print(numbers, n);
	qsort(numbers, n, sizeof(int), compareOddEven);
	print(numbers, n);


}
