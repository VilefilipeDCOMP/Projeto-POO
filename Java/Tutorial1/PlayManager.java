package Java.Tutorial1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import Java.Tutorial1.memo.Peca;
import Java.Tutorial1.memo.tetriPeca_Quadrada;

public class PlayManager {
    final int PlayerScene_x = 360;
    final int PlayerScene_y = 620;
    final int PlayerScene_posy = 50;
    final int PlayerScene_posx = ( (int) 1262/2) - ( (int) PlayerScene_x/2);
    
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    public int i = 0;

    // TetriPeca_Barra atual;
    Peca atual;

    // pecas = [tetriPeca_Barra(posInicial_x, posInicial_y), tetriPeca_Quadrada(posInicial_x, posInicial_y), tetriPeca_L(posInicial_x, posInicial_y) , tetriPeca_LInv(posInicial_x, posInicial_y), tetriPeca_S(posInicial_x, posInicial_y), tetriPeca_SInv(posInicial_x, posInicial_y), tetriPeca_Triangulo(posInicial_x, posInicial_y)];
    // blocosFixos = []

    public PlayManager() {

        //Main Play Area Frame
        left_x = (GameWindow.WIDTH/2) - (PlayerScene_x/2);
        right_x = left_x + PlayerScene_x;
        top_y = 50;
        bottom_y = top_y + PlayerScene_y;
    }

    public Peca gerarAleatorio () {
        this.atual = new tetriPeca_Quadrada(0, 0);
        this.atual.changeXY(PlayerScene_posx + (PlayerScene_x/2) - 30, 50, this.atual.rot, this.atual.b);
        return atual;
    }

    public boolean possivelY() {
        // int aux = 0;
        for (int i = 0; i < 4; i++) {
            if (this.atual.b[i].getY() + 30 != PlayerScene_y+PlayerScene_posy) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public void update(Graphics2D g2) {
        if (this.possivelY() == true) {
            atual.changeXY(atual.b[1].x, atual.b[1].y + 30, atual.rot, atual.b);
        }
        if (this.atual != null) {
            atual.draw(g2);
            g2.setColor(Color.white);
            g2.drawRect(PlayerScene_posx, PlayerScene_posy, PlayerScene_x, PlayerScene_y);
        } 
        
    }

    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(PlayerScene_posx, PlayerScene_posy, PlayerScene_x, PlayerScene_y);
        g2.drawRect(PlayerScene_posx+PlayerScene_x + 150, PlayerScene_posy+PlayerScene_y-200, 200, 200);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", (PlayerScene_posx+PlayerScene_x + 150) + 78, (PlayerScene_posy+PlayerScene_y-200)+30);
        
        // # Pintar bloco fixo
        // for (bloco_x, bloco_y) in self.blocosFixos:
        //     qp.drawRect(bloco_x, bloco_y, 30, 30)

    }
}
