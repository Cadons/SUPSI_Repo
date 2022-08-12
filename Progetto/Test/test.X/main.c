/* 
 * File:   main.h
 * Author: Cadons
 *
 * Created on 28 dicembre 2021, 16.19
 */

#include <stdio.h>
#include <stdlib.h>
#include <p32xxxx.h>
#include "RGB.h"
#include "BTN.h"
#include "Timer.h"
// Device Config Bits in  DEVCFG1:	
#pragma config FNOSC =FRCPLL
#pragma config FSOSCEN =OFF
#pragma config POSCMOD =XT
#pragma config OSCIOFNC =ON
#pragma config FPBDIV =	DIV_2

// Device Config Bits in  DEVCFG2:	
#pragma config FPLLIDIV =DIV_2
#pragma config FPLLMUL =MUL_20
#pragma config FPLLODIV =DIV_2
int cnt = 0;
#pragma interrupt Timer2Handler ipl1AUTO vector 8

void Timer2Handler(void) {

    cnt++;
    resetTimer2();
    IFS0bits.T2IF = 0;

}

int main(int argc, char** argv) {
    int inserito = 0, allarm = 0;
    ;
    initRGB();
    initBTN();
    init_Timer2(0, 1000, 20000000, 256);
    T2setInterruptPriority(1, 0);
    setBlue();

    while (1) {
        if (inserito && allarm) {
            startTimer2();

            if (cnt % 2 == 0)
                setRed();
            else
                setBlue();
        }
        if (getButtonPressedName() == 'C')
            allarm = 1;
        if (getButtonPressedName() == 'U')
            inserito = 1;
        if (getButtonPressedName() == 'D') {
            inserito = 0;
            allarm = 0;
            setBlue();
        }
    }


    return (EXIT_SUCCESS);
}

