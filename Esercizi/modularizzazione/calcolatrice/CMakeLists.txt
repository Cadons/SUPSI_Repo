cmake_minimum_required(VERSION 3.16)
project(calcolatrice C)

set(CMAKE_C_STANDARD 11)

add_executable(calcolatrice main.c stack.c stack.h)
