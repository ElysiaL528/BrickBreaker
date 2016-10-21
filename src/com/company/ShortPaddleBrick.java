package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 4/22/2016.
 */
public class ShortPaddleBrick extends Brick{
    public ShortPaddleBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.ShortPaddleBrick;
    }
    @Override
    public void Label(Graphics g)
    {
        g.drawString(">--<", X + (Width/3), Y + (Height/2));
    }
}
