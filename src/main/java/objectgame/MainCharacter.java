package objectgame;

import utils.Animation;
import utils.Resource;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

public class MainCharacter {
    private float x = 50;
    private float y = 90;
    private float speedY = 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    private BufferedImage jumping;
    private Animation downRunAnim;
    private BufferedImage deathImage;

    private static final int NORMAL_RUN = 0;
    private static final int JUMPING = 1;
    private static final int DOWN_RUN = 2;
    private static final int DEATH = 3;
    private static final int SCORE = 4;

    public void setState(int state) {
        this.state = state;
    }

    private int state = NORMAL_RUN;

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public MainCharacter() {
        characterRun = new Animation(80);
        characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
        jumping = Resource.getResourceImage("data/main-character3.png");
        deathImage = Resource.getResourceImage("data/main-character4.png");
        downRunAnim = new Animation(80);
        downRunAnim.addFrame(Resource.getResourceImage("data/main-character5.png"));
        downRunAnim.addFrame(Resource.getResourceImage("data/main-character6.png"));
        rect = new Rectangle();
    }

    public void update() {
        characterRun.update();
        downRunAnim.update();
        if (y >= GROUNDY - characterRun.getFrame().getHeight()) {
            speedY = 0;
            y = GROUNDY - characterRun.getFrame().getHeight(); //height of Rect
        } else {
            speedY += GRAVITY;
            y += speedY;
        }
        if (state == NORMAL_RUN) {
            rect.y = (int)y;
            rect.x = (int)x;
            rect.width = characterRun.getFrame().getWidth();
            rect.height = characterRun.getFrame().getHeight();
        }
        else if (state == DOWN_RUN) {
            rect.x = (int)x;
            rect.y = (int)y + 20;
            rect.width = downRunAnim.getFrame().getWidth();
            rect.height = downRunAnim.getFrame().getHeight();
        }
    }

    public Rectangle getBound() {
        return rect;
    }

    public void down(boolean isDown) {
        if(isDown) {
            state = DOWN_RUN;
        } else {
            state = NORMAL_RUN;
        }
    }


    public void sound(int soundState) {
        String soundName = null;

        System.out.println(soundState);
        switch (soundState) {
            case JUMPING:
                soundName = "data/jump.wav";
                break;
//            case DOWN_RUN:
//                soundName = "data/RUNAWAY.WAV";
//                break;
            case SCORE:
                soundName = "data/scoreup.wav";
                break;
            case DEATH:
                soundName = "data/dead.wav";
                break;
        }

        AudioInputStream audioInputStream = null;
        try {
            assert soundName != null;
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert clip != null;
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }



    public void draw(Graphics g) {
        switch(state) {
            case NORMAL_RUN:
                g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
                break;
            case JUMPING:
                g.drawImage(jumping, (int) x, (int) y, null);
                break;
            case DOWN_RUN:
                g.drawImage(downRunAnim.getFrame(), (int) x, (int) (y + 20), null);
                break;
            case DEATH:
                g.drawImage(deathImage, (int) x, (int) y, null);
                break;
        }
//        g.setColor(Color.BLACK);
////        g.drawRect((int)x,(int) y,characterRun.getFrame().getWidth(),characterRun.getFrame().getHeight());
//        g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
    }

    public void jump() {
        if (speedY == 0) {
            speedY = -4;
            y += speedY;
            state = JUMPING;
            sound(JUMPING);
        }
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
