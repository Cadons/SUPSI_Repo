cmake_minimum_required(VERSION 3.16)
project(librerie C)

set(CMAKE_C_STANDARD 11)

add_executable(librerie main.c stack.c stack.h)
