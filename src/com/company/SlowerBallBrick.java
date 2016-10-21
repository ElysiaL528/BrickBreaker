package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 4/15/2016.
 */
public class SlowerBallBrick extends Brick {
    public SlowerBallBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.SlowerBallBrick;
    }

    @Override
    public void Label(Graphics g)
    {
        g.drawString("slow", X + (Width/2), Y + (Height/2));
    }
}
