#include <stdio.h>
int main()
{
    int num=0;
    do{
 printf("Inserire un numero: ");
    scanf("%d",&num);
    if(num<1000||num>9999)
        {
            printf("NO\n");
            
        }else
        {
          printf("SI\n");
          int tmp=0;
          for(int i=1000;i>0;i/=10)
            {
                tmp=(num%i);
                tmp=(num-tmp)/i;
                num=num%i;
                printf("%d\t",tmp);  
            }  
            printf("\n");
        }
    }while (1);
    
    
   
    return 0;
}