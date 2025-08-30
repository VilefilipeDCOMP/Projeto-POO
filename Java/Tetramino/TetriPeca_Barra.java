package Java.Tetramino;

import java.awt.Color;

public class TetriPeca_Barra extends Peca {
    
    public TetriPeca_Barra(int x, int y) {
        super(x, y, new Color(15,155,215,255));
        this.changeXY(x+50, y, this.rot, this.b);
    }

    @Override
    public void changeXY (int x, int y, int rot, Block b[]) {
        if ((rot == 1) || (rot == 3)) {
            b[0].changeXY(x, y - boxSize);
            b[1].changeXY(x, y);
            b[2].changeXY(x, y + boxSize);
            b[3].changeXY(x, y + (boxSize * 2));
        } else {
            b[0].changeXY(x - boxSize, y);
            b[1].changeXY(x, y);
            b[2].changeXY(x  + boxSize, y);
            b[3].changeXY(x  + (boxSize * 2), y);
        }

    }
}
