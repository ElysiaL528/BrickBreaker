package com.company;

import java.awt.*;

/**
 * Created by ElysiaLopez on 6/11/2016.
 */
public class TeleportationBrick extends Brick{

    //int tlayers;
    int tbrickx;
    int tbricky;

    public TeleportationBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY, 5);
        Layers = layers;
        bricktype = BrickType.TeleportationBrick;
        tbrickx = x;
        tbricky = y;
    }
    @Override
    public void Label(Graphics g)
    {
        g.setColor(Color.YELLOW);

        if(Layers >= 1)
        {
            g.drawRect(X + 43, Y + 3, 33, 33);
            g.drawString(".", X + 46, Y + 10);
        }
        if(Layers >= 2)
        {
            g.drawString(".", X + 65, Y + 10);
        }
        if(Layers >= 3)
        {
            g.drawString(".", X + 55, Y + 20);
        }
        if(Layers >= 4) {
            g.drawString(".", X + 46, Y + 30);
        }
        if(Layers >= 5)
        {
            g.drawString(".", X + 65, Y + 30);
        }
        // g.drawString(String.format("%d", Layers), X, Y + 15);
        // g.drawString("☺☺☺☺☺", X + (Width / 2), Y + (Height / 2));
    }
    public void Update()
    {

    }
}
