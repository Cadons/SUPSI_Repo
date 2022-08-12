//
// Created by cadons on 01/04/22.
//

#include "TodoContainer.h"
#include <iostream>

void TodoContainer::clear() {
    TodoContainer::container.clear();
}

void TodoContainer::write(std::ostream &out) const {
    for(auto e : container)
    {
        // out.write(e.getTitle()+","+e.getDescription()+","+ std::to_string(e.getPriority())+"\n",);
    }

}

void TodoContainer::read(const std::istream &in) {
    while (in.eof())
    {

    }
}

TodoElement& TodoContainer::push_back(const TodoElement* e) {
    container.push_back(e);
    return const_cast<TodoElement &>(*e);
}

void TodoContainer::erase(unsigned int index) {
    TodoContainer::container.erase(TodoContainer::container.begin()+index);
}

int TodoContainer::size() const {
    return TodoContainer::container.size();
}

TodoElement &TodoContainer::operator[](unsigned int index) const {
if(index>size()||container.empty())
    throw std::out_of_range("Index not valid");
    return *container[index];
}
