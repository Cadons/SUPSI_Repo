
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void minMax(int vect[], int len, int* min, int* max)
{
    *min = vect[0];
    *max = vect[0];

    for (int i = 0; i < len; i++)
    {
        if (vect[i] > *max)
        {
            *max = vect[i];
        }
        if (vect[i] < *min)
        {
            *min = vect[i];
        }
    }
    
}
int main()
{
    const int N = 10;
    int vect[N];
    srand(time(0));
    for (int i = 0; i < N; i++)
    {
        vect[i] = rand() % 100;
    }
    int min, max;
    minMax(vect, N, &min, &max);
    printf("Minimo: %d\n", min);
    printf("Massimo: %d\n", max);

}

