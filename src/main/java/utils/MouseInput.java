package utils;

import userinterface.GameScreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    private GameScreen gameScreen;


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
        public Rectangle playButton = new Rectangle(350, 150, 100, 50);
        public Rectangle quitButton = new Rectangle(350, 220, 100, 50);
         */

        //Play
        if (mx >= 290 && mx <= 570) {
            if (my >= 130 && my <= 170) {
                GameScreen.gameState = GameScreen.GAME_PLAY_STATE;
                System.out.println("play");
            }
        }
        if (mx >= 290 && mx <= 570) {
            if (my >= 170 && my <= 210) {
                System.out.println("avatarset");
                GameScreen.gameState = GameScreen.GAME_SETT_STATE;
            }
        }

        if (mx >= 290 && mx <= 570) {
            if (my >= 210 && my <= 280) {
                System.exit(0);
            }
        }
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
