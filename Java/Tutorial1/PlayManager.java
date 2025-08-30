package Java.Tutorial1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import Java.Tutorial1.memo.*;

public class PlayManager {
    final int PlayerScene_x = 360;
    final int PlayerScene_y = 600;
    final int PlayerScene_posy = 50;
    final int PlayerScene_posx = (1262/2) - ( (int) PlayerScene_x/2);
    
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    boolean movimentar = true;
    public boolean gameOver;

    public int i = 0;

    Peca atual;
    Peca proximaPeca;
    public Random random;
    ArrayList<Block> blocosFixos;
    int pontos = 0;

    public PlayManager() {

        //Main Play Area Frame
        left_x = (GameWindow.WIDTH/2) - (PlayerScene_x/2);
        right_x = left_x + PlayerScene_x;
        top_y = 50;
        bottom_y = top_y + PlayerScene_y;

        random = new Random();
        gameOver = false;
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
    public void resetGame() {
        blocosFixos.clear();
        pontos = 0;
        proximaPeca = gerarAleatorio();
        spawnPeca();
        gameOver = false;
    }

    public void spawnPeca() {
        atual = proximaPeca;
        atual.changeXY(PlayerScene_posx + (PlayerScene_x / 2) - 30, PlayerScene_posy, atual.rot, atual.b);
        // Mudei de -15 para -30 para ficar certinho.
        // atual.changeXY(PlayerScene_posx + (PlayerScene_x / 2) - 15, PlayerScene_posy, atual.rot, atual.b);

        proximaPeca = gerarAleatorio();
        proximaPeca.changeXY(PlayerScene_posx + PlayerScene_x + 250, PlayerScene_posy + PlayerScene_y - 100, proximaPeca.rot, proximaPeca.b);
        for (Block blocoPeca : atual.b) {
            for (Block blocoFixo : blocosFixos) {
                if (blocoPeca.getX() == blocoFixo.getX() && blocoPeca.getY() == blocoFixo.getY()) {
                    gameOver = true;
                    break;
                }
            }
        }
    }

    public boolean possivelY(Block[] blocos) {
        for (Block blocoAtual : blocos) {
            int proximoY = blocoAtual.getY() + 30;

            if (proximoY >= PlayerScene_posy + PlayerScene_y) {
                return false;
            }


            for (Block blocoFixo : blocosFixos) {
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
        if (this.movimentar) {
            if (this.possivelY(this.atual.b)) {
                atual.changeXY(atual.b[1].x, atual.b[1].y + 30, atual.rot, atual.b);
            } else {
                for (Block bloco : atual.b) {
                    blocosFixos.add(bloco);
                }
                checarLinhasCompletas();
                spawnPeca();
            }
            this.movimentar = false;
        }
    }

    public void checarLinhasCompletas() {
        // A largura do tabuleiro em número de blocos (360 / 30 = 12)
        ArrayList<Integer> linhasCompletasY = getIntegers();

        if (!linhasCompletasY.isEmpty()) {
            int linhasRemovidas = linhasCompletasY.size();

            switch (linhasRemovidas) {
                case 1: pontos += 100; break; // Single
                case 2: pontos += 300; break; // Double
                case 3: pontos += 500; break; // Triple
                case 4: pontos += 800; break; // Tetris
            }


            blocosFixos.removeIf(bloco -> linhasCompletasY.contains(bloco.getY()));

            // Desce os blocos restantes
            Collections.sort(linhasCompletasY);
            for (Block bloco : blocosFixos) {
                int shift = 0;
                for (int y_removida : linhasCompletasY) {
                    if (bloco.getY() < y_removida) {
                        shift++;
                    }
                }
                if (shift > 0) {
                    bloco.changeXY(bloco.getX(), bloco.getY() + (shift * 30));
                }
            }
        }
    }

    private ArrayList<Integer> getIntegers() {
        int larguraEmBlocos = PlayerScene_x / 30;
        ArrayList<Integer> linhasCompletasY = new ArrayList<>();

        // Primeiro, encontra todas as linhas que estão completas
        for (int y = PlayerScene_posy; y < PlayerScene_posy + PlayerScene_y; y += 30) {
            int blocosNaLinha = 0;
            for (Block bloco : blocosFixos) {
                if (bloco.getY() == y) {
                    blocosNaLinha++;
                }
            }
            if (blocosNaLinha == larguraEmBlocos) {
                linhasCompletasY.add(y);
            }
        }
        return linhasCompletasY;
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

        g2.setFont(new Font("Arial", Font.PLAIN, 25));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("PONTOS: " + pontos, PlayerScene_posx + PlayerScene_x + 185, 120);
        
        g2.setStroke(new BasicStroke(0f));
        g2.setColor(Color.black);
        g2.fillRect(PlayerScene_posx, 0, PlayerScene_x, PlayerScene_posy);
        
        g2.drawRect(0, 0, GameWindow.WIDTH - 1, GameWindow.HEIGHT - 1);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(PlayerScene_posx, PlayerScene_posy, PlayerScene_x, PlayerScene_y);
        g2.drawRect(PlayerScene_posx+PlayerScene_x + 150, PlayerScene_posy+PlayerScene_y-200, 200, 200);
        
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        // g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", (PlayerScene_posx+PlayerScene_x + 150) + 78, (PlayerScene_posy+PlayerScene_y-200)+30);
        
    }
}
