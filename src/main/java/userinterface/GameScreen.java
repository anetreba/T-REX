package userinterface;

import objectgame.*;
import utils.MouseInput;
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
    public static final int GAME_SETT_STATE = 3;

    public static boolean avatar = false;
    private Settings settings;

    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 270; //by pixel

    private MainCharacter mainCharacter;
    private Thread thread;

    private Land land;
    private Clouds cloud;

    private EnemyManager enemyManager;

    public int getScore() {
        return score;
    }

    private int score = 0;

    private int hi_score = 0;

    public static int gameState = GAME_FIST_STATE;
    private boolean isKeyPressed = false;

    private BufferedImage imageGameOverText;
    private BufferedImage imageReset;

    private MenuBar menuBar;
    private MouseInput mouseInput;


    public GameScreen() {
        settings = new Settings(this);
        thread = new Thread(this);
        menuBar = new MenuBar();
        mainCharacter = new MainCharacter();
        land = new Land(this);
        cloud = new Clouds();
        enemyManager = new EnemyManager(mainCharacter, this);
        imageGameOverText = Resource.getResourceImage("data/gameover_text.png");
        imageReset = Resource.getResourceImage("data/replay_button.png");
        mouseInput = new MouseInput();
        this.addMouseListener(mouseInput);
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
                land.update(score);
                enemyManager.update(score);
                if (!(mainCharacter.getAlive())) {
                    gameState = GAME_OVER_STATE;
                    mainCharacter.setState(3);
                    mainCharacter.sound(3);
                }
                break;
        }
    }

    public void plusScore(int score) {
        this.score += score;
    }

    public void resetGame() {
        if (score > hi_score) {
            hi_score = score;
        }
        score = 0;
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

        if (gameState != GAME_FIST_STATE)
            this.removeMouseListener(mouseInput);

        switch (gameState) {
            case GAME_FIST_STATE:
                g.drawImage(Resource.getResourceImage("data/imgonline-com-ua-Resize-FCO2RUZbhttV.jpg"), 0, 0, null);
                menuBar.render(g);
                break;
            case GAME_SETT_STATE:
                g.drawImage(Resource.getResourceImage("data/set.jpg"), 0, 0, null);
                settings.draw(g);
                this.addMouseListener(settings);
                System.out.println(avatar);
                break;
            case GAME_PLAY_STATE:
//                g.setColor(Color.decode("#f7f7f7"));
//                g.fillRect(0,0, getWidth(), getHeight());
                land.draw(g);
                cloud.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.setColor(Color.BLACK);
                g.drawString("SCORE: " + String.valueOf(score), 680, 20);
                g.drawString("HI: " + String.valueOf(hi_score), 610, 20);
                break;
            case GAME_OVER_STATE:
//                g.setColor(Color.decode("#f7f7f7"));
//                g.fillRect(0,0, getWidth(), getHeight());
                land.draw(g);
                cloud.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawImage(imageGameOverText, 300, 110, null);
                g.drawImage(imageReset, 380, 150, null);
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
