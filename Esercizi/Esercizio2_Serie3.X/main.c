/* 
 * File:   newmain.c
 * Author: Cadons
 *
 * Created on 16 ottobre 2021, 17.46
 */

#include <stdio.h>
#include <stdlib.h>
#include "Pins.h"
#include "Timer.h"
#include "UART.h"
#include <string.h>
#include <p32xxxx.h>/*
 * 
 */
/* Pragma definitio for clock configuration */
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

void main() {
    /**
     * Il programma riceve comandi da UART:
     * ledson : accende i led in modo sequenziale dal 0 - 7 con un delay di 0.5 secondi, il tempo è gestito dal timer2
     * ledsoff: spegne i led in modo sequenziale dal 7 - 0 con delay di 0.5 secondi gestiti dal timer2
     */
    Init_Output_Led(); //Inizializza i led come output
    resetLeds(); //spegni tutti i led
    UART4_ConfigurePins(); //configura i pin per uart4
    UART4_ConfigureUart(9600, 20000000, 0, 0, 0); //configura uart4 con baud 9600, frequanza 20mhz(frequenza processore impostata dalle pragrama), bit parità=0,usa un bit di stop STSEL=0, non esegure controllo del flusso RTSMD=0
    init_Timer2(1, 500, 20000000); //Configura il timer: usa modalità 32 bit, tempo 0.5 s=500ms, imposta frequenza di 20Mhz
    char options[2][80] = {"ledson", "ledsoff"}; //stringhe dei comandi ammesse
    char inputString[80] = ""; //buffer stringa di input
    while (1) {//eseugui all'infinito


        if (getU4_string(inputString)) {//attendi che nel buffer ci sia una stringa che termini con \r o \n

            if (strcmp(inputString, options[0]) == 0) {//controlla se la stringa è uguale a ledson
                putU4_string("Leds turning on\n\r"); //stampa leds turning on sul terminale seriale

                for (int i = 0; i < 8; i++) {//imposta lo spegniment dal led 0
                    resetTimer2(); //resetta il timer
                    awaitT2(); //attendi che il timer finisca un giro
                    switchOnLed(i); //accendi l'iesimo led
                    putU4_string("Leds turning on\n\r"); //stampa su terminale

                }

            } else if (strcmp(inputString, options[1]) == 0) {//verifica ledsoff
                putU4_string("Leds turning off\n\r"); //stampa sul terminale
                for (int i = 7; i >= 0; i--) {//imposta lo spegniment dal led 7
                    resetTimer2(); //reset timer
                    awaitT2(); //attendi timer
                    switchOffLed(i); //spegni l'iesimo led
                    putU4_string("Leds turning off\n\r"); //stampa sul terminale
                }
            }
        }



    }

}

