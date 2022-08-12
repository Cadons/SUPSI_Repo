#include <stdio.h>
#include <stdlib.h>
#include <time.h>
const int N = 10;

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
void trasponi(int *n, int *m,int matr[][*m])
{
    int rows = *n;
    int column = *m;
    int tmpMatr[column][rows];
    //Inizializza matrice
    for (int i = 0; i < column; i++)
    {
        for (int j = 0; j < rows; j++)
        {
            tmpMatr[i][j] = 0;
        }
    }
    //trasponi
    for (int i = 0; i < column; i++)
    {
        for (int j = 0; j < rows; j++)
        {
            tmpMatr[i][j] = matr[j][i];
        }
    }

    //modifica matrice iniziale
    for (int i = 0; i < column; i++)
    {
        for (int j = 0; j < rows; j++)
        {
            matr[i][j] = tmpMatr[i][j];
        }


    }
    *n = column;
    *m = rows;
}
void * trasponiDynamic(int *n, int *m,int  matr[][*m])
{
    int rows = *n;
    int column = *m;
    int (*tmpMatr) [column];
    //Inizializza matrice
    tmpMatr = calloc(column*rows*sizeof (int),0);//matrice con puntatori
    //trasponi
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < column; j++)
        {
            tmpMatr[j][i] = matr[i][j];

        }

    }

    *n = column;
    *m = rows;
    return tmpMatr;
}
int main(int argc, char const *argv[])
{
    int n, m;
    do
    {

        printf("Inserire numero righe colonne: ");
        scanf("%d %d", &n, &m);

    } while (n > N || m > N);
    srand(time(0));
    int mat[N][N];
    carica(mat,n,m);
    stampa(mat, n, m);

    stampa((int *)trasponiDynamic(&n,&m,mat), n, m);
    return 0;
}
