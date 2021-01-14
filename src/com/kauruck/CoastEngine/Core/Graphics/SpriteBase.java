package com.kauruck.CoastEngine.Core.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class SpriteBase {

    protected int width;
    protected int height;

    protected BufferedImage image;

    public void draw(Graphics g){
        g.drawImage(image,0,0,null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
