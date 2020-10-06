package world.ucode.objectgame;

import world.ucode.utils.Animation;
import world.ucode.utils.Resource;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static world.ucode.userinterface.GameScreen.GRAVITY;
import static world.ucode.userinterface.GameScreen.GROUNDY;

public class MainCharacter {
    private float x = 50;
    private float y = 90;
    private float speedY = 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    private boolean avatar = false;

    public boolean isAvatar() {
        return avatar;
    }

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

    public int getState() {
        return state;
    }

    private int state = NORMAL_RUN;

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public MainCharacter(boolean avatar) {
        characterRun = new Animation(80);
        characterRun.addFrame(Resource.getResourceImage("src/resources/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("src/resources/main-character2.png"));
        jumping = Resource.getResourceImage("src/resources/main-character3.png");
        deathImage = Resource.getResourceImage("src/resources/main-character4.png");
        downRunAnim = new Animation(80);
        downRunAnim.addFrame(Resource.getResourceImage("src/resources/main-character5.png"));
        downRunAnim.addFrame(Resource.getResourceImage("src/resources/main-character6.png"));
        rect = new Rectangle();
    }

    public void changeCharacter(boolean avatar) {
        if (avatar) {
            this.avatar = avatar;
            characterRun.addFrame(Resource.getResourceImage("src/resources/new-main-character1.png"));
            characterRun.addFrame(Resource.getResourceImage("src/resources/new-main-character2.png"));
            jumping = Resource.getResourceImage("src/resources/new-main-character3.png");
            deathImage = Resource.getResourceImage("src/resources/new-main-character4.png");
            downRunAnim.addFrame(Resource.getResourceImage("src/resources/new-main-character5.png"));
            downRunAnim.addFrame(Resource.getResourceImage("src/resources/new-main-character6.png"));
        }
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
        else if (state == JUMPING) {
            rect.x = (int)x;
            rect.y = (int)y + 20;
            rect.width = jumping.getWidth();
            rect.height = jumping.getHeight();
        }
    }

    public Rectangle getBound() {
        return rect;
    }

    public void down(boolean isDown) {
        if(isDown) {
            state = DOWN_RUN;
            sound(DOWN_RUN);
        } else {
            state = NORMAL_RUN;
        }
    }


    public void sound(int soundState) {
        String soundName = null;

        switch (soundState) {
            case JUMPING:
                soundName = "src/resources/JUMP1.wav";
                break;
            case DOWN_RUN:
                soundName = "src/resources/COLLECT.WAV";
                break;
            case SCORE:
                soundName = "src/resources/BRILLIANT.wav";
                break;
            case DEATH:
                soundName = "src/resources/NOOO.wav";
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
