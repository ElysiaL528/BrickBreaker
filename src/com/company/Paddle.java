package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

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

    public boolean autoplay = false;
    public boolean MoveLeft;
    public boolean MoveRight;

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

        if(MoveLeft) {
            X -= Xspeed;
        }
        if(MoveRight)
        {
            X += Xspeed;
        }
        if (X + Width >= Main.ScreenWidth - 30) {
            X = 1000-Width-17;
        }

        if (X <= 0) {
            X = 2;
        }

    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            MoveLeft = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            MoveRight = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD9)
        {
            if(!autoplay) {
                autoplay = true;
            }
            else{
                autoplay = false;
            }
        }
    }

}
