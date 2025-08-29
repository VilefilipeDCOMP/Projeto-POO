package Java.Tutorial1.memo;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Peca {
    public int rot = 0;
    public final int boxSize = 30;

    public Block bTemp[] = {new Block(Color.white), new Block(Color.white), new Block(Color.white), new Block(Color.white)};
    public Block[] b = new Block[4];
   
    
    public Peca(int x, int y, Color cor) {
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(cor);
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < 4 ; i++) {
            g2.setStroke(new BasicStroke(1f));
            g2.setColor(this.b[i].c);
            g2.fillRect(this.b[i].getX(), this.b[i].getY(), b[0].size, b[0].size);
            g2.setColor(Color.white);
            g2.drawRect(this.b[i].getX(), this.b[i].getY(), b[0].size, b[0].size);

            // Se quiser voltar para a versão só com a cor na borda
            // g2.drawRect(this.b[i].getX(), this.b[i].getY(), b[0].size, b[0].size);
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
