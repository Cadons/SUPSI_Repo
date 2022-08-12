#include <stdio.h>
int separaCifra(int num, int mod)
{
    int tmp = (num % mod);
    tmp = (num - tmp) / mod;
    return tmp;
}
int abacus(int num)
{
    if (num < 100 || num > 999)
        {
            printf("Numero non valido!\n");
            return 1;
        }

        else
        {
            int mod = 100;
            for (int i = 0; i < 3; i++)
            {
                printf("|");
                int numeroX = separaCifra(num, mod);
                num = num % mod;
                mod /= 10;
                for (int j = 0; j < numeroX; j++)
                {
                    printf("x");
                }
                printf("-----");
                for (int j = 0; j < 10 - numeroX; j++)
                {
                    printf("x");
                }

                printf("|\n");
            }
            return 0;
        }
}
int main()
{
    int num=0;
    
   do
   {
       printf("Inserire un numero a 3 cifre: ");
         scanf("%d", &num);
   }   while(abacus(num)!=0);
   
  
  
    return 0;
}
