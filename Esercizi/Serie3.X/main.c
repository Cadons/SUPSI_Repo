/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 11 ottobre 2021, 12.59
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "Timer.h"
#include "Pins.h"
#include "UART.h"

/*
 * 
 */
int main(int argc, char** argv) {

    Init_Output_Led();
    init_Timer(16,500,20000000);//0.5 secondi
    while(1)
    {
        while(TMR2<=(PR2-1));
        
            Toggle(0);
            resetTimer();
        
    }
    return (EXIT_SUCCESS);
}

