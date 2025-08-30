package Java.Tutorial1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class GameWindow extends JPanel implements Runnable, KeyListener{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 30;
    Thread gameThread;
    PlayManager pm;
    
    public GameWindow() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);

        pm = new PlayManager();

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(pm.PlayerScene_posx + pm.PlayerScene_x + 150 + 50, 135, 100, 40);
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pm.resetGame();
                GameWindow.this.requestFocusInWindow();
            }
        });

        this.add(resetButton);

        
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        int counter = 0;
        int velocidadeQueda = 9;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                if (counter == velocidadeQueda) {
                    pm.movimentar = true;
                    counter = 0;
                }
                if (pm.gameOver) {
                    checkGameOver();
                    break;
                }
                update();
                repaint();
                counter++;
                delta--;
            }
        }
    }

    public void update() {
        pm.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        pm.draw(g2);
        pm.update();
    }

    public void checkGameOver() {
        if (pm.gameOver) {
            String[] options = {"Resetar", "Sair"};
            String message = "Fim de Jogo!\nSua pontuação final foi: " + pm.pontos;

            int choice = JOptionPane.showOptionDialog(
                    this, message, "Game Over",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]
            );
            if (choice == 0) { // Resetar
                pm.resetGame();
                 startGame();
            } else { // Sair
                System.exit(0);
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 65: // A
            case 37: // ESQUERDA
                if (pm.possivelX(-30, pm.atual.b) == true) {
                    pm.atual.changeXY(pm.atual.b[1].x - 30, pm.atual.b[1].y, pm.atual.rot, pm.atual.b);
                }
                break;
            case 68: // D
            case 39: // DIREITA
                if (pm.possivelX(30, pm.atual.b) == true) {
                    pm.atual.changeXY(pm.atual.b[1].x + 30, pm.atual.b[1].y, pm.atual.rot, pm.atual.b);
                }
                break;
            case 87: // W
            case 38: // CIMA
                int test_rot = pm.atual.rot;
                if (test_rot == 3) {
                    test_rot = 0;
                } else {
                    test_rot++;
                }
        
                int x = pm.atual.b[1].getX();
                int y = pm.atual.b[1].getY();
        
                pm.atual.changeXY(x, y, test_rot, pm.atual.bTemp);

                if ((pm.possivelX(0, pm.atual.bTemp) == true) && (pm.possivelY(pm.atual.bTemp) == true)) {
                    pm.atual.rotacionar();
                }
                break;
            case 83: // S
            case 40: // BAIXO
                if (pm.possivelY(pm.atual.b) == true) {
                    pm.atual.changeXY(pm.atual.b[1].x, pm.atual.b[1].y + 30, pm.atual.rot, pm.atual.b);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
