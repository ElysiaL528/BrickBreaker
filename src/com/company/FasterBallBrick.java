package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 4/15/2016.
 */
public class FasterBallBrick extends Brick {
    public FasterBallBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.FasterBallBrick;

    }
    @Override
    public void Label(Graphics g)
    {
        g.drawString("",X+(Width/2), Y+(Width/2));
    }
}
