package Java.Tetramino;

import java.awt.Color;

public class TetriPeca_Quadrada extends Peca {
    
    public TetriPeca_Quadrada(int x, int y) {
        super(x, y, new Color(227,159,2,255));
        this.changeXY(x+50, y, this.rot, this.b);
    }

    @Override
    public void changeXY (int x, int y, int rot, Block b[]) {
            b[0].changeXY(x - boxSize, y);
            b[1].changeXY(x, y);
            b[2].changeXY(x, y + boxSize);
            b[3].changeXY(x  - boxSize, y + boxSize);
    }
}
