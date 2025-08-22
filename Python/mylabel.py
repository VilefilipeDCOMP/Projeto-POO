from PyQt5 import QtCore, QtGui, QtWidgets
from MainWindow import Ui_MainWindow
from Tetramino import *
import random

class Bullet:
    def __init__ (self):
        self.y = 1
        self.x = 0.5

    def inc (self):
        self.y -= 0.01

    def getY(self):  
        return self.y

    def getX(self):
        return self.x

class MyLabel(QtWidgets.QLabel):
    posInicial_x = 451 + int(451/3)
    posInicial_y = 100
    pecas = [tetriPeca_Barra(posInicial_x, posInicial_y), tetriPeca_Quadrada(posInicial_x, posInicial_y), tetriPeca_L(posInicial_x, posInicial_y) , tetriPeca_LInv(posInicial_x, posInicial_y), tetriPeca_S(posInicial_x, posInicial_y), tetriPeca_SInv(posInicial_x, posInicial_y), tetriPeca_Triangulo(posInicial_x, posInicial_y)]
    # atual = tetriPeca(451 + int(451/3), 100)
    # atual = tetriPeca_Quadrada(451 + int(451/3), 100)
    PlayerScene_x = 360
    PlayerScene_y = 620
    PlayerScene_posy = 50
    PlayerScene_posx = int(1262/2) - int(PlayerScene_x/2)
    
    blocosFixos = []

    def pecaAleatoria(self):
        try:
            for bloco in self.atual.b:
                # print(f'{bloco.getX(), bloco.getY()}')
                self.blocosFixos.append((bloco.getX(), bloco.getY()))
                # print(self.blocosFixos)
            self.atual.changeXY(451 + int(451/3), 100)
        except:
            print("deu erro")
            pass
        n = random.randint(0, len(self.pecas) - 1)
        # print(self.pecas[n])
        self.atual = self.pecas[n]

    def __init__(self,parms) -> None:
        super().__init__(parms)
        self.setText ("MyLabel")
        self.px = 0.5
        self.py = 0.5
        self.paint = True
        self.pecaAleatoria()
    
    def paintEvent (self, event):
        super().paintEvent(event)
        qp = QtGui.QPainter(self)
        br = QtGui.QBrush(QtGui.QColor(0,0,0,255))
        height = self.height()
        width = self.width()
        
        # print("PlayerScene_posx: " + str(self.PlayerScene_posx))
        

        #clear
        qp.setBrush(br)
        qp.drawRect(0,0,width-5,height - 5)
        # print(PlayerScene_x)
        qp.setPen(QtGui.QColor(255,255,255))  
        qp.drawRect(self.PlayerScene_posx, self.PlayerScene_posy, self.PlayerScene_x, self.PlayerScene_y)
        qp.drawRect(width - 205 - 50, height - 205 - 50, 200, 200)

        br = QtGui.QBrush(QtGui.QColor(255,0,0,255))
        qp.setBrush(br)
        qp.setPen(QtGui.QColor(255,255,255))
        qp.setFont(QtGui.QFont("Arial", 15))
        qp.drawText(width - 205 - 50 + 80, height - 205 - 50 + 25, "NEXT")
        
        # Pintar bloco fixo
        for (bloco_x, bloco_y) in self.blocosFixos:
            qp.drawRect(bloco_x, bloco_y, 30, 30)

        self.atual.draw(qp)

    def possivelY(self, soma):
        aux = 0
        for bloco in self.atual.b:
            if (bloco.getY() + soma != self.PlayerScene_y+self.PlayerScene_posy):
                try:
                    self.blocosFixos.index((bloco.x, bloco.y + soma))
                    return False
                except ValueError:
                    aux += 1
                    print(aux)
                    if (aux == 4):
                        return True
            else:
                return False
    
        # if (self.atual.ymax.getY() + soma != self.PlayerScene_y+self.PlayerScene_posy):
        #     aux = 0
        #     for bloco in self.atual.b:
        #         try:
        #             self.blocosFixos.index((bloco.x, bloco.y + soma))
        #             return False
        #         except ValueError:
        #             aux += 1
        #             print(aux)
        #             if (aux == 4):
        #                 return True
        #     return True
        # else:
        #     return False
        
    def possivelXPos(self):
        if (self.atual.xmax.getX() + 30 != self.PlayerScene_x+self.PlayerScene_posx) :
            aux = 0
            for bloco in self.atual.b:
                try:
                    self.blocosFixos.index((bloco.x + 30, bloco.y))
                    return False
                except ValueError:
                    aux += 1
                    print(aux)
                    if (aux == 4):
                        return True
            return True
        else:
            return False
        
    def possivelXNeg(self):
        if (self.atual.xmin.getX() != self.PlayerScene_posx) :
            aux = 0
            for bloco in self.atual.b:
                try:
                    self.blocosFixos.index((bloco.x - 30, bloco.y))
                    return False
                except ValueError:
                    aux += 1
                    print(aux)
                    if (aux == 4):
                        return True
            return True
        else:
            return False

    def moveBlock (self):
        if (self.possivelY(+30) == True):
            self.atual.changeXY(self.atual.b[1].x, self.atual.b[1].y + 30)
            self.repaint()
        else:
            self.pecaAleatoria()

    
    def controlarBlock (self, opcao):
        if (opcao == "D") and (self.possivelXPos() == True):
            self.atual.changeXY(self.atual.b[1].x + 30, self.atual.b[1].y)
        elif (opcao == "E") and (self.possivelXNeg() == True):
            self.atual.changeXY(self.atual.b[1].x - 30, self.atual.b[1].y)
        elif (opcao == "S") and (self.possivelY(+30) == True):
            self.atual.changeXY(self.atual.b[1].x, self.atual.b[1].y + 30)
        self.repaint()

    # def controlarBlock (self, opcao):
    #     if (opcao == "D") and (self.atual.b[1].x != 721):
    #         self.atual.changeXY(self.atual.b[1].x + 30, self.atual.b[1].y)
    #     elif (opcao == "E") and (self.atual.b[1].x != 481):
    #         self.atual.changeXY(self.atual.b[1].x - 30, self.atual.b[1].y)
    #     elif (opcao == "S") and (self.atual.b[1].y < 620):
    #         self.atual.changeXY(self.atual.b[1].x, self.atual.b[1].y + 60)
    #     self.repaint()

    # def moveTarget (self):
    #     # self.px += .01
    #     # if self.px >= 1:
    #     #     self.px = 0
    #     # for b in self.bullet:
    #     #     b.inc()
    #     #     if b.getY() <= 0:
    #     #         self.bullet.remove(b)

    #     self.repaint()
    





