package userinterface;

import objectgame.MainCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 300; //by pixel

    private MainCharacter mainCharacter;
    private Thread thread;

    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                mainCharacter.update();
                repaint();
                thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);
        mainCharacter.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        mainCharacter.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
    }
}
