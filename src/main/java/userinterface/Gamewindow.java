package userinterface;

import javax.swing.*;


public class Gamewindow extends JFrame {
    private GameScreen gameScreen;

    public Gamewindow() {
        super("T-REX");
        setSize(600, 200);
//        setLocation(400, 200); //location of gamewindow in screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }

    public void startGame() {
        gameScreen.startGame();
    }

    public static void main(String args[]) {
       Gamewindow gw = new Gamewindow();
       gw.setVisible(true);
       gw.startGame();

    }
}

