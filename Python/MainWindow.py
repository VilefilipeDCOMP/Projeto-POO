# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'tiro_alvo.ui'
#
# Created by: PyQt5 UI code generator 5.9.2
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    HEIGHT = 1280
    WIDTH = 800
    

    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(self.HEIGHT, self.WIDTH)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.verticalLayout = QtWidgets.QVBoxLayout(self.centralwidget)
        self.verticalLayout.setObjectName("verticalLayout")
        self.label = PlayManager(self.centralwidget)
        self.label.setMinimumSize(QtCore.QSize(350, 250))
        self.label.setText("")
        self.label.setObjectName("label")
        self.verticalLayout.addWidget(self.label)
        spacerItem = QtWidgets.QSpacerItem(18, 13, QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Maximum)
        self.verticalLayout.addItem(spacerItem)
        MainWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MainWindow)
        # self.pushButton.clicked.connect(MainWindow.slotAtirar)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

        self.resetButton = QtWidgets.QPushButton(self.centralwidget)
        self.resetButton.setText("Reset")
        self.resetButton.setFixedSize(80, 30)
        
        self.resetButton.move((self.label.PlayerScene_x+self.label.PlayerScene_posx + 150) + 68, self.label.PlayerScene_posy + 25 + 40) 
        # self.resetButton.move(self.HEIGHT - 120, 20) 
        self.resetButton.clicked.connect(MainWindow.resetGame)

        self.verticalLayout.addWidget(self.label)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))

from PlayManager import PlayManager
