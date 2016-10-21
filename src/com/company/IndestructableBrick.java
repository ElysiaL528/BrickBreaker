package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 2/5/2016.
 */
public class IndestructableBrick extends Brick {


    //4
    public IndestructableBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers) {
        super(x, y, width, height, brickxspeed, brickyspeed, tint, LowBounceY, HighBounceY, -1);
        bricktype = BrickType.IndestructableBrick;
    }

    @Override
    public void Label(Graphics g) {
        g.fillRect(X, Y, Width, Height);
        //g.drawString("☺☺☺☺", X + (Width/2), Y + (Height/2));
    }

}
