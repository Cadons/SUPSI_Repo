#include <stdio.h>
#include <stdlib.h>
typedef struct _byte
{
    unsigned char b0:1;
    unsigned char b1:1;
    unsigned char b2:1;
    unsigned char b3:1;
    unsigned char b4:1;
    unsigned char b5:1;
    unsigned char b6:1;
    unsigned char b7:1;


}Byte;
int convertToDecimal(Byte b)
{
    char ris=(b.b7<<7)+(b.b6<<6)+(b.b5<<5)+(b.b4<<4)+(b.b3<<3)+(b.b2<<2)+(b.b1<<1)+b.b0;
    return ris;
}
int main() {
    printf("SizeOf: %d\n",sizeof (Byte));
    FILE * file;
    Byte * myByte= malloc(sizeof (Byte));
    myByte->b0=0;
    myByte->b1=0;
    myByte->b2=1;
    myByte->b3=0;
    myByte->b4=1;
    myByte->b5=0;
    myByte->b6=0;
    myByte->b7=0;
    file= fopen("ris.bin","w");
    if(file!=NULL)
    {
        fwrite(myByte,sizeof (Byte),1,file);

    }
    fclose(file);
    file= fopen("ris.bin","r");
    if(file!=NULL)
    {
        fseek(file,0,SEEK_END);
        printf("SizeOf from file: %d\n", ftell(file));

    }
    fclose(file);
    printf("%d\n"+convertToDecimal(*myByte));
        return 0;
}
