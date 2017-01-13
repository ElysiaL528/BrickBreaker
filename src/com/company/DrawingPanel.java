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
 * TODO: Create Trampoline - Lava - Random Bricks || Scrolling Map || Be able to control where ball is launched || Work on pause function || Upside down mode
 * Bugs:
 * Make a ball class
 * The ball doesn't bounce when it hits the brick from the top
 * Special brick bug
 * Fix slow brick bug
 * Program faster ball brick
 * The powerball shouldn't destroy all layers
 */


public class DrawingPanel extends JPanel implements ActionListener, KeyListener {

    int x = 450;
    int y = 400;
    int ballX = 500;
    int ballY = 350;
    int ballXspeed = 10;
    int ballYspeed = 10;
    int ballHeight = 50;
    int ballWidth = 50;
    int paddleXspeed = 15;
    int paddleHeight = 15;
    int paddleWidth = 150;
    int brickWidth = 120;
    int brickHeight = 40;
    int brickX = 0;
    int brickY = 0;
    Random gen = new Random();
    public int score = 0;
    int lives = 100;
    int level = 9;
    int layers = 1;

    boolean right = false;
    boolean left = false;
    boolean isonpaddle = true;
    boolean isSlow = false;
    boolean isFast = false;
    boolean isShort = false;
    boolean isLong = false;
    boolean ispowerball = false;
    boolean autoplay = true;
    boolean RandomBLabel = false;
    boolean brickhit = false;
    boolean intersectingBrick = false;

    int specialCount = 0;

    int TeleportYmax = 200;
    int TeleportYmin = 0;

    int TeleportXmax = 925;
    int TeleportXmin = 0;




    ArrayList<Brick> bricks = new ArrayList<Brick>();

    //ArrayList<Brick> IndestructableBrick = new ArrayList<Brick>();

    Brick exmaple = new IndestructableBrick(0, 0, 120, 40, 0, 0, Color.GREEN, 43, 0, 10);


    Rectangle paddleHitbox = new Rectangle(x, y, paddleWidth, paddleHeight);
    Rectangle ballHitbox = new Rectangle(ballX, ballY, ballWidth, ballHeight);

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
    public void Pause()
    {

    }
    public void Reset() {
        brickX = 0;
        brickY = 0;
        ispowerball = false;
        specialCount = 0;
        paddleWidth = 150;
        paddleHitbox.setSize(paddleWidth, paddleHeight);

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
            ballWidth = 50;
            ballHeight = 50;
            ballHitbox.width = ballWidth;
            ballHitbox.height = ballHeight;
            paddleHeight = 15;
            paddleWidth = 150;
            paddleHitbox.width = paddleWidth;
            paddleHitbox.height = paddleHeight;
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
            ballWidth = 60;
            ballHeight = 60;
            ballHitbox.width = ballWidth;
            ballHitbox.height = ballHeight;
            paddleHeight = 15;
            paddleWidth = 150;
            paddleHitbox.width = paddleWidth;
            paddleHitbox.height = paddleHeight;
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
            ballWidth = 50;
            ballHeight = 50;
            ballHitbox.width = ballWidth;
            ballHitbox.height = ballHeight;
            paddleHeight = 15;
            paddleWidth = 150;
            paddleHitbox.width = paddleWidth;
            paddleHitbox.height = paddleHeight;
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
                            specialCount++;
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
                        specialCount++;
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
                        specialCount++;
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
                        specialCount++;
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
                        specialCount++;
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
        if (!ispowerball) {
            g.drawOval(ballX, ballY, ballWidth, ballHeight);
            g.drawOval(ballHitbox.x, ballHitbox.y, ballHitbox.width, ballHitbox.height);
        } else{
            g.fillOval(ballX, ballY, ballWidth, ballHeight);
        }
        if(!autoplay) {
            g.setColor(Color.GREEN);
            g.drawRect(x, y, paddleWidth, paddleHeight);
            g.drawRect(paddleHitbox.x, paddleHitbox.y, paddleHitbox.width, paddleHitbox.height);
        }
        else{
            g.setColor(Color.red);
            g.drawRect(x, y, paddleWidth, paddleHeight);
            g.drawRect(paddleHitbox.x, paddleHitbox.y, paddleHitbox.width, paddleHitbox.height);
        }
        //test.Draw(g);
        for (int i = 0; i < bricks.size(); i++) {
            if(level == 11)
            {

                    if(bricks.get(i).bricktype == BrickType.DisappearingBrick) {
                        if(!Main.disappear) {
                            bricks.get(i).Draw(g);
                            bricks.get(i).Label(g);
                        }
                    }
                else {
                        bricks.get(i).Draw(g);
                        bricks.get(i).Label(g);
                    }
            }
            else {
                bricks.get(i).Draw(g);
                bricks.get(i).Label(g);
            }
            if(level == 15)
            {

                if(bricks.get(i).bricktype == BrickType.TeleportationBrick) {
                    if (!Main.WaitToTeleport) {
                        for (int j = 0; j < bricks.size(); j++) {
                            randomNum = gen.nextInt(7) * 130;
                            bricks.get(j).X = randomNum;
                            bricks.get(j).Hitbox.x = randomNum;
                            randomNum = gen.nextInt(6) * 50;
                            bricks.get(j).Y = randomNum;
                            bricks.get(j).Hitbox.y = randomNum;

                        }
                        Main.WaitToTeleport = true;
                    }
                }
            }

            /*if(ballHitbox.intersects(bricks.get(i).Hitbox) && bricks.get(i).bricktype == BrickType.TeleportationBrick)
            {
                bricks.get(i).Label(g);
            }*/
        }

        g.setColor(Color.gray);
        Font font = new Font("Quartz MS", Font.PLAIN, 100);
        g.setFont(font);
        g.setColor(Color.green);
        if (lives > 0 && !bricks.isEmpty()) {
            if (bricks.size() > specialCount) {
                g.setColor(Color.green);
                g.drawString(String.format("%d", score), 450, 330);

            }
        }
        if (bricks.isEmpty() || bricks.size() == specialCount) {
            isonpaddle = true;

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
        g.drawString(String.format("Special Count: %d, %d", specialCount, bricks.size()), 300, 450);
    }

    public void Update() {

        //moving

        if (right) {
            x += paddleXspeed;
        }
        if (left) {
            x -= paddleXspeed;
        }
        if (isonpaddle) {
            ballX = x + (paddleWidth / 2) - (ballWidth / 2);
            ballY = y - ballHeight;
        }
        /*if(isonpaddle == false)
        {
            ballX -=10;
            ballY -=10;

        }
        if(isonpaddle)
        {
            ballX += 50;
        }
        if(isonpaddle)
        {
            ballX -= 50;
        }*/

        else if (!isonpaddle) {
            ballX += ballXspeed;
            ballY += ballYspeed;
        }

        ballHitbox.setLocation(ballX, ballY);
        paddleHitbox.x = x;
        /*
        if(isonpaddle == true)
        {
            ballXspeed = 0;
            ballYspeed = 0;

        }

        else if(isonpaddle == false)
        {
            ballXspeed = 1;
            ballYspeed = -1;
        }
        */
        if(autoplay)
        {
            x = ballX;


        }
        if (ballX >= 950) {
            ballXspeed = Math.abs(ballXspeed) * -1;
        }
        if (ballX <= 0) {
            ballXspeed = Math.abs(ballXspeed);
        }
        if (ballY >= 425) {
            //ballYspeed = Math.abs(ballYspeed) * -1;
            isonpaddle = true;
            lives--;
            ispowerball = false;
            isSlow = false;
            isShort = false;
            isLong = false;
            isFast = false;
            paddleWidth = 150;
            paddleHitbox.setSize(paddleWidth, paddleHeight);
        }

        if (ballY <= 0) {
            ballYspeed = Math.abs(ballYspeed);
        }

        if (x + paddleWidth >= 970) {
            x = 1000-paddleWidth-17;
        }

        if (x <= 0) {
            x = 2;
        }
        if (ballHitbox.intersects(paddleHitbox)) {
            if(!isSlow) {
                ballYspeed = -10;
            }
            else if(isSlow)
            {
                ballYspeed = -5;
            }

        }

        for(Brick brick : bricks)
        {
            brick.Update();
        }

        for (int i = 0; i < bricks.size(); i++) {

            if (ballHitbox.intersects(bricks.get(i).Hitbox) && !intersectingBrick)
            {

                bricks.get(i).Layers--;

                if (!ispowerball) {
                    if(ballHitbox.intersects(bricks.get(i).TopHitbox))
                    {
                        ballYspeed = -Math.abs(ballYspeed);
                    }
                    else {
                        ballYspeed = Math.abs(ballYspeed);
                    }
                }

                if(bricks.get(i).bricktype == BrickType.SlowerBallBrick)
                {
                    ballXspeed /= 2;
                    ballYspeed /= 2;

                    isSlow = true;
                }

                if(bricks.get(i).bricktype == BrickType.ShortPaddleBrick && paddleWidth >= 51)
                {
                    paddleWidth *= 0.9;
                    paddleHitbox.setSize(paddleWidth, paddleHeight);
                }
                if(bricks.get(i).bricktype == BrickType.FasterBall)
                {

                }
                if(bricks.get(i).bricktype == BrickType.LongPaddleBrick && paddleWidth >= 200)
                {
                    paddleWidth *= 1.1;
                    paddleHitbox.setSize(paddleWidth, paddleHeight);
                    isLong = true;
                }

                if(bricks.get(i).bricktype == BrickType.PowerupBrick)
                {
                    ispowerball = true;
                }

                if(bricks.get(i).bricktype == BrickType.HealthBrick)
                {
                    lives+=5;
                    //specialCount++;
                }
                if (/*level == 3 || level == 4*/ bricks.get(i).bricktype == BrickType.ShrinkBPBrick) {
                    ballWidth--;
                    ballHeight--;
                    ballHitbox.width--;
                    ballHitbox.height--;
                    paddleWidth -= 2;
                    paddleHitbox.width -= 2;
                }

                if (bricks.get(i).Layers <= 0 && bricks.get(i).bricktype != BrickType.IndestructableBrick)
                {
                    bricks.remove(i);
                    score++;
                    i--;
                }

                intersectingBrick = true;
            }
            else
            {
                intersectingBrick = false;
            }
        }


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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (left) {
                ballXspeed = -Math.abs(ballXspeed);
            }
            if (right) {
                ballXspeed = Math.abs(ballXspeed);
            }
            isonpaddle = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (bricks.isEmpty() || bricks.size() == specialCount) {
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

        checkShortcutKeys(e);

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
        quickAccessKeys.put(KeyEvent.VK_P, 19);
        quickAccessKeys.put(KeyEvent.VK_A, 20);

        if(quickAccessKeys.containsKey(e.getKeyCode()))
        {
            bricks.clear();
            level = quickAccessKeys.get(e.getKeyCode());
            isonpaddle = true;
            Reset();
        }

        if(e.getKeyCode() == KeyEvent.VK_NUMPAD9)
        {
            if(!autoplay) {
                autoplay = true;
            }
            else
            {
                autoplay = false;
            }
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
