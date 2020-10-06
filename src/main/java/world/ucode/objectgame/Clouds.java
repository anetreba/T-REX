package world.ucode.objectgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import world.ucode.utils.Resource;

import java.util.ArrayList;
import java.util.List;

public class Clouds {

    private BufferedImage cloudImage;
    private List<Cloud> clouds;

    public Clouds() {
        cloudImage = Resource.getResourceImage("src/resources/cloud.PNG");
        clouds = new ArrayList<>();
        int j = 20;

        for (int i = 150; i < 800; i += 150, j += 10) {
            Cloud cloud1 = new Cloud();
            cloud1.posX = i;
            if (i % 100 != 0)
                cloud1.posY = 170 - j;

            clouds.add(cloud1);
        }
    }

    public void update() {
        for (Cloud cloud : clouds) {
            cloud.posX--;
        }
        Cloud firstCloud = clouds.get(0);
        if (firstCloud.posX + cloudImage.getWidth() < 0) {
            firstCloud.posX = 800;
            clouds.remove(firstCloud);
            clouds.add(firstCloud);
        }
    }

    public void draw(Graphics g) {
        for (Cloud cloud : clouds)
            g.drawImage(cloudImage, (int) cloud.posX, (int)cloud.posY, null);
    }

    private class Cloud {
        float posX;
        float posY;
    }

}
