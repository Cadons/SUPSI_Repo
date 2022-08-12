/* 
 * File:   Uart.h
 * Author: LSIB
 *
 * Created on 09. ottobre 2017, 14:00
 */


#ifndef __UART__
#define __UART__
void InitU4(int baud, unsigned int PbusClock, unsigned parity, unsigned int stopBit, unsigned int flowControl, int priority, int sub_priority);/**Configure UART4 */
void putU4_string(char szData[]);/**Write on UART4*/
unsigned int getU4_string(char strg[]);/*Read on UART4**/
void UART4_EnableTX();/**Enable transmition*/
void U4setInterruptPriority(int priority, int sub_priority);/**set new UART4 interrupt's priority*/
#endif