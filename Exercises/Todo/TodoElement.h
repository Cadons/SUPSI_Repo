//
// Created by cadons on 25/03/22.
//

#ifndef EXECERCISES_TODOELEMENT_H
#define EXECERCISES_TODOELEMENT_H
#include <string>
enum Priority{
    HIGH, MEDIUM,LOW,DONE
};
class TodoElement {
public:

    const std::string &getTitle() const;
    const std::string &getDescription() const;
    Priority getPriority() const;

    void setTitle(const std::string &title);
    void setDescription(const std::string &description);
    void setPriority(Priority priority);

    TodoElement();
    TodoElement(const std::string &title, const std::string &description, Priority priority);

private:
    std::string title;
    std::string description;
    Priority priority;
};


#endif //EXECERCISES_TODOELEMENT_H
