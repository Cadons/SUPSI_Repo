#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{


    connect(ui->pushButton,SIGNAL(clicked(bool)),this, SLOT(update()));
    connect(ui->checkBox,SIGNAL(clicked(bool)),this, SLOT(status()));
    connect(this,SIGNAL(cambiato(bool)),this, SLOT(update2(bool)));
       ui->setupUi(this);
}
void MainWindow::update(){
    int y=rand()%100;
    QString x;

    QString out{"Number "+ x.number(y)};
    out.append(x);
    ui->label->setText(out);
  //  setNum(y);
    ui->label2->setText(out.number(property("num").value<int>()));

}
void MainWindow::update2(bool x){

    ui->label2->setText("ddd");


}
void MainWindow::status(){
  m_opt=ui->checkBox->isChecked();
 //   emit ui->pushButton->clicked(true);
 // emit cambiato(2);
}


MainWindow::~MainWindow()
{
    delete ui;
}

