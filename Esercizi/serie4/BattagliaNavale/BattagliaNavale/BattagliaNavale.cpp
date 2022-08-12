
// BattagliaNavale.cpp : Questo file contiene la funzione 'main', in cui inizia e termina l'esecuzione del programma.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctype.h>
const int N = 4;
int randomPosition()
{
	return rand() % N;
}
void setupWorld(char world[][N])
{
	for (int i = 0; i < N; i++)
	{

		for (int j = 0; j < N; j++)
		{
			world[i][j] = ' ';
		}
	}
	int r = randomPosition();
	int c = randomPosition();
	world[r][c] = 'N';
	while (world[r][c] == 'N')
	{
		r = randomPosition();
		c = randomPosition();
	}
	world[r][c] = 'S';
}
void printWorld(char world[][N])
{
	for (int i = 0; i < N; i++)
	{
		printf("|");
		for (int j = 0; j < N; j++)
		{
			
				
			printf(" %c ", world[i][j]);
			printf("|");
		}
		
		printf("\n");

	}
	printf("\n");
}
void cleanPlace(char world[][N],char ch)
{
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
		{
			if (world[i][j] == ch)
			{
				world[i][j] = ' ';
				return;
			}
		}
}
int isUpon(char world[][N], int r, int c)
{
	if (world[r][c] != ' ')
		return true;
	else
		return false;
}
void play(char world[][N])
{
	int r = randomPosition();
	int c = randomPosition();
	int round = 0;

	do
	{
		if (round % 2 == 0)
		{
			//CPU	
			r = randomPosition();
			c = randomPosition();
			cleanPlace(world, 'S');
			if (isUpon(world, r, c))
			{
				printf("Game Over! CPU has won");
				return;
			}
			else
				world[r][c] = 'S';
		}
		else
		{
			//User
			do
			{
				printf("Insert new coordinates x y:");
				scanf_s("%d %d", &r, &c);
				if ( r < 0 || r >= N || c<0 || c>=N)
				{
					c = -1;
					r = -1;
					printf("ERROR: Insert valid values!\n");
					
				}
				
			} while ( r < 0 || r >= N || c<0 || c>=N);
			cleanPlace(world, 'N');
			if (isUpon(world, r, c))
			{
				printf("Game Over! User has won");
				return;
			}
			else
				world[r][c] = 'N';
			printWorld(world);

			
		}
		round++;
	} while (true);
}
int main()
{
	char world[N][N];
	srand(time(NULL));
	setupWorld(world);
	play(world);

	return 0;

 
}
