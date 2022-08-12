#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
int generatore(int n)
{
    return rand() % n;
}
int max(int a[], int n)
{
    int max = a[0];
    for (int i = 0; i < n; i++)
    {
        if (a[i] > max)
            max = a[i];
    }

    return max;
}
void statistica(int mod)
{
    int n;
    const int N = 10;
    printf("Inserire n : ");
    scanf("%d", &n);

    int values[N]; //ogni posizione corrisponde al numero
    for (int i = 0; i < N; i++)
    {
        values[i] = 0;
    }

    srand(time(NULL));
    for (int i = 0; i < n; i++)
    {
        int tmp = generatore(9);
        values[tmp]++;
    }
    int maxStars = 30;
    int maximum = max(values, N);
    switch (mod)
    {
    case 1:
    case 2:

        if (mod == 1)
        {
            for (int i = 0; i < N; i++)
            {
                printf("Quantità di %d : %d\n", i, values[i]);
            }
        }
        else
        {
            for (int i = 0; i < N; i++)
            {
                printf("Quantità di %d : ", i);
                for (int j = 0; j < values[i]; j++)
                {
                    printf("*");
                }
                printf("\n");
            }
        }
        break;
    case 3:

        for (int i = 0; i < N; i++)
        {
            printf("Quantità di %d (%d): ", i, values[i]);
                int stop=(int)((double)((values[i]*100)/maximum)*((double)maxStars/100));
                for (int j = 0; j < stop; j++)
                {
                    printf("*");
                }
                printf("\n");
        }
        break;
    }
}
int main(int argc, char const *argv[])
{
    // statistica(1);
    //  statistica(2);
    statistica(3);
    return 0;
}
