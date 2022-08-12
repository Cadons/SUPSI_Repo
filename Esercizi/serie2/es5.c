#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void versione2()
{
    srand(time(NULL));
    const int N = 1000;
    int num[N];
    int limMax = 100000, limMin = 0;
    int max, min;
    for (int i = 0; i < N; i++)
    {
        num[i] = rand() % ((limMax - limMin + 1) + limMin);
    }

    max = num[0];
    min = num[0];
    for (int i = 0; i < N - 1; i++)
    {
        if (num[i + 1] - num[i] > max)
            max = abs(num[i + 1] - num[i]);
        else if (num[i + 1] - num[i] < min)
            min = abs(num[i + 1] - num[i]);
    }
    printf("Distanza massima:%d\n", max);
    printf("Distanza minima:%d\n", min);
}
void versione1()
{
    srand(time(NULL));
    const int N = 1000;
    int numNext, numPrev;
    int limMax = 10000, limMin = 0;
    int max = -1, min = -1;
    for (int i = 0; i < N - 1; i++)
    {
        numNext = rand() % ((limMax - limMin + 1) + limMin);
        numPrev = rand() % ((limMax - limMin + 1) + limMin);
        if (max == -1 && min == -1)
        {
            max = abs(numNext-numPrev);
            min = abs(numNext-numPrev);
        }
        if(abs(numNext-numPrev)>max)
            max=abs(numNext-numPrev);
            if(abs(numNext-numPrev)<min)
            min=abs(numNext-numPrev);
    }

    printf("Distanza massima:%d\n", max);
    printf("Distanza minima:%d\n", min);
}
int main(int argc, char const *argv[])
{
   versione1();
    return 0;
}
