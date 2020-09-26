package objectgame;

import utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private List<Enemy> enemies;
    private Random random;
    private BufferedImage imageCactus1, ImageCactus2;

    public EnemyManager() {
        enemies = new ArrayList<Enemy>();
        random = new Random();

        imageCactus1 = Resource.getResourceImage("data/cactus1.png");
        ImageCactus2 = Resource.getResourceImage("data/cactus2.png");
        enemies.add(getRandomCactus());
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
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

    public Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus();
        cactus.setX(600);
        if (random.nextBoolean())
            cactus.setImage(imageCactus1);
        else {
            cactus.setImage(ImageCactus2);
            cactus.setY(120);
        }
        return cactus;
    }
}
