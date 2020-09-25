package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Animation {
    private List<BufferedImage> frames;
    private int frameindex = 0;

    public Animation() {
        frames = new ArrayList<BufferedImage>();
    }

    public void update() {
        frameindex++;
        if (frameindex >= frames.size())
            frameindex = 0;
    }

    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    public BufferedImage getFrame() {
        return frames.size() > 0 ? frames.get(frameindex) : null;
    }
}
