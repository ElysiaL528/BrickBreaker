package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 2/19/2016.
 */
public class DisappearingBrick extends Brick{
    //6
    public DisappearingBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.DisappearingBrick;

    }

    @Override
    public void Label(Graphics g)
    {
        g.drawString("§", X + (Width/2), Y + (Height/2));
        //g.drawRect(X + (Width / 2), Y + (Height / 2), 10, 10);
    }

}
