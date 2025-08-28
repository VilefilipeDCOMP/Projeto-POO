package Java.Tutorial1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import Java.Tutorial1.memo.Block;
import Java.Tutorial1.memo.Peca;
import Java.Tutorial1.memo.TetriPeca_Barra;
import Java.Tutorial1.memo.tetriPeca_Quadrada;

public class PlayManager {
    final int PlayerScene_x = 360;
    final int PlayerScene_y = 600;
    final int PlayerScene_posy = 50;
    final int PlayerScene_posx = ( (int) 1262/2) - ( (int) PlayerScene_x/2);
    
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    public int i = 0;

    // TetriPeca_Barra atual;
    Peca atual;
    Peca proximaPeca;
    private Random random;
    ArrayList<Block> blocosFixos;

    // pecas = [tetriPeca_Barra(posInicial_x, posInicial_y), tetriPeca_Quadrada(posInicial_x, posInicial_y), tetriPeca_L(posInicial_x, posInicial_y) , tetriPeca_LInv(posInicial_x, posInicial_y), tetriPeca_S(posInicial_x, posInicial_y), tetriPeca_SInv(posInicial_x, posInicial_y), tetriPeca_Triangulo(posInicial_x, posInicial_y)];
    // blocosFixos = []

    public PlayManager() {

        //Main Play Area Frame
        left_x = (GameWindow.WIDTH/2) - (PlayerScene_x/2);
        right_x = left_x + PlayerScene_x;
        top_y = 50;
        bottom_y = top_y + PlayerScene_y;

        random = new Random();
        blocosFixos = new ArrayList<>();
        proximaPeca = gerarAleatorio();
        spawnPeca();
    }

    public Peca gerarAleatorio () {
        int tipoPeca = random.nextInt(2);
        Peca peca = switch (tipoPeca) {
            case 0 -> new tetriPeca_Quadrada(0, 0);
            case 1 -> new TetriPeca_Barra(0, 0);
            default -> null;
        };
        return peca;
    }

    public void spawnPeca() {
        atual = proximaPeca;
        atual.changeXY(PlayerScene_posx + (PlayerScene_x / 2) - 15, PlayerScene_posy, atual.rot, atual.b);

        proximaPeca = gerarAleatorio();
        proximaPeca.changeXY(PlayerScene_posx + PlayerScene_x + 250, PlayerScene_posy + PlayerScene_y - 120, proximaPeca.rot, proximaPeca.b);
    }

    public boolean possivelY() {
        // int aux = 0;
        for (Block blocoAtual : this.atual.b) {
            int proximoY = blocoAtual.getY() + 30; // Posição Y no próximo movimento

            // 1. Checa se colide com o chão
            if (proximoY >= PlayerScene_posy + PlayerScene_y) {
                return false;
            }

            // 2. Checa se colide com algum dos blocos já fixados
            for (Block blocoFixo : blocosFixos) {
                // Checa se as coordenadas X são as mesmas e se o bloco atual está prestes a entrar na posição Y de um bloco fixo
                if (blocoAtual.getX() == blocoFixo.getX() && proximoY == blocoFixo.getY()) {
                    return false;
                }
            }
        }
        return true;
    }


    public void update() {
        if (this.possivelY()) {
            atual.changeXY(atual.b[1].x, atual.b[1].y + 30, atual.rot, atual.b);
        } else {
            for (Block bloco : atual.b) {
                blocosFixos.add(bloco);}
            spawnPeca();}
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
        for (Block bloco : blocosFixos) {
            bloco.draw(g2);
        }

        if (this.atual != null) {
            atual.draw(g2);
        }
        // Desenha a próxima peça se ela existir
        if (this.proximaPeca != null) {
            proximaPeca.draw(g2);
        }
    }
}
