package com.kauruck.coastEngine.core.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class LayerdSprite extends Sprite{

    private final BufferedImage[] layers;
    private int maxWidth = 0;
    private int maxHeight = 0;


    public LayerdSprite(int layerCount){
        layers =  new BufferedImage[layerCount];
    }

    public BufferedImage getLayer(int index){
        return layers[index];
    }

    public void setLayer(int index, BufferedImage value){
        layers[index] = value;
        if(value.getWidth() > maxWidth)
            maxWidth = value.getWidth();
        if(value.getHeight() > maxHeight)
            maxHeight = value.getHeight();
    }

    @Override
    protected void recach(){
        Graphics imgG = cache.getGraphics();
        for(BufferedImage current : layers){
            imgG.drawImage(current,0,0,null);
        }
        super.recach();
    }
}
