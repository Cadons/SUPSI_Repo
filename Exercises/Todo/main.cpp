//
// Created by cadons on 25/03/22.
//

#include "main.h"
#include <iostream>
#include <string>
#include <limits>
#include "TodoElement.h"
#include "TodoContainer.h"

bool checkInput() {
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<int>::max(), '\n');

    if (std::cin.fail()) {
        std::cerr << "Input error!" << std::endl;
        return false;
    } else {
        return true;
    }

}

void menu() {
    std::cout << "[A] Add, [R] Remove, [L] List, [E] Edit, [Q] Quit" << std::endl;
}

void add(TodoElement &value) {
    std::string title, description;
    Priority priority;
    std::cout << "Insert data" << std::endl;
    std::cout << "Title:" << std::endl;
    std::getline(std::cin, title);
    std::cout << "Description:" << std::endl;
    std::getline(std::cin, description);
    int prty;
    do {
        prty = 0;
        std::cout << "Priority:[1 LOW, 2 MEDIUM, 3 HIGH, 4 DONE]" << std::endl;
        std::cin >> prty;
    } while (!checkInput());
    switch (prty) {
        case 1:
            priority = LOW;
            break;
        case 2:
            priority = MEDIUM;
            break;
        case 3:
            priority = HIGH;
            break;
        case 4:
            priority = DONE;
            break;
        default:
            priority = LOW;
    }
    value.setTitle(title);
    value.setDescription(description);
    value.setPriority(priority);
}

bool edit(TodoElement &e) {
    add(e);
    return true;
}

bool remove() {
    return true;
}

int main() {
    char opt;
    TodoContainer container{};

    do {
        menu();
        std::string tmp;
        std::getline(std::cin, tmp);
        opt = tmp[0];
        opt = tolower(opt);
        switch (opt) {
            case 'a': {
                TodoElement tmp{};
                add(tmp);
                std::cout << tmp.getDescription() << std::endl;
                container.push_back(&tmp);
                break;
            }
            case 'r':
                remove();
                break;
            case 'l':
                container.read(std::cout);
                break;
            case 'e':
                int tmp;
                do {
                    std::cout << "Select which element you want modify:(0 to cancel):";
                    std::cin >>tmp;
                } while (!checkInput());
                edit();
                break;
            case 'q':
                std::cout << "Program terminated" << std::endl;
                break;

            default:
                std::cerr << "Invalid command" << std::endl;
                break;
        }
    } while (opt != 'q');

}