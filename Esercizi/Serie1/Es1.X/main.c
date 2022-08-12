/* 
 * File:   main.c
 * Author: Cadons
 *
 * Created on 27 settembre 2021, 13.02
 */

/* Includes */
# include <stdio.h>
# include <stdlib.h>
# include <p32xxxx.h>
void main ()
{
int counter = 100;
TRISD = 0x0000 ;
    while (1) // forever
    {
        while ( counter )
        {
        counter--;
        }
        /* toggle RD0 */
            LATDbits.LATD0 = ~ LATDbits.LATD0 ;
    }
}
