import sys
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import QTimer

from MainWindow import Ui_MainWindow

class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):
    def __init__(self, *args, obj=None, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle ("Tetris")
        self.timer=QTimer()
        self.timer.timeout.connect(self.label.moveBlock)
        # self.timer.start(36)
        self.timer.start(360)
    
    def keyPressEvent(self, event): ## Switch case seria uma boa aqui
        if event.key() == QtCore.Qt.Key_D:
            self.label.controlarBlock("D")
        elif event.key() == QtCore.Qt.Key_A:
            self.label.controlarBlock("E")
        elif event.key() == QtCore.Qt.Key_S:
            self.label.controlarBlock("S")
        elif event.key() == QtCore.Qt.Key_W:
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

