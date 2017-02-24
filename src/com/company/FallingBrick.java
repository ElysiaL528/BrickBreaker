package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 2/19/2016.
 */
public class FallingBrick extends Brick{

    public FallingBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.FallingBrick;

    }

    @Override
    public void Label(Graphics g)
    {
        g.drawString("*", X + (Width/2), Y + (Height/2));
    }
}
