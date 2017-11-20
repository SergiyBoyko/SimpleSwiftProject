package com.company;

import com.company.controller.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        JFrame frame = new JFrame();
        frame.setTitle("Simple swing project");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);

        frame.add(controller.getView());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        controller.startGame();

    }
}
