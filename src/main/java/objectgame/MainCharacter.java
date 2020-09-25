package objectgame;

import utils.Animation;
import utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter {
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 300; //by pixel
    private float x = 0;
    private float y = 0;
    private float speedY = 0;
    private Animation characterRun;

    public MainCharacter() {
        characterRun = new Animation();
        characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
    }

    public void update() {
        characterRun.update();
        if (y >= GROUNDY - characterRun.getFrame().getHeight()) {
            speedY = 0;
            y = GROUNDY - characterRun.getFrame().getHeight(); //height of Rect
        } else {
            speedY += GRAVITY;
            y += speedY;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect((int)x,(int) y,characterRun.getFrame().getWidth(),characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
    }

    public void jump() {
        speedY = -4;
        y += speedY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
