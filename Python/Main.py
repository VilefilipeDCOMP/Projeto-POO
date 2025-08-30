import sys
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import QTimer

from MainWindow import Ui_MainWindow

class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):
    def __init__(self, *args, obj=None, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle ("Tetris - Python")
        self.setFixedSize(1280, 800)
        self.timer=QTimer()
        self.timer.timeout.connect(self.label.moveBlock)
        self.timer.start(360)
        self.setFocusPolicy(QtCore.Qt.StrongFocus)

    
    def keyPressEvent(self, event):
        match (event.key()):
            case QtCore.Qt.Key_D | QtCore.Qt.Key_Right:
                self.label.controlarBlock("D")
            case QtCore.Qt.Key_A | QtCore.Qt.Key_Left:
                self.label.controlarBlock("E")
            case QtCore.Qt.Key_S | QtCore.Qt.Key_Down:
                self.label.controlarBlock("S")
            case QtCore.Qt.Key_W | QtCore.Qt.Key_Up:
                self.label.controlarBlock("W")

    def slotAtirar (self):
        self.label.shoot()

    def resetGame(self):
        self.label.blocosFixos.clear()
        self.label.pontos = 0
        self.label.atual = None
        self.label.pecaAleatoria()
        self.label.repaint()

        
app = QtWidgets.QApplication(sys.argv)

window = MainWindow()
window.show()
app.exec()

