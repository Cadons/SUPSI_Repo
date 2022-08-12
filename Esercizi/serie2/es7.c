#include <stdio.h>
#include <stdlib.h>
//variante 1
int parz[10000] = {0};

int fibo(int n)
{
    if(n<=0)
    {
        parz[0]=0;
        return 0;
    }
    else if(n==1)
    {
        parz[1]=1;
        return 1;
    }else
    {
        if (parz[n]) {
           return parz[n];
        }
        int ris= fibo(n-1)+fibo(n-2);
        parz[n]=ris;
        
        return ris;
    }
        
}
int fibo2(int n,int p[])
{
   
    if(n<=0)
    {
        p[n]=0;
        return 0;
    }
    else if(n==1)
    {
        p[n]=1;
        return 1;
    }else
    {
        if (p[n]) {
           
           return p[n];
        }
        int ris= fibo2(n-1,p)+fibo2(n-2,p);
        p[n]=ris;
    
        return ris;
    }
        
}
int main(int argc, char const *argv[])
{
    int n=8;
    int vet[n+1];
    for (int i = 0; i < n+1; i++)
    {
        vet[i]=0;
    }

    printf("%d\n",fibo(n));
    printf("%d\n",fibo2(n,vet));
   
    return 0;
}

