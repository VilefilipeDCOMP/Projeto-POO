from PyQt5 import QtCore, QtGui, QtWidgets

class box:
    size = 30
    x = 0
    y = 0

    def __init__ (self, QColor): # QtGui.QColor(0,0,0,255)
        self.br = QtGui.QBrush(QColor)
        self.y = 50
        self.x = 50

    def changeXY(self, x, y):
        self.x = x
        self.y = y

    def getY(self):  
        return self.y

    def getX(self):
        return self.x
    
class Peca:
    cor = 0
    rot = 0

    def __init__(self, x, y):
        self.b = [box(self.cor), box(self.cor), box(self.cor), box(self.cor)]
        self.bTemp = [box(self.cor), box(self.cor), box(self.cor), box(self.cor)]
        self.changeXY(x,y, self.rot, self.b)

    def draw(self, qp):
        # for block in self.bTemp:
        #     qp.setBrush(QtGui.QBrush(QtGui.QColor(0,0,0,0)))
        #     qp.drawRect(block.getX(), block.getY(), box.size, box.size)
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

    def rotacionar(self):
        if (self.rot == 3):
            self.rot = 0 
        else:
            self.rot += 1

    def verificarXPos(self, setBlocos, blocosFixos, PlayerScene_x, PlayerScene_posx):
        aux = 0
        for bloco in setBlocos:
            if (bloco.getX() + 30 != PlayerScene_x+PlayerScene_posx):
                try:
                    blocosFixos.index((bloco.x + 30, bloco.y))
                    return False
                except ValueError:
                    aux += 1
                    if (aux == 4):
                        return True
            else:
                return False

    def verificarXNeg(self, setBlocos, blocosFixos, PlayerScene_x, PlayerScene_posx):
        aux = 0
        for bloco in setBlocos:
            if (bloco.getX() != PlayerScene_posx):
                try:
                    blocosFixos.index((bloco.x - 30, bloco.y))
                    return False
                except ValueError:
                    aux += 1
                    if (aux == 4):
                        return True
            else:
                return False
    
    def rotacionarTest(self, blocosFixos, PlayerScene_x, PlayerScene_posx):
        test_rot = self.rot
        if (test_rot == 3):
            test_rot = 0 
        else:
            test_rot += 1

        x = self.b[1].getX()
        y = self.b[1].getY()

        self.changeXY(x, y, test_rot, self.bTemp)

        print(self.verificarXNeg(self.bTemp, blocosFixos, PlayerScene_x, PlayerScene_posx), self.verificarXPos(self.bTemp, blocosFixos, PlayerScene_x, PlayerScene_posx))

        if (self.verificarXNeg(self.bTemp, blocosFixos, PlayerScene_x, PlayerScene_posx) == True and self.verificarXPos(self.bTemp, blocosFixos, PlayerScene_x, PlayerScene_posx) == True):
            return True
        else:
            return False


class tetriPeca_Barra (Peca):
    cor = QtGui.QColor(15,155,215,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        if (rotAtual == 1) or (rotAtual == 3):
            setBlocos[0].changeXY(x, y - box.size)
            setBlocos[1].changeXY(x, y)
            setBlocos[2].changeXY(x, y + box.size)
            setBlocos[3].changeXY(x, y + (box.size) * 2)
        else:
            setBlocos[0].changeXY(x - box.size, y)
            setBlocos[1].changeXY(x, y)
            setBlocos[2].changeXY(x + box.size, y)
            setBlocos[3].changeXY(x + (box.size) * 2, y)


class tetriPeca_Quadrada (Peca): 
    cor = QtGui.QColor(227,159,2,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        setBlocos[0].changeXY(x - box.size, y)
        setBlocos[1].changeXY(x, y)
        setBlocos[2].changeXY(x, y + box.size)
        setBlocos[3].changeXY(x - box.size, y + box.size)
    
    def rotacionar(self):
        pass


class tetriPeca_L (Peca): 
    cor = QtGui.QColor(33,65,198,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        match rotAtual:
            case 0:
                setBlocos[0].changeXY(x - box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y - box.size)
                setBlocos[3].changeXY(x, y - (box.size*2))
            case 1:
                setBlocos[0].changeXY(x, y  - box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x + box.size, y)
                setBlocos[3].changeXY(x + (box.size*2), y)
            case 2:
                setBlocos[0].changeXY(x + box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y + box.size)
                setBlocos[3].changeXY(x, y + (box.size*2))
            case 3:
                setBlocos[0].changeXY(x, y  + box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x - box.size, y)
                setBlocos[3].changeXY(x - (box.size*2), y)

class tetriPeca_LInv (Peca): 
    cor = QtGui.QColor(227,91,2,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        match rotAtual:
            case 0:
                setBlocos[0].changeXY(x + box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y - box.size)
                setBlocos[3].changeXY(x, y - (box.size*2))
            case 1:
                setBlocos[0].changeXY(x, y  + box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x + box.size, y)
                setBlocos[3].changeXY(x + (box.size*2), y)
            case 2:
                setBlocos[0].changeXY(x - box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y + box.size)
                setBlocos[3].changeXY(x, y + (box.size*2))
            case 3:
                setBlocos[0].changeXY(x, y  - box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x - box.size, y)
                setBlocos[3].changeXY(x - (box.size*2), y)

class tetriPeca_S (Peca):
    cor = QtGui.QColor(89,177,1,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        match rotAtual:
            case 0 | 2:
                setBlocos[0].changeXY(x - box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y - box.size)
                setBlocos[3].changeXY(x + box.size, y - box.size)
            case 1 | 3:
                setBlocos[0].changeXY(x, y  - box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x + box.size, y)
                setBlocos[3].changeXY(x + box.size, y + box.size)

class tetriPeca_SInv (Peca): 
    cor = QtGui.QColor(215,15,55,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        match rotAtual:
            case 0 | 2:
                setBlocos[0].changeXY(x + box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y - box.size)
                setBlocos[3].changeXY(x - box.size, y - box.size)
            case 1 | 3:
                setBlocos[0].changeXY(x, y  - box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x - box.size, y)
                setBlocos[3].changeXY(x - box.size, y + box.size)

class tetriPeca_Triangulo (Peca): 
    cor = QtGui.QColor(175,41,138,255)

    def changeXY(self, x, y, rotAtual, setBlocos):
        match rotAtual:
            case 0:
                setBlocos[0].changeXY(x - box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x + box.size, y)
                setBlocos[3].changeXY(x, y + box.size)
            case 1:
                setBlocos[0].changeXY(x, y  - box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y + box.size)
                setBlocos[3].changeXY(x - box.size, y)
            case 2:
                setBlocos[0].changeXY(x + box.size, y)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x - box.size, y)
                setBlocos[3].changeXY(x, y - box.size)
            case 3:
                setBlocos[0].changeXY(x, y + box.size)
                setBlocos[1].changeXY(x, y)
                setBlocos[2].changeXY(x, y  - box.size)
                setBlocos[3].changeXY(x + box.size, y)