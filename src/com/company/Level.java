package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ElysiaLopez on 5/26/2017.
 */
public class Level {
    //This class is used for storing info. It cannot draw the bricks in the level because the brick pattern should be modifiable
    int rows;
    int columns;
    int xSpeed = 0;
    int ySpeed = 0;
    int brickWidth = 120;
    int brickHeight = 40;
    int xBrickGap = 10;
    int yBrickGap = 10;
    int brickx = 500;
    int brickY = 0;

    boolean isScrollingLevel = false;
    boolean hasDisappearingBricks = false;
    boolean hasRandomBricks = false;

    public Level(int Rows, int Columns, boolean scrollingLevel, boolean DisappearingBricks, boolean randomBricks)
    {
        Rows = rows;
        Columns = columns;
        scrollingLevel = isScrollingLevel;
        DisappearingBricks = hasDisappearingBricks;
        randomBricks = hasRandomBricks;
    }
}
