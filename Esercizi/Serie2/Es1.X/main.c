/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 4 ottobre 2021, 12.52
 */

#include <stdio.h>
#include <stdlib.h>

#include <p32xxxx.h>
#include <string.h>
#include "Pins.h"

#include "UART.h"

int main(int argc, char** argv) {


    //Esercizio 1
    /*
    UART_ConfigurePins();
    UART_ConfigureUart(9600);
    Init_Output_Led();
    Init_Input_Switch();
    int led[7] = {0};
   
    while (1) {

        if (led[0] != getSwitchValue(0))
            if (getSwitchValue(0) == 1) {
                putU4_string("Led0 ON\n\r");
                switchOnLed(0);
                led[0] = 1;
            } else {
                led[0] = 0;
                putU4_string("Led0 OFF\n\r");
                switchOffLed(0);
            }
        if (led[1] != getSwitchValue(1))
            if (getSwitchValue(1) == 1) {
                putU4_string("Led1 ON\n\r");
                switchOnLed(1);
                led[1] = 1;
            } else {
                putU4_string("Led1 OFF\n\r");
                switchOffLed(1);
                led[1] = 0;
            }
        if (led[2] != getSwitchValue(2))
            if (getSwitchValue(2) == 1) {
                putU4_string("Led2 ON\n\r");
                switchOnLed(2);
                led[2] = 1;
            } else {
                putU4_string("Led2 OFF\n\r");
                switchOffLed(2);
                led[2] = 0;
            }
        if (led[3] != getSwitchValue(3))
            if (getSwitchValue(3) == 1) {
                putU4_string("Led3 ON\n\r");
                switchOnLed(3);
                led[3] = 1;
            } else {
                putU4_string("Led3 OFF\n\r");
                switchOffLed(3);
                led[3] = 0;
            }
        if (led[4] != getSwitchValue(4))
            if (getSwitchValue(4) == 1) {
                putU4_string("Led4 ON\n\r");
                switchOnLed(4);
                led[4] = 1;
            } else {
                putU4_string("Led4 OFF\n\r");
                switchOffLed(4);
                led[4] = 0;
            }
        if (led[5] != getSwitchValue(5))
            if (getSwitchValue(5) == 1) {
                putU4_string("Led5 ON\n\r");
                switchOnLed(5);
                led[5] = 1;
            } else {
                putU4_string("Led5 OFF\n\r");
                switchOffLed(5);
                led[5] = 0;
            }
        if (led[6] != getSwitchValue(6))
            if (getSwitchValue(6) == 1) {
                putU4_string("Led6 ON\n\r");
                switchOnLed(6);
                led[6] = 1;
            } else {
                putU4_string("Led6 OFF\n\r");
                switchOffLed(6);
                led[6] = 0;
            }
        if (led[7] != getSwitchValue(7))
            if (getSwitchValue(7) == 1) {
                putU4_string("Led7 ON\n\r");
                switchOnLed(7);
                led[7] = 1;
            } else {
                putU4_string("Led7 OFF\n\r");
                switchOffLed(7);
                led[7] = 0;
            }
        
    }
     */
    //Esercizio 2
    UART_ConfigurePins();
    UART_ConfigureUart(9600);
    Init_Output_Led();

    char strg[80];
    char tmpON[80] = "LED";
    char tmpOFF[80] = "LED"; 

    while (1) {

        if (getU4_string(strg)) {
            
            char output[80]="";
            int ledNumber = strg[3] - '0';

            tmpON[3] = strg[3];
            tmpOFF[3] = strg[3];
            strcat(tmpON, " ON");
            strcat(tmpOFF, " OFF");
            strcpy(output, "OK, LED");
            output[7]=strg[3];
            output[8]=' ';
            if (strcmp(strg, tmpON) == 0) {

                strcat(output, " acceso\n\r");
                putU4_string(output);
                switchOnLed(ledNumber);
            } else if (strcmp(strg, tmpOFF) == 0) {

                strcat(output, " spento\n\r");
                putU4_string(output);
                switchOffLed(ledNumber);
            }

        }




    }

    return (EXIT_SUCCESS);
}

