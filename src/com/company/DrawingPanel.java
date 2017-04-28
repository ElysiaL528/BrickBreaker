package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by ElysiaLopez on 10/9/2015.
 * TODO: Upside down mode? || Clean up & comment code || Bricks that shoot at the paddle
 * Bugs: Fix scrolling in level 18 (the top row shifts every time it meets the borders
 *
 * DONE
 * Pause function
 */


public class DrawingPanel extends JPanel implements ActionListener, KeyListener {


    int brickWidth = 120;
    int brickHeight = 40;
    int brickX = 0;
    int brickY = 0;
    Random gen = new Random();
    public int score = 0;
    int lives = 100;
    int level = 8;
    int layers = 1;

    boolean right = false;
    boolean left = false;
    boolean isonpaddle = true;
    boolean isSlow = false;
    boolean isFast = false;
    boolean isShort = false;
    boolean isLong = false;
    boolean autoplay = true;
    boolean RandomBLabel = false;
    boolean brickhit = false;
    boolean intersectingBrick = false;
    boolean FinishedLevel = false;
    boolean isPaused = false;

    int specialCount = 0;

    int TeleportYmax = 200;
    int TeleportYmin = 0;

    int TeleportXmax = 925;
    int TeleportXmin = 0;




    ArrayList<Brick> bricks = new ArrayList<Brick>();

    //ArrayList<Brick> IndestructableBrick = new ArrayList<Brick>();

    Brick exmaple = new IndestructableBrick(0, 0, 120, 40, 0, 0, Color.GREEN, 43, 0, 10);

    Ball ball = new Ball(500, 350, 50, 50);
    Paddle paddle = new Paddle(450, 400, 150, 15);


    //Rectangle paddleHitbox = new Rectangle(PaddleX, PaddleY, paddleWidth, paddleHeight);
    //Rectangle ballHitbox = new Rectangle(ballX, ballY, ballWidth, ballHeight);

    //Color[] columnColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.PINK, Color.white};
    Color[] columnColors = {Color.green, Color.green, Color.green, Color.green, Color.green, Color.green, Color.green};

    int randomNum;

    public DrawingPanel() {

        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(16, this);

        Reset();

        //test = new Brick(brickX,brickY,120,40,Color.GREEN);

        /*

       ;

        brick.clear();
        */


        timer.start();
        Main.TeleportWaitTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.WaitToTeleport = !Main.WaitToTeleport;
            }
        });


    }

    public void Reset() {
        brickX = 0;
        brickY = 0;
        //ispowerball = false;
        specialCount = 0;
        paddle.Width = 150;
        ball.Yspeed = 10;
        ball.Xspeed = 10;
        FinishedLevel = false;

        paddle.Hitbox.setSize(paddle.Width, paddle.Height);

        if (level == -1) {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 7; col++) {
                    Brick newBrick = new RandomBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 300, 0, 0);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;

                    bricks.add(newBrick);
                    brickX += brickWidth;   //Move by one brick's width
                    brickX += xBrickGap;            //"little gap"
                    specialCount++;
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }

        }
        if (level == 1) {
            score = 0;
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 8; i++) {
                    bricks.add(new Brick(brickX, brickY, 120, 40, 0, 0, Color.GREEN, 43, 0, 1));
                    brickX += 123;
                }
                brickY += 43;
                brickX = 0;
            }
        } else if (level == 2) {
            score = 16;
            for (int j = 0; j < 6; j++) {

                for (int i = 0; i < 8; i++) {
                    bricks.add(new Brick(brickX, brickY, 120, 40, 0, 0, Color.GREEN, 43, 0, 1));
                    brickX += 123;
                }
                brickY += 43;
                brickX = 0;
            }
        } else if (level == 3) {
            score = 64;
            ball.Width = 50;
            ball.Height = 50;
            ball.Hitbox.width = ball.Width;
            ball.Hitbox.height = ball.Height;
            paddle.Height = 15;
            paddle.Width = 150;
            paddle.Hitbox.width = paddle.Width;
            paddle.Hitbox.height = paddle.Height;
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 8; i++) {
                    bricks.add(new ShrinkBPBrick(brickX, brickY, 120, 40, 0, 0, Color.GREEN, 43, 0, 1));
                    brickX += 123;
                }
                brickY += 43;
                brickX = 0;
            }
        } else if (level == 4) {
            score = 96;
            ball.Width = 60;
            ball.Height = 60;
            ball.Hitbox.width = ball.Width;
            ball.Hitbox.height = ball.Height;
            paddle.Height = 15;
            paddle.Width = 150;
            paddle.Hitbox.width = paddle.Width;
            paddle.Hitbox.height = paddle.Height;
            for (int j = 0; j < 1; j++) {
                for (int i = 0; i < 8; i++) {
                    bricks.add(new ShrinkBPBrick(brickX, brickY, 120, 40, 0, 0, Color.GREEN, 43, 0, 1));
                    brickX += 123;
                }
                brickY += 43;
                brickX = 0;
            }
        } else if (level == 5) {
            score = 104;
            ball.Width = 50;
            ball.Height = 50;
            ball.Hitbox.width = ball.Width;
            ball.Hitbox.height = ball.Height;
            paddle.Height = 15;
            paddle.Width = 150;
            paddle.Hitbox.width = paddle.Width;
            paddle.Hitbox.height = paddle.Height;
            int xSpeed = 5;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 5;


            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 5; col++) {
                    Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 43, 0, 1);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;

                    bricks.add(newBrick);
                    brickX += brickWidth;   //Move by one brick's width
                    brickX += xBrickGap;            //"little gap"
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }
        } else if (level == 6) {
            score = 134;
            int xSpeed = 10;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 5;

            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 5; col++) {
                    Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 43, 0, 1);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;

                    bricks.add(newBrick);
                    brickX += brickWidth;   //Move by one brick's width
                    brickX += xBrickGap;            //"little gap"
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }
        } else if (level == 7) {
            score = 164;
            int xSpeed = 2;
            int ySpeed = 2;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 7; col++) {
                    Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 300, 0, 1);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;

                    bricks.add(newBrick);
                    brickX += brickWidth;   //Move by one brick's width
                    brickX += xBrickGap;            //"little gap"
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }
        } else if (level == 8) {
            score = 185;
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 7; col++) {
                    Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 0, 0, 5);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;
                    if (newBrick.RowID == 0 && newBrick.ColumnID == 0) {
                        newBrick = new PowerupBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.magenta, 0, 0, 5);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;
                    }
                    bricks.add(newBrick);
                    brickX += brickWidth;   //Move by one brick's width
                    brickX += xBrickGap;            //"little gap"
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
                layers = 7;
            }
        } else if (level == 9) {
            score = 285;
            int xSpeed = 3;
            int ySpeed = 5;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {
                    if (row < 3) {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, columnColors[col], 0, 300, 8);
                        if (row == 0 && col == 0) {
                            newBrick = new PowerupBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.magenta, 0, 300, 8);
                        }
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;


                        brickX += brickWidth;   //Move by one brick's width
                        brickX += xBrickGap;            //"little gap"


                        bricks.add(newBrick);
                    }

                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
                layers = 7;
            }
        } else if (level == 10) {
            score = 446;
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {

                    if (row == 3) {
                        Brick newIBrick = new IndestructableBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.orange, 0, 0, -1);
                        bricks.add(newIBrick);
                        brickX += brickWidth;
                        brickX += xBrickGap;
                        specialCount++;
                    } else {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.green, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;

            }
        } else if (level == 11) {
            Main.disappearTimer =  new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.disappear = !Main.disappear;
                }
            });
            score = 657;
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {

                    if (row == 3 && col <= 3) {
                        Brick newDisappearingBrick = new DisappearingBrick(brickX, brickY, brickWidth, brickHeight, 20, ySpeed, Color.CYAN, 0, 0, -1);
                        bricks.add(newDisappearingBrick);
                        brickX += brickWidth;
                        brickX += xBrickGap;
                    } else if (row <= 2) {
                        Brick newBrick = new HealthBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.red, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;

            }
            Main.disappearTimer.start();
        }
        else if (level == 12) {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {

                    if (row == 3) {
                        Brick newIBrick = new SlowerBallBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.gray, 0, 0, -1);
                        bricks.add(newIBrick);
                        brickX += brickWidth;
                        brickX += xBrickGap;
                    } else {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.GREEN, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;

            }
        }
        else if(level == 13)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {

                    if (row == 3) {
                        Brick newIBrick = new ShortPaddleBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.gray, 0, 0, -1);
                        bricks.add(newIBrick);
                        brickX += brickWidth;
                        brickX += xBrickGap;
                    } else {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.GREEN, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;

            }
        }
        else if(level == 14)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 7; col++) {

                    if (row == 3) {
                        Brick newIBrick = new LongPaddleBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.BLUE, 0, 0, -1);
                        bricks.add(newIBrick);
                        brickX += brickWidth;
                        brickX += xBrickGap;
                    } else {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.GREEN, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;

            }
        }
        if(level == 15)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;
            int brickx = 500;
            int brickY = 0;

            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 7; col++) {

                    Brick newBrick = new TeleportationBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.green, 0, 0, 5);
                    newBrick.RowID = row;
                    newBrick.ColumnID = col;
                    newBrick.BrickGap = xBrickGap;

                    brickX += brickWidth;
                    brickX += xBrickGap;

                    bricks.add(newBrick);
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }

            //Main.TeleportTimer.start();
            Main.TeleportWaitTimer.start();
        }
        if(level == 16)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;
            int brickx = 500;
            int brickY = 0;

            BrickType[] brickTypeArray = {BrickType.DisappearingBrick, BrickType.HealthBrick, BrickType.Brick, BrickType.LongPaddleBrick, BrickType.ShortPaddleBrick};

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 7; col++) {

                    if(row == 0 && col == 0) {
                        Brick newBrick = new PowerupBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.green, 0, 0, 1);
                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);
                    }
                    else {
                        Random random = new Random();


                        RandomBrick newBrick = new RandomBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.WHITE, 0, 0, 1);

                        newBrick.bricktype = brickTypeArray[random.nextInt(brickTypeArray.length)];

                        if (newBrick.bricktype == BrickType.DisappearingBrick)
                        {
                            newBrick.isDisappearing = true;
                        }
                        else
                        {
                            newBrick.isDisappearing = false;
                        }

                        if (newBrick.bricktype == BrickType.HealthBrick)
                        {
                            newBrick.isHealth = true;
                        }
                        else
                        {
                            newBrick.isHealth = false;
                        }

                        if (newBrick.bricktype == BrickType.ShortPaddleBrick)
                        {
                            newBrick.isShortPaddle = true;
                        }
                        else
                        {
                            newBrick.isShortPaddle = false;
                        }

                        if (newBrick.bricktype == BrickType.LongPaddleBrick)
                        {
                            newBrick.isLongPaddle = true;
                        }
                        else
                        {
                            newBrick.isLongPaddle = false;
                        }

                        if (newBrick.bricktype == BrickType.Brick)
                        {
                            newBrick.isNormal = true;
                        }
                        else
                        {
                            newBrick.isNormal = false;
                        }

                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth;
                        brickX += xBrickGap;

                        bricks.add(newBrick);

                    }
                    }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }
        if(level == 17)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;
            int brickx = 500;
            int brickY = 0;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 7; col++) {

                    if(row == 0) {
                        Brick newBrick = new FasterBallBrick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.red, 0, 0, 1);


                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth + xBrickGap;

                        bricks.add(newBrick);
                    }
                    else{
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.green, 0, 0, 1);

                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth + xBrickGap;

                        bricks.add(newBrick);
                    }

                    }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
                }

            }

        if(level == 18)
        {
            int xSpeed = 0;
            int ySpeed = 0;
            int brickWidth = 120;
            int brickHeight = 40;
            int xBrickGap = 10;
            int yBrickGap = 10;
            int brickx = 500;
            int brickY = 0;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 7; col++) {
                        Brick newBrick = new Brick(brickX, brickY, brickWidth, brickHeight, xSpeed, ySpeed, Color.green, 0, 0, 1);

                        newBrick.RowID = row;
                        newBrick.ColumnID = col;
                        newBrick.BrickGap = xBrickGap;

                        brickX += brickWidth + xBrickGap;

                    bricks.add(newBrick);
                }
                brickY += brickHeight + yBrickGap;
                brickX = 0;
            }


        }
    }



//specialCount = 0;


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Update();
    }


    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.green);

        ball.Draw(g);
        paddle.Draw(g);
        for (int i = 0; i < bricks.size(); i++) {
            if(!isPaused) {
                if (level == 11 || level == 16) {

                    if (bricks.get(i).bricktype == BrickType.DisappearingBrick) {
                        if (!Main.disappear) {
                            bricks.get(i).Draw(g);
                            bricks.get(i).Label(g);
                        }
                    } else {
                        bricks.get(i).Draw(g);
                        bricks.get(i).Label(g);
                    }
                } else {
                    bricks.get(i).Draw(g);
                    bricks.get(i).Label(g);
                }
                if (level == 15) {

                    if (bricks.get(i).bricktype == BrickType.TeleportationBrick) {
                        if (!Main.WaitToTeleport) {
                            for (int j = 0; j < bricks.size(); j++) {
                                randomNum = gen.nextInt(7) * 130;
                                bricks.get(j).X = randomNum;
                                bricks.get(j).TopHitbox.x = randomNum;
                                bricks.get(j).Hitbox.x = randomNum;
                                randomNum = gen.nextInt(6) * 50;
                                bricks.get(j).Y = randomNum;
                                bricks.get(j).TopHitbox.y = randomNum;
                                bricks.get(j).Hitbox.y = randomNum;

                            }
                            Main.WaitToTeleport = true;
                        }
                    }
                }
            }
        }

        g.setColor(Color.gray);
        Font font = new Font("Quartz MS", Font.PLAIN, 100);
        g.setFont(font);
        g.setColor(Color.green);
        if (lives > 0 && !bricks.isEmpty()) {
            if (bricks.size() > specialCount) {
                g.drawString(String.format("%d", score), 400, 330);
                if(isPaused) {
                    g.setColor(Color.red);
                    font = new Font("Quartz MS", Font.PLAIN, 30);
                    g.setFont(font);
                    g.drawString("PAUSED", 450, 50);
                }
            }
        }
        g.setColor(Color.green);
        font = new Font("Quartz MS", Font.PLAIN, 100);
        g.setFont(font);
        if (bricks.isEmpty() || bricks.size() == specialCount) {
            isonpaddle = true;
            FinishedLevel = true;

            font = new Font("Quartz MS", Font.PLAIN, 100);
            g.setFont(font);
            g.drawString("You Won!", 280, 270);
            font = new Font("Calibri", Font.PLAIN, 30);
            g.setFont(font);
            g.drawString("Press Enter to Continue", 280, 295);

        } else if (lives == 0) {
            isonpaddle = true;
            font = new Font("Quartz MS", Font.PLAIN, 100);
            g.setFont(font);
            g.drawString("You Lose!", 280, 270);
        }


        font = new Font("Quartz MS", Font.PLAIN, 30);
        g.setFont(font);

        g.drawString(String.format("Lives:%d", lives), 0, 450);
        g.drawString(String.format("Level:%d", level), 800, 450);
        g.drawString(String.format("Ball speed: %d, %d", ball.Xspeed, ball.Yspeed), 300, 450);
    }

    public void Update() {

        //moving
        if(!isPaused) {
           if (right) {
                paddle.X += paddle.Xspeed;
                if(level == 18)
                {
                    for(int i = 0; i < bricks.size(); i++)
                    {
                        if(bricks.get(6).X + brickWidth< Main.ScreenWidth - 10)
                        {
                            bricks.get(i).X += paddle.Xspeed;
                        }
                    }
                }
            }
            if (left) {
                paddle.X -= paddle.Xspeed;
                if(level == 18)
                {
                    for(int i = 0; i < bricks.size(); i++)
                    {
                        if(bricks.get(0).X > 0)
                        {
                            bricks.get(i).X -= paddle.Xspeed;
                        }
                    }
                }
            }

            //NEW GAME MODE??

            /*if(right)
            {
                paddle.MoveRight = true;
                paddle.MoveLeft = false;
            }
            if(left)
            {
                paddle.MoveLeft = true;
                paddle.MoveRight = false;
            }*/

            if (isonpaddle) {
                ball.X = paddle.X + (paddle.Width / 2) - (ball.Width / 2);
                ball.Y = paddle.Y - ball.Height;
            } else if (!isonpaddle) {
                ball.X += ball.Xspeed;
                ball.Y += ball.Yspeed;
            }
        }
        ball.Hitbox.setLocation(ball.X, ball.Y);
        paddle.Hitbox.x = paddle.X;

        if (ball.X >= Main.ScreenWidth - 50) {
            ball.Xspeed = Math.abs(ball.Xspeed) * -1;
        }
        if (ball.X <= 0) {
            ball.Xspeed = Math.abs(ball.Xspeed);
        }
        if (ball.Y >= 425) {
            //ballYspeed = Math.abs(ballYspeed) * -1;
            isonpaddle = true;
            lives--;
            ball.ispowerball = false;
            isSlow = false;
            isShort = false;
            isLong = false;
            isFast = false;
            paddle.Width = 150;
            paddle.Hitbox.setSize(paddle.Width, paddle.Height);
        }

        if (ball.Y <= 0) {
            ball.Yspeed = Math.abs(ball.Yspeed);
        }

        if (ball.Hitbox.intersects(paddle.Hitbox)) {
            ball.Yspeed = -Math.abs(ball.Yspeed);
        }


        for(Brick brick : bricks)
        {
            brick.Update();
        }

        for (int i = 0; i < bricks.size(); i++) {

            if (ball.Hitbox.intersects(bricks.get(i).Hitbox))
            {

                if(!bricks.get(i).intersectingBall) {
                    bricks.get(i).Layers--;
                }
                bricks.get(i).intersectingBall = true;
                if (!ball.ispowerball) {
                    if(ball.Hitbox.intersects(bricks.get(i).TopHitbox))
                    {
                        ball.Yspeed = -Math.abs(ball.Yspeed);
                    }
                    else {
                        ball.Yspeed = Math.abs(ball.Yspeed);
                    }
                }


                if(bricks.get(i).bricktype == BrickType.SlowerBallBrick)
                {
                    if(ball.Xspeed >= 1 && ball.Yspeed >= 1) {
                        ball.Xspeed /= 2;
                        ball.Yspeed /= 2;

                        isSlow = true;
                    }
                }

                if(bricks.get(i).bricktype == BrickType.FasterBallBrick)
                {
                    int speedIncrement = 2;
                    if(Math.abs(ball.Xspeed) <= 20 && Math.abs(ball.Yspeed) <= 20)
                    {
                        if(paddle.Xspeed >= 0) {
                            paddle.Xspeed += speedIncrement;
                        }
                        else
                        {
                            paddle.Xspeed -= speedIncrement;
                        }

                        if(ball.Xspeed >= 0) {
                            ball.Xspeed += speedIncrement;
                        }
                        else
                        {
                            ball.Xspeed -= speedIncrement;
                        }

                        if(ball.Yspeed >= 0)
                        {
                            ball.Yspeed += speedIncrement;
                        }
                        else
                        {
                            ball.Yspeed -= speedIncrement;
                        }
                    }
                }

                if(bricks.get(i).bricktype == BrickType.ShortPaddleBrick && paddle.Width >= 51)
                {
                    paddle.Width *= 0.9;
                    paddle.Hitbox.setSize(paddle.Width, paddle.Height);
                }
                if(bricks.get(i).bricktype == BrickType.FasterBall)
                {

                }
                if(bricks.get(i).bricktype == BrickType.LongPaddleBrick && paddle.Width >= 200)
                {
                    paddle.Width *= 1.1;
                    paddle.Hitbox.setSize(paddle.Width, paddle.Height);
                    isLong = true;
                }

                if(bricks.get(i).bricktype == BrickType.PowerupBrick)
                {
                    ball.ispowerball = true;
                }

                if(bricks.get(i).bricktype == BrickType.HealthBrick)
                {
                    lives+=5;
                    //specialCount++;
                }
                if (/*level == 3 || level == 4*/ bricks.get(i).bricktype == BrickType.ShrinkBPBrick) {
                    ball.Width--;
                    ball.Height--;
                    ball.Hitbox.width--;
                    ball.Hitbox.height--;
                    paddle.Width -= 2;
                    paddle.Hitbox.width -= 2;
                }
                if (bricks.get(i).Layers <= 0 && bricks.get(i).bricktype != BrickType.IndestructableBrick)
                {
                    bricks.remove(i);
                    score++;
                    i--;
                }

            }
            else
            {
                bricks.get(i).intersectingBall = false;
            }
        }

        paddle.Update(ball);
        repaint();
    }

    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if (left) {
                ball.Xspeed = -Math.abs(ball.Xspeed);
            }
            if (right) {
                ball.Xspeed = Math.abs(ball.Xspeed);
            }
            isonpaddle = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (FinishedLevel) {
                bricks.clear();
                level++;
                lives += 5;
                specialCount = 0;
                Reset();
            }

               /* if(bricks.size() == specialCount) {
                    //you win if you have ibricks
                    level++;
                    lives+=5;
                    Reset();

            }*/
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            if(ball.ispowerball)
            {
                ball.ispowerball = false;
            }
            else
            {
                ball.ispowerball = true;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_P)
        {
            if(isPaused)
            isPaused = false;

            else
            isPaused = true;

        }

        checkShortcutKeys(e);
        paddle.keyPressed(e);

    }

    private void checkShortcutKeys(KeyEvent e) {
        HashMap<Integer, Integer> quickAccessKeys = new HashMap<>();
        quickAccessKeys.put(KeyEvent.VK_ALT, -1);
        quickAccessKeys.put(KeyEvent.VK_R, 1);
        quickAccessKeys.put(KeyEvent.VK_1, 1);
        quickAccessKeys.put(KeyEvent.VK_2, 2);
        quickAccessKeys.put(KeyEvent.VK_3, 3);
        quickAccessKeys.put(KeyEvent.VK_4, 4);
        quickAccessKeys.put(KeyEvent.VK_5, 5);
        quickAccessKeys.put(KeyEvent.VK_6, 6);
        quickAccessKeys.put(KeyEvent.VK_7, 7);
        quickAccessKeys.put(KeyEvent.VK_8, 8);
        quickAccessKeys.put(KeyEvent.VK_9, 9);
        quickAccessKeys.put(KeyEvent.VK_0, 10);
        quickAccessKeys.put(KeyEvent.VK_Q, 11);
        quickAccessKeys.put(KeyEvent.VK_W, 12);
        quickAccessKeys.put(KeyEvent.VK_E, 13);
        quickAccessKeys.put(KeyEvent.VK_T, 14);
        quickAccessKeys.put(KeyEvent.VK_Y, 15);
        quickAccessKeys.put(KeyEvent.VK_U, 16);
        quickAccessKeys.put(KeyEvent.VK_I, 17);
        quickAccessKeys.put(KeyEvent.VK_O, 18);
        quickAccessKeys.put(KeyEvent.VK_A, 19);

        if(quickAccessKeys.containsKey(e.getKeyCode()))
        {
            bricks.clear();
            level = quickAccessKeys.get(e.getKeyCode());
            isonpaddle = true;
            Reset();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }


}
