package world.ucode.userinterface;

import world.ucode.utils.Animation;
import world.ucode.utils.Resource;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Settings implements MouseListener {
    private Animation avatar1, avatar2;
    private GameScreen gameScreen;

    Settings(GameScreen gameScreen) {
        avatar1 = new Animation(80);
        avatar2 = new Animation(80);

        avatar1.addFrame(Resource.getResourceImage("src/resources/main-character1.png"));
        avatar1.addFrame(Resource.getResourceImage("src/resources/main-character2.png"));
        avatar2.addFrame(Resource.getResourceImage("src/resources/new-main-character1.png"));
        avatar2.addFrame(Resource.getResourceImage("src/resources/new-main-character2.png"));

        this.gameScreen = gameScreen;
    }

    public void update() {
        avatar1.update();
        avatar2.update();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        Font fnt0 = new Font("standard 07_57", Font.BOLD, 25);
        g.setFont(fnt0);
        g.setColor(Color.GRAY);
        g.drawString("CHOSE AVATAR:", 300, 160);

        Font fnt1 = new Font("standard 07_57", Font.BOLD, 20);
        g.setFont(fnt1);
        g.drawString("DINO", 280, 270);
        g.drawString("BULLY", 460, 270);

        g2d.drawImage(avatar1.getFrame(), 300, 200, null);
        g2d.drawImage(avatar2.getFrame(), 460, 195, null);
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx >= 280 && mx <= 330) {
            if (my >= 200 && my <= 250) {
                gameScreen.avatar = false;
                gameScreen.setGameState(gameScreen.GAME_PLAY_STATE);
            }
        }
        if (mx >= 460 && mx <= 520) {
            if (my >= 200 && my <= 250) {
                gameScreen.avatar = true;
                gameScreen.setGameState(gameScreen.GAME_PLAY_STATE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
