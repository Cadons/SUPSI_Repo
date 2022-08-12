/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 6 dicembre 2021, 13.02
 */

#include <stdio.h>
#include <stdlib.h>
#include "LCD.h"
#include "Timer.h"
#include "Pins.h"
#include <p32xxxx.h>
#include <string.h>
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
#pragma config FCKSM = CSECMD

/*
 * 
 */
void DelayNmSec(int n) {
    startTimer1();
    for (int i = 0; i < n; i++)
        awaitT1();
    //Toggle(0);
    stopTimer1();
}

void analogicalManualReader() {
    int ADCValue;
    ANSELB = 0xFFFB; // PORTB = D i g i t a l ; RB2 = a n a l o g
    AD1CON1 = 0x0000; // SAMP b i t = 0 e n d s s am p l i n g
    // and s t a r t s c o n v e r t i n g
    AD1CHS = 0x00020000; // Connec t RB2/AN2 a s CH0 i n p u t
    // i n t h i s ex ample RB2/AN2 i s t h e i n p u t
    AD1CSSL = 0;
    AD1CON3 = 0x0002; // Manual Sample , TAD = i n t e r n a l 6 TPB
    AD1CON2 = 0;
    AD1CON1SET = 0x8000; // t u r n on t h e ADC
    char str[10];
    while (1) // r e p e a t c o n t i n u o u s l y
    {
        AD1CON1SET = 0x0002; // start sampling . . .
        DelayNmSec(1); // for 100 ms
        AD1CON1CLR = 0x0002; // start Converting
        while (!(AD1CON1 & 0x0001)); // c o n v e r s i o n done ?
        ADCValue = ADC1BUF0; // y e s t h e n g e t ADC v a l u e
        cmdLCD(0x80 | 0x40);
        sprintf(str, "ADCval  %d ", ADCValue);
        putsLCD(str);
    } // r e p e a t
}

void analogicalAutomaticReader() {
    int ADCValue;

    ANSELB = 0xFF7F; // a l l PORTB = D i g i t a l bu t RB7 = a n a l o g
    AD1CON1 = 0x0004; // ASAM b i t = 1 i m p l i e s a c q u i s i t i o n
    // s t a r t s i mm e d i a t e l y a f t e r l a s t
    // c o n v e r s i o n i s done
    AD1CHS = 0x00070000; // Connec t RB7/AN7 a s CH0 i n p u t
    // i n t h i s ex ample RB7/AN7 i s t h e i n p u t
    AD1CSSL = 0;
    AD1CON3 = 0x0002; // Sample tim e manual , TAD = i n t e r n a l 6 TPB
    AD1CON2 = 0;
    AD1CON1SET = 0x8000; // t u r n ON t h e ADC
    char str[10];

    while (1) // r e p e a t c o n t i n u o u s l y
    {
        DelayNmSec(1); // s am ple f o r 100 mS
        AD1CON1CLR = 0x0002; // S top s am pli n g , s t a r t C o n v e r t i n g
        while (!(AD1CON1 & 0x0001)); // c o n v e r s i o n done ?
        ADCValue = ADC1BUF0; // y e s t h e n g e t ADC v a l u e
        cmdLCD(0x80 | 0x40);
        sprintf(str, "ADCval %d ", ADCValue);
        putsLCD(str);
    } // r e p e a t
}

int main(int argc, char** argv) {
    init_Timer1(100, 20000000, 256);
    LCDinit(20000000);
    putsLCD("AD Converter");
    cmdLCD(0x82);

    putsLCD("-");

/*    cmdLCD(0x80 | 0x40);

    Init_Output_Led();

    analogicalAutomaticReader();*/
    return (EXIT_SUCCESS);
}

