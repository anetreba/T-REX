package world.ucode.objectgame;

import world.ucode.userinterface.GameScreen;
import world.ucode.utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private List<Enemy> enemies;
    private Random random;
    private BufferedImage imageCactus1, ImageCactus2;

    private MainCharacter mainCharacter;
    private GameScreen gameScreen;


    public EnemyManager(MainCharacter mainCharacter, GameScreen gameScreen) {
        this.mainCharacter = mainCharacter;
        this.gameScreen = gameScreen;
        enemies = new ArrayList<Enemy>();
        random = new Random();

        imageCactus1 = Resource.getResourceImage("src/resources/cactus1.png");
        ImageCactus2 = Resource.getResourceImage("src/resources/cactus2.png");
        enemies.add(getRandomCactus());
    }

    public void update(int score) {
        for (Enemy e : enemies) {
            e.update(score);
            if (e.isOver() && !e.isScoreGot()) {
                if (gameScreen.getScore() > 600)
                    gameScreen.plusScore(60);
                if (gameScreen.getScore() > 100)
                    gameScreen.plusScore(40);
                else {
                    gameScreen.plusScore(20);
                }
                mainCharacter.sound(4);
                e.setScoreGot(true);
            }
            if (e.getBound().intersects(mainCharacter.getBound()))
                mainCharacter.setAlive(false);
        }
        Enemy firstEnemy = enemies.get(0);
        if (enemies.get(0).isOutOfScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomCactus());
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    public void reset() {
        enemies.clear();
        enemies.add(getRandomCactus());
    }

    public Enemy getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus(mainCharacter);
        cactus.setX(800);
        int flag = random.nextInt(3);
        if (flag == 0)
            cactus.setImage(imageCactus1);
        else if (flag == 1){
            cactus.setImage(ImageCactus2);
            cactus.setY(240);
        }
        else if (flag == 2) {
            Bird bird = new Bird(mainCharacter);
            bird.setX(800);
            if (mainCharacter.isAvatar())
                bird.setY(180);
            else
                bird.setY(200);
            return bird;
        }
        return cactus;
    }
}
