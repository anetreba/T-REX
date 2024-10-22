package world.ucode.objectgame;

import world.ucode.userinterface.GameScreen;
import world.ucode.utils.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static world.ucode.userinterface.GameScreen.GROUNDY;

public class Land {

    private List<ImageLand> listImage;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;

    public Land(GameScreen game) {
        random = new Random();
        imageLand1 = Resource.getResourceImage("src/resources/land1.png");
        imageLand2 = Resource.getResourceImage("src/resources/land2.png");
        imageLand3 = Resource.getResourceImage("src/resources/land3.png");
        listImage = new ArrayList<ImageLand>();
        int numberOfLandTitle = 800 / imageLand1.getWidth() + 2;
        for (int i = 0; i < numberOfLandTitle; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int) (i * imageLand1.getWidth());
            imageLand.image = getImageLand();
            listImage.add(imageLand);
        }

    }

    public void update(int score) {
        for (ImageLand imageLand : listImage) {
            if (score > 1100)
                imageLand.posX -= 10;
            else if (score > 600)
                imageLand.posX -= 8;
            else if (score < 100)
                imageLand.posX -= 4;
            else
                imageLand.posX -= 6;
        }
        ImageLand firstElement = listImage.get(0);

        if (listImage.get(0).posX + imageLand1.getWidth() < 0) {
            firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
            listImage.add(listImage.get(0));
            listImage.remove(0);
        }
    }

    public void draw(Graphics g) {
        for (ImageLand imageLand : listImage)
            g.drawImage(imageLand.image, imageLand.posX, (int)GROUNDY - 20, null);
    }

    private BufferedImage getImageLand() {
        int i = random.nextInt(4); // 0 -> 3 any number
        switch (i) {
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand {
        int posX;
        BufferedImage image;
    }

}
