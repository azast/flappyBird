package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.ImageObserver;



public class Main extends JFrame {

    private JButton restartButton1;

    private int score;
    private JLabel scoreLabel;

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);


    }

    private JPanel gamePanel;
    private JLabel birdLabel;
    private int birdY = 300;
    private Timer timer;
    private JLabel pipeTop;
    private JLabel pipeBottom;
    private int pipeX = 600;



    int numOfPipes = 100;
    JLabel[] pipeTopArray = new JLabel[numOfPipes];
    JLabel[] pipeBottomArray = new JLabel[numOfPipes];
    int[] pipeXArray = new int[numOfPipes];
    int[] pipeYArray = new int[numOfPipes];

    public Main() {

        gamePanel = new JPanel();




        restartButton1 = new JButton("Restart");
        gamePanel.add(restartButton1);
        restartButton1.setBounds(200,300,200,100);
        restartButton1.setVisible(false);
        restartButton1.setBackground(Color.GREEN);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(500, 50, 100, 50);
        gamePanel.add(scoreLabel);




        restartButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartButton1.setVisible(false);
                birdY = gamePanel.getHeight() / 2;
                birdLabel.setLocation(30, birdY);
                for(int i = 0; i < numOfPipes; i++) {
                    pipeXArray[i] = 600 + i*200;
                    pipeYArray[i] = (int)(Math.random() * 100) + 150;
                    pipeTopArray[i].setBounds(pipeXArray[i], 0, 50, pipeYArray[i]);
                    pipeBottomArray[i].setBounds(pipeXArray[i], pipeYArray[i] + 200, 50, 350);
                }
                timer.start();
            }
        });



        for(int i = 0; i < numOfPipes; i++) {
            pipeTopArray[i] = new JLabel(new ImageIcon("C:\\Users\\Strix\\Downloads\\pipe3.png"));
            pipeBottomArray[i] = new JLabel(new ImageIcon("C:\\Users\\Strix\\Downloads\\pipe3.png"));
            gamePanel.add(pipeTopArray[i]);
            gamePanel.add(pipeBottomArray[i]);
            pipeXArray[i] = 600 + i*200;
            pipeYArray[i] = (int)(Math.random() * 100) + 150;
            pipeTopArray[i].setBounds(pipeXArray[i], 0, 50, pipeYArray[i]);
            pipeBottomArray[i].setBounds(pipeXArray[i], pipeYArray[i] + 200, 50, 350);
        }




        setTitle("Flappy Bird");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        gamePanel.setBackground(Color.BLUE);
        add(gamePanel);
        gamePanel.setVisible(true);



        birdLabel = new JLabel(new ImageIcon("C:\\Users\\Strix\\Downloads\\bird2.png"));
        gamePanel.add(birdLabel);



        pipeTop = new JLabel(new ImageIcon("C:\\Users\\Strix\\Downloads\\pipe3.png"));
        pipeBottom = new JLabel(new ImageIcon("C:\\Users\\Strix\\Downloads\\pipe3.png"));
        gamePanel.add(pipeTop);
        gamePanel.add(pipeBottom);









        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }



            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    scoreLabel.setBackground(Color.white);
                    birdY -= 50;
                    birdLabel.setLocation(birdLabel.getX(), birdY);
                    gamePanel.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);

        timer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + 1;
                scoreLabel.setBounds(10, 1, 100, 50);
                birdLabel.setBounds(30, birdY, 50, 50);
                birdY += 5;
                birdLabel.setLocation(birdLabel.getX(), birdY);
                gamePanel.repaint();
                pipeBottom.setVisible(true);
                for(int i = 0; i < numOfPipes; i++) {
                    pipeXArray[i] -= 5;
                    pipeTopArray[i].setBounds(pipeXArray[i], 0, 50, pipeYArray[i]);
                    pipeBottomArray[i].setBounds(pipeXArray[i], pipeYArray[i] + 200, 50, 350);
                }
                checkCollision();

            }
        });
        timer.start();
    }
    private void checkCollision() {
        for(int i = 0; i < numOfPipes; i++) {
            if(birdLabel.getBounds().intersects(pipeTopArray[i].getBounds()) ||
                    birdLabel.getBounds().intersects(pipeBottomArray[i].getBounds())) {
                timer.stop();
                restartButton1.setVisible(true);
            }
            else{
                score = score + 1;
                gamePanel.repaint();
                }


                JButton restartButton = new JButton("Restart");
                restartButton.setVisible(true);
                gamePanel.add(restartButton);

                restartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePanel.remove(restartButton);
                        birdY = gamePanel.getHeight() / 2;
                        birdLabel.setLocation(30, birdY);
                        for(int i = 0; i < numOfPipes; i++) {
                            pipeXArray[i] = 600 + i*200;
                           pipeYArray[i] = (int)(Math.random() * 100) + 150;
                            pipeTopArray[i].setBounds(pipeXArray[i], 0, 50, pipeYArray[i]);
                            pipeBottomArray[i].setBounds(pipeXArray[i], pipeYArray[i] + 200, 50, 350);
                        }
                        timer.start();
                    }
                });
            }
        }
    }



