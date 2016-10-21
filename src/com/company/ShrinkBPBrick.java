package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 7/16/2016.
 */
public class ShrinkBPBrick extends Brick {
    public ShrinkBPBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.ShrinkBPBrick;
    }
    @Override
    public void Label(Graphics g)
    {
        g.drawOval(X, Y, Width, Height);
    }

}
