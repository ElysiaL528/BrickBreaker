package com.company;


import sun.plugin.javascript.navig4.Layer;

import java.awt.*;

/**
 * Created by ElysiaLopez on 10/23/2015.
 */
public class Brick {

    //variables
    int X;
    int Y;
    int Width;
    int Height;
    int BounceYLow;
    int BounceYHigh;
    int Layers;
    public int BrickXspeed;
    public int BrickYspeed;
    Color color;

    public Rectangle Hitbox;
    public Rectangle TopHitbox;
    public BrickType bricktype;

    public int RowID;
    public int ColumnID;
    public int BrickGap = 0;

    //functions
    public Brick(int x, int y, int width, int height, int brickxspeed, int brickyspeed, Color tint , int LowBounceY, int HighBounceY, int layers) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        color = tint;
        BrickYspeed = brickyspeed;
        BrickXspeed = brickxspeed;
        Hitbox = new Rectangle(x, y, width, height);
        TopHitbox = new Rectangle(x, y, width, 1);
        BounceYLow = LowBounceY;
        BounceYHigh = HighBounceY;
        Layers = layers;
        bricktype = BrickType.Brick;

    }

    public void Draw(Graphics g)
    {
        g.setColor(color);
        g.drawRect(X, Y, Width, Height);
        Font font = new Font("Segoe WP Black", Font.PLAIN, 20);
        g.setFont(font);
    }

    public void Label(Graphics g)
    {
            g.drawString(String.format("%d", Layers), X + (Width / 2), Y + (Height / 2));
        }

    public void Update()
    {
        X += BrickXspeed;
        Y += BrickYspeed;

        Hitbox.x = X;
        Hitbox.y = Y;
        TopHitbox.x = X;
        TopHitbox.y = Y;
        TopHitbox.width = Width;
        TopHitbox.height = 1;

        if(X + Width + BrickGap >= 1000) {
            BrickXspeed *= -1;
            X = 1000 - BrickGap - Width;        //Reset the position to an exact known point to avoid columns disconnecting...
        }
        if(X <= 0)
        {
            BrickXspeed *= -1;
        }
        if(Y >= BounceYLow)
        {
            BrickYspeed *= -1;
        }
        if(Y <= BounceYHigh)
        {
            BrickYspeed *= -1;
        }
    }
}
