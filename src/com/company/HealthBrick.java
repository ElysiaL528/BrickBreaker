package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 4/1/2016.
 */
public class HealthBrick extends Brick{
    //8
    public HealthBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.HealthBrick;

    }

    @Override
    public void Label(Graphics g)
    {
        g.fillRect(X+(Width/2) - Height/12, (Y + Height/4), Height/6, Height/2);
        g.fillRect(X + Width/2 - Height/12 - Height/6, Y + Height/4 + Height/6, Height/2, Height/6);


    }

}
