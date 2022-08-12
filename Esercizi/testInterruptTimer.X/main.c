/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 24 ottobre 2021, 11.10
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
/*
 * 
 */
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

#define macro_enable_interrupts()\
{ unsigned int val = 0;\
asm volatile(" mfc0 %0 , $13":"=r"(val) );\
val |= 0x00800000 ;\
asm volatile(" mtc0 %0 , $13" : "+r"(val) );\
INTCONbits.MVEC = 1;\
__builtin_enable_interrupts ();}
/* Thi rd ISR a l t e r n a t i v e */
#pragma interrupt Timer2IntHandler ipl1AUTO vector 8
void Timer2IntHandler( void )
{
LATAbits.LATA0 = ~LATAbits.LATA0; // t o g g l e RA1
/* Re s e t T2 i n t e r r u p t f l a g */
IFS0bits.T2IF = 0 ;
TMR2=0;
}
int main(int argc, char** argv) {
 TRISA = 0x0000; //set led
    /* s e t T2CON r e g i s t e r */
T2CONbits.ON = 0 ; // Di s a b l e Timer2
T2CONbits.T32 = 0 ; // us e 16?b i t mode
T2CONbits.TCKPS = 0b111 ; // s e l e c t p r e s c a l e r 256
T2CONbits.TCS = 0 ; // i n t e r n a l p e r i p h e r a l c l o c k
TMR2 = 0 ; // Cl e a r TMR2 r e g i s t e r
PR2 = 39062;
IPC2bits.T2IP = 1 ; // p r i o r i t y
IPC2bits.T2IS = 0 ;
macro_enable_interrupts();
IEC0bits.T2IE = 1 ; // i n t e r r u p t Timer2 e n a b l e
T2CONbits.ON = 1 ;

while(1)
{
    if(TMR2>=PR2)
    {
        
    }
}
return (EXIT_SUCCESS);
}

