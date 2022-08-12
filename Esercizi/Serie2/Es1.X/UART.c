
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>


void UART_ConfigurePins()   //used to configure UART4 TX and RX
{
	TRISFbits.TRISF12 = 0;   //TX digital output
    RPF12R = 2;     // 0010 U4TX - Mapping U4TX to RPF12;
    
    TRISFbits.TRISF13  = 1;   //RX digital input
    U4RXR = 9;     // 1001 RF13 - Mapping U4RX to RPF13
}
void UART4_EnableTX()
{
    U4STAbits.UTXEN=1;
}
void UART_ConfigureUart(int baud)
{
    unsigned int PbusClock = 40000000;
    unsigned int UartBrg = 0;
    
	U4MODEbits.ON     = 0;
    U4MODEbits.SIDL   = 0;
    U4MODEbits.IREN   = 0; 
    U4MODEbits.RTSMD  = 0;
    U4MODEbits.UEN0   = 0; 
    U4MODEbits.UEN1   = 0;
    U4MODEbits.WAKE   = 0;
    U4MODEbits.LPBACK = 0; 
    U4MODEbits.ABAUD  = 0;
    U4MODEbits.RXINV  = 0; 
    U4MODEbits.PDSEL1 = 0; 
    U4MODEbits.PDSEL0 = 0; 
    U4MODEbits.STSEL  = 0;  
    
    U4MODEbits.BRGH   = 0; 

    /* calculate brg */
    UartBrg = (int)(((float)PbusClock/(16 * baud) - 1) + 0.5 );
    U4BRG = UartBrg;

    U4STAbits.UTXEN    = 1;
    U4STAbits.URXEN    = 1;
    U4MODEbits.ON      = 1; 
    
    
}


int putU4( int c)
{
    
while(U4STAbits.UTXBF == 1);
    U4TXREG = c;
} // putU2

char getU4( void)
{
//RTS=0; // assert Request To Send !RTS

while (!U4STAbits.URXDA); // wait for a new char to arrive
//RTS=1;
    return U4RXREG; // read char from receive buffer
} // getU4

void putU4_string(char szData[])
{
    char *pData = szData;
    while(*pData)
    {
        putU4((*(pData++)));
    }
}

unsigned int getU4_string(char strg[])
{
     unsigned char c;
     static int j=0;
     while(U4STAbits.URXDA)
        {
            //c = (unsigned char)U4RXREG;
            c = getU4();
            strg[j++] = c;      
            if(j>0 && c == '\r'|| c=='\n')
            {
                strg[j-1] = 0;
                j = 0;
                return 1;   // flag goes to 1 when string is received completely. Remember to reset the flag in main.c 
            }       
        }

        
}