#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
    char character;
    int words=0,rows=0,characters=0;
    character=getchar();
    if(character!=EOF)
    rows++;
    else
    
    printf("Il file è vuoto!");
    while (character!=EOF)
    {
       characters++;
       character=getchar();
       if(character=='\n')
        rows++;
        else if (character==' ')
        words++;
    }
    printf("Characters:%d\nWords:%d\nRows:%d\n",characters,words,rows);
    
    return 0;
}
