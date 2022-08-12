cmake_minimum_required(VERSION 3.21)
project(LessonsTests)

set(CMAKE_CXX_STANDARD 14)

add_executable(LessonsTests main.cpp standardLibrary.cpp InheritancePolymorphism.cpp Employee.cpp Employee.h)
