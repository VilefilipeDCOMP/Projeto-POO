package Java.Tutorial1;
import javax.swing.*;

public class Display extends JFrame{
    public Display() {
        super("Tetris");
        System.out.println("vasco");
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
        
        GameWindow janelaJogo = new GameWindow();
        add(janelaJogo);
        
    }

    public static void main (String[] args) {
        Display mainTela = new Display();
    }
}