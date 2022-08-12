#include <stdio.h>
#include <math.h>
#include <stdlib.h>
//2x-cos(x)
double f(double x)
{
    return 2 * x - cos(x);
  
}
double bisezione(double a, double b, double eps)
{
    double c;
    if(f(a) * f(b)<0)
    {
       do
    {
        c = (b + a) / 2;

        if (f(a) * f(c) > 0)
        {
            a = c;
        }
        else
        {
            b = c;
        }
      
    } while ((b-a)/2 > eps);  
    
    }
        
    
   
    return c;
}
int main(int argc, char const *argv[])
{
    double a, b;
    const double EPS=0.001;
    do{
    printf("Inserire il valore a - b:");
    scanf("%lf %lf",&a,&b);
    }while (a>b);
    
    if(f(a)*f(b)<0)
    printf("X=%f\n",bisezione(a,b,EPS));
    else
        printf("Non ci sono intersezione nell'intervallo specificato!\n");
    return 0;
}
