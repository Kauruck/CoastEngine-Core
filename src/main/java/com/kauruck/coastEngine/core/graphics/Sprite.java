package com.kauruck.coastEngine.core.graphics;

import com.kauruck.coastEngine.core.math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite extends SpriteBase{

    protected Vector2 position;
    protected Vector2 center;
    protected BufferedImage cache = null;

    public void translate(Vector2 position){
        if(!position.equals(position)){
            this.position = position;
            recach();
        }
    }

    @Override
    public void draw(Graphics g) {
        if(cache == null)
            recach();
        g.drawImage(cache, (int)position.getX(), (int)position.getY(), null);
    }

    protected void recach(){
        cache = image;
    }


}
