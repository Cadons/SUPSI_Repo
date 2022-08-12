#ifndef __BTN__
#define __BTN__
void initBTN();
char getButtonPressedName();/**
 * it returns the last letter of the pressed button. example BTNC is pressed function will return C
 */
int getButtonValue(char BtnName);/**
                                  BtnName must be equals to 'U' or 'R' or 'D' or 'L' or 'C'. They rappresent the final char of BTNX 
                                  */
#endif