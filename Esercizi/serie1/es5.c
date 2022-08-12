#include <stdio.h>
int fat(int n)
{
    if (n == 0)
        return 1;
    else
    {
        return n * fat(n - 1);
    }
}
int main(int argc, char const *argv[])
{
    int n=0,k=0,ris=0;
    do{
    printf("Inserire prima n poi k: ");
    scanf("%d%d",&n,&k);
    }while(n<0||k<0||k>n);

    ris=fat(n)/fat(k);
    printf("Risultato: %d\n",ris);
    return 0;
}
