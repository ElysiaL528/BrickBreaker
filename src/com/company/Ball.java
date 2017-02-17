package com.company;

import java.awt.*;
/**
 * Created by ElysiaLopez on 2/10/2017.
 */
public class Ball {

    public int X;
    public int Y;
    public int Width;
    public int Height;
    public int Xspeed;
    public int Yspeed;

    public Rectangle Hitbox;

    public boolean BallIntersectingBrick = false;
    public boolean ispowerball = false;

    public Ball(int x, int y, int width, int height)
    {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        Hitbox = new Rectangle(X, Y, Width, Height);
    }

    public void Draw(Graphics g)
    {
        if(ispowerball) {
            g.fillOval(X, Y, Width, Height);
        }
        else{
            g.drawOval(X, Y, Width, Height);
        }
    }

    public void Update()
    {
        X += Xspeed;
        Y += Yspeed;

        Hitbox.setLocation(X, Y);

        //If the ball intersects with the walls

        if(Y <= 0)
        {
            Y = Math.abs(Y);
        }

        if(Y >= Main.ScreenHeight - Height)
        {
            Y = -Math.abs(Y);
        }

        if(X <= 0)
        {
            X = Math.abs(X);
        }

        if(X > Main.ScreenWidth + Width)
        {
            X = -Math.abs(X);
        }
        //
    }
}
