
#include <stdio.h>
#include <stdlib.h>
#include<string.h>
int position(char str[], char ch)
{
	for (int i = 0; i < strlen(str); i++)
	{
		if (str[i] == ch)
			return i;
	}
	return -1;
}
char* tokenizer(char* str, char delimiter)
{
	static char* tmpString;
	if (str != NULL)
		tmpString = str;
	if (strlen(tmpString) > 1)
	{
		int pos=position(tmpString, delimiter);
		if (pos == -1)
		{
			char* tmp = tmpString;
			tmpString = tmpString + strlen(tmpString);
			
			return tmp;
		}
		else
		{
			 *(tmpString + pos) = '\0';
			 char* tmp = tmpString;
			 tmpString = tmpString + pos + 1;
			 return tmp;
		}
	}
	else
	{
		return NULL;

	}

	
}
int main()
{
	char str[] = "Ciao come stai";
	char* punt = tokenizer(str, ' ');
	for (int i = 0;*punt!='\0'; i++)
	{
		printf("%c",*punt);
		punt++;
	}
	printf("\n");
	punt = tokenizer(NULL, ' ');
	for (int i = 0; *punt != '\0'; i++)
	{
		printf("%c", *punt);
		punt++;
	}
	printf("\n"); 
	punt = tokenizer(NULL, ' ');
	for (int i = 0; *punt != '\0'; i++)
	{
		printf("%c", *punt);
		punt++;
	}
	printf("\n");

}

