package com.company;

import java.awt.*;
import java.util.Random;

/**
 * Created by ElysiaLopez on 7/10/2016.
 */
public class RandomBrick extends Brick {

    public BrickType TypeOfBrick;
    public boolean isDisappearing;
    public boolean isNormal;
    public boolean isHealth;
    public boolean isLongPaddle;
    public boolean isShortPaddle;


    public RandomBrick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint, int LowBounceY, int HighBounceY, int layers)
    {
        super(x,y,width,height, brickxspeed,brickyspeed,tint,LowBounceY,HighBounceY,2);

        //Random random = new Random();
        //bricktype = brickTypeArray[random.nextInt(brickTypeArray.length)];//BrickType.RandomBrick;
    }

    @Override
    public void Label(Graphics g)
    {
        if(Layers == 2) {
            g.drawString("█", X + (Width / 2), Y + (Height / 2));
            g.fillRect(X + (Width / 2), Y + (Height / 2), 10, 10);
        }
        else if(Layers == 1)
        {
            if(isDisappearing)
            {
                g.setColor(Color.CYAN);
                g.drawRect(X, Y, Width, Height);
                g.drawString("§", X + (Width/2), Y + (Height/2));
            }
            else if(isHealth)
            {
                g.setColor(Color.RED);
                g.drawRect(X, Y, Width, Height);
                g.fillRect(X+(Width/2) - Height/12, (Y + Height/4), Height/6, Height/2);
                g.fillRect(X + Width/2 - Height/12 - Height/6, Y + Height/4 + Height/6, Height/2, Height/6);
            }
            else if(isLongPaddle)
            {
                g.setColor(Color.BLUE);
                g.drawRect(X, Y, Width, Height);
                g.drawString("⟷", X + (Width/2), Y + (Height/2));
            }
            else if(isNormal)
            {
                g.setColor(Color.GREEN);
                g.drawRect(X, Y, Width, Height);
                g.drawString(String.format("%d", Layers), X + (Width / 2), Y + (Height / 2));
            }
            else if(isShortPaddle)
            {
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(X, Y, Width, Height);
                g.drawString(">--<", X + (Width/3), Y + (Height/2));
            }

        }
    }

    public void Update()
    {

    }
}
