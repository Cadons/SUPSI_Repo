
#include <stdio.h>
#include<stdlib.h>
#include <time.h>
/*//es3
const int N = 10;

int minimoRigheV1(int mat[][N])
{
	int min = mat[0][0];
	int minRighe[10] = { 0 };

		for (int i = 0; i < N; i++)
		{
			minRighe[i] = mat[i][0];
			for (int j = 1; j < N; j++)
			{
				if (mat[i][j] < minRighe[i])
				{
					minRighe[i] = mat[i][j];
				}
				
			}
			
		}
	//cerca massimo dei minimo
			int max = minRighe[0];
			int k = 0;
			for (int i = 0; i < N; i++)
			{
				if (minRighe[i] > max)
				{
					max = minRighe[i];
					k = i;
				}
			}
			return k;
	
	
}
int minimoRigheV3(int mat[][N],int n)
{
	int min = mat[0][0];
	int minRighe[N] = { 0 };

	for (int i = 0; i < n; i++)
	{
		minRighe[i] = mat[i][0];
		for (int j = 1; j < n; j++)
		{
			if (mat[i][j] < minRighe[i])
			{
				minRighe[i] = mat[i][j];
			}
			printf(" %d\t", mat[i][j]);

		}
		printf("\n");
	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < n; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
int minimoRigheV2(int* mat)
{
	
	int min = *mat;
	int minRighe[10] = { 0 };

	for (int k = 0; k < N; k++)
	{

		minRighe[k] = *mat;
		for (int i = 0; i < N; i++)
		{
		
		
				if (*mat < minRighe[k])
				{
					minRighe[k] = *mat;
				}
				//printf("%d\t", *mat);
				mat++;
		}
		//printf("\n");
		
	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < N; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
int minimoRigheV4(int* mat, int n)
{

	int min = *mat;
	int minRighe[10] = { 0 };

	for (int k = 0; k < n; k++)
	{

		minRighe[k] = *mat;
		for (int i = 0; i < n; i++)
		{


			if (*mat < minRighe[k])
			{
				minRighe[k] = *mat;
			}

			
			mat++;
		}
		mat =  mat +(N-n);


	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < N; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
int main()
{
	int mat[N][N];
	srand(time(0));
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			mat[i][j] = rand() % 100;
			printf(" %d\t", mat[i][j]);
		}
		printf("\n");
	}
	int rigaMinimo = minimoRigheV1(mat);
	printf("\n1)Riga con valore minimo: %d\n", rigaMinimo);
	rigaMinimo = minimoRigheV2(&mat[0][0]);
	printf("2)Riga con valore minimo: %d\n", rigaMinimo);
	rigaMinimo = minimoRigheV3(mat,5);
	printf("3)Riga con valore minimo: %d\n", rigaMinimo);
	rigaMinimo = minimoRigheV4(&mat[0][0], 5);
	printf("4)Riga con valore minimo: %d\n", rigaMinimo);

}*/
//es4

int minimoRigheV1( int m, int n,int mat[][n])
{
	int min = mat[0][0];
	int minRighe[n];

	for (int i = 0; i < m; i++)
	{
		minRighe[i] = mat[i][0];
		for (int j = 1; j < n; j++)
		{
			if (mat[i][j] < minRighe[i])
			{
				minRighe[i] = mat[i][j];
			}

		}

	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < n; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
/*
int minimoRigheV3(int mat[][N], int n)
{
	int min = mat[0][0];
	int minRighe[N] = { 0 };

	for (int i = 0; i < n; i++)
	{
		minRighe[i] = mat[i][0];
		for (int j = 1; j < n; j++)
		{
			if (mat[i][j] < minRighe[i])
			{
				minRighe[i] = mat[i][j];
			}
			printf(" %d\t", mat[i][j]);

		}
		printf("\n");
	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < n; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
int minimoRigheV2(int* mat)
{

	int min = *mat;
	int minRighe[10] = { 0 };

	for (int k = 0; k < N; k++)
	{

		minRighe[k] = *mat;
		for (int i = 0; i < N; i++)
		{


			if (*mat < minRighe[k])
			{
				minRighe[k] = *mat;
			}
			//printf("%d\t", *mat);
			mat++;
		}
		//printf("\n");

	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < N; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}
int minimoRigheV4(int* mat, int n)
{

	int min = *mat;
	int minRighe[10] = { 0 };

	for (int k = 0; k < n; k++)
	{

		minRighe[k] = *mat;
		for (int i = 0; i < n; i++)
		{


			if (*mat < minRighe[k])
			{
				minRighe[k] = *mat;
			}


			mat++;
		}
		mat = mat + (N - n);


	}
	//cerca massimo dei minimo
	int max = minRighe[0];
	int k = 0;
	for (int i = 0; i < N; i++)
	{
		if (minRighe[i] > max)
		{
			max = minRighe[i];
			k = i;
		}
	}
	return k;


}*/
int main()
{
	int n;
	printf("Insert n:");
	scanf("%d", &n);
	int mat[n][n];
	srand(time(0));
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			mat[i][j] = rand() % 100;
			printf(" %d\t", mat[i][j]);
		}
		printf("\n");
	}
	int rigaMinimo = minimoRigheV1(m,n,mat);
	printf("\n1)Riga con valore minimo: %d\n", rigaMinimo);
	/*rigaMinimo = minimoRigheV2(&mat[0][0]);
	printf("2)Riga con valore minimo: %d\n", rigaMinimo);
	rigaMinimo = minimoRigheV3(mat, 5);
	printf("3)Riga con valore minimo: %d\n", rigaMinimo);
	rigaMinimo = minimoRigheV4(&mat[0][0], 5);
	printf("4)Riga con valore minimo: %d\n", rigaMinimo);*/
}
