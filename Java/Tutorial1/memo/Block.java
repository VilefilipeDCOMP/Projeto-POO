package Java.Tutorial1.memo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Block {
    public int x = 0;
    public int y = 0;
    public final int size = 30;
    public Color c;

    public Block(Color c) {
        this.c = c;
    }

    public void changeXY (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }

    public void draw (Graphics2D g2) {
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(Color.red);
        g2.fillRect(x, y, size, size);
        g2.setColor(Color.white);
        g2.drawRect(x, y, size, size);

        // g2.setColor(c);
        // g2.fillRect(x, y, size, size);
    }

    

}
