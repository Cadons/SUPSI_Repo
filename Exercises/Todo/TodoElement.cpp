//
// Created by cadons on 25/03/22.
//

#include "TodoElement.h"

void TodoElement::setTitle(const std::string &title) {
    TodoElement::title = title;
}

void TodoElement::setDescription(const std::string &description) {
    TodoElement::description = description;
}

Priority TodoElement::getPriority() const {
    return priority;
}

void TodoElement::setPriority(Priority priority) {
    TodoElement::priority = priority;
}

TodoElement::TodoElement(const std::string &title, const std::string &description, Priority priority) : title(title),
                                                                                                   description(
                                                                                                           description),
                                                                                                   priority(priority) {

}

TodoElement::TodoElement() {}

const std::string &TodoElement::getTitle() const {
    return title;
}

const std::string &TodoElement::getDescription() const {
    return description;
}
