#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int* smallest(int vet[], int len)
{
    int* min = &vet[0];
	for (int i = 0; i < len; i++)
	{
		if (vet[i] < *min)
		{
			min = &vet[i];
		}
	}
	return min;
}
int main()
{
	int vet[10];
	srand(time(0));
	for (int i = 0; i < 10; i++)
	{
		vet[i] = rand() % 100;
	}
	int* min = smallest(vet, 10);
	printf("Minimo45: %u -> %d\n", min,*min);
}

