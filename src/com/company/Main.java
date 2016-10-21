package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public class Main {
    public static boolean disappear = false;
    public static Timer disappearTimer;
    public static boolean teleport = false;
    public static Timer TeleportTimer;
    public static boolean WaitToTeleport = false;
    public static Timer TeleportWaitTimer;


    public static void main(String[] args) {


        JFrame forms = new JFrame();
        forms.setSize(1000, 500);
        forms.setLayout(null);


        //make controls
        final DrawingPanel dpanel = new DrawingPanel();
        dpanel.setSize(1000, 500);
        dpanel.setBackground(Color.BLACK);
        dpanel.setLocation(0, 0);

        /*JLabel scorelabel = new JLabel();
        scorelabel.setText(String.format("Score: %d", dpanel.score));
        scorelabel.setLocation(0, 300);
        scorelabel.setBackground(Color.BLACK);
        scorelabel.setSize(scorelabel.getPreferredSize());
        scorelabel.setVisible(true);*/


        disappearTimer = new Timer(30000000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disappear = !disappear;
            }
        });
        /*TeleportTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                teleport = !teleport;
            }
        });
        */
        TeleportWaitTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WaitToTeleport = !teleport;
                WaitToTeleport = !WaitToTeleport;

            }
        });




        //add controls


        forms.add(dpanel);
        //dpanel.add(scorelabel);

        forms.setVisible(true);

    }
}
