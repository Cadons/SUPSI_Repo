#include <p32xxxx.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
void i2c_master_setup(int Pblck, int Fsck) {
    I2C1BRG =0x02F;//(((1 / (2 * Fsck))-(104*pow(10,-9))) * Pblck)-2; // I2CBRG = [ 1 / ( 2 * F sc k ) ? PGD] * P blck ? 2
    // Fsckis t h e freq ( 1 0 0 kHz h e r e ) , PGD = 104 n s
    I2C1CONbits.ON = 1; // turn on the I2C1 module
}

void i2c_master_start(void) {
    I2C1CONbits.SEN = 1; // se n d t h e s t a r t b i t
    while (I2C1CONbits.SEN) {
        ;
    } // w a i t f o r t h e s t a r t b i t t o be s e n t
}

void i2c_master_restart(void) {
    I2C1CONbits.RSEN = 1; // se n d a r e s t a r t
    while (I2C1CONbits.RSEN) {
        ;
    } // w a i t f o r t h e r e s t a r t t o c l e a r
}

void i2c_master_send(unsigned char byte) { // se n d a b y t e t o s l a v e
    I2C1TRN = byte; // i f an a d d r e s s , b i t 0 = 0 f o r w r i t e , 1 f o r r e a d
    while (I2C1STATbits.TRSTAT) {
        
    } // wait for the transmission to finish
     if (I2C1STATbits.ACKSTAT) // optional , useful for debugging
     { // if this is high , slave has not acknowledged
       //  putSerial("failed to receive ACK\r \n");
         printf("no ack");
     }
}

unsigned char i2c_master_recv(void) { // r e c e i v e a b y t e f rom t h e s l a v e
    I2C1CONbits.RCEN = 1; // s t a r t r e c e i v i n g d a t a
    while (!I2C1STATbits.RBF) {;} // w a i t t o r e c e i v e t h e d a t a
    return I2C1RCV; // r e a d and r e t u r n t h e d a t a
}

void i2c_master_ack(int val) { // s e n d s ACK = 0 ( s l a v e s h o u l d se n d a n o t h e r b y t e )
    // o r NACK = 1 ( no more b y t e s r e q u e s t e d f rom s l a v e )
    I2C1CONbits.ACKDT = val; // s t o r e ACK/NACK i n ACKDT
    I2C1CONbits.ACKEN = 1; // se n d ACKDT
    while (I2C1CONbits.ACKEN) {
        ;
    } // w a i t f o r ACK/NACK t o be s e n t
}

void i2c_master_stop(void) { // se n d a STOP:
    I2C1CONbits .PEN = 1; // comm i s c om pl e t e and m a s t e r r e l i n q u i s h e s
    while (I2C1CONbits.PEN) {
        ;
    } // w a i t f o r STOP t o c om pl e t e
}
