package world.ucode.utils;

import world.ucode.userinterface.GameScreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx >= 290 && mx <= 570) {
            if (my >= 130 && my <= 170) {
                GameScreen.gameState = GameScreen.GAME_PLAY_STATE;
            }
        }
        if (mx >= 290 && mx <= 570) {
            if (my >= 170 && my <= 210) {
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
