/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 29 novembre 2021, 13.32
 */

#include <stdio.h>
#include <stdlib.h>
#include "LCD.h"
#include <p32xxxx.h>

#pragma config FNOSC = FRCPLL // Select XTPLL , HSPLL , ECPLL , FRCPLL in

#pragma config FSOSCEN = OFF // Disable Secondary oscillator
#pragma config POSCMOD = XT // external crystal / resonator

#pragma config OSCIOFNC = ON // CLKO Enable Configuration bit
#pragma config FPBDIV = DIV_2 // Peripheral Bus Clock Divisor
// Device Config Bits in DEVCFG2 :
#pragma config FPLLIDIV = DIV_2 // PLL Input Divider
#pragma config FPLLMUL = MUL_20 // PLL Multiplier
#pragma config FPLLODIV = DIV_2 // PLL Output Divider
// disable JTAG
#pragma config JTAGEN = OFF
#pragma config FWDTEN = OFF
/*
 * 
 */
#pragma interrupt Timer1Handler ipl1AUTO vector 4
int cnt = 0;
int flag = 0;

void Timer1Handler(void) {
    cnt++;
    IFS0bits.T1IF = 0;
    flag = 1;
}

void main() {
    LCDinit(20000000);
    putsLCD("Serie6");
    init_Timer1(1000, 20000000, 256);
    T1setInterruptPriority(1, 0);
    cmdLCD(0x80 | 0x40);
    startTimer1();
    cmdLCD(0x80 | 0x40);
    char stringa[9];
    sprintf(stringa, "%d", cnt);
    putsLCD(stringa);

    do {
        if (flag == 1) {
            clearLCD();
            putsLCD(cnt);
            flag = 0;
            resetTimer1();

        }

    } while (1);

}

