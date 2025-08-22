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

class tetriPeca_Barra:
    cor = QtGui.QColor(15,155,215,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x - box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x + box.size, y)
        self.b[3].changeXY(x + (box.size) * 2, y)

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[3]
        self.xmin = self.b[0]
        self.ymin = self.b[1]
        self.ymax = self.b[1]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_Quadrada: 
    cor = QtGui.QColor(227,159,2,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x - box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x, y + box.size)
        self.b[3].changeXY(x - box.size, y + box.size)

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[1]
        self.xmin = self.b[0]
        self.ymin = self.b[0]
        self.ymax = self.b[3]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_L: 
    cor = QtGui.QColor(33,65,198,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x - box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x, y - box.size)
        self.b[3].changeXY(x, y - (box.size*2))

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[1]
        self.xmin = self.b[0]
        self.ymin = self.b[1]
        self.ymax = self.b[3]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_LInv: 
    cor = QtGui.QColor(227,91,2,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x + box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x, y - box.size)
        self.b[3].changeXY(x, y - (box.size*2))

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[0]
        self.xmin = self.b[1]
        self.ymin = self.b[1]
        self.ymax = self.b[3]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_S: 
    cor = QtGui.QColor(89,177,1,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x - box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x, y - box.size)
        self.b[3].changeXY(x + box.size, y - box.size)

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[3]
        self.xmin = self.b[0]
        self.ymin = self.b[2]
        self.ymax = self.b[1]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_SInv: 
    cor = QtGui.QColor(215,15,55,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x + box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x, y - box.size)
        self.b[3].changeXY(x - box.size, y - box.size)

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[0]
        self.xmin = self.b[3]
        self.ymin = self.b[3]
        self.ymax = self.b[1]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)

class tetriPeca_Triangulo: 
    cor = QtGui.QColor(175,41,138,255)
    b = [box(cor), box(cor), box(cor), box(cor)]

    def changeXY(self, x, y):
        self.b[0].changeXY(x - box.size, y)
        self.b[1].changeXY(x, y)
        self.b[2].changeXY(x + box.size, y)
        self.b[3].changeXY(x, y + box.size)

    def __init__(self, x, y):
        self.changeXY(x,y)
        self.xmax = self.b[2]
        self.xmin = self.b[0]
        self.ymin = self.b[1]
        self.ymax = self.b[3]

    def draw(self, qp):
        for block in self.b:
            qp.setBrush(QtGui.QBrush(self.cor))
            qp.drawRect(block.getX(), block.getY(), box.size, box.size)
