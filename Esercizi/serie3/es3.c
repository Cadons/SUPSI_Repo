#include <stdio.h>
#include <stdlib.h>
#include <time.h>
const int N=99;
void carica(int matr[][N],int n, int m)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            
           matr[i][j] = rand() % 10;
        }
    }
}
void stampa(int matr[][N], int rows, int col)
{
    printf("\n");
    for (int i = 0; i < rows; i++)
    {
        printf("|");
        for (int j = 0; j < col; j++)
        {
            printf("%d\t", matr[i][j]);
        }
        printf("|\n");
    }
}
void simmetria(int mat[][N],const int *n, const int *m)
{
    //Simmetria rispetto colonna centrale
    for (int i = 0; i < *n; i++)
    {
        for (int j = 0; j < (*m/2)+1; j++)
        {
            if(mat[i][j]>mat[i][(*m-1)-j])
            {
                mat[i][(*m-1)-j]=mat[i][j];

            }else
            {
               mat[i][j]= mat[i][(*m-1)-j];
            }
                
        }
        
    }
    
}
int main(int argc, char const *argv[])
{
       int n, m;
    do
    {
        printf("Inserire numero righe colonne(Numero dispari): ");
        scanf("%d %d", &n, &m);

    } while (n<0||m > N||(m%2==0));
    int mat[n][m];
    srand(time(0));
    carica(mat,n,m);
    stampa(mat,n,m);
    simmetria(mat,&n,&m);
    stampa(mat,n,m);
    return 0;
}
