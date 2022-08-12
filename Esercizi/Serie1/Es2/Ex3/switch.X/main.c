/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 27 settembre 2021, 13.29
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "Pins.h"


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
int main(int argc, char** argv) {
    //Accensione led tramite switch
    Init_Input_Switch();
    Init_Output_Led();
    while (1) {
        LATAbits.LATA0 = PORTFbits.RF3;
        LATAbits.LATA1 = PORTFbits.RF5;
        LATAbits.LATA2 = PORTFbits.RF4;
        LATAbits.LATA3 = PORTDbits.RD15;
        LATAbits.LATA4 = PORTDbits.RD14;
        LATAbits.LATA5 = PORTBbits.RB11;
        LATAbits.LATA6 = PORTBbits.RB10;
        LATAbits.LATA7 = PORTBbits.RB9;

        if (PORTAbits.RA0 == 1 && PORTAbits.RA0 == 1)
            while (PORTBbits.RB9 == 1 && PORTFbits.RF3 == 1) {
                Delay(1000000);
                for(int i=0;i<=
                        7;i++)
                    Toggle(i);
                
            }


    }
}

