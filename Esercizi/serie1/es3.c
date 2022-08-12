#include <stdio.h>



double perim(double b, double h)
{
    return 2*b+2*h;
}
double area(double b, double h)
{
    return b*h;
}
double rap(double b, double h)
{
    return perim(b,h)/area(b,h);
}
int main()
{
    double h,b;
    do
    {
        printf("Inserire base: ");
        scanf("%lf",&b);
        printf("Inserire altezza: ");
        scanf("%lf",&h);
        printf("Base\tAltezza\tPerimetro Area\tRapporto\n");
        int atab=6;
        for(int i=0;i<11;i++)
        {
            double area_=area(b,h);
            if(area_>=10)
                atab++;
            printf("%.2lf\t%.2lf\t%.2lf\t%6.2lf\t%.2lf\n",b,h,perim(b,h),area_,rap(b,h));
            h+=0.1;
            b+=0.1;
        }
    
    } while (h<=0&&b<=0);
    
    /* code */
    return 0;
}
