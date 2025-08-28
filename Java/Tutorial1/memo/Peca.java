package Java.Tutorial1.memo;

import java.awt.Color;
import java.awt.Graphics2D;

public class Peca {
    Color cor = new Color(15,155,215,255);
    // Color cor;
    public int rot = 0;
    public final int boxSize = 30;

    // Block b[];
    // Block bTemp[];
    public Block b[] = {new Block(cor), new Block(cor), new Block(cor), new Block(cor)};
    public Block bTemp[] = {new Block(cor), new Block(cor), new Block(cor), new Block(cor)};
   
    
    public Peca(int x, int y) {
        // b[] = {new Block(cor), new Block(cor), new Block(cor), new Block(cor)};
        // Block bTemp[] = {new Block(cor), new Block(cor), new Block(cor), new Block(cor)};
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < 4 ; i++) {
            g2.setColor(cor);
            g2.drawRect(this.b[i].getX(), this.b[i].getY(), b[0].size, b[0].size);
        }
        // for (int i = 0; i < 4 ; i++) {
        //     g2.setColor(Color.white);
        //     g2.drawRect(this.bTemp[i].getX(), this.bTemp[i].getY(), b[0].size, b[0].size);
        // }
    }
    
    public void rotacionar() {
        if (this.rot == 3) {
            this.rot = 0;
        } else {
            this.rot++;
        }
    }

    public void changeXY (int x, int y, int rot, Block b[]) {}
}
