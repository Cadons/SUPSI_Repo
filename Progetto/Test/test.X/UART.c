
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "UART.h"
#define macro_enable_interrupts()\
{ unsigned int val = 0;\
asm volatile(" mfc0 %0 , $13":"=r"(val) );\
val |= 0x00800000 ;\
asm volatile(" mtc0 %0 , $13" : "+r"(val) );\
INTCONbits.MVEC = 1;\
__builtin_enable_interrupts ();}

static void UART4_ConfigurePins() //used to configure UART4 TX and RX
{
    TRISFbits.TRISF12 = 0; //TX digital output
    RPF12R = 2; // 0010 U4TX - Mapping U4TX to RPF12;

    TRISFbits.TRISF13 = 1; //RX digital input
    U4RXR = 9; // 1001 RF13 - Mapping U4RX to RPF13
}

void UART4_EnableTX()//abilita trasmissione
{
    U4STAbits.UTXEN = 1;
}

void InitU4(int baud, unsigned int PbusClock, unsigned parity, unsigned int stopBit, unsigned int flowControl, int priority, int sub_priority) {
    UART4_ConfigurePins();
    unsigned int UartBrg = 0;
    U4MODEbits.ON = 0; //disabilita UART4
    IFS2bits.U4RXIF = 0;

    IPC9bits.U4IP = priority;
    IPC9bits.U4IS = sub_priority;



    U4MODEbits.SIDL = 0;
    U4MODEbits.IREN = 0;
    U4MODEbits.RTSMD = flowControl; //controllo di flusso
    U4MODEbits.UEN0 = 0;
    U4MODEbits.UEN1 = 0;
    U4MODEbits.WAKE = 0;
    U4MODEbits.LPBACK = 0;
    U4MODEbits.ABAUD = 0;
    U4MODEbits.RXINV = 0;
    U4MODEbits.PDSEL1 = parity; //""
    U4MODEbits.PDSEL0 = parity; //0 no parity 2 odd parity 1 even parity 3 no parity 
    U4MODEbits.STSEL = stopBit; //bit di stop 0=1 stop-bit 1=2 stop-bit  

    U4MODEbits.BRGH = 0; //imposta scala di velocita del baud o [0 o 1]

    /* calculate brg */
    UartBrg = (int) (((float) PbusClock / (16 * baud) - 1) + 0.5);
    U4BRG = UartBrg; //imposta brg

    U4STAbits.UTXEN = 1; //abilita trasmissione
    U4STAbits.URXEN = 1; //abilita ricezione

    macro_enable_interrupts();
    IEC2bits.U4RXIE = 1;
    U4MODEbits.ON = 1; //abilita UART4
}

void U4setInterruptPriority(int priority, int sub_priority) {

    IPC9bits.U4IP = priority;
    IPC9bits.U4IS = sub_priority;
}

static int putU4(int c) //scrivi un carattere su UART
{

    while (U4STAbits.UTXBF == 1); //fin quando non si può trasmettere richiedi di trasmettere(il buffer è pieno)
    U4TXREG = c; //inserisci nel registro di trasmissione il carattere
} // putU2

static char getU4(void)//leggi un carattere su UART
{
    //RTS=0; // assert Request To Send !RTS

    while (!U4STAbits.URXDA); // wait for a new char to arrive
    //RTS=1;
    return U4RXREG; // read char from receive buffer
} // getU4

void putU4_string(char szData[])//stampa una stringa su UART
{
    char *pData = szData; //buffer stirnga di output
    while (*pData) {
        putU4((*(pData++))); //scrivi sul terminale il carattere e sposta il puntatore all'elemento successivo
    }
}

unsigned int getU4_string(char strg[]) {
    unsigned char c;
    static int j = 0;
    while (U4STAbits.URXDA)//esegui fin quando c'è qualcosa del buffer ad ogni ciclo rimuovi un byte(un carattere)
    {
        //c = (unsigned char)U4RXREG;
        c = getU4(); //leggi carattere dal buffer uart
        strg[j++] = c; //inserisci il carattere nella stringa
        if (j > 0 && c == '\r' || c == '\n') {
            strg[j - 1] = 0; //rimuovi l'ultimo carattere(di stop)
            j = 0; //resetta l'indice
            return 1; // flag goes to 1 when string is received completely. Remember to reset the flag in main.c 
        }
    }
    return 0;

}
