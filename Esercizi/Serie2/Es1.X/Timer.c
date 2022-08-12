
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>

void init_Timer(int mode) {
    T2CONbits.ON = 0;//Disabilita timer
    T2CONbits.TCKPS = 0b111;//prescalar 256
    //Imposta modalità timer
    if (mode == 16) {
        
        T2CONbits.T32 = 0;
    } else {
        T2CONbits.T32 = 0;
    }
    TMR2=0;//reset
    PR2=2000;
    T2CONbits.ON=1;//Abilita timer

}
