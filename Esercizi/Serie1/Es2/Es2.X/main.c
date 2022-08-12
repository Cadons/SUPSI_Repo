/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 27 settembre 2021, 13.29
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
#define DELAY 1000000

/*
 * 
 */
int main(int argc, char** argv) {


    TRISA = 0x0000;
    int cnt = 0;
    LATAbits.w = 0;



    while (1) // forever
    {
        int counter = DELAY;
        while (counter) {
            counter--;
        }
        cnt++;

        switch (cnt) {
            case 1:
                LATAbits.LATA0 = 1;
                break;

            case 2:
                LATAbits.LATA1 = 1;
                break;

            case 3:
                LATAbits.LATA2 = 1;
                break;
            case 4:
                LATAbits.LATA3 = 1;
                break;
            case 5:
                LATAbits.LATA4 = 1;
                break;
            case 6:
                LATAbits.LATA5 = 1;
                break;
            case 7:
                LATAbits.LATA6 = 1;
                break;
            case 8:
                LATAbits.LATA7 = 1;
                break;
            case 9:
                LATAbits.w = 0;
                cnt = 0;
                break;
        }

        // LATAbits.LATA0 = ~ LATAbits.LATA0;
    }

}

