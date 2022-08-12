

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "Timer.h"
#pragma config FNOSC =	PRIPLL
#pragma config FSOSCEN =	OFF
#pragma config POSCMOD =	XT
#pragma config OSCIOFNC =	ON
#pragma config FPBDIV =	DIV_4

// Device Config Bits in  DEVCFG2:	
#pragma config FPLLIDIV =	DIV_4
#pragma config FPLLMUL =	MUL_20
#pragma config FPLLODIV =	DIV_2

int main(int argc, char** argv) {

    init_Timer2(0,20,10000000,256);
    RPB8R=0b1011;
    ANSELBbits.ANSB8=0;
    TRISBbits.TRISB8=0;
    OC5CONbits.OCM = 0b110; // PWM mode without fault pin
    OC5RS =350; //(int)((50./100.)*(PR2+1)); // d u ty c y c l e = OC1RS/ (PR2+1) = 25%
    OC5R =350; //(int)((50./100.)*(PR2+1)); // i n i t b e f o r e t u r n i n g OC1 on , t h e n i t i s re ad ?o n l y
    startTimer2(); // t u r n on Time r2
    OC5CONbits.ON = 1; // t u r n on OC1
    // some code . . .
    while (1) {
    }

}

