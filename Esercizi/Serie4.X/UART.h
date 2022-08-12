/* 
 * File:   Uart.h
 * Author: LSIB
 *
 * Created on 09. ottobre 2017, 14:00
 */



void UART4_ConfigurePins(void);
void UART4_ConfigureUart(int baud, unsigned int PbusClock, unsigned parity, unsigned int stopBit, unsigned int flowControl);
int putU4(int c);
char getU4(void);
void putU4_string(char szData[]);
unsigned char getU4_string(char strg[]);
void UART4_EnableTX();
void U4setInterruptPriority(int priority, int sub_priority);