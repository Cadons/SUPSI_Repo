
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include <math.h>
#include "Timer.h"
#define macro_enable_interrupts()\
{ unsigned int val = 0;\
asm volatile(" mfc0 %0 , $13":"=r"(val) );\
val |= 0x00800000 ;\
asm volatile(" mtc0 %0 , $13" : "+r"(val) );\
INTCONbits.MVEC = 1;\
__builtin_enable_interrupts ();}

void init_Timer2(int mode, int T, unsigned long freq, int presc) {

    T2CONbits.ON = 0; //Disabilita timer
    //Imposta modalità timer

    if (mode == 0) {

        T2CONbits.T32 = 0;
        IPC2bits.T2IP = 1;
        IPC2bits.T2IS = 0;
    } else {
        T2CONbits.T32 = 1;
        PR3 = 0;
        IPC3bits.T3IP = 1;
        IPC3bits.T3IS = 0;
    }
    switch (presc) {
        case 1:
            T2CONbits.TCKPS = 0b000; //prescalar 1
            presc = 1;
            break;
        case 2:
            T2CONbits.TCKPS = 0b001; //prescalar 2
            presc = 2;
            break;
        case 4:
            T2CONbits.TCKPS = 0b010; //prescalar 4
            presc = 4;
            break;
        case 8:
            T2CONbits.TCKPS = 0b011; //prescalar 8
            presc = 8;
            break;
        case 16:
            T2CONbits.TCKPS = 0b100; //prescalar 16
            presc = 16;
            break;
        case 32:
            T2CONbits.TCKPS = 0b101; //prescalar 32
            presc = 32;
            break;
        case 64:
            T2CONbits.TCKPS = 0b110; //prescalar 64
            presc = 64;
            break;
        case 256:
            T2CONbits.TCKPS = 0b111; //prescalar 256
            presc = 256;
            break;
        default:
            //se valore errato
            T2CONbits.TCKPS = 0b111; //prescalar 256
            presc = 256;
            break;

    }
    T2CONbits.TCS = 0;
    TMR2 = 0; //reset
    PR2 = (int) ((float) (T * pow(10, -3)) / (float) ((1 / (float) freq) * presc)); //calcola PR2 ossia valore massimo a cui il timer conta e poi ricomincia il ciclo

    macro_enable_interrupts();
    if (mode)
        IEC0bits.T3IE = 1; // i n t e r r u p t Timer2 e n a b l e

    else
        IEC0bits.T2IE = 1; // i n t e r r u p t Timer2 e n a b l e

}
void changePR(int T, int freq, int presc)
{
        PR2 = (int) ((float) (T * pow(10, -3)) / (float) ((1 / (float) freq) * presc)); //calcola PR2 ossia valore massimo a cui il timer conta e poi ricomincia il ciclo

}
void T2setInterruptPriority(int priority, int sub_priority) {
    if (T2CONbits.T32 == 0) {
        IPC2bits.T2IP = priority;
        IPC2bits.T2IS = sub_priority;
    } else {
        PR3 = 0;
        IPC3bits.T3IP = priority;
        IPC3bits.T3IS = sub_priority;
    }
}

void startTimer2() {
    T2CONbits.ON = 1;
}

void stopTimer2() {
    T2CONbits.ON = 0;
}

void resetTimer2() {
    TMR2 = 0; //reset
}

int awaitT2() {
    while (TMR2 <= (PR2 - 1)); //attendi che il timer raggiunga il valore di PR2-1
    return 1;
}