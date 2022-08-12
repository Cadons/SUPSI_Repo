#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define  n  500000

double getPi(double points[][2],long  len)
{
    long tentativi=len;
    long bersaglio=0;

    for (int i = 0;i<tentativi ; i++)
    {
        if((pow(points[i][0],2)+pow(points[i][1],2))<1)
            bersaglio++;
    }
    return 4*(double)((double)bersaglio/(double)tentativi);
}
/* generate a random floating point number from min to max */
double randfrom(double min, double max) 
{
    double range = (max - min); 
    double div = RAND_MAX / range;
    return min + (rand() / div);
}
int main(int argc, char const *argv[])
{
    srand(time(0));
    double points[n][2];
    for(long i=0;i<n;i++)
    {
        points[i][0]=randfrom(0,1);
        points[i][1]=randfrom(0,1);
       
    }
    printf("Risultato: %lf\n",getPi(points,n));
    return 0;
}
