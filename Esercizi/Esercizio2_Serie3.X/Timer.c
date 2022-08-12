
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include <math.h>

#include "Timer.h"
void init_Timer2(int mode,int T,unsigned long freq) {
    T2CONbits.ON = 0;//Disabilita timer
    T2CONbits.TCKPS = 0b111;//prescalar 256
    //Imposta modalità timer
    if (mode == 16) {
        
        T2CONbits.T32 = 0;
    } else {
        T2CONbits.T32 = 1;
    }
    TMR2=0;//reset
    int presc=pow(2,T2CONbits.TCKPS+1);
   
    PR2=(int)((float)(T*pow(10,-3))/(float)((1/(float)freq)*presc));//calcola PR2 ossia valore massimo a cui il timer conta e poi ricomincia il ciclo
    T2CONbits.ON=1;//Abilita timer

}
void resetTimer2()
{
    TMR2=0;//reset
}
int awaitT2()
{
     while(TMR2<=(PR2-1));//attendi che il timer raggiunga il valore di PR2-1
     return 1;
}