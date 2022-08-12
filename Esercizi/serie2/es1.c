#include <stdio.h>
#include <math.h>
#include <time.h>
int separaCifra(int num, int mod)
{
    int tmp = (num % mod);
    tmp = (num - tmp) / mod;
    return tmp;
}
int verfica(int num)
{
    int mod = 100;
    int cifre[3];
    int num_tmp=num;
    for (int i = 0; i < 3; i++)
            {
                cifre[i]= separaCifra(num, mod);
                num = num % mod;
                mod /= 10;
             
            }
    int sum=0;
    
    for(int i=0;i<3;i++)
    {
        sum+=pow(cifre[i],3);
    }
  
    if(sum==num_tmp)
        return 1;
    else
        return 0;
}
int main(int argc, char const *argv[])
{
    int num;

   /* do{
    printf("Inserire un numero positivo di 3 cifre:");
    scanf("%d",&num);
    }while(num<100||num>999);*/
    srand(time(NULL));
    int max=999;
    int min=100;
    num=rand();
    if(verfica(num)==1)
    {
        printf("Si\n");
    }else
    {
        printf("No\n");
    }

    
    return 0;
}
