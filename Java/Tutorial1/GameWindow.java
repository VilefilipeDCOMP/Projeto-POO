package Java.Tutorial1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Java.Tutorial1.memo.tetriPeca_Quadrada;


public class GameWindow extends JPanel implements Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 5;
    Thread gameThread;
    PlayManager pm;
    
    public GameWindow() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // this.setSize(200, 200);
        this.setBackground(Color.black);
        this.setLayout(null);

        pm = new PlayManager();
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // System.out.println(gameThread);

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
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
    }
}
