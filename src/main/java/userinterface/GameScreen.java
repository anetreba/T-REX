package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    private float x = 0;
    private float y = 0;
    private Thread thread;
    private float speedY = 0;
    public GameScreen() {
        thread = new Thread(this);

    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                speedY += GRAVITY;
                y += speedY;
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
        g.drawRect((int)x,(int) y,100,100);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        speedY = -4;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
    }
}
