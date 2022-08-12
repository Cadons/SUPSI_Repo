#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT
    Q_PROPERTY(int num READ getNum WRITE setNum)
    Q_PROPERTY(bool opt MEMBER m_opt NOTIFY cambiato)
public:
    MainWindow(QWidget *parent = nullptr);
    void setNum(int&){ num++;}
    int getNum() const{
        return num;
    }
    ~MainWindow();
signals:
    void cambiato(bool z);
private:
    Ui::MainWindow *ui;
    int num;
    bool m_opt;


public slots:
    void update();
    void update2(bool);

    void status();

};
#endif // MAINWINDOW_H
