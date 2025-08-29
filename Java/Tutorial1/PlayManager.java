package Java.Tutorial1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import Java.Tutorial1.memo.*;

public class PlayManager {
    final int PlayerScene_x = 360;
    final int PlayerScene_y = 600;
    final int PlayerScene_posy = 50;
    final int PlayerScene_posx = ( (int) 1262/2) - ( (int) PlayerScene_x/2);
    
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    boolean movimentar = true;

    public int i = 0;

    Peca atual;
    Peca proximaPeca;
    private Random random;
    ArrayList<Block> blocosFixos;

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
        int tipoPeca = random.nextInt(7);
        Peca peca = switch (tipoPeca) {
            case 0 -> new TetriPeca_Quadrada(0, 0);
            case 1 -> new TetriPeca_Barra(0, 0);
            case 2 -> new TetriPeca_L(0, 0);
            case 3 -> new TetriPeca_LInv(0, 0);
            case 4 -> new TetriPeca_S(0, 0);
            case 5 -> new TetriPeca_SInv(0, 0);
            case 6 -> new TetriPeca_Triangulo(0, 0);
            default -> null;
        };
        return peca;
    }

    public void spawnPeca() {
        atual = proximaPeca;
        atual.changeXY(PlayerScene_posx + (PlayerScene_x / 2) - 30, PlayerScene_posy, atual.rot, atual.b);
        // Mudei de -15 para -30 para ficar certinho.
        // atual.changeXY(PlayerScene_posx + (PlayerScene_x / 2) - 15, PlayerScene_posy, atual.rot, atual.b);

        proximaPeca = gerarAleatorio();
        proximaPeca.changeXY(PlayerScene_posx + PlayerScene_x + 250, PlayerScene_posy + PlayerScene_y - 120, proximaPeca.rot, proximaPeca.b);
    }

    public boolean possivelY(Block[] blocos) {
        for (Block blocoAtual : blocos) {
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

    public boolean possivelX(int soma, Block[] blocos) {
        // int aux = 0;
        for (Block blocoAtual : blocos) {
            if (soma == 30) {
                if (blocoAtual.getX() + 30 != PlayerScene_x+PlayerScene_posx) {
                    // 2. Checa se colide com algum dos blocos já fixados
                    for (Block blocoFixo : blocosFixos) {
                        // Checa se as coordenadas X são as mesmas e se o bloco atual está prestes a entrar na posição Y de um bloco fixo
                        if (blocoAtual.getX() + 30 == blocoFixo.getX() && blocoAtual.getY() == blocoFixo.getY()) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    return false;
                }
            } else if (soma == -30){
                if (blocoAtual.getX() - 30 >= PlayerScene_posx) {
                    // 2. Checa se colide com algum dos blocos já fixados
                    for (Block blocoFixo : blocosFixos) {
                        // Checa se as coordenadas X são as mesmas e se o bloco atual está prestes a entrar na posição Y de um bloco fixo
                        if (blocoAtual.getX() - 30 == blocoFixo.getX() && blocoAtual.getY() == blocoFixo.getY()) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    System.out.println(PlayerScene_posx);
                    return false;
                }
            } else if (soma == 0) {
                if ((blocoAtual.getX() >= PlayerScene_posx) && (blocoAtual.getX() != PlayerScene_x+PlayerScene_posx)) {
                    for (Block blocoFixo : blocosFixos) {
                        // Checa se as coordenadas X são as mesmas e se o bloco atual está prestes a entrar na posição Y de um bloco fixo
                        if (blocoAtual.getX() == blocoFixo.getX() && blocoAtual.getY() == blocoFixo.getY()) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    return false;
                }
            }
            else {
                System.out.println("Erro do Desenvolvedor, soma não é igual a nenhum dos elementos");
            }

        }
        return true;
    }


    public void update() {
        if (this.possivelY(this.atual.b)) {
            if (this.movimentar == true) {
                atual.changeXY(atual.b[1].x, atual.b[1].y + 30, atual.rot, atual.b);
                this.movimentar = false;
            }
        } else {
            for (Block bloco : atual.b) {
                blocosFixos.add(bloco);}
            spawnPeca();}
    }

    public void draw(Graphics2D g2) {
        g2.setStroke(new BasicStroke(2f));
        
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
        
        g2.drawRect(0, 0, GameWindow.WIDTH - 1, GameWindow.HEIGHT - 1);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(PlayerScene_posx, PlayerScene_posy, PlayerScene_x, PlayerScene_y);
        g2.drawRect(PlayerScene_posx+PlayerScene_x + 150, PlayerScene_posy+PlayerScene_y-200, 200, 200);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", (PlayerScene_posx+PlayerScene_x + 150) + 78, (PlayerScene_posy+PlayerScene_y-200)+30);
        
    }
}
