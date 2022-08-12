/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 24 ottobre 2021, 15.56
 */

#include <stdio.h>
#include <stdlib.h>
#include "Pins.h"
#include "Timer.h"
#include <p32xxxx.h>
// Device Config Bits in DEVCFG1 :
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
int flag = 0;
int n_interrupt = 0;
#pragma interrupt Timer2Handler ipl1AUTO vector 8

void Timer2Handler(void) {

    flag = 1;
    n_interrupt++;
    IFS0bits.T2IF = 0;

}

void ModifOscillator4(int nosc, int molt) {
    asm volatile ("di"); // Disabling interrupts IS required
    SYSKEY = 0x33333333; // ensure OSCCON is locked
    SYSKEY = 0xAA996655; // Key1
    SYSKEY = 0x556699AA; // Key2 this writing must be back -to - back
    // Beware : Secured registers are now unlocked !
    OSCCONbits.NOSC = 1; // FRC divided by 4 (8/4=2 MHz )
    OSCCONbits.PLLMULT = 0b111; // Div_4

    OSCCONbits.PBDIV = 0;
    OSCCONbits.PLLODIV = 2;
    OSCCONbits.OSWEN = 1; // Initiate clock switch
    SYSKEY = 0x33333333; // Lock
    // Secured registers are now Locked . This is a safe mode .
    while (OSCCONbits . OSWEN != 0) {
    }
    asm volatile ("ei"); // Enable interrupts

}

int main(int argc, char** argv) {

    Init_Output_Led();

    for (int i = 0; i < 8; i++) {
        switchOffLed(i);

    }

    init_Timer2(0, 500, 20000000, 256); //Configura il timer: usa modalità 32 bit, tempo 0.5 s=500ms, imposta frequenza di 20Mhz
    T2setInterruptPriority(1, 0); //imposta priorità timer2
    resetTimer2();
    T2CONbits.ON = 1;
    int div = 0;
    while (1) {
        if (flag) {
            Toggle(0);
            flag = 0;
            resetTimer2();
        }
        /* if(n_interrupt>=10)
         {
             //cambia clock
             ModifOscillator4(0b111,0b011);
             n_interrupt=0;
            
         }*/
        if (n_interrupt >= 10) {
            ModifOscillator4(0b001, 0b111);
            n_interrupt = 0;
        }
    }
    return (EXIT_SUCCESS);
}

