cmake_minimum_required(VERSION 3.21)
project(Execercises)

set(CMAKE_CXX_STANDARD 14)

add_executable(Exercises Todo/main.cpp Todo/main.h Todo/TodoElement.cpp Todo/TodoElement.h Todo/TodoContainer.cpp Todo/TodoContainer.h)
