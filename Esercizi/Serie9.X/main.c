/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 20 dicembre 2021, 12.51
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "i2c.h"
#include "LCD.h"
#include "Pins.h"
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
#pragma config FCKSM = CSECMD
#define ACK 0
#define NAK 1

int main(int argc, char** argv) {

    LCDinit(10000000);
    Init_Output_Led();
    int i;
    for (i = 0; i < 8; i++) {
    switchOffLed(i);

    }

  
    i2c_master_setup(10000000, 100000);
    
    i2c_master_start();
    i2c_master_send(0x1D << 1 | 0);//address
    i2c_master_send(0x0D);//register
    i2c_master_restart();
    i2c_master_send(0x1D << 1 | 1);//address
    unsigned char receivedData = i2c_master_recv();
    i2c_master_ack(NAK);
    i2c_master_stop();
    if(receivedData=='J')
        putsLCD("0x4A");
    return (EXIT_SUCCESS);
}


