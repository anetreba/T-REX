package userinterface;

import objectgame.*;
import utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 150; //by pixel

    private MainCharacter mainCharacter;
    private Thread thread;

    private Land land;
    private Clouds cloud;

    private EnemyManager enemyManager;

    private int score = 0;

    private int gameState = GAME_FIST_STATE;
    private boolean isKeyPressed = false;

    private BufferedImage imageGameOverText;
    private BufferedImage imageReset;


    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        land = new Land(this);
        cloud = new Clouds();
        enemyManager = new EnemyManager(mainCharacter, this);
        imageGameOverText = Resource.getResourceImage("data/gameover_text.png");
        imageReset = Resource.getResourceImage("data/replay_button.png");
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                update();
                repaint();
                thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        switch (gameState) {
            case GAME_PLAY_STATE:
                mainCharacter.update();
                cloud.update();
                land.update();
                enemyManager.update();
                if (!(mainCharacter.getAlive())) {
                    gameState = GAME_OVER_STATE;
                    mainCharacter.setState(3);
                }
                break;
        }
    }

    public void plusScore(int score) {
        this.score += score;
    }

    public void resetGame() {
        mainCharacter.setAlive(true);
        mainCharacter.setState(0);
        mainCharacter.setX(50);
        mainCharacter.setY(90);
        enemyManager.reset();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0,0, getWidth(), getHeight());

        switch (gameState) {
            case GAME_FIST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                land.draw(g);
                cloud.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.setColor(Color.BLACK);
                g.drawString("Hi: " + String.valueOf(score), 500, 20);
                break;
            case GAME_OVER_STATE:
                land.draw(g);
                cloud.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawImage(imageGameOverText, 200, 50, null);
                g.drawImage(imageReset, 280, 80, null);
                break;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (!isKeyPressed) {
            isKeyPressed = true;
            switch (gameState) {
                case GAME_FIST_STATE:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        gameState = GAME_PLAY_STATE;
                    }
                    break;
                case GAME_PLAY_STATE:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        mainCharacter.jump();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        mainCharacter.down(true);
                    }
                    break;
                case GAME_OVER_STATE:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        gameState = GAME_PLAY_STATE;
                        resetGame();
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
        if (gameState == GAME_PLAY_STATE) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                mainCharacter.down(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE)
                mainCharacter.setState(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
