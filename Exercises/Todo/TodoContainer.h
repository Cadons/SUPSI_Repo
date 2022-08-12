//
// Created by cadons on 01/04/22.
//

#ifndef EXECERCISES_TODOCONTAINER_H
#define EXECERCISES_TODOCONTAINER_H
#include <vector>
#include "TodoElement.h"

class TodoContainer {
private:
    std::vector<TodoElement*> container;
public:
    TodoElement& operator[](unsigned int index) const;
    void clear();
    void write(std::ostream& out) const;
    void read(const std::istream& in);
    TodoElement& push_back(const TodoElement* e);
    void erase(unsigned int index);
    int size() const;
};


#endif //EXECERCISES_TODOCONTAINER_H
