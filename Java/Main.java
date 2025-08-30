package Java;

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris - Java");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GameWindow gp = new GameWindow();
        window.add(gp);
        window.addKeyListener(gp);
        window.pack();
        gp.startGame();
        

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
