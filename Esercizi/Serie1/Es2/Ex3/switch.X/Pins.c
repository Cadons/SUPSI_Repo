/* 
 * File:   Pins.c
 * Author: Cadons
 *
 * Created on 27 settembre 2021, 21.27
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
/* Disable JTAG to use RA0 */
#pragma config JTAGEN = OFF
#pragma config FWDTEN = OFF
/* Device Config Bits in DEVCFG1 : */
#pragma config FNOSC = FRCPLL
#pragma config FSOSCEN = OFF
#pragma config POSCMOD = XT
#pragma config OSCIOFNC = ON
#pragma config FPBDIV = DIV_2
/* Device Config Bits in DEVCFG2 : */
#pragma config FPLLIDIV = DIV_2
#pragma config FPLLMUL = MUL_20
#pragma config FPLLODIV = DIV_1

/*
 * 
 */
void Delay(int d) {

    while (d) {
        d--;
    }
}

void Init_Output_Led() {
    TRISA = 0x0000; //set led
}

void Init_Input_Switch() {
    TRISFbits.TRISF3 = 1; // RF3 (SW0) configured as input
    TRISFbits.TRISF5 = 1; // RF5 (SW1) configured as input
    TRISFbits.TRISF4 = 1; // RF4 (SW2) configured as input
    TRISDbits.TRISD15 = 1; // RD15 (SW3) configured as input
    TRISDbits.TRISD14 = 1; // RD14 (SW4) configured as input
    TRISBbits.TRISB11 = 1; // RB11 (SW5) configured as input
    ANSELBbits.ANSB11 = 0; // RB11 (SW5) disabled analog
    TRISBbits.TRISB10 = 1; // RB10 (SW6) configured as input
    ANSELBbits.ANSB10 = 0; // RB10 (SW6) disabled analog
    TRISBbits.TRISB9 = 1; // RB9(SW6) configured as input
    ANSELBbits.ANSB9 = 0; // RB9 (SW6) disabled analog
}

void Toggle(int ledNumber) {
    //toggle su led0
    
    switch (ledNumber) {
            case 0:
            LATAbits.LATA0 = ~LATAbits.LATA0;
            break;
             case 1:
            LATAbits.LATA1 = ~LATAbits.LATA1;
            break;
             case 2:
            LATAbits.LATA2 = ~LATAbits.LATA2;
            break;
             case 3:
            LATAbits.LATA3 = ~LATAbits.LATA3;
            break;
             case 4:
            LATAbits.LATA4 = ~LATAbits.LATA4;
            break;
             case 5:
            LATAbits.LATA5 = ~LATAbits.LATA5;
            break;
             case 6:
            LATAbits.LATA6 = ~LATAbits.LATA6;
            break;
             case 7:
            LATAbits.LATA7 = ~LATAbits.LATA7;
            break;
    }
}

