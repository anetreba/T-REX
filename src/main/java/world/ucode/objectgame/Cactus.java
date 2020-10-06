package world.ucode.objectgame;

import world.ucode.utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {

    private BufferedImage image;
    private int posX, posY;
    private Rectangle rect;
    private boolean isScoreGot = false;

    private MainCharacter mainCharacter;


    public Cactus(MainCharacter mainCharacter) {
        image = Resource.getResourceImage("src/resources/cactus1.png");

        posX = 200;
        posY = 227;
        rect = new Rectangle();
        this.mainCharacter = mainCharacter;
    }

    @Override
    public void update(int score) {
        if (score > 1100)
            posX -= 10;
        else if (score > 600)
            posX -= 8;
        else if (score < 100)
            posX -= 4;
        else
            posX -= 6;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
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
        return posX + image.getWidth() < 0;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
