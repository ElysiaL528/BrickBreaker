package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ElysiaLopez on 2/17/2017.
 */
public class Paddle {
    public int X;
    public int Y;
    public int Width;
    public int Height;
    public int Xspeed = 15;

    public Rectangle Hitbox;

    public boolean autoplay;

    public Paddle(int x, int y, int width, int height)
    {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        Hitbox = new Rectangle(x, y, width, height);
    }

    public void Draw(Graphics g)
    {
        if(!autoplay) {
            g.setColor(Color.green);
            g.drawRect(X, Y, Width, Height);
        }
        else
        {
            g.setColor(Color.red);
            g.drawRect(X, Y, Width, Height);
        }
    }

    public void Update(Ball ball)
    {
        if(autoplay)
        {
            X = ball.X;
        }

    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            X -= Xspeed;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            X += Xspeed;
        }
    }

}
