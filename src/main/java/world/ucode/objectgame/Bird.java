package world.ucode.objectgame;

import world.ucode.utils.Animation;
import world.ucode.utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends Enemy {
    private Animation bird;
    private int posX, posY;
    private Rectangle rect;
    private boolean isScoreGot = false;

    private MainCharacter mainCharacter;


    public Bird(MainCharacter mainCharacter) {
        bird = new Animation(100);
        bird.addFrame(Resource.getResourceImage("src/resources/100-offline-sprite.png"));
        bird.addFrame(Resource.getResourceImage("src/resources/bird2.png"));

        posX = 200;
        posY = 105;
        rect = new Rectangle();
        this.mainCharacter = mainCharacter;
    }

    @Override
    public void update(int score) {
        bird.update();
        if (score > 1100)
            posX -= 10;
        if (score > 600)
            posX -= 8;
        else if (score < 100)
            posX -= 4;
        else
            posX -= 6;
        rect.x = posX;
        rect.y = posY;
        rect.width = bird.getFrame().getWidth();
        rect.height = bird.getFrame().getHeight();
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bird.getFrame(), posX, posY, null);
    }

    @Override
    public boolean isOver() {
        return (mainCharacter.getX() > posX);
    }

    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void setScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }

    @Override
    public boolean isOutOfScreen() {
        return posX + bird.getFrame().getWidth() < 0;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }
}
