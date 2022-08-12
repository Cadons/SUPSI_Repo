
#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include <math.h>
void init_Timer(int mode,int T,int freq) {
    T2CONbits.ON = 0;//Disabilita timer
    T2CONbits.TCKPS = 0b111;//prescalar 256
    //Imposta modalità timer
    if (mode == 16) {
        
        T2CONbits.T32 = 0;
    } else {
        T2CONbits.T32 = 1;
    }
    TMR2=0;//reset
    
    PR2=(int)((float)(T*pow(10,-3))/(float)(1/freq)*pow(2,T2CONbits.TCKPS));//39062
    T2CONbits.ON=1;//Abilita timer

}
void resetTimer()
{
    TMR2=0;//reset
}