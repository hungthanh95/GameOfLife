package com.life;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame implements  ActionListener {
    protected RectGrid rectGrid;
    private JLabel generationLabel;
    private JLabel aliveLabel;
    private JButton playToggleButton;
    private JButton resetButton;
    private boolean playToggle;
    private boolean reset;


    public GameOfLife() {
        playToggle = false;
        reset = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setBounds(20, 0, 100, 30);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(20, 20, 100, 30);

        playToggleButton = new JButton("Pause");
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.setBounds(780, 10, 90, 40);
        playToggleButton.setVerticalTextPosition(AbstractButton.TOP);
        playToggleButton.setHorizontalTextPosition(SwingConstants.LEADING);


        resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.setBounds(690, 10, 80, 40);
        resetButton.setVerticalTextPosition(AbstractButton.TOP);
        resetButton.setHorizontalTextPosition(SwingConstants.LEADING);

    }

    public void createGui(int gen, int alive) {
        generationLabel.setText("Generation #" + gen);
        add(generationLabel);
        aliveLabel.setText("Alive: " + alive);
        add(aliveLabel);
        add(playToggleButton);
        add(resetButton);
        rectGrid = new RectGrid();
        add(rectGrid);

        setVisible(true);

    }

    public void getAction() {
        playToggleButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
    public boolean getPlayToggle() {
        return this.playToggle;
    }
    public boolean getReset() {
        return this.reset;
    }
    public void setReset() {
        this.reset = false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Pause")) {
            playToggleButton.setText("Resume");
            this.playToggle = true;
        } else if (e.getActionCommand().equals("Resume")) {
            playToggleButton.setText("Pause");
            this.playToggle = false;
        } else if (e.getActionCommand().equals("Reset")) {
            this.reset = true;
        }
    }

}