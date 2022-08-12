#include<stdio.h>
#include<stdlib.h>
#include<math.h>
double apow(double a, double b)
{
    return pow(a,b);
}
double hypotenuse(double a, double b)
{
    return sqrt((pow(a,2)+pow(b,2)));
}
double tang(double a)
{
    //tan(a)=sin/cos
    return sin(a)/cos(a);
}
double round_rel(double a,double b)
{
    double ris=a/b;

    return round(ris*1000)/1000;
}
int main(int argc, char const *argv[])
{
    printf("a^b:%lf\n",apow(2,4));
    printf("hypotenuse:%lf\n",hypotenuse(2,5));
    printf("tan a:%lf\n",tang(2));
    printf("a/b:%.3lf\n",round_rel(2,3));
    return 0;
}
