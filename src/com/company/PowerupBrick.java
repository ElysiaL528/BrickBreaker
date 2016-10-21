package com.company;


import java.awt.*;
/**
 * Created by ElysiaLopez on 2/12/2016.
 */
public class PowerupBrick extends Brick {
    //5
    public PowerupBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,1);
        bricktype = BrickType.PowerupBrick;
    }


   @Override
    public void Label(Graphics g)
   {
       g.drawOval(X + (Width/2), Y + (Height/2), 10, 10);
          // g.drawString("☺☺☺☺☺", X + (Width / 2), Y + (Height / 2));
   }
}
