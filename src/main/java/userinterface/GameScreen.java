package userinterface;

import objectgame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 150; //by pixel

    private MainCharacter mainCharacter;
    private Thread thread;

    private Land land;
    private Clouds cloud;

    private EnemyManager enemyManager;

    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        land = new Land(this);
        cloud = new Clouds();
        enemyManager = new EnemyManager();
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                mainCharacter.update();
                cloud.update();
                land.update();
                enemyManager.update();
                repaint();
                thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);
        land.draw(g);
        cloud.draw(g);
        mainCharacter.draw(g);
        enemyManager.draw(g);
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
