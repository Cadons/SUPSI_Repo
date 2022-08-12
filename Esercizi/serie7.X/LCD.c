/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 23 novembre 2021, 9.00
 */

#include <stdio.h>
#include <stdlib.h>
#include "Timer.h"
#include <p32xxxx.h>
/*
 * 
 */
#define LCDDATA 1 // RS = 1 ; access data register
#define LCDCMD 0 // RS = 0 ; access command register
#define PMDATA PMDIN // PMP data buffer
#define cmdLCD(c) writeLCD((LCDCMD),(c));
#define busyLCD() readLCD(LCDCMD)&0x80
#define putLCD(d) writeLCD(LCDDATA,(d))
#define clearLCD() writeLCD((LCDCMD),1)

int delaysCounter = 0;
#pragma interrupt Timer2Handler ipl1AUTO vector 8
void Timer2Handler(void) {
    delaysCounter++;
    IFS0bits.T2IF = 0;
}

void Delayms(unsigned t) {
   
    delaysCounter = 0;  
    startTimer2();
    while (delaysCounter < t);
    stopTimer2();
    delaysCounter = 0;
}

void LCDinit(int FPBCLK) {

    TRISBbits.TRISB15 = 0;
    ANSELBbits.ANSB15 = 0; //DISP_RS
    TRISDbits.TRISD5 = 0; //DISP_RW    
    TRISDbits.TRISD4 = 0; //DISP_EN
    //LCD IO pins
    TRISEbits.TRISE0 = 1;
    TRISEbits.TRISE1 = 1;
    TRISEbits.TRISE2 = 1;
    ANSELEbits.ANSE2 = 0;

    TRISEbits.TRISE3 = 1;
    TRISEbits.TRISE4 = 1;
    ANSELEbits.ANSE4 = 0;
    TRISEbits.TRISE5 = 1;
    ANSELEbits.ANSE5 = 0;

    TRISEbits.TRISE6 = 1;
    ANSELEbits.ANSE6 = 0;

    TRISEbits.TRISE7 = 1;
    ANSELEbits.ANSE7 = 0;


    /* before init the pins related to LCD - ANSx ,
    TRISx */
    // PMP initialization
    PMCON = 0x83BF; // Enable the PMP , long waits
    PMMODE = 0x3FF; // Master Mode 1
    PMAEN = 0x0001; // PMA0 enabled
    init_Timer2(0, 1, FPBCLK, 256); //Configura il timer: usa modalit? 32 bit, tempo 0.5 s=1, imposta frequenza di 20Mhz
    T2setInterruptPriority(1, 0);
    PMADDR = LCDCMD; // command register ( ADDR = 0)
    PMDATA = 0x38; // 8 -bit interface , 2 lines , 5x7
    Delayms(1); // >48 us
    PMDATA = 0x0f; // ON , on cursor , on blink
    Delayms(1); // >48 us
    PMDATA = 0x01; // clear display
    Delayms(2); // >1.6 ms
    PMDATA = 0x06; // increment cursor , no shift
    Delayms(2); // >1.6 ms
}

char readLCD(int addr) {
    int dummy;
    while (PMMODEbits . BUSY) {
    } // wait for PMP available
    PMADDR = addr; // select the command address
    dummy = PMDATA; // init read cycle , dummy read
    while (PMMODEbits . BUSY) {
    } // wait for PMP to be available
    return ( PMDATA); // read the status register
}
void putsLCD ( char *s)
{
while (*s)
{
putLCD(*s++) ;
}
}// putsLCD
void writeLCD(int addr, char c) {
    while (busyLCD()) {
    } // check busy flag of LCD
    while (PMMODEbits.BUSY) {
    } // wait for PMP available
    PMADDR = addr;
    PMDATA = c;
}



