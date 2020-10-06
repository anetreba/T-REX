package world.ucode.userinterface;

import javax.swing.*;
import java.awt.*;

public class MenuBar {
    public Rectangle playButton = new Rectangle(290, 130, 100, 50);
    public Rectangle helpButton = new Rectangle(290, 170, 100, 50);
    public Rectangle quitButton = new Rectangle(290, 210, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("standard 07_57", Font.BOLD, 25);
        g.setFont(fnt0);
        g.setColor(Color.GRAY);

        Font fnt1 = new Font("standard 07_57", Font.BOLD, 25);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 19, playButton.y + 30);
        System.out.println();
        g.drawString("CHOSE AVATAR", helpButton.x + 19, helpButton.y + 30);
        g.drawString("QUIT", quitButton.x + 19, quitButton.y + 30);
    }


}
