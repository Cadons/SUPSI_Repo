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
//Variabili globali
char inputString[80];
int j = 0;
int led = 0;
int endOfString = 0;
int ledsAreWorking = 0;


//Interrupt ISR

#pragma interrupt Uart4Handler ipl6AUTO vector 39

void Uart4Handler() {
    unsigned char c;

    //c = (unsigned char)U4RXREG;
    //  while (!U4STAbits.URXDA); // wait for a new char to arrive
    //RTS=1;
    c = U4RXREG; // read char from receive buffer
    if (j > 0 && c == '\n') {
        inputString[j - 1] = 0; //rimuovi l'ultimo carattere(di stop)

        j = 0;
        endOfString = 1;
    } else {
        inputString[j++] = c; //inserisci il carattere nella stringa 
    }

    IFS2bits.U4RXIF = 0;
}
#pragma interrupt Timer2Handler ipl1AUTO vector 8

void Timer2Handler(void) {

    ledsAreWorking = 1;
    IFS0bits.T2IF = 0;

}

//Utility Functions

void printHelp() {
    putU4_string("Insert command: ");
}

void printError(char str[]) {
    putU4_string(str);

}

void main() {
    /**
     * Il programma riceve comandi da UART:
     * ledson : accende i led in modo sequenziale dal 0 - 7 con un delay di 0.5 secondi, il tempo è gestito dal timer2
     * ledsoff: spegne i led in modo sequenziale dal 7 - 0 con delay di 0.5 secondi gestiti dal timer2
     */
    int ledsMode = 1;

    //Configurazione
    Init_Output_Led(); //Inizializza i led come output
    resetLeds(); //spegni tutti i led

    UART4_ConfigurePins(); //configura i pin per uart4
    UART4_ConfigureUart(9600, 20000000, 0, 0, 0); //configura uart4 con baud 9600, frequanza 20mhz(frequenza processore impostata dalle pragrama), bit parità=0,usa un bit di stop STSEL=0, non esegure controllo del flusso RTSMD=0
    U4setInterruptPriority(6, 3); //Imposta priorità interrupt uart
    //Per usare il timer singolo bisogna metterlo in modalità 16bit poichè nel case 32bit bisognerebbe settare l'interrupt sull'ultimo timer es: timer2:timer3 il timer3 getisce gli interrupt
    init_Timer2(0, 500, 20000000, 256); //Configura il timer: usa modalità 32 bit, tempo 0.5 s=500ms, imposta frequenza di 20Mhz
    T2setInterruptPriority(1, 0); //imposta priorità timer2

    //Avvio
    char options[2][80] = {"ledson", "ledsoff"}; //stringhe dei comandi ammesse

    resetTimer2();
    printHelp();
    while (1) {//eseugui all'infinito


        if (endOfString) {
            //Comando ricevuto

            if (strcmp(inputString, options[0]) == 0) {//controlla se la stringa è uguale a ledson
                resetTimer2();//reset del timer
                putU4_string("Start Timer(ledson)\n\r");//stampa preliminare
                ledsMode = 1;//imposta modalità led
                led = 0;//imposta il primo led da accendere
                startTimer2();//avvia il timer
            } else if (strcmp(inputString, options[1]) == 0) {//verifica ledsoff
                ledsMode = 0;
                resetTimer2();
                putU4_string("Start Timer(ledsoff)\n\r");
                led = 7;
                startTimer2();
            } else {
                //Comando sconosciuto => errore
                printError("Invalid command!\n[ledson]:turns on leds;\n[ledsoff]:turns off leds\n\r");
                printHelp();
            }
            endOfString = 0;
        }
        
        if (ledsAreWorking) {//Comando valido operazioni avvengono ogni PR del timer che imposta il flag di abilitazione
            if (ledsMode) {
                //accensione led
                if (led < 8) {
                    switchOnLed(led);
                    putU4_string("Leds turning on\n\r"); //stampa su terminale
                    led++;
                } else
                    T2CONbits.ON = 0;
            } else {
                if (led >= 0) {
                    //spegnimento led
                    switchOffLed(led);
                    putU4_string("Leds turning off\n\r"); //stampa su terminale
                    led--;
                } else
                    T2CONbits.ON = 0;
                resetTimer2();

            }
            //reset flag
            ledsAreWorking = 0;
            //ripristina terminale
            if (T2CONbits.ON == 0)
                printHelp();
        }
    }
}

