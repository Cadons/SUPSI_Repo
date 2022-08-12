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
    int num;
    //a
    do
    {
        printf("Inserire un numero: ");
        scanf("%d", &num);
        if (num >= 0)
            printf("%d\n", fat(num));
    } while (0);
    //b
    do
    {
        printf("Inserire un numero: ");
        scanf("%d", &num);
        if (num >= 0)
            if (num == 0)
                printf("1");
            else
            {
                int ris = 1;
                for (int i = 0; num > 1; i++)
                {

                    ris *= num;
                    num--;
                    printf("Parziale %d: %d\n", i + 1, ris);
                }
            }

    } while (0);
    return 0;
}
