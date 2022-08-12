#include <stdio.h>
#include <limits.h>
int fat(int n)
{
    if (n == 0)
        return 1;
    else
    {
        //n*fat(n-1)<max => fat(n-1)<max/n
        return fat(n - 1)>(__INT_MAX__/n)?0:n * fat(n - 1);
    }
}
void start()
{
    int num;
    do
    {
        printf("Inserire un numero:");

        scanf("%d", &num);
       
        if (num < 0)
            printf("ERRORE! inserire un valore maggiore di 0\n");
        else
        { 
            int t=fat(num);
            if(t==0)
                printf("Overflow!\n");
            else

             printf("Fattoriale di %d = %d\n",num,t);
             
             }
          
    } while (num<0);
}
int main(int argc, char const *argv[])
{
    start();
    return 0;
}
