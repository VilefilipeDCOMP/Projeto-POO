from PyQt5 import QtCore, QtGui, QtWidgets
from Tetramino import *
import random

class PlayManager(QtWidgets.QLabel):
    posInicial_x = 451 + int(451/2.5)
    posInicial_y = 70
    # posInicial_x = 451 + int(451/3)
    # posInicial_y = 100

    pecas = [tetriPeca_Barra(posInicial_x, posInicial_y), tetriPeca_Quadrada(posInicial_x, posInicial_y), tetriPeca_L(posInicial_x, posInicial_y) , tetriPeca_LInv(posInicial_x, posInicial_y), tetriPeca_S(posInicial_x, posInicial_y), tetriPeca_SInv(posInicial_x, posInicial_y), tetriPeca_Triangulo(posInicial_x, posInicial_y)]
    pecas_Res = [tetriPeca_Barra(posInicial_x, posInicial_y), tetriPeca_Quadrada(posInicial_x, posInicial_y), tetriPeca_L(posInicial_x, posInicial_y) , tetriPeca_LInv(posInicial_x, posInicial_y), tetriPeca_S(posInicial_x, posInicial_y), tetriPeca_SInv(posInicial_x, posInicial_y), tetriPeca_Triangulo(posInicial_x, posInicial_y)]
    
    PlayerScene_x = 360
    PlayerScene_y = 620
    PlayerScene_posy = 50
    PlayerScene_posx = int(1262/2) - int(PlayerScene_x/2)
    
    blocosFixos = []

    num_res = -1

    def pecaAleatoria(self):
        try:
            for bloco in self.atual.b:
                self.blocosFixos.append((bloco.getX(), bloco.getY()))
            self.atual.changeXY(451 + int(451/3), 100, self.atual.rot, self.atual.b)
        except:
            pass

        self.atual = self.pecas[self.num_res]
        self.atual.changeXY(self.posInicial_x, self.posInicial_y, 0, self.atual.b)
        self.atual.rot = 0

        self.num_res = random.randint(0, len(self.pecas) - 1)
        self.reserva = self.pecas_Res[self.num_res]

        self.reserva.changeXY((self.PlayerScene_posx+self.PlayerScene_x + 150) + 85, (self.PlayerScene_posy+self.PlayerScene_y)-90, self.reserva.rot, self.reserva.b)

        for bloco in self.atual.b:
            if (bloco.getX(), bloco.getY()) in self.blocosFixos:
                self.gameOver()
                return

    def __init__(self,parms) -> None:
        super().__init__(parms)
        self.setText ("MyLabel")
        self.px = 0.5
        self.py = 0.5
        self.paint = True
        self.num_res = random.randint(0, len(self.pecas) - 1)
        self.pecaAleatoria()
        self.pontos = 0
    
    def paintEvent (self, event):
        super().paintEvent(event)
        qp = QtGui.QPainter(self)
        height = self.height()
        width = self.width()
        
        br = QtGui.QBrush(QtGui.QColor(0,0,0,255))
        qp.setBrush(br)
        qp.drawRect(0,0,width-5,height - 5)
        qp.setPen(QtGui.QColor(255,255,255))  
        qp.drawRect(self.PlayerScene_posx, self.PlayerScene_posy, self.PlayerScene_x, self.PlayerScene_y)
        qp.drawRect(self.PlayerScene_posx+self.PlayerScene_x + 150, self.PlayerScene_posy+self.PlayerScene_y-200, 200, 200)

        br = QtGui.QBrush(QtGui.QColor(255,0,0,255))
        qp.setBrush(br)
        qp.setPen(QtGui.QColor(255,255,255))
        qp.setFont(QtGui.QFont("Arial", 15))
        qp.drawText((self.PlayerScene_posx+self.PlayerScene_x + 150) + 78, (self.PlayerScene_posy+self.PlayerScene_y-200)+30, "NEXT")

        qp.setPen(QtGui.QColor(255,255,255))
        qp.setFont(QtGui.QFont("Arial", 15))
        qp.drawText((self.PlayerScene_posx+self.PlayerScene_x + 150) + 60, self.PlayerScene_posy + 25, f"Pontos: {self.pontos}")
        
        # Pintar bloco fixo
        for (bloco_x, bloco_y) in self.blocosFixos:
            qp.drawRect(bloco_x, bloco_y, 30, 30)

        self.atual.draw(qp)

        qp.setPen(QtCore.Qt.NoPen) 
        qp.setBrush(QtGui.QBrush(QtGui.QColor(0,0,0,255))) 
        qp.drawRect(self.PlayerScene_posx, 1, self.PlayerScene_x, self.PlayerScene_posy - 1)
        qp.setPen(QtGui.QColor(255,255,255))  

        for bloco in self.reserva.b:
            qp.setBrush(QtGui.QBrush(self.reserva.cor))
            qp.drawRect(bloco.getX(), bloco.getY(), box.size, box.size)


    def possivelY(self, soma):
        aux = 0
        for bloco in self.atual.b:
            if (bloco.getY() + soma != self.PlayerScene_y+self.PlayerScene_posy):
                try:
                    self.blocosFixos.index((bloco.x, bloco.y + soma))
                    return False
                except ValueError:
                    aux += 1
                    if (aux == 4):
                        return True
            else:
                return False

    def moveBlock (self):
        if (self.possivelY(+30) == True):
            self.atual.changeXY(self.atual.b[1].x, self.atual.b[1].y + 30, self.atual.rot, self.atual.b)
            self.repaint()
        else:
            self.pecaAleatoria()
            self.checarLinhas()

    
    def controlarBlock (self, opcao):
        if (opcao == "D") and (self.atual.verificarXPos(self.atual.b, self.blocosFixos, self.PlayerScene_x, self.PlayerScene_posx) == True):
            self.atual.changeXY(self.atual.b[1].x + 30, self.atual.b[1].y, self.atual.rot, self.atual.b)
        elif (opcao == "E") and (self.atual.verificarXNeg(self.atual.b, self.blocosFixos, self.PlayerScene_x, self.PlayerScene_posx) == True):
            self.atual.changeXY(self.atual.b[1].x - 30, self.atual.b[1].y, self.atual.rot, self.atual.b)
        elif (opcao == "S") and (self.possivelY(+30) == True):
            self.atual.changeXY(self.atual.b[1].x, self.atual.b[1].y + 30, self.atual.rot, self.atual.b)
        elif (opcao == "W"):
            if (self.atual.rotacionarTest(self.blocosFixos, self.PlayerScene_x, self.PlayerScene_posx) == True):
                self.atual.rotacionar()
        self.repaint()
    
    def resetGame(self):
        self.blocosFixos.clear()
        self.pontos = 0
        self.atual = None
        self.pecaAleatoria()
        self.repaint()

    def gameOver(self):
        # trava a movimentação
        self.paint = False  

        # mostrar mensagem
        msg = QtWidgets.QMessageBox()
        msg.setWindowTitle("Game Over")
        msg.setText(f"Fim de jogo!\nPontuação final: {self.pontos}")
        msg.setIcon(QtWidgets.QMessageBox.Critical)
        ok_btn = msg.addButton("Ok", QtWidgets.QMessageBox.AcceptRole)
        reset_btn = msg.addButton("Resetar", QtWidgets.QMessageBox.RejectRole)

        msg.exec_()

        if msg.clickedButton() == ok_btn:
            QtWidgets.qApp.quit()
        elif msg.clickedButton() == reset_btn:
            self.resetGame()


    def checarLinhas(self):
        linhas_removidas = 0
        largura_blocos = self.PlayerScene_x // 30  # 12 colunas

        # agrupar blocos por linha y
        linhas = {}
        for (x, y) in self.blocosFixos:
            linhas.setdefault(y, []).append((x, y))

        # identificar linhas cheias
        linhas_cheias = [y for y, blocos in linhas.items() if len(blocos) == largura_blocos]

        if linhas_cheias:
            linhas_removidas = len(linhas_cheias)

            # remove blocos dessas linhas
            self.blocosFixos = [(x,y) for (x,y) in self.blocosFixos if y not in linhas_cheias]

            for y_removida in sorted(linhas_cheias):
                novos_blocos = []
                for (x,y) in self.blocosFixos:
                    if y < y_removida:  # blocos acima descem
                        novos_blocos.append((x, y+30))
                    else:
                        novos_blocos.append((x, y))
                self.blocosFixos = novos_blocos

            if linhas_removidas == 1:
                self.pontos += 100
            elif linhas_removidas == 2:
                self.pontos += 300
            elif linhas_removidas == 3:
                self.pontos += 500
            elif linhas_removidas >= 4:
                self.pontos += 800
    





