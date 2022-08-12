#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void findA_B()
{
    int N=100;
    int quadratiPerfetti[N];

    for (int i = 0; i < N; i++)
    {
        quadratiPerfetti[i]=pow(i+1,2);
    }

    for (int i = 0; i < N-1; i++)
    {

        if((int)sqrt(quadratiPerfetti[i]+quadratiPerfetti[i+1])-(double)sqrt(quadratiPerfetti[i]+quadratiPerfetti[i+1])==0)
        {
       
              printf("%.0lf^2+%.0lf^2=%.0lf^2\n",sqrt(quadratiPerfetti[i]),sqrt(quadratiPerfetti[i+1]),(double)sqrt(quadratiPerfetti[i]+quadratiPerfetti[i+1]));
        }
      
    }
    
    
}


int main(int argc, char const *argv[])
{
    findA_B();
    return 0;
}
