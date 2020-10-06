package world.ucode.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Animation {
    private List<BufferedImage> frames;
    private int frameindex = 0;

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    private int deltaTime;
    private long priviousTime;

    public Animation(int deltaTime) {
        this.deltaTime = deltaTime;
        frames = new ArrayList<BufferedImage>();
    }

    public void update() {
        if (System.currentTimeMillis() - priviousTime > deltaTime) {
            frameindex++;
            if (frameindex >= frames.size())
                frameindex = 0;
            priviousTime = System.currentTimeMillis();
        }
    }

    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    public BufferedImage getFrame() {
        return frames.size() > 0 ? frames.get(frameindex) : null;
    }
}
