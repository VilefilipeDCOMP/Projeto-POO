package Java.Tutorial1.memo;

import java.awt.Color;

public class TetriPeca_LInv extends Peca {
    
    public TetriPeca_LInv(int x, int y) {
        Color cor = new Color(227,91,2,255);
        super(x, y, cor);
        this.changeXY(x+50, y, this.rot, this.b);
    }

    @Override
    public void changeXY (int x, int y, int rot, Block b[]) {
        switch (rot) {
            case 0:
                b[0].changeXY(x + boxSize, y);
                b[1].changeXY(x, y);
                b[2].changeXY(x, y - boxSize);
                b[3].changeXY(x, y - (boxSize*2));
                break;
            case 1:
                b[0].changeXY(x, y  + boxSize);
                b[1].changeXY(x, y);
                b[2].changeXY(x + boxSize, y);
                b[3].changeXY(x + (boxSize*2), y);
                break;
            case 2:
                b[0].changeXY(x - boxSize, y);
                b[1].changeXY(x, y);
                b[2].changeXY(x, y + boxSize);
                b[3].changeXY(x, y + (boxSize*2));
                break;
            case 3:
                b[0].changeXY(x, y  - boxSize);
                b[1].changeXY(x, y);
                b[2].changeXY(x - boxSize, y);
                b[3].changeXY(x - (boxSize*2), y);
                break;
            default:
                break;
        }

    }
}
